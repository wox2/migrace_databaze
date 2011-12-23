/* 
 * File:   Algorithm.cpp
 * Author: woxie
 * 
 * Created on 4. leden 2011, 15:24
 */

#include <iostream>
#include <string.h>

#include "Algorithm.h"
#include "NetworkCreator.h"
#include "Condensator.h"
using namespace std;

Algorithm::Algorithm() {
    components = vector<StrongComponent *>();
    netCreated = false;
}

/*Algorithm::Algorithm(const Algorithm& orig) {
}*/

Algorithm::~Algorithm() {
    for(int i = addedRoads.size()-1; i >= 0;i--){
        Road * road = addedRoads[i];
        addedRoads.pop_back();
        delete road;
    }
    for(unsigned int i = 0 ; i < components.size() ; i++){
        StrongComponent * component = components[i];
        components.pop_back();
        delete component;
    }
}

void Algorithm::condensate() {
    Condensator condensator = Condensator(&components);
    condensator.condensate();
}

void Algorithm::init() {
    insertDiscretePointsToStrongComponents();
    updatePrecedessorsAndSuccesorsInStrongComponents();
    condensate();
    initPriorityQueue();
    initRootSet();
    //cout <<"condensed" << endl;
    //printNetwork();
}

void Algorithm::updatePrecedessorsAndSuccesorsInStrongComponents() {
    for (unsigned int i = 0; i < net->roads.size(); i++) {
        Road * road = net->roads[i];
        StrongComponent * end = getComponentIncludingCity(road->_end);
        StrongComponent * start = getComponentIncludingCity(road->_start);
        cout << start->getRepresentant()->_id << "_";
        cout << end->getRepresentant()->_id << endl;
        end->insertPrecedessor(start);
        start->insertSuccesor(end);
        
    }
    //printNetwork();
    //cout << "pr\n";
}

bool Algorithm::getDataAndCreateNetwork() {
    NetworkCreator creator;

    if (creator.createNetwork()) {
        net = creator.getNetwork();
        netCreated = true;
    } else {
        
        delete creator.getNetwork();
        net = NULL;
        cout << "Spatne zadana data" << endl;
        return false;
    }
    return true;
}

void Algorithm::insertDiscretePointsToStrongComponents() {
    for (int i = 1; i < net->nodeCount + 1; i++) {
        City * city = net->getCity(i);
        StrongComponent * comp = new StrongComponent(city);
        components.push_back(comp);
    }
}

StrongComponent * Algorithm::getComponentIncludingCity(int cityId) {
    for (unsigned int i = 0; i < components.size(); i++) {
        if (components[i]->isContaining(cityId)) {
            return components[i];
        }
    }
    return NULL;
}

void Algorithm::process() {
    if(net == NULL){
        return;
    }
    init();

    if(components.size() == 1){
        printResults();
        return;
    }

    //int ko = 1;
    while(!isFinished()){
        //cout << "cyklus " << ko++ << endl;
        //cout << "roots.size:" << roots.size() << " components.size:" << components.size() << endl;
        doStep();
    }

    cout << "process ";
    printResults();
    delete net;
}

void Algorithm::doStep() {
    //cout << "precedessorQueue.size:" << precedessorsQueue.getSize() << endl;
    StrongComponent * startLocation = precedessorsQueue.dequeue();
    StrongComponent * potentialEndLocation = getSuitableRoot(startLocation) ;

    cout << "pridavam silnici:";
    startLocation->print();
    cout << "_";
    potentialEndLocation->print();
    cout << endl;
    addedRoads.push_back(new Road(startLocation->getRepresentant()->_id, potentialEndLocation->getRepresentant()->_id));
    startLocation->insertSuccesor(potentialEndLocation);
    potentialEndLocation->insertPrecedessor(startLocation);

    //printNetwork();
}

bool Algorithm::isFinished() {
    if(roots.size() == 0 || components.size() == 1) {
        return true;
    }
    return false;
}

void Algorithm::printResults(){
    if(net==NULL){
        cout << "zadani bylo nevyhovujici" << endl;
        return;
    }
    if(addedRoads.empty()){
        cout << "Odpoved: Sit je vyhovujici." << endl;
    } else{
        cout << "Je treba jeste vybudovat: ";
        for(unsigned int i=0; i < addedRoads.size() ; i++){
            addedRoads[i]->print();
            if( i == addedRoads.size() - 1){
                cout <<'.';
            } else{
                cout << ",";
            }
        }
    }
}

void Algorithm::printNetwork(){
    for(unsigned int i = 0 ; i < components.size() ; i++){
        components[i]->printAll();
    }
}

void Algorithm::initPriorityQueue(){
    for(unsigned int i = 0 ; i < components.size() ; i++){
        precedessorsQueue.enqueue(components[i]);
    }
}

void Algorithm::initRootSet(){
    for(unsigned int i = 0 ; i < components.size() ; i++){
        StrongComponent * potentialRoot = components[i];
        if(potentialRoot->isRoot()){
            roots.push_back(potentialRoot);
        }
    }
}

StrongComponent * Algorithm::getSuitableRoot(StrongComponent * component){
    unsigned int index = 0;
    StrongComponent * temp = roots[index];
    if(roots.size()==1){
        roots.erase(roots.begin());
        return temp;
    }
    while((component->hasPrecedessor(temp) || component == temp) && index < roots.size()){
        index++;
        temp = roots[index];
    }

    vector<StrongComponent *>::iterator it = roots.begin();
    for(unsigned int i =0 ; i < index ; i++){
        it++;
    }
    roots.erase(it);
    return temp;
}

/* 
 * File:   StrongComponent.cpp
 * Author: woxie
 * 
 * Created on 4. leden 2011, 18:32
 */

#include "StrongComponent.h"
#include <iostream>

StrongComponent::StrongComponent(City * city) {
    cities=vector<City*>(1);
    cities[0]=city;
}

StrongComponent::StrongComponent(const StrongComponent& orig) {
}

StrongComponent::~StrongComponent() {
}

void StrongComponent::merge(StrongComponent* component){
    if(component == this){
        return;
    }

    /*
    for(int i = 0 ; i < precedessors.size() ; i++){
        
    }

    for(int i = 0 ; i < succesors.size() ; i++ ){

    }
    */
    
    vector<City *>::iterator it;

    for ( it = component->cities.begin() ; it < component->cities.end() ; it++ )
    {
        this->cities.push_back(*it);
    }

    
    vector<StrongComponent *>::iterator it_prec;
    for(it_prec = component->precedessors.begin() ; it_prec < component->precedessors.end() ; it_prec++){
        this->precedessors.push_back(*it_prec);
    }

    vector<StrongComponent *>::iterator it_succ;
    for(it_succ = component->precedessors.begin() ; it_succ < component->precedessors.end() ; it_succ++){
        this->precedessors.push_back(*it_succ);
    }
}

City * StrongComponent::getRepresentant() const{
    if(cities.empty()) {
        return NULL;
    }
    return cities[0];
}

void StrongComponent::print ()const{
    if(getRepresentant() == NULL) {
        return;
    }
    cout << this->getRepresentant()->_id;
}

void StrongComponent::printAll()const{
    if(getRepresentant() == NULL){
        cout << "componenta bez reprezentanta" << endl;
        return;
    }
    cout << "componenta s reprezentantem: " << getRepresentant()->_id <<endl;
    cout << "Pocet mest-" << cities.size()<<":";
    for(unsigned int i= 0 ; i < this->cities.size() ; i++){
        cout << cities[i]->_id;
        if( i!=this->cities.size() -1){
            cout <<",";
        }
    }
    cout << endl;
    cout << precedessors.size() <<" reprezentantu predchudcu: ";
    for(unsigned int i = 0 ; i < precedessors.size() ; i++){
        precedessors[i]->print();
        if(i != precedessors.size()-1){
            cout << ",";
        }
    }
    cout << endl;

    cout << succesors.size() <<" reprezentatu nasledniku: ";
    for(unsigned int i = 0; i < succesors.size() ; i++){
        succesors[i]->print();
        if(i != succesors.size()-1){
            cout << ",";
        }
    }
    cout << endl;
    cout << "============="<<endl;
}

bool StrongComponent::isContaining(int city_id) const{
    for(unsigned int it = 0; it < cities.size() ; it++){
        if(city_id == cities[it]->_id){
            return true;
        }
    }
    return false;
}

bool StrongComponent::hasPrecedessor(StrongComponent * component){
    for(unsigned int i = 0 ; i < precedessors.size() ; i++){
        if(component == precedessors[i]){
            return true;
        }
    }
    return false;
}

bool StrongComponent::hasSuccessor(StrongComponent * component){
    for(unsigned int i = 0 ; i < succesors.size() ; i++){
        if(component == succesors[i]){
            return true;
        }
    }
    return false;
}

int StrongComponent::getPrecedessorCount(){
    return precedessors.size();
}

int StrongComponent::getSuccesorCount(){
    return succesors.size();
}



void StrongComponent::removePrecedessor(StrongComponent* precedessor){
    if(precedessor == this || !this->hasPrecedessor(precedessor)){
        return;
    }

    for(unsigned int i = 0 ; i < precedessors.size() ; i++){
        if(precedessor == precedessors[i]){
            vector<StrongComponent *>::iterator it = precedessors.begin();
            for(unsigned int j = 0 ; j < i ; j++){
                it++;
            }
            precedessors.erase(it);
            i--;
        }
    }

    for(unsigned int i = 0 ; i < succesors.size() ; i++){
        succesors[i]->removePrecedessor(precedessor);
    }
}

void StrongComponent::removeSuccesor(StrongComponent* successor){
    if(successor == this || !this->hasSuccessor(successor)){
        return;
    }

    for(unsigned int i = 0 ; i < succesors.size() ; i++){
        if(successor == succesors[i]){
            vector<StrongComponent *>::iterator it = succesors.begin();
            for(unsigned int j = 0 ; j < i ; j++){
                it++;
            }
            succesors.erase(it);
            i--;
        }
    }

    for(unsigned int i = 0 ; i < precedessors.size() ; i++){
        precedessors[i]->removeSuccesor(successor);
    }
}

void StrongComponent::insertPrecedessor(StrongComponent * component){
    if(this != component && !hasPrecedessor(component)){
        this->precedessors.push_back(component);
        for(unsigned int i = 0 ; i < component->precedessors.size() ; i++){
            if(!this->hasPrecedessor(component->precedessors[i])){
                this->precedessors.push_back(component->precedessors[i]);
            }
        }

        for(unsigned int i = 0 ; i < this->succesors.size() ; i++){
            this->succesors[i]->insertPrecedessor(component);
        }
    } else{
        if(!hasPrecedessor(component)){
            this->precedessors.push_back(component);
        }
    }
}

void StrongComponent::insertSuccesor(StrongComponent * component){
    if(this != component && !hasSuccessor(component)) {
        this->succesors.push_back(component);

        for(unsigned int i = 0 ; i < component->succesors.size() ; i++){
            if(!this->hasSuccessor(component->succesors[i])){
                this->succesors.push_back(component->succesors[i]);
            }
        }

        for(unsigned int i = 0 ; i < this->precedessors.size() ; i++){
            this->precedessors[i]->insertSuccesor(component);
        }
    } else{
        if(!hasSuccessor(component)){
            this->succesors.push_back(component);
        }
    }
}

void StrongComponent::changePrecedessor(StrongComponent * precedessor, StrongComponent * newPrecedessor){
    removePrecedessor(precedessor);
    insertPrecedessor(newPrecedessor);
}

void StrongComponent::changeSuccessor(StrongComponent * succesor, StrongComponent * newSuccesor){
    removeSuccesor(succesor);
    insertSuccesor(newSuccesor);
}

bool StrongComponent::isRoot(){
    if(precedessors.size() == 0 ){
        return true;
    }

    for(unsigned int i = 0 ; i < precedessors.size() ; i++){
        if(precedessors[i]!=this) {
            return false;
        }
    }
    return true;
}

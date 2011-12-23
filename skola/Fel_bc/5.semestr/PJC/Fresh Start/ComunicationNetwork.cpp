/* 
 * File:   ComunicationNetwork.cpp
 * Author: woxie
 * 
 * Created on 4. leden 2011, 15:24
 */

#include "ComunicationNetwork.h"
#include "City.h"
#include "Algorithm.h"
#include "iostream"

ComunicationNetwork::ComunicationNetwork(int nodes) {
    nodeCount = nodes;
    for(int i = 1 ; i < nodes + 1; i++){
        City * city = new City(i);
        cities.push_back(city);
    }
}


ComunicationNetwork::ComunicationNetwork(const ComunicationNetwork& orig) {
    for(unsigned int i = 0; i < orig.roads.size() ; i++){
        Road * road = new Road((orig.roads[i])->_start, (orig.roads[i])->_start);
        roads.push_back(road);
    }

    for(unsigned int i = 0; i < orig.cities.size() ; i++){
        City * city = new City(orig.cities[i]->_id);
        cities.push_back(city);
    }
}

ComunicationNetwork::~ComunicationNetwork() {
    for (int i = roads.size()-1; i >= 0; i-- ){
        delete roads[i];
    }
    for(int i = cities.size()-1; i >= 0 ; i--){
        delete cities[i];
    }
}

void ComunicationNetwork::addRoad(int start, int end){
    Road * road = new Road(start, end);
    roads.push_back(road);
}

City * ComunicationNetwork::getCity(int id){
    if(id > nodeCount || id < 1 ) return NULL;
    return cities.at(id-1);
}

void ComunicationNetwork::print(){
    cout << "mest:" << nodeCount << " ,silnice:";
    for(unsigned int i = 0 ; i < roads.size() ; i++){
        roads[i]->print();
        if(i != roads.size() - 1 ){
            cout << ", ";
        } else{
            cout << ".";
        }
    }
    cout << endl;
}

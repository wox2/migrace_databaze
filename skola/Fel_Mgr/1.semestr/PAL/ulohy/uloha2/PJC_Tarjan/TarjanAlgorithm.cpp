/*
 * TarjanAlgorithm.cpp
 *
 *  Created on: 8.1.2012
 *      Author: woxie
 */

#include "TarjanAlgorithm.h"
#include <cstddef>
#include <climits>
TarjanAlgorithm::TarjanAlgorithm() {

}

TarjanAlgorithm::~TarjanAlgorithm() {
}

void TarjanAlgorithm::readData(){
	cin >> nodesCount ;
	while(readEdge()){
	};
}

bool TarjanAlgorithm::readEdge() {
    int inputEdgeVal1 ;
    int inputEdgeVal2;
    cin >> inputEdgeVal1;
    cin >> inputEdgeVal2;
    int weight;
    cin >> weight;
    if(inputEdgeVal1 == 0 && inputEdgeVal2 == 0 && weight == 0){
	   return false;
    }
    Edge * edge = new Edge(inputEdgeVal1, inputEdgeVal2, weight);
    edges->push_back(edge);
    return true;
}

	void TarjanAlgorithm::init(){
		strongComponentsSets = new FindUnionSet(nodesCount);
		weakComponentsSets = new FindUnionSet (nodesCount);
		adeptEdges = new vector<Edge*>;
		roots = new vector<int>();
		edges = new vector<Edge *>();
		queues = new vector<BinomialHeap *>();
		enterEdges = new vector<Edge*>();
		for(int i = 0 ; i < nodesCount; i++){
			roots->push_back(i);
		}

		for(int i = 0 ; i < nodesCount ; i++){
			queues->push_back(new BinomialHeap());
		}
		for(unsigned int i = 0 ; i < edges->size(); i++){
			queues->at(edges->at(i)->to)->insert(edges->at(i));
		}
	}



	void TarjanAlgorithm::process(){
		init();
		while(roots->size() > 0){
			int processedPseudoNode = roots->back();
			roots->pop_back();
			Edge * processedEdge = queues->at(processedPseudoNode)->accessMin();
			queues->at(processedPseudoNode)->deleteMin();
			if(processedEdge == NULL){
				/*if (ik == null) {
				                rSet.add(k.name); // Add to root set when k has no incoming
				                if (rSet.size() > 1) {
									cout << ("-1"); //FORESTE UTIKEJ!
				                   	break;
				                }*/
			}




				// pokud jsou ve stejne silne komponente
				if(strongComponentsSets->find(processedEdge->from) == strongComponentsSets->find(processedEdge->to)){
					roots->push_back(processedPseudoNode);
//					cout << ("skip");
					continue;
				}

				//adeptEdges->push_back(processedEdge);

				//pokud jsou ve stejne slabe komponente
				if(weakComponentsSets->find(processedEdge->from) == weakComponentsSets->find(processedEdge->to)){
					decreaseEdgeKeys(processedEdge, processedPseudoNode);
					roots->push_back(processedPseudoNode);
				}else{
					weakComponentsSets->union_(weakComponentsSets->find(processedEdge->from), weakComponentsSets->find(processedEdge->to));
					enterEdges->at(processedPseudoNode) = processedEdge;
					continue;
				}

			}
		}

	void TarjanAlgorithm::decreaseEdgeKeys(Edge * processedEdge, int processedPseudoNode){
		Edge * cycleEdge = processedEdge;
		int maxVal = INT_MIN;
		int vertex = INT_MIN;

		vector<Edge *> cycle;
	while (cycleEdge!=NULL) { //hledame hranu v cyklu s maximalni vahou (ktera nejvice poskodi cyklus)
		cycle.push_back(cycleEdge);
		if (cycleEdge->weight > maxVal) {
			maxVal = cycleEdge->weight;
			vertex = strongComponentsSets->find(cycleEdge->to);
		}
		cycleEdge = enterEdges->at(strongComponentsSets->find(cycleEdge->from));
	}
	//cycles[s.find(k)] = cycle;
	queues->at(processedPseudoNode)->decreaseKeys(processedEdge->weight - maxVal);
	//min[processedPseudoNode] = min[vertex];
	//int m = strongComponentsSets->find(maxInCycle.from);
	//					rset[processedPseudoNode] = m;
	}

	void TarjanAlgorithm::mergeQueues(int destination, int source){
		queues->at(destination)->mergeHeaps(queues->at(source));
	}

	void TarjanAlgorithm::decreaseKeys(int node, int value){
		queues->at(node)->decreaseKeys(value);
	}

	void TarjanAlgorithm::writeResults(){

	}

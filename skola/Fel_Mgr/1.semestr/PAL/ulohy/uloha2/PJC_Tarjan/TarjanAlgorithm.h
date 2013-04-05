/*
 * TarjanAlgorithm.h
 *
 *  Created on: 8.1.2012
 *      Author: woxie
 */

#ifndef TARJANALGORITHM_H_
#define TARJANALGORITHM_H_
#include <iostream>
#include <ostream>
#include "FindUnionSet.h"
#include <vector>
#include "BinomialHeap.h"

class TarjanAlgorithm {
public:
	TarjanAlgorithm();
	virtual ~TarjanAlgorithm();
	/**
	 * starts processing algorithm
	 */
	void process();
	/**
	 *  reads graph G
	 */
	void readData();
	void mergeQueues(int source, int destination);
	/**
	 * Decrease keys in queue
	 */
	void decreaseKeys(int queu, int value);
	void writeResults();
	void init();
private:
	/**
	 * Helper data structure. Contains representants of weak components
	 */
	FindUnionSet * weakComponentsSets;
	/**
	 * Helper data structure. Contains representants of strong components
	 */

	FindUnionSet * strongComponentsSets;
	/**
	 * count of nodes in graph G
	 */
	int nodesCount;
	/**
	 *  Contains all edges of G
	 */
	vector<Edge *> * edges;
	/**
	 * Obsahuje hrany, ktere jsou adepty do
	 */
	vector<Edge *> * adeptEdges;
	/**
	 *  Qeueues that contains edges incoming into a strong component. When components are merged, queues should be also merged, so optimal implementation should be able to merge queues in O(log(n))
	 */
	vector<BinomialHeap *> * queues;
	/**
	 *  Helper structure. Set of roots that is used during algorithm execution
	 */
	vector<int> * roots;
	/**
	 * Helper structure. Pointers to edge that are chosen and are incoming into pseudonode during algorithm execution
	 */
	vector<Edge *> * enterEdges;
	/**
	 * Helper. Reads edge
	 */
	bool readEdge();
	/**
	 * Helper. Decrease keys in all queues
	 */
	void decreaseEdgeKeys(Edge * processedEdge, int processedPseudoNode);
	/**
	 * Set of result roots (in this exercise there is only one, but in general there can be more than one)
	 */
	vector<Edge *> * rset;
	/**
	 * Helper. Creates cycle during algorithm execution
	 */
	void createCycle();

};

#endif /* TARJANALGORITHM_H_ */

/*
 * BinomialHeap.h
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */
#ifndef BINOMIALHEAP_H_
#define BINOMIALHEAP_H_
#include <vector>
#include <ostream>
#include "Edge.h"
#include "TreeNode.h"

class BinomialHeap {
public:
	BinomialHeap();
	BinomialHeap(Edge * edge);

	virtual ~BinomialHeap();
	void print(ostream & os);
	void decreaseKeys(int value);
	void init();
	void insert(Edge * edge);
	void mergeHeaps(BinomialHeap * heap);
	Edge * accessMin();
	void deleteMin();

private:
	vector<TreeNode *> trees;
	TreeNode * root; //minimum
    int rank;
    void actualizeMinimum();
};
#endif /* BINOMIALHEAP_H_ */


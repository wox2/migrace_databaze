/*
 * TreeNode.h
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */

#ifndef TREENODE_H_
#define TREENODE_H_
#include "Edge.h"
#include <iostream>
#include <vector>
class TreeNode {
public:
	/*------------------Constructors, destructor, operators --------*/
	virtual ~TreeNode();
	TreeNode(Edge * entity);

	/*----------------Methods---------------------------------*/
	TreeNode * merge (TreeNode * merged);
	int compare(TreeNode * comparedNode);
	void decreaseKey(int value);
	void print(ostream & os);

	Edge * getEdge();

	/*-----------------Attributes---------------------------*/
	TreeNode * parent;
	vector<TreeNode *> children;
	int rank;
private:
	Edge * edge;

};
#endif /* TREENODE_H_ */

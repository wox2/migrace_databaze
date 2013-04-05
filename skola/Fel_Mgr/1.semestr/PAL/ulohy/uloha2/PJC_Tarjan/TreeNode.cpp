/*
 * TreeNode.cpp
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */

#include "TreeNode.h"
#include <cstddef>
/*------------------Constructors, destructor, operators --------*/
TreeNode::TreeNode(Edge* entity) {
	edge = entity;
	this->children = vector<TreeNode *>();
	this->edge = entity;
	this->parent = NULL;
	this->rank = 0;
}

TreeNode::~TreeNode() {

}


/*----------------Methods---------------------------------*/
Edge * TreeNode::getEdge() {
	return edge;
}

TreeNode * TreeNode::merge (TreeNode * merged){
	if(this->compare(merged) == -1){
		this->children[rank] = merged;
		merged->parent = this;
		this->rank++;
		return this;
	}
	merged->children[rank] = this;
	this->parent = merged;
	merged->rank++;
	return merged;
}

int TreeNode::compare(TreeNode * node) {
	return this->edge->compare(*(node->edge));
}

void TreeNode::decreaseKey(int value){
	this->edge->algorithmWeight -= value;
	for(int i = 0 ; i < rank ; i++){
		children[i]->decreaseKey(value);
	}
}

void TreeNode::print(ostream & os){
	os << "e:";
	os << this->edge;
	os << " r:";
	os << rank;
	os << endl;
	for(int i = 0 ; i < rank ; i++){
		if(&children[i] != NULL){
			children[i]->print(os);
		}
	}
}

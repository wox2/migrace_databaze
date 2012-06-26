/*
 * Edge.cpp
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */

#include "Edge.h"
#include <cstddef>
using namespace std;

/*------------------Constructors, destructor, operators --------*/
Edge::~Edge() {
}

Edge::Edge(int input, int output, int weight){
	this->from = input;
	this->to = output;
	if(weight != -1){
		this->weight = weight;
		this->algorithmWeight = weight;
		this->isConstructed = false;
	} else{
		this->weight = 0;
		this->algorithmWeight = 0;
		this->isConstructed = true;
	}
	this->deleted = false;

}

/*----------------Methods---------------------------------*/
int Edge::compare(const Edge & edge){
	if(this->algorithmWeight < edge.algorithmWeight){
		return -1;
	}
	if(this->algorithmWeight > edge.algorithmWeight){
		return 1;
	}
	return 0;
}

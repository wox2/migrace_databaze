/*
 * Edge.h
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */

#ifndef EDGE_H_
#define EDGE_H_
#include <iostream>
using namespace std;
class Edge {
public:
	/*------------------Constructors, destructor, operators --------*/
	Edge(int input, int output, int weight);
	virtual ~Edge();

	/*----------------Methods---------------------------------*/
	int compare(const Edge & edge);
	//friend ostream& operator<<(ostream& os, Edge edge);
	friend ostream& operator<<(ostream & os, Edge &edge){
		os << "from:" << edge.from << " to:" << edge.to << " w:" << edge.weight << " aw:" << edge.algorithmWeight;
		return os;
	}
	int from ;
	int to ;
	int weight ;
	int algorithmWeight;
	bool deleted;
	bool isConstructed;

};

#endif /* EDGE_H_ */

/*
 * AbstractSetIssueSolver.h
 *
 *  Created on: 8.1.2012
 *      Author: woxie
 */

#ifndef FINDUNIONSOLVER_H_
#define FINDUNIONSOLVER_H_

class FindUnionSet {
public:
	FindUnionSet(int range);
	virtual ~FindUnionSet();
	int find(int i);
	void union_(int source, int target);
	void makeSet(int i);
protected:
	int * sets;
};

#endif /* ABSTRACTSETISSUESOLVER_H_ */

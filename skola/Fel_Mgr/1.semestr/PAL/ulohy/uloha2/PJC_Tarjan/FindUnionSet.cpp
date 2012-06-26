/*
 * AbstractSetIssueSolver.cpp
 *
 *  Created on: 8.1.2012
 *      Author: woxie
 */

#include "FindUnionSet.h"
FindUnionSet::FindUnionSet(int range) {
	sets = new int [range+1];
	for(int i = 1 ; i <= range ; i++){
		makeSet(i);
	}
}

FindUnionSet::~FindUnionSet() {
	delete []sets;
}

void FindUnionSet::makeSet(int i){
	sets[i] = i;
}

int FindUnionSet::find(int i){
    if(sets[i]== i){
        return i;
    }
    // may be wrong
    sets[i] = find(sets[i]);
    return find(sets[i]);

}


void FindUnionSet::union_(int source, int destination){
      int sourceRoot = find( source);
      int destinationRoot = find(destination);
      if (sourceRoot == destinationRoot){
          return;
      }
      sets[sourceRoot] = destinationRoot;
  }

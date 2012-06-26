/*
 * BinomialHeap.cpp
 *
 *  Created on: 3.1.2012
 *      Author: woxie
 */

#include "BinomialHeap.h"
#include <vector>
#include "TreeNode.h"
#include "Edge.h"
#include <cmath>
using namespace std;

/*------------------Constructors, destructor, operators --------*/
BinomialHeap::BinomialHeap(){
	trees = vector<TreeNode *>();
}

BinomialHeap::~BinomialHeap(){
	for(unsigned int i = 0 ; i < trees.size(); i++){
		delete trees[i];
	}
}

BinomialHeap::BinomialHeap(Edge * edge){
	init();
    TreeNode * node = new TreeNode(edge);
    root = node;
    trees.push_back(node);
    rank = 0;
}

/*----------------Methods---------------------------------*/
void BinomialHeap::print(ostream & os){
	for(int i = 0 ; i <= rank ; i++){
		if(trees[i] != NULL){
			trees[i]->print(os);
    		}
    	}
    }

    void BinomialHeap::decreaseKeys(int value){
    	for(int i = 0 ; i < rank ; i++){
    		if(trees[i] != NULL){
    			trees[i]->decreaseKey(value);
    		}
    	}
    }



    void BinomialHeap::init(){
    	trees = vector<TreeNode *>();
    }

    void BinomialHeap::insert(Edge * edge){
    	BinomialHeap * newHeap = new BinomialHeap(edge);
    	this->mergeHeaps(newHeap);
    	delete newHeap;
    }

    void BinomialHeap::mergeHeaps(BinomialHeap * heap){
    	int i = 0;
// @NOT supported now
//    	if(heap == NULL){
//    		SHOULD throw RuntimeException("Pokus o merge heapu s NULL");
//    	}

// @NOT supported now
//    	if(heap == *this){
//    		//SHOULD throw RuntimeException("Pokus o merge heapu se sebou samotnou");
//    		return;
//    	}
    	TreeNode * iterateTree = NULL;
    	while(i <= this->rank || i <= heap->rank ){
    		if( this->trees[i] != NULL){
    			if(heap->trees[i] != NULL){
    				if(iterateTree != NULL){
    					//iterateTree != NULL ,heap.trees != NULL, this.trees != NULL
    					iterateTree = heap->trees[i]->merge(iterateTree);
    					heap->trees[i] = NULL;
    				}else{
    					//iterateTree = NULL ,heap.trees != NULL, this.trees != NULL
    					iterateTree = heap->trees[i]->merge(this->trees[i]);
    					heap->trees[i] = NULL;
    					this->trees[i] = NULL;
    				}
    			}else{
    				if(iterateTree != NULL){
    					//iterateTree != NULL ,heap.trees = NULL, this.trees != NULL
    					iterateTree = this->trees[i]->merge(iterateTree);
    					this->trees[i] = NULL;
    				}else{
    					//iterateTree = NULL ,heap.trees = NULL, this.trees != NULL
    				}
    			}
    		}else {
    			if(heap->trees[i] != NULL){
    				if(iterateTree != NULL){
    					//iterateTree != NULL ,heap.trees != NULL, this.trees = NULL
    					iterateTree = iterateTree->merge(heap->trees[i]);
    					heap->trees[i] = NULL;
    				}else{
    					//iterateTree = NULL ,heap.trees != NULL, this.trees = NULL
    					this->trees[i] = heap->trees[i];
    					heap->trees [i] = NULL;
    				}
    			} else{
    				if(iterateTree != NULL){
    					//iterateTree != NULL ,heap.trees = NULL, this.trees = NULL
    					this->trees[i] = iterateTree;
    					iterateTree = NULL;
    				}else{
    					//iterateTree = NULL ,heap.trees = NULL, this.trees = NULL
    				}
    			}
    		}
    		i++;
    	}

    	if(iterateTree != NULL){
    		this->trees[i] = iterateTree;
    		this->rank = i;
    	} else{
    		this->rank = max(this->rank, heap->rank);
    	}
    	//check it tohle ted nevidim
    	actualizeMinimum();
    }

    Edge * BinomialHeap::accessMin(){
    	if(root == NULL){
    		return NULL;
    	}
    	return root->getEdge();
    }

    void BinomialHeap::deleteMin(){
    	if(root == NULL){
    		return;
    	}
    	if(root->rank == 0){
    		trees[0]= NULL;
    	}else{
    		if(this->rank == root->rank){
    			this->rank = root->rank - 1 ;
    		}
    		TreeNode * deletedNode = root;
    		trees[deletedNode->rank] = NULL;

    		BinomialHeap * newHeap = new BinomialHeap();

    		for(int i = 0 ; i < deletedNode->rank ; i++){
    			newHeap->trees[i] = deletedNode->children[i];
    		}
    		newHeap->rank = deletedNode->rank - 1;
    		this->mergeHeaps(newHeap);
    		delete newHeap;
    		delete deletedNode;
    	}
    	root = NULL;
    	actualizeMinimum();
    }

    void BinomialHeap::actualizeMinimum(){
    	root = NULL;
    	for(int i = 0 ; i < rank + 1; i++){
    		if(trees[i] != NULL){
    			if(root == NULL){
    				root = trees[i];
    			}else{
    				if(root->compare(trees[i]) == 1){
    					root = trees[i];
    				}
    			}
    		}
    	}
    }

/* 
 * File:   bltree.h
 * Author: woxie aka Martin Lukes
 */


#include <cstddef>
#include <iostream>
using namespace std;
class AbstractNode{
public:
	AbstractNode * parent;
    
	AbstractNode(){
		parent = NULL;
	}
	virtual ~AbstractNode(){
	}
};

class Leaf:public AbstractNode{
public:	
        int _value;
        int count;
	
	Leaf(int value):AbstractNode(){
            count =1;
            _value = value;
	}

	virtual ~Leaf(){
	}
};

class InnerNode:public AbstractNode{
public:
	AbstractNode * left;
	AbstractNode * right;
        int leftMaximalBound;
        int rightMinimalBound;

	InnerNode():AbstractNode(){
		left = NULL;
		right = NULL;
	}

	AbstractNode * getSecondSon(AbstractNode * son) const{
		if(this->left == son){
			return this->right;
		}
		if(this->right == son){
			return this->left;
		}
		return NULL;
	}

	void removeConnectionToChild(AbstractNode * child){
		if(child == NULL) {
			return;
		}
		
		if(child == ((InnerNode * )(this))->left){
			left = NULL;
		}

		if(child == ((InnerNode * )(this))->right){
			right = NULL;
		}

		child->parent = NULL;
	}

	void changeSonToNode(AbstractNode * son, AbstractNode * newSon){
		if(son!= left && son != right){
			return;
		}
		if(son == left){
			left = newSon;
		}else{
			right = newSon;
		}

		if(newSon != NULL){
			newSon->parent=this;
		}
		if(son!= NULL){
			son->parent = NULL;
		}
	}

        virtual ~InnerNode(){
		if(left != NULL){
			delete left;
		}
		if(right != NULL){
            		delete right;
		}
	}
	
};

class BLTree{
public:
	AbstractNode * root;
    	
	BLTree(){
		root = NULL;
    	}
	
	int getLeafDepth(int value, AbstractNode * temp, int &count){
		int depth = 0;
		while(dynamic_cast<InnerNode *>(temp)  ){
			depth++;
            		if(value <= ((InnerNode *)temp)->leftMaximalBound ){
                		temp = ((InnerNode *)temp)->left;
            		}
			else{        
				if(value >= ((InnerNode *)temp)->rightMinimalBound){
					temp = ((InnerNode *)temp)->right;
				}
				else{
					if( value > ((InnerNode *)temp)->leftMaximalBound && value < ((InnerNode *)temp)->rightMinimalBound){
						return -1;
					}
				}
			}
		}
	
		if(dynamic_cast<Leaf *> (temp)){
			count = dynamic_cast<Leaf *> (temp) ->count; 
		}
		
		if(value != ((Leaf *)temp )->_value){
			return -1;
		}
		return depth;
	}

	Leaf * getLeaf(int value){
		AbstractNode * temp = root;
		while(dynamic_cast<InnerNode *>(temp) && temp != NULL){
            		if(value <= ((InnerNode *)temp)->leftMaximalBound ){
                		temp = ((InnerNode *)temp)->left;
           		}
			else{        
				if(value >= ((InnerNode *)temp)->rightMinimalBound){
					temp = ((InnerNode *)temp)->right;
				} else{
					return NULL;
				}
			}
		}

		if(dynamic_cast<Leaf *> (temp))
		{
			if( ((Leaf *)temp)->_value == value){
				return (Leaf *)temp;
			}
		}
		return NULL;
	}

	Leaf * findLeafAndRepairParrents(int value, AbstractNode * node){
		AbstractNode * temp = node;
		while(dynamic_cast<InnerNode *>(temp) ){
            		if(value <= ((InnerNode *)temp)->leftMaximalBound ){
                		temp = ((InnerNode *)temp)->left;
				continue;
			}
			
			if(value >= ((InnerNode *)temp)->rightMinimalBound){
                		temp = ((InnerNode *)temp)->right;
                		continue;
            		}
			if(value <= ((InnerNode *)temp)->leftMaximalBound + (((InnerNode *)temp)->rightMinimalBound - ((InnerNode *)temp)->leftMaximalBound) / 2){
				((InnerNode *)temp)->leftMaximalBound = value;
				temp = ((InnerNode *)temp)->left;
				continue;
            		}else
			{
				((InnerNode *)temp)->rightMinimalBound = value;
				temp = ((InnerNode *)temp)->right;
                		continue;
            		}
        	}
		return (Leaf *) temp;
	}

	void addLeafes(InnerNode * node, Leaf * firstLeaf, Leaf * secondLeaf ){
		if(firstLeaf->_value > secondLeaf->_value){
			node->left = secondLeaf;
			node->right = firstLeaf;
			node->leftMaximalBound = secondLeaf->_value;
			node->rightMinimalBound = firstLeaf->_value;
		}else
		{
			node->left = firstLeaf;
			node->right = secondLeaf;
			node->rightMinimalBound = secondLeaf->_value;
			node->leftMaximalBound = firstLeaf->_value;
		}
		firstLeaf->parent = node;
		secondLeaf->parent = node;
	}

	void addValue(int value, Leaf * leaf){
		if( value == leaf ->_value ){
            		(leaf->count)++;
        	}else
		{
			Leaf * newLeaf = new Leaf(value);
			InnerNode * newInnerNode = new InnerNode();
			if(leaf == root){
				root = newInnerNode;
			}
			newInnerNode->parent = leaf->parent;

			if(dynamic_cast<InnerNode *>(newInnerNode->parent)){
				((InnerNode *)newInnerNode->parent)->changeSonToNode(leaf,newInnerNode);
			}
			addLeafes(newInnerNode, newLeaf, leaf);			 
        	}		
	}

	//vlozeni elementu
	BLTree & operator <<(int value){
		if(root == NULL){
			root = new Leaf(value);
            		return *this;
    		}

		AbstractNode * temp = root; 
		Leaf * leaf = this->findLeafAndRepairParrents(value, temp);

		this->addValue(value, leaf);
        	return *this;
    	}

	int getMaximum(AbstractNode * node)const{
		if(dynamic_cast<InnerNode *> (node) ){
			return getMaximum( ((InnerNode *)node)->right );
		}
		else{
			return ((Leaf*)node)->_value;
		}
	}

	int getMinimum(AbstractNode * node)const{
		if(dynamic_cast<InnerNode *> (node) ){
			return getMinimum( ((InnerNode *)node)->left );
		} 
		else{
			return ((Leaf*)node)->_value;
		}
	}

	//tohle je blbe
	void checkBound(AbstractNode * checkedNode){

		if(checkedNode == NULL || dynamic_cast<Leaf *>(checkedNode) ){
			return;
		}		
		
		((InnerNode *)checkedNode)->leftMaximalBound = getMaximum( ((InnerNode *)checkedNode)->left );
        	((InnerNode *)checkedNode)->rightMinimalBound = getMinimum( ((InnerNode *)checkedNode)->right );
        	
		if(checkedNode-> parent != NULL){
			checkBound(checkedNode ->parent);
		}        	
    	}

	int getValueFromSibling(AbstractNode * sibling){
		if(dynamic_cast<Leaf *> (sibling)){
			return ( (Leaf *)sibling)->_value;
		}
		if(dynamic_cast<InnerNode *> (sibling)){
			if(((InnerNode *)(sibling->parent))->left == sibling){
				return ((InnerNode *)sibling)->rightMinimalBound;
			} 
			else{
				return ((InnerNode *)sibling)->leftMaximalBound;
			}
		}
		return -1;
	}

	//vyplivnuti elementu
	BLTree & operator >>(int value){
		//root NULL, nestane se nic
		if(root == NULL){
			return *this;
		}
		AbstractNode * temp = getLeaf(value);
		
		//temp neni nalezen		
		if(temp == NULL ){
			return *this;		
		}
		
		//nalezeny node je root
		if(temp == root && ((Leaf *)temp)-> count == 1){
			delete root;
			root = NULL;
			return *this;
		}						

		//node s zadanou hodnotou je nalezen a jeho count > 1
		if( ((Leaf *)temp)-> count > 1 ){
			((Leaf *)temp)-> count--;
			return * this;
		}

		//node s zadanou hodnotou je nalezen a jeho count = 1 		 
		InnerNode * tempParent = (InnerNode *)(temp->parent);
		
		if(tempParent == NULL){
			delete root;
			root = NULL;
			return *this;
		}

		AbstractNode * tempSibling = ((InnerNode *)tempParent)->getSecondSon(temp);
		AbstractNode * grandParent = tempParent->parent;
			
		//tempParent je root
		if(grandParent == NULL ) {
				tempParent->removeConnectionToChild(tempSibling);
				root = tempSibling;
		}else{
				tempParent->removeConnectionToChild(tempSibling);
				((InnerNode *)grandParent)->changeSonToNode(tempParent, tempSibling);
				checkBound(grandParent);
		}
				
		delete tempParent;
		return *this;

	}

	//vypise hloubku elementu
	int operator[](int element){
		if(root == NULL) {
			return -1;
		}
		AbstractNode * temp = root;
		int i =0;
		int result = getLeafDepth(element, temp, i);
		return result;
	}

	//vypise pocet elementu v stromu
	int operator()(int element){
		if(root == NULL) {
			return 0;
		}
		AbstractNode * temp = root;
		int i=0;
		if(getLeafDepth(element, temp,i) != -1 ) {
			return i;
		}
		return 0;
	}

	virtual ~BLTree(){
		if(root == NULL) return;
		delete root;
	}

};


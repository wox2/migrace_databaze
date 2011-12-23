#include <iostream>

using namespace std;

unsigned int * trees;
unsigned int treeCount;

/*
 * get treecount as Catalan number
 * http://en.wikipedia.org/wiki/Binary_tree
* Catalan number Ci = number of trees
* C0 = 1
* sum(i = 0; i <= n-1) Ci * C(n-1-i)
*/

unsigned int getTreeCount(unsigned int * catalanArray, unsigned int index, unsigned int current){
	if(current == index){
		return catalanArray[index];
	}
	if(current == 0){
		catalanArray[0] = 1;
	} else{
		int sum = 0;
		for(unsigned int i = 0 ; i < current - 1 ; i++){
			sum+= (catalanArray[i] * catalanArray[current-1-i]);
		}
		catalanArray[current] = sum;
	}
	return getTreeCount(catalanArray, index, current+1);
}

unsigned int getLength(unsigned int val){
	unsigned int count = 0;
	while(val > 0){
		count++;
		val/=2;
	}
	return count;
}

unsigned int append(unsigned int tree, unsigned int subtree){
	return (tree << getLength(subtree)) + subtree;
}

unsigned int getLeftSubtree(unsigned int tree){
	unsigned int subtree = 0;
	unsigned int countOnes = 0;
	unsigned int countZeroes = 0;
	unsigned int length = getLength(tree);
	// musi byt od dvou vys, posun o length -1 by ukazoval na hodnotu root uzlu
	for(unsigned int i = 2 ; i < length ; i++){
		unsigned int valAtIndexI = (tree >> (length - i)) & 1 ;
		if(valAtIndexI == 1){
			countOnes++;
		} else{
			countZeroes++;
		}
		subtree = (subtree << 1) + valAtIndexI;
		if(countZeroes > countOnes){
			break;
		}
	}
	return subtree;
}

unsigned int getRightSubtree(unsigned int tree, unsigned int startIndex){
	unsigned int subtree = 0;
	unsigned int countZeroes = 0;
	unsigned int countOnes = 0;
	unsigned int length = getLength(tree);
	for(unsigned int i = startIndex ; i < length ; i++){
		unsigned int valAtIndexI = (tree >> (length - i)) & 1 ;
		if(valAtIndexI == 1){
			countOnes++;
		} else{
			countZeroes++;
		}
		subtree = (subtree << 1) + valAtIndexI;
		if(countZeroes > countOnes){
			break;
		}
	}
	return subtree;
}


unsigned int applySwitchCommand(unsigned int tree){
	unsigned int leftSubtree = getLeftSubtree(tree);
	unsigned int rightSubtree = getRightSubtree(tree, 2 + getLength(leftSubtree));
		if(rightSubtree == 0 && leftSubtree == 0){
			return 0;
		}
		return append(append(1, rightSubtree), leftSubtree);
}

unsigned int applyLeftRotation(unsigned int tree){
	unsigned int leftSubtree = getLeftSubtree(tree);
	unsigned int rightSubtree = getRightSubtree(tree, 2 + getLength(leftSubtree));
	if(rightSubtree == 0){
		return 0;
	}
	unsigned int rightLeftSubtree = getLeftSubtree(rightLeftSubtree);
	unsigned int rightRightSubtree = getRightSubtree(rightSubtree, 2 + getLength(rightLeftSubtree));
	unsigned int newLeft = append(append(1, leftSubtree), rightLeftSubtree);
	return append(append(1, newLeft), rightRightSubtree);
}



//void generateTree(int totalNodeCount, int remainingNodes, int tree, int indexLastOneInTree, int maxDepth, int lastTreeIndex){
//	if(pow(2, maxDepth) - 1 < totalNodeCount){
//
//	}
//}


class Edge{
public:
	unsigned int parent;
	unsigned int child;
	bool isLeft;

	int compare(Edge edge){
		if(parent < edge.parent || (parent == edge.parent && isLeft)){
			return true;
		}
		return false;
	}
};

int main(){

//	unsigned int index;
//	cin >> index;
//	unsigned int * catalanArray = new int[index];
//	treeCount = getTreeCount(catalanArray, index, 0);
//	delete catalanArray;
//	trees = new int[treeCount];

//unsigned int val = 100;
//for(unsigned int i = 0 ; i < 32 ; i++){
//	cout << ((val >> (31 - i)) & 1) ;
//}

	unsigned int tree = 408;
	cout << getLeftSubtree(tree) << endl;
	return 0;
}


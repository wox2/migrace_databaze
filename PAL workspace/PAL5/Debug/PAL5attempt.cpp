#include <iostream>
#include <vector>
#include <deque>
#include <string>

using namespace std;

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

class BFSAttributes{
public:
	unsigned int tree;
	unsigned int height;
};

void printTreeBinary(const unsigned int &tree);
unsigned int getLength(const unsigned int &val);
unsigned int getTreeCount(unsigned int * catalanArray, unsigned int index, unsigned int current);
unsigned int append(const unsigned int &tree, const unsigned int &subtree);
unsigned int getLeftSubtree(const unsigned int &tree);
unsigned int getRightSubtree(const unsigned int &tree, const unsigned int &leftSubtree);
unsigned int applySwitchCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree);
unsigned int applyLeftRotationCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree);
unsigned int applyRightRotationCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree);
void BFS(const unsigned int &tree, const unsigned int &height);
void BFSHelper(const unsigned int &height, const unsigned int &prefix, const unsigned int &postfix, const unsigned int &modifiedSubTree, bool isFresh);
bool isNew(const unsigned int &tree);
unsigned int readAndCreateTree();
void readData();
Edge* readAndCreateEdge();
void findEdgeInCollection(unsigned int &prefix, const unsigned int &parent, const bool &isLeft, const vector<Edge *> &edges);
unsigned int completeTree(const unsigned int &prefix, const unsigned int &sufix, const unsigned int &modifiedTree);
bool isLeaf(const unsigned int &tree);
bool isNull(const unsigned int &tree);
bool isToBeProcessed(const unsigned int &tree);
unsigned int appendSuffix(const unsigned int &firstPart, const unsigned int &secondPart);
unsigned int trimTree(const unsigned int &valueOrTree, const unsigned int &index);
void isResult(const unsigned int &tree, const unsigned int &height);


//vector<unsigned int> closedTrees;
unsigned int * closedTrees;
unsigned int closedTreesCount;

deque<BFSAttributes *> BFSque;
int actualWidth;
unsigned int startTree;
unsigned int finalTree;
bool isProgramFinished;
unsigned int startTreeLength;
const unsigned int MAX_CLOSED_TREE_SIZE = 1000000000;
const unsigned int leaf = 4;
const unsigned int NULL_SUFFIX = 1;

int main(){

	isProgramFinished = false;
	readData();
	startTreeLength = getLength(startTree);
	if(startTree == finalTree){
		cout << 0;
	} else{
	//	closedTrees.push_back(startTree);
		closedTrees = new unsigned int[MAX_CLOSED_TREE_SIZE];
		closedTreesCount = 0;
		closedTrees[closedTreesCount++] = startTree;
		BFS(startTree, 0);
	}
	return 0;
}

bool isNull(const unsigned int &tree){
	return tree == 0;
}

bool isLeaf(const unsigned int &tree){
	return tree == leaf;
}

bool isToBeProcessed(const unsigned int &tree){
	return !isNull(tree) && !isLeaf(tree);
}

void isResult(const unsigned int &tree, const unsigned &height){
	if(!isProgramFinished && tree == finalTree){
		cout << height + 1 << endl;
		isProgramFinished = true;
	}
}

unsigned int getLength(const unsigned int &val){
	unsigned int tmpVal = val;
	if(val == 0){
		return 1;
	}
	unsigned int count = 0;
	while(tmpVal > 0){
		count++;
		tmpVal/=2;
	}
	return count;
}

void printTreeBinary(const unsigned int &tree){
	unsigned int length = getLength(tree);
	for (unsigned int i = 0 ; i < length ; i++){
		cout << ((tree >> (length - i - 1)) & 1);
	}
}

/**
 * Recursively count number of possible roots
 * thanks to vector it is not used
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

/**
 * 	This is modified append, if suffix is number, it can be 0. append(0,0) = 0,
 * 	in chars it should be '00'
 * 	so suffix should be coded as 1 + real suffix
 * 	NULL suffix is coded as 1 , appendSuffix(1,1) = 1
 * 	zero suffix is coded as 10 = 2,  appendSuffix(2,2)  = 100
 */
unsigned int appendSuffix(const unsigned int &firstPart, const unsigned int &secondPart){
	if(secondPart == 1){
		return firstPart;
	}
	unsigned returnedValue = firstPart;
	unsigned int lengthSecondPart = getLength(secondPart);
	unsigned int realSecondPart = secondPart - (1 << (lengthSecondPart - 1));
	unsigned int lengthRealSecondPart = getLength(realSecondPart);
	if( lengthRealSecondPart != lengthSecondPart - 1 ){
		returnedValue = firstPart << (lengthSecondPart - lengthRealSecondPart - 1);
	}
	return append(returnedValue, realSecondPart);
}

unsigned int append(const unsigned int &tree, const unsigned int &subtree){
	return (tree << getLength(subtree)) + subtree;
}

/**
 * 	@Helper
 *  trim tree from index
 */
unsigned int trimTree(const unsigned int &valueOrTree, const unsigned int &index){
	unsigned int subtree = 0;
	unsigned int countOnes = 0;
	unsigned int countZeroes = 0;
	unsigned int length = getLength(valueOrTree);
	// must be in range index
	for(unsigned int i = index ; i <= length ; i++){
		unsigned int valAtIndexI = (valueOrTree >> (length - i)) & 1 ;
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

unsigned int getLeftSubtree(const unsigned int &tree){
	return trimTree(tree, 2);
}

unsigned int getRightSubtree(const unsigned int &tree, const unsigned int &leftSubtree){
	unsigned int length = getLength(tree);
	unsigned int leftLength = getLength(leftSubtree);
	unsigned int shiftSize = length - leftLength - 1;
	//is not tree, but tree + sufix
	unsigned int cutLeftAndRootValue = tree - ((tree >> shiftSize) << shiftSize);
	return trimTree(cutLeftAndRootValue, 1);
}

/**
 * Apply switch command to tree
 */
unsigned int applySwitchCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree){
		if(rightSubtree == 0 && leftSubtree == 0){
			return 0;
		}
		return append(append(1, rightSubtree), leftSubtree);
}

/**
 * 	Applies left rotation to tree
 */
unsigned int applyLeftRotationCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree){
	if(rightSubtree == 0){
		return 0;
	}
	unsigned int rightLeftSubtree = getLeftSubtree(rightSubtree);
	unsigned int rightRightSubtree = getRightSubtree(rightSubtree, rightLeftSubtree);
//	cout << "RL:";
//	printTreeBinary(rightLeftSubtree);
//	cout << "RR:";
//	printTreeBinary(rightRightSubtree);
	unsigned int newLeft = append(append(1, leftSubtree), rightLeftSubtree);
	return append(append(1, newLeft), rightRightSubtree);
}

unsigned int applyRightRotationCommand(const unsigned int &tree, const unsigned int &leftSubtree, const unsigned int &rightSubtree){
	if(leftSubtree == 0){
		return 0;
	}
	unsigned int leftLeftSubtree = getLeftSubtree(leftSubtree);
	unsigned int leftRightSubtree = getRightSubtree(leftSubtree, leftLeftSubtree);
	unsigned int newRight = append(append(1,leftRightSubtree),rightSubtree);
	return append(append(1, leftLeftSubtree),newRight);
}

/**
 * 	@Helper.
 * 	Complete tree after Rotation or Switch command
 */
unsigned int completeTree(const unsigned int &prefix, const unsigned int &sufix, const unsigned int &modifiedTreeBody){
	unsigned int completeTree = append(prefix, appendSuffix(modifiedTreeBody, sufix));
//	if(getLength(completeTree) != startTreeLength && modifiedTreeBody != 0){
//		cout << "prefix:";
//		printTreeBinary(prefix);
//		cout << " body:";
//		printTreeBinary(modifiedTreeBody);
//		cout << " sufix:";
//		printTreeBinary(sufix);
//		cout << " completeTree:";
//		printTreeBinary(completeTree);
//		cout << " completeTree length:" << getLength(completeTree);
//		cout << " startTreeLengt:" << getLength(startTree);
//		cout << endl;
//	}
	return completeTree;
}

/**
 * 	@Helper.
 * 	Checks if tree isNew or if it was processed
 */
bool isNew(const unsigned int &tree){
	for(unsigned int i = 0 ; i < closedTreesCount ; i++){
		if(closedTrees[i] == tree){
			return false;
		}
	}
	return true;
}

/**
 * Read data
 */
void readData(){
	startTree = readAndCreateTree();
	finalTree = readAndCreateTree();
}

/**
 * 	@Helper
 * 	@Reader
 * 	Read tree in format:
 * 	"firstNode secondNode"
 * 	parent is lower, if firstNode is parent then Edge is left
 */
Edge* readAndCreateEdge(){
	unsigned int firstNode;
	unsigned int secondNode;
	cin >> firstNode;
	cin >> secondNode;
	Edge* edge = new Edge();
	if(firstNode > secondNode){
		edge->child = firstNode;
		edge->parent = secondNode;
		edge->isLeft = true;
	} else{
		edge->child = secondNode;
		edge->parent = firstNode;
		edge->isLeft = false;
	}
	return edge;
}

/**
 * 	@Reader
 * 	Reads data in format:
 * 	treeNodeCount\n
 * 	//N x Edge
 * 	firstNode secondNode
 */
unsigned int readAndCreateTree(){
	unsigned int countNodes;
	cin >> countNodes;
	vector<Edge *> edges;
	for(unsigned int i = 0 ; i < countNodes - 1 ; i++){
		edges.push_back(readAndCreateEdge());
	}
	unsigned int prefix = 1;
	findEdgeInCollection(prefix,1,true, edges);
	findEdgeInCollection(prefix,1,false, edges);
	return prefix;
}

/**
 * 	@Finder
 * 	@ParameterModifier{prefix}
 * 	Find edge in collection
 */
void findEdgeInCollection(unsigned int &prefix, const unsigned int &parent, const bool &isLeft, vector<Edge *> &edges){
	bool isEdgeFound = false;
	for(unsigned int i = 0 ; i < edges.size() ; i++){
		if(edges[i]->isLeft == isLeft && parent == edges[i]->parent){
			isEdgeFound = true;
			prefix = append(prefix, 1);
			findEdgeInCollection(prefix, edges[i]->child, true, edges);
			findEdgeInCollection(prefix, edges[i]->child, false, edges);
			break;
		}
	}
	if(!isEdgeFound){
		prefix = append(prefix, 0);
	}
}

void BFS(const unsigned int &processedTree, const unsigned int &height){
	if(isProgramFinished){
		return;
	}
//	isResult(processedTree, height);
//	cout << "height:" << height << " processedTree:" << processedTree << " final:" << finalTree << endl;
//	cout << "binary:";
//	printTreeBinary(processedTree);
//	cout << endl;
	BFSHelper(height, 0, NULL_SUFFIX, processedTree, true);
	BFSAttributes * attributes = BFSque.front();
	BFSque.pop_front();
	BFS(attributes->tree, attributes->height);
}

void BFSHelper(const unsigned int &height, const unsigned int &prefix, const unsigned int &sufix, const unsigned int &modifiedSubTree, bool isFresh){
	if(isProgramFinished){
		return;
	}
//	cout << "processedSUBTree:";
//	printTreeBinary(modifiedSubTree);
//	cout << endl;
	unsigned int leftSubtree = getLeftSubtree(modifiedSubTree);
	unsigned int rightSubtree = getRightSubtree(modifiedSubTree, leftSubtree);

	unsigned int leftRotatedTree = applyLeftRotationCommand(modifiedSubTree, leftSubtree, rightSubtree);
	unsigned int switchedTree = applySwitchCommand(modifiedSubTree, leftSubtree, rightSubtree);
	unsigned int rightRotatedTree = applyRightRotationCommand(modifiedSubTree, leftSubtree, rightSubtree);

	unsigned int rightRotatedComposedTree;
	unsigned int switchedComposedTree;
	unsigned int leftRotatedComposedTree;

	if(!isProgramFinished && !isFresh){
		rightRotatedComposedTree = completeTree(prefix, sufix, rightRotatedTree);
		switchedComposedTree = completeTree(prefix, sufix, switchedTree);
		leftRotatedComposedTree = completeTree(prefix, sufix, leftRotatedTree);
	} else{
		rightRotatedComposedTree = rightRotatedTree;
		switchedComposedTree = switchedTree;
		leftRotatedComposedTree = leftRotatedTree;
	}
	isResult(rightRotatedComposedTree, height);
	isResult(leftRotatedComposedTree, height);
	isResult(switchedComposedTree, height);
	if(!isProgramFinished && leftRotatedTree != 0 && isNew(leftRotatedComposedTree)){
//		cout << "leftRotatedTree:";
//		printTreeBinary(leftRotatedTree);
//		cout << endl;
//		cout << "leftRotatedComposedTree:";
//		printTreeBinary(leftRotatedComposedTree);
//		cout << endl;
		closedTrees[closedTreesCount++] = leftRotatedComposedTree;
		BFSAttributes * attributes = new BFSAttributes();
		attributes->height = height + 1;
		attributes->tree = leftRotatedComposedTree;
		BFSque.push_back(attributes);
	}

	if(!isProgramFinished && switchedTree != 0 && isNew(switchedComposedTree)){
//		cout << "switchedTree:";
//		printTreeBinary(switchedTree);
//		cout << endl;
//		cout << "switchedComposedTree:";
//		printTreeBinary(switchedComposedTree);
//		cout << endl;
		closedTrees[closedTreesCount++] = switchedComposedTree;
		BFSAttributes * attributes = new BFSAttributes();
		attributes->height = height + 1;
		attributes->tree = switchedComposedTree;
		BFSque.push_back(attributes);
	}

	if(!isProgramFinished && rightRotatedTree != 0 && isNew(rightRotatedComposedTree)){
//		cout << "rightRotatedTree:";
//		printTreeBinary(rightRotatedTree);
//		cout << endl;
//		cout << "rightRotatedComposedTree:";
//		printTreeBinary(rightRotatedComposedTree);
//		cout << endl;
		closedTrees[closedTreesCount++] = rightRotatedComposedTree;
		BFSAttributes * attributes = new BFSAttributes();
		attributes->height = height + 1;
		attributes->tree = rightRotatedComposedTree;
		BFSque.push_back(attributes);
	}

	if(!isProgramFinished && isToBeProcessed(leftSubtree)){
		BFSHelper(height, append(prefix, 1), appendSuffix(append(1,rightSubtree),sufix), leftSubtree, false);
	}
	if(!isProgramFinished && isToBeProcessed(rightSubtree)){
		BFSHelper(height, append(append(prefix, 1), leftSubtree), sufix, rightSubtree, false);
	}
}

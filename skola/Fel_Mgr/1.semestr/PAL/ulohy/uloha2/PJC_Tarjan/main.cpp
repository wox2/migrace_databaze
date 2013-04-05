#include <iostream>
#include "TreeNode.h"
#include "TarjanAlgorithm.h"
using namespace std;

int main(){
	TarjanAlgorithm tarjan;
	tarjan.readData();
	tarjan.process();
	tarjan.writeResults();
	return 0;
}

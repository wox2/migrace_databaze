#include <vector>
#include <iostream>

class ArrayInt {
	int *array;
	int _size;
public:
	ArrayInt(int n) {
		array = new int[n];
		_size = n;
	}
	
	void setVal(int position, int n) {
		array[position] = n;
	}
	
	int getVal(int pos) {
		return array[pos];
	}
	
	int size() {
		return _size;
	}
	
	
};

class ArrayIntIterator {
	int position;
	ArrayInt* ai;
public:
	ArrayIntIterator(ArrayInt* _ai): ai(_ai) {
		position = 0;
	}
	
	void next() {
		if(position < (ai->size() - 1) ) {
			position++;
		}
	}
	void setVal(int n) {
		ai->setVal(position, n);
	}
	
	int getVal() {
		return ai->getVal(position);
	}
	
	bool end() {
		if(position==(ai->size() - 1)) {
			return true;
		}
		else {
			return false;
		}
	}
};

int main(void) {
	ArrayInt a(10);
	ArrayIntIterator ai(&a); 
	while(!ai.end()) {
		ai.setVal(10);
		ai.next();
		
		std::cout << ai.getVal() << std::endl; 
	}
	/*ai.next();
	ai.setVal(20);
	std::cout << ai.getVal() << std::endl; 
	
/*	std::vector<std::vector<int> > vec(8);
	
	for(int i=0; i<vec.size(); i++) {
		for(int j=0; j<vec.size(); j++) {
			vec[i].push_back(i+j);
		}
	}
	
	
	for(std::vector<std::vector<int> >::const_iterator c_it = vec.begin(); c_it!=vec.end(); c_it++) {
		for(std::vector<int>::const_iterator c_it2 = (*c_it).begin(); c_it2!=(*c_it).end(); c_it2++) {
			std::cout << (*c_it2) << " ";
			*c_it2 = 10; 
		}
		std::cout << std::endl;
	}
*/	
	
	return 0;
}
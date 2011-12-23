#include <iostream>
#include <stdlib.h>
#include <string>

#include "bltree.h"

using namespace std;


int main(int argc, char** argv) {
    BLTree *bltr = new BLTree();
    BLTree &bl2tr = *bltr;
	string token;
	int number;
	while(1){
    		getline(cin, token);
		if(token.substr(0, 2) == string("IN")){
			// insert number
			number = atoi(token.substr(3).c_str());
			bl2tr << number;
		} else if(token.substr(0, 3) == string("OUT")){
			// delete number
			number = atoi(token.substr(4).c_str());
			bl2tr >> number;
		} else if(token.substr(0, 5) == string("DEPTH")){
			// depth of number
			number = atoi(token.substr(6).c_str());
			cout << "bltree[" << number << "] == " << bl2tr[number] << endl;
		} else if(token.substr(0, 5) == string("COUNT")){
			// count of number
			number = atoi(token.substr(6).c_str());
			cout << "bltree(" << number << ") == " << bl2tr(number) << endl;
		} else if(token.substr(0, 4) == string("EXIT")){
			break;
		}
	}

    delete bltr;

	return (EXIT_SUCCESS);
}

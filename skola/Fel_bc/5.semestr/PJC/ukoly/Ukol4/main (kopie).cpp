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
/*	while(1){
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
/*
    *bltr << 5;
	//cout << " cislo 5 ma hloubku:"<<(*bltr)[5]<<endl;
    *bltr << 3;
    *bltr << 7;
	*bltr << 5;
    *bltr << 3;
    *bltr << 7;
	*bltr << 9;
	*bltr << 9;
	*bltr << 9;
	*bltr << 9;
	*bltr << 6;
	*bltr << 6;
	*bltr << 6;
	*bltr << 6;

	bltr->printTree();
	*bltr >> 3;
	bltr->printTree();
	*bltr >> 3;
	bltr->printTree();
*/

	//cout << "root L:" << (((InnerNode *)(bltr)->root))->leftMaximalBound << "  R:" <<(((InnerNode *)(bltr)->root))->rightMinimalBound << endl;
	//bltr->printTree();
	//cout << endl;
	/*cout << "Hloubka elementu 1: "<<((*bltr)[1]) << endl;
    cout << "Hloubka elementu 5: "<<((*bltr)[5]) << endl;
    cout << "Hloubka elementu 7: "<<((*bltr)[7]) << endl;
    cout << "Hloubka elementu 9: "<<((*bltr)[9]) << endl;
    cout << "Hloubka elementu 6: "<<((*bltr)[6]) << endl;
    cout << "Hloubka elementu 3: "<<((*bltr)[3]) << endl;
    
	cout << "Cetnost elementu 1: " << (*bltr)(1) << endl;
	cout << "Cetnost elementu 5: " << (*bltr)(5) << endl;
	cout << "Cetnost elementu 7: " << (*bltr)(7) << endl;
	cout << "Cetnost elementu 9: " << (*bltr)(9) << endl;
	cout << "Cetnost elementu 6: " << (*bltr)(6) << endl;
	cout << "Cetnost elementu 3: " << (*bltr)(3) << endl;*/
	//delete bltr;
*bltr << 21;
*bltr << 30;  
*bltr << 11;
*bltr << 11;

cout << (*bltr)[11];
cout << (*bltr)(11);
 *bltr >> 21;
cout << (*bltr)[11];


	return (EXIT_SUCCESS);
}

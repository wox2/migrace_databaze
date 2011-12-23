//============================================================================
// Name        : DU3.cpp
// Author      : vikturek
// Version     :
// Copyright   : 
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

// Zdrojovy kod s vasi implementaci BSTSET
#include "BSTSET.h"

using namespace std;

// Funkce zalozi instanci objektu BSTSET a vlozi do ni hodnoty ze vstupniho souboru
void test(ifstream &commandFile){
	BSTSET *bsTree = new BSTSET;
	BSTSET &b = *bsTree;
	int i;
	string line;
	stringstream lineStream;

	while(!commandFile.eof()){
		getline(commandFile, line);
		lineStream.clear();
		if(line.size() < 3){
			continue;
		}
		lineStream << line.substr(3);
		lineStream >> i;
		if(line.substr(0,3) == string("ADD")){
			cout << "ADD " << i << endl;
			if(!lineStream.fail()){
				// Vlozi hodnotu do mnoziny
				b << i;
				// Zjisti jak hluboko je dany prvek vlozen v BST
				cout << "b[" << i << "] == " << b[i] << endl;
				// Zjisti kolikrat byla hodnota do mnoziny vlozena
				cout << "b(" << i << ") == " << b(i) << endl;
				continue;
			}
		} else {
			cerr << "Parse error!" << endl;
			exit(1);
		}
	}
	cout << b << endl;
	delete bsTree;
}

int main(int argc, char **argv) {
	ifstream commandFile;
	string line;
	stringstream lineStream(line);

	if(argc == 2){
		commandFile.open(argv[1]);
		if(!commandFile.is_open()){
			cerr << "Can't open command file: " << argv[1] << endl;
			return -1;
		}
	} else {
		cout << "You need get command file as first argument." << endl;
		return 0;
	}
	getline(commandFile, line);
	if(line == string("INT")){
		test(commandFile);
	} else {
		cerr << "Parse error!" << endl;
		return 1;
	}
	return 0;
}

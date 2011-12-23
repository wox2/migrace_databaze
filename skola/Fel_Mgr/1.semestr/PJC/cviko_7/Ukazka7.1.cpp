//============================================================================
// Name        : Cviceni7-example1.cpp
// Author      : vikturek
// Version     :
// Copyright   : 
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int main() {
	int *pole = new int[1024];
	pole = NULL; // Chyba - memory leak
	delete pole; // Chyba - dealokace nuloveho ukazatele
	return 0;
}

//============================================================================
// Name        : Ukazka1.cpp
// Author      : vikturek
// Description : Program vypise vsechny sve parametry predane z prikazoveho
//               radku.
//============================================================================

#include <iostream>
using namespace std;

int main(int argc, char **argv) {
	cout << "Pocet parametru je " << argc << "." << endl;
	for(int i = 0; i < argc; ++i){
		cout << "Parametr " << i << ": ";
		cout << argv[i] << endl;
	}
	return 0;
}

//============================================================================
// Name        : Ukazka2.cpp
// Author      : vikturek
// Description : Pouziti funkce s promennym poctem parametru.
//============================================================================

#include <cstdarg>
#include <iostream>

using namespace std;

int prumer(int pocetParametru, ...){
	int prum;
	va_list seznam;
	va_start(seznam, pocetParametru);
	for(int i = 0; i < pocetParametru; ++i){
		prum += va_arg(seznam, int);
	}
	va_end(seznam);
	return prum/pocetParametru;
}

int main() {
	cout << "Prumer z cisel 2, 4, 6, 8, 10 je ";
	cout << prumer(5, 2, 4, 6, 8, 10) << "." << endl;
	return 0;
}

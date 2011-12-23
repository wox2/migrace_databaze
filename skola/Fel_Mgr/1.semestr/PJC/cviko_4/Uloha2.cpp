//============================================================================
// Name        : Uloha2.cpp
// Author      : vikturek
// Popis       : Program nacita cela cisla dokud nedostane spatny vstup, nebo
//				 nenajde konec souboru. Potom vytiskne serazena cisla, kazde
//				 na jednom radku.
// Ukol        : Doplnte kod tak, aby na soubor vstupnich dat "vstup.txt"
// 				 vypsal naprosto presne nasledujici tabulku:
/*
Dec        Hex      HexRight HexFill  Binary
----------------------------------------------------------------------
5          5               5 00000005 00000000000000000000000000000101
6          6               6 00000006 00000000000000000000000000000110
23         17             17 00000017 00000000000000000000000000010111
45         2d             2d 0000002d 00000000000000000000000000101101
89         59             59 00000059 00000000000000000000000001011001
2147483647 7fffffff 7fffffff 7fffffff 01111111111111111111111111111111
*/
//				 Vstupni data jsou i zde:
/*
2147483647
23 45 6


89    5


*/
// Napoveda    : Podivejte se na cinnost manipulatoru setw(), setfill(), right,
//               left, hex, dec. Pro vypis binarni hodnoty upravte svuj kod
//               z ulohy 1.
//============================================================================

#include <algorithm>
#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

int main() {
	vector<int> cisla;
	int cislo;

	cin >> cislo;
	while (!cin.eof() && cin.good()){
		cisla.push_back(cislo);
		cin >> cislo;
	};
	sort(cisla.begin(), cisla.end());

	/**************************************
	 * 	Zde doplnte kod pro vypis hlavicky
	 **************************************/
	 
	for(unsigned int i = 0; i < cisla.size(); ++i){

		/*******************************************************
		 * 	Zde doplnte kod pro vytisknuti jednoho radku tabulky
		 *
		 *******************************************************/
	}

	return 0;
}

//============================================================================
// Name        : Ukazka.cpp
// Author      : vikturek
// Popis       : Program nacita cela cisla dokud nedostane spatny vstup, nebo
//		 nenajde konec souboru. Potom vytiskne serazena cisla, kazde
//		 na jednom radku.
//============================================================================

#include <algorithm>
#include <iostream>
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
	for(unsigned int i = 0; i < cisla.size(); ++i){
		cout << cisla[i] << endl;
	}

	return 0;
}

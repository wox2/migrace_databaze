//============================================================================
// Name        : Uloha3.cpp
// Author      : vikturek
// Zadani      : Dokoncete zdrojovy kod. Trida Zasobnik implementuje zasobnik
//               na kladna cela cisla mensi nez _maxCislo a cely zasobnik ma
//               hloubku _hloubka. Do zasobniku se ukladaji cisla pomoci tridy 
//               Number z ulohy 2. Zasobnik ma navic metodu tiskni(), ktera
//               cely zasobnik vytiskne v tabulce z ulohy 2. Funkce main() prebira
//               z prikazove radky tri parametry. Prvnim je hloubka zasobniku,
//               druhym maximalni hodnota v zasobniku a tretim je soubor
//               s prikazy. Prikazy jsou dohromady tri a kazdy lezi vzdy na
//               jednom radku. Prvni je ve tvaru "PUSH cislo". Misto "cislo"
//               je uvedena samozrejme ciselna hodnota a prikaz rika, ze se ma
//               do zasobniku vlozit ono cislo. Druhy prikaz je "POP". Ten
//               pouze vytahne hodnotu z vrcholu zasobniku. Posledni prikaz je
//               "EXIT" a znamena konec prikazu.
//
//               Pri testovani spoustejte aplikaci s parametry:
//               ./Uloha3 5 20 prikazy.txt
/*               Soubor s prikazy prikazy.txt vypada takto:
PUSH 1
PUSH 30
PUSH 20
PUSH 3
PUSH 8
PUSH 19
PUSH 11
PUSH 13
POP
PUSH 13
POP
POP
POP
PUSH 18
EXIT
*//*            Na tento vstup by mela aplikace reagovat takto:

Dec        Hex      HexRight HexFill  Binary
----------------------------------------------------------------------
1          1               1 00000001 00000000000000000000000000000001
20         14             14 00000014 00000000000000000000000000010100
18         12             12 00000012 00000000000000000000000000010010

*/
//============================================================================

#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

void tiskniPouziti(){
	cout << endl << "Pouziti: Uloha3 velikostZasobniku maxCislo prikazovySoubor" << endl;
}

/****************************************************************************
 *  Tridu zasobnik dokoncete dle pokynu v zadani.
 ****************************************************************************/

class Zasobnik{
	vector<Number> cisla;
public:
	Zasobnik(int _hloubka, int _maxCislo){
	}
	void push(Number cislo){
	}
	Number pop(){
	}
	void tiskni(){
	}
};

int main(int argc, char **argv) {
	stringstream ss;
	int hloubka;
	int maxCislo;
	ifstream prikazovySoubor;

	if(argc < 4){
		tiskniPouziti();
		return 1;
	}

	/*************************************************************************
	 *  Zde je treba zpracovat argumenty, tedy prvni dva prevest na cislo
	 *  typu integer a pomoci tretiho otevrit soubor se vstupnimi daty.
	 *************************************************************************/

	Zasobnik zasobnik(hloubka, maxCislo);

	/*************************************************************************
	 *  Zde je treba zpracovat soubor s prikazy
	 *************************************************************************/

	zasobnik.tiskni();

	return 0;
}

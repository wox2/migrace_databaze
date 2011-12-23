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

#include "Number.h"
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <vector>
#include <cstdlib>

using namespace std;

/****************************************************************************
 *  Tridu zasobnik dokoncete dle pokynu v zadani.
 ****************************************************************************/

class Zasobnik {
    
public:

    Zasobnik(int _hloubka, int _maxCislo) {
        maxHloubka = _hloubka;
        maxCislo = _maxCislo;
    }
    
    ~Zasobnik() {
        
    }

    void push(Number cislo) {
        if (cisla.size() == maxHloubka || cislo.get() > maxCislo) return;
        cisla.push_back(cislo);
    }

    Number pop() {
        Number nr;
        if (cisla.empty()) return nr;
        nr = cisla.back();
        cisla.resize(cisla.size()-1);
        std::cout << "size after pop: " << cisla.size() << std::endl; 
        return nr;
    }

    void tiskni() {
        vytiskniCislaDoTabulky(cisla);
    }
    
private:
    vector<Number> cisla;
    unsigned int maxHloubka;
    unsigned int maxCislo;
    
    void dec2bin(long dec) {
        unsigned int i = 0;
        unsigned int bin[32];

        for (i = 0; i < 32; i++) {
            bin[i] = 0;
        }

        for (i = 31; dec > 0; i--) {
            bin[i] = dec % 2;
            dec /= 2;
        }
        for (i = 0; i < 32; i++) {
            std::cout << bin[i];
        }
        std::cout << std::endl;
    }

    void vytiskniCislaDoTabulky(vector<Number> cisla) {
        // Hlavicka tabulky
        std::cout << setw(10) << setfill(' ') << left << "Dec" << " ";
        std::cout << setw(8) << "Hex" << " ";
        std::cout << setw(8) << right << "HexRight" << " ";
        std::cout << setw(8) << left << "HexFill" << " ";
        std::cout << setw(32) << "Binary" << std::endl;
        std::cout << "----------------------------------------------------------------------" << std::endl;

        int num = 0;
        for (unsigned int i = 0; i < cisla.size(); ++i) {
            num = cisla.at(i).get();
            // radek rabulky
            std::cout << setw(10) << dec << setfill(' ') << left << num << " ";
            std::cout << setw(8) << hex << setfill(' ') << left << num << " ";
            std::cout << setw(8) << hex << setfill(' ') << right << num << " ";
            std::cout << setw(8) << hex << setfill('0') << right << num << " ";
            dec2bin(num);
            std::cout << std::endl;
        }
    }
};

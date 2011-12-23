//============================================================================
// Name        : Uloha4.cpp
// Author      : vikturek
// Zadani      : Dokoncete zdrojovy kod. Vysledna aplikace by mela obsahovat
//             : novou tridu Memory, ktera simuluje jedoduchou pamet. Detaily
//             : o metodach teto tridy jsou uvedeny primo v jeji v definici.
//             :
//             : Do tridy Number pridejte funkcnost. Number nyni nove obsahuje
//             : pole typu int, ktere ma obsahovat hodnoty 0 a 1 a tvori
//             : binarni reprezentaci ulozeneho cisla.
//             :
//             : Aplikace opet parsuje prikazovy soubor. Ten nyni obsahuje
//             : nove prikazy:
//             : STORE <adresa> - vyjme hodnotu z vrcholu zasobniku a vlozi ji
//             : 	do pameti memory na adresu 'adresa'
//             : PRINT <adresa> - vytiskne hodnotu cisla typu Number, ulozeneho
//             : 	v pameti na adrese 'adresa'
//             : SAVE <jmeno_souboru> - ulozi obsah pameti memory do souboru
//             : 	jmenem 'jmeno_souboru'
//             : LOAD <jmeno_souboru> - nacte obsah pameti memory ze souboru
//             : 	jmenem 'jmeno_souboru'
//             :
//             : Spusteni ulohy: ./Uloha4 5 20 prikazy.txt
//             : Na tento soubor s prikazy:
/*
PUSH 18
STORE 0
PUSH 12
STORE 512
PUSH 15
STORE 256
PRINT 256
PRINT 512
PRINT 0
SAVE memory.bin
PUSH 2
STORE 0
PRINT 0
LOAD memory.bin
PRINT 0
PUSH 3
STORE 0
PRINT 0
LOAD memory.bin
PRINT 0
EXIT
 */
//             : by mela aplikace vypsat tento vypsat tento vystup:
/*
Binarni hodnota cisla ulozeneho v pameti na adrese 256 je 00000000000000000000000000001111
Binarni hodnota cisla ulozeneho v pameti na adrese 512 je 00000000000000000000000000001100
Binarni hodnota cisla ulozeneho v pameti na adrese 0 je 00000000000000000000000000010010
Binarni hodnota cisla ulozeneho v pameti na adrese 0 je 00000000000000000000000000000010
Binarni hodnota cisla ulozeneho v pameti na adrese 0 je 00000000000000000000000000010010
Binarni hodnota cisla ulozeneho v pameti na adrese 0 je 00000000000000000000000000000011
Binarni hodnota cisla ulozeneho v pameti na adrese 0 je 00000000000000000000000000010010
 */
//             : Kazdy radek vystupu je vytisknut po zavolani prikazu PRINT <adresa>.

// Napoveda    : Pro kopirovani pouzijte funkci memcpy().

//============================================================================

#include <cstdlib>
#include <cstring>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <vector>
#include "Number.h"
#include "Uloha3.cpp"

using namespace std;

void tiskniPouziti() {
	cout << endl<< "Pouziti: Uloha4 velikostZasobniku maxCislo prikazovySoubor"<< endl;
}

#define MEMORY_SIZE 1024

class Memory {
	char memo[MEMORY_SIZE];

public:
	// Zapise promennou, na kterou ukazuje parametr data,
	// o velikosti size na adresu address.
	int write(char *data, unsigned size, unsigned address){
            memcpy((void *)data, (const void *)memo[address], size); 
            return 1;
	}
        
	// Vrati ukazatel na objekt typu Number, ulozeny v pameti
	// na adrese address.
	Number *getNumber(unsigned address) {
            return (Number *) memo[address];
		// !!! POZOR !!! Pokud chcete ziskat bod za tuto ulohu
		// nesmite pro navratovou hodotu pouzit promennou typu
		// Number!!!
	}
        
	// Ulozi (BINARNE!) obsah cele pameti do souboru zadaneho
	// parametrem jmeno.
	void saveToFile(const char *jmeno) {
            ofstream fos;
            fos.open(jmeno);            
            fos << memo;
	}
        
	// Nahraje obsah cele pameti ze souboru zadaneho parametrem
	// jmeno.
	void loadFromFile(const char *jmeno) {
            ifstream fi;
            fi.open(jmeno);            
            fi >> memo;
	}
};

class Zasobik;

int main(int argc, char **argv) {
	stringstream ss;
	int velikost;
	int maxCislo;
	ifstream prikazovySoubor;
	string radek, token;

	if (argc < 4) {
		tiskniPouziti();
		return 1;
	}
	ss.str(string(argv[1]));
	ss >> velikost;
	if (ss.fail()) {
		tiskniPouziti();
		return 1;
	}
	ss.clear();
	ss.str(string(argv[2]));
	ss >> maxCislo;
	if (ss.fail()) {
		tiskniPouziti();
		return 1;
	}
	prikazovySoubor.open(argv[3]);
	if (!prikazovySoubor.is_open()) {
		cout << endl << "Nemohu otevrit prikazovy soubor: " << argv[3] << endl;
		return 1;
	}

	Zasobnik zasobnik(velikost, maxCislo);
	Memory memory;
        string str;
	while(!prikazovySoubor.eof()) {
                prikazovySoubor >> str;
                if (str.compare("STORE") == 0) {
                    char * fileName = new char[80];  
                    memory.saveToFile(fileName);
                    delete fileName;
                }else if(str.compare("LOAD")){
                    char * fileName = new char[80];  
                    memory.loadFromFile(fileName); 
                    delete fileName;
               }else if(str.compare("PRINT")){
                   unsigned int address;
                   prikazovySoubor >> address;
                   memory.getNumber(address)->printBinary(); // address
                } else if (str.compare("EXIT") == 0) {
            break;
        }
    }

	return 0;
}

/*
PUSH 18
STORE 0
PUSH 12
STORE 512
PUSH 15
STORE 256
PRINT 256
PRINT 512
PRINT 0
SAVE memory.bin
PUSH 2
STORE 0
PRINT 0
LOAD memory.bin
PRINT 0
PUSH 3
STORE 0
PRINT 0
LOAD memory.bin
PRINT 0
EXIT
 */
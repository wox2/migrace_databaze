//============================================================================
// Name        : Uloha5.cpp
// Author      : vikturek
// Description : Uloha k procviceni dynamicke alokace pameti. Navrhnete
//               tridu Pole2D, ktera v sobe bude obsahovat dynamicky alokovane
//               dvourozmerne pole objektu typu Number. Tento objekt bude mit
//               navic dve metody - store a load. Metody slouzi k ulozeni a
//               nacteni Pole2D z objektu typu Memory (vice viz kod). Opet
//               mate k dispozici prikazovy soubor, ktery obsahuje novou sadu
//               prikazu. Celou aplikaci spustite takto:
//               ./Uloha5 2488 prikazy.txt
//               Prvnim parametrem je velikost vnitrni pameti objektu Memory a
//               pri kontrole bude vyzadovana presne tato velikost pameti!

//               Dale upravte tridu Memory tak, aby bylo pole memo alokovano
//               dynamicky podle podle hodnoty z konstrukoru.

//               !!Pozor!! V kodu nesmi vzniknout memory leaky!!!

//               Nove prikazy:
//               MATRIX ALLOCATE <x-rozmer> <y-rozmer>
//               	- alokuje Pole2D pro zadane rozmery, na dalsich radcich
//               	jsou jednotlive polozky dvourozmerneho pole (x-rozmer je
//               	horizontalni a y-rozmer vertikalni)
//               MATRIX PRINT
//               	- vytiskne vsechny prvky z Pole2D ve stejnem poradi, jak
//               	byly zadany prikazovym souborem
//               MATRIX STORE <adresa>
//               	- ulozi data z Pole2D do objektu Memory na adresu 'adresa'
//               MATRIX LOAD <adresa>
//               	- nacte data z objektu Memory z adresy 'adresa' do objektu
//               	Pole2D

//               Ukazka souboru prikazy.txt:
/*
MATRIX ALLOCATE 4 3
5	8	30	23454
345	1435	24352	8576
1234 	4234	36753	0
MATRIX PRINT
MATRIX STORE 832
MATRIX ALLOCATE 4 3
0	0	0	0
0	0	0	0
0	0	0	0
MATRIX PRINT
MATRIX LOAD 832
MATRIX PRINT
MATRIX ALLOCATE 3 2
3	4	6
15	5	9
MATRIX PRINT
MATRIX STORE 0
MATRIX ALLOCATE 4 3
0	0	0	0
0	0	0	0
0	0	0	0
MATRIX LOAD 832
MATRIX PRINT
MATRIX STORE -1
MATRIX STORE 833
EXIT
 */
//               Na tento vstup by mela aplikace reagovat nasledujicim vystupem:
/*
5 8 30 23454
345 1435 24352 8576
1234 4234 36753 0

0 0 0 0
0 0 0 0
0 0 0 0

5 8 30 23454
345 1435 24352 8576
1234 4234 36753 0

3 4 6
15 5 9

5 8 30 23454
345 1435 24352 8576
1234 4234 36753 0

Nepodarilo se ulozit pole2D na adresu -1.
Nepodarilo se ulozit pole2D na adresu 833.
 */
//============================================================================

#include <cstdlib>
#include <cstring>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

void tiskniPouziti() {
	cout << endl << "Pouziti: Uloha5 velikostPameti prikazovySoubor" << endl;
}

class Number {
	int hodnota;
	bool hodnotaInicializovana;
	int binary[sizeof(int) * 8];

	void toBinary(int cislo) {
		int zbytek = cislo;

		for (unsigned int i = 0; i < sizeof(int) * 8; ++i) {
			binary[i] = 0;
		}
		for (int i = sizeof(int) * 8 - 1; zbytek > 0; --i) {
			binary[i] = zbytek % 2;
			zbytek /= 2;
		}
	}
public:
	Number() {
		hodnotaInicializovana = false;
	}
	Number(int hodnota) {
		this->hodnota = hodnota;
		hodnotaInicializovana = true;
		toBinary(hodnota);
	}
	void setHodnota(int hodnota) {
		this->hodnota = hodnota;
		hodnotaInicializovana = true;
		toBinary(hodnota);
	}
	void printBinary() {
		if (hodnotaInicializovana) {
			for (unsigned int i = 0; i < sizeof(int) * 8; ++i) {
				cout << binary[i];
			}
			cout << endl;
		} else {
			cout << endl << "Error! Number - neinicializovana hodnota" << endl;
			return;
		}

	}
	int getHodnota() {
		if (hodnotaInicializovana) {
			return this->hodnota;
		} else {
			cout << endl << "Error! Number - neinicializovana hodnota" << endl;
			exit(1);
		}
	}
};

#define MEMORY_SIZE 1024

class Memory {
	char memo[MEMORY_SIZE];
public:
	int write(char *data, unsigned size, int address){
		if(address < 0){
			cout << endl << "Memory::write(): Parametr 'address' je mensi nez 0!" << endl;
			return -1;
		}
		if(address + size > MEMORY_SIZE){
			cout << endl << "Memory::write(): Malo mista v pameti pro provedeni zapisu." << endl;
			return -2;
		}
		if(data == NULL){
			cout << endl << "Memory::write(): Misto ukazatele na data byl predan NULL." << endl;
			return -3;
		}
		// Funkce memcpy() kopiruje z ukazatele 'data' pocet bytu o velikosti 'size' na misto
		// kam ukazuje prvni parametr. Prvni parametr je adresa prvku s indexem 'address' od
		// zacatku pameti 'memo'. Volani 'memo[address]' vrati primo prvek (= jeho hodnotu)
		// s indexem 'address'. Adresu tohoto prvku ziskame pomoci &. Oba prvni parametry musi
		// byt pretypovany na univerzalni ukazatel 'void *', ktery je vyzadovan fuknci 'memcpy'.
		memcpy((void *) &memo[address], (void *) data, size);
		return 0;
	}

	Number *getNumber(unsigned adresa) {
		if (adresa < 0 or adresa + sizeof(Number) >= MEMORY_SIZE) {
			cout << endl << "Memory::getNumber(): Cteni by skoncilo mimo alokovanou oblast." << endl;
			exit(1);
		}
		// Vracime adresu prvku s indexem 'address' z pole 'memo'. Protoze ocekavame na tomto
		// miste ulozeny objekt typu 'Number' a zaroven ukazatel na typ na 'Number' je ocekavan
		// jako navratova hodnota, tak je treba tuto adresu pretypovat, aby prosla typova
		// kontrola.
		return (Number *) &memo[adresa];
	}
	void saveToFile(const char *jmeno) {
		ofstream soubor(jmeno);
		if (!soubor.is_open()) {
			cout << "Chyba - nepodarilo se otevrit vystupni soubor: " << jmeno
					<< endl;
			exit(1);
		}
		soubor.write((const char *) memo, MEMORY_SIZE);
		if (soubor.bad()) {
			cout << "Chyba - nepodarilo se zapsat pamet do vystupniho souboru."
					<< endl;
			exit(1);
		}
		soubor.close();
	}
	void loadFromFile(const char *jmeno) {
		ifstream soubor(jmeno);
		if (!soubor.is_open()) {
			cout << "Chyba - nepodarilo se otevrit vstupni soubor: " << jmeno
					<< endl;
			exit(1);
		}
		soubor.read((char *) memo, MEMORY_SIZE);
		if (soubor.bad()) {
			cout << "Chyba - nepodarilo se zapsat pamet do vystupniho souboru."
					<< endl;
			exit(1);
		}
		soubor.close();
	}
};

class Pole2D{
	// Tento ukazatel by mel ukazovat na dynamicky alokovane
	// dvourozmerne pole instanci objektu typu Number
	Number **pole2D;

public:
	Pole2D(){

	}
	Pole2D(unsigned _size1D, unsigned _size2D){

	}
	// Dynamicky alokuje pamet pro dvourozmerne pole
	void allocate(unsigned _size1D, unsigned _size2D){

	}
	// Nastavi prvkek v poli na dane cislo 'number'
	// Navratova hodnota je 0, kdyz se zapis zdari, nebo
	// cokoliv jineho pokud je vstup mimo rozsah
	int set(unsigned x, unsigned y, int number){

	}
	// Stejne jako set, jen pro cteni prvku
	int get(unsigned x, unsigned y){

	}
	// Zalohuje vsechna sva data do objektu typu Memory
	int store(Memory &memory, unsigned address){

	}
	// Nacte vsechna zva data z objektu Memory
	int load(Memory &memory, unsigned address){

	}
	// Vytiskne dvourozmerne pole ve stejnem poradi, jak bylo zadano
	// v prikazovem souboru.
	int print(){

	}
	// Destruktor, zde je misto pro dealokaci pameti
	~Pole2D(){

	}
};

int main(int argc, char **argv) {
	stringstream ss;
	int velikost;
	ifstream prikazovySoubor;
	string token;

	if (argc < 3) {
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
	prikazovySoubor.open(argv[2]);
	if (!prikazovySoubor.is_open()) {
		cout << endl << "Nemohu otevrit prikazovy soubor: " << argv[3] << endl;
		return 1;
	}

	int cislo, size1D, size2D;
	Memory memory(velikost);
	Pole2D pole2D;

	/******************************************************************
	 *               Parsovani souboru prikazy.txt                    *
	 ******************************************************************/

	return 0;
}

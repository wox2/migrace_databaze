/*
 * main.cpp
 *
 *  Created on: Nov 1, 2010
 *      Author: vikturek
 */

#include <fstream>
#include <iomanip>
#include <iostream>
#include <stdlib.h>

using namespace std;

// Trida reprezentujici cely simulator

class Pytel {
    // Registr AX
    unsigned char ax;
    // Registr BX
    unsigned char bx;
    // Promenna na ulozeni instrukce
    short int instruction;
    // Objekt pro nacitani programu
    ifstream programFile;
    // Objekt pro vystup
    ofstream outputFile;

    // Nacte dalsi instrukci do promenne "instrukce"
    // Dokud se dari nacitat nove instrukce vraci true.
    bool nextInstruction();
public:
    Pytel(const char *_programFileName, const char *_outputFileName);
};

Pytel::Pytel(const char *_programFileName, const char *_outputFileName) {
    ax = 0;
    bx = 0;
    programFile.open(_programFileName);
    if (!programFile.is_open()) {
        cout << "Soubor s programem se nepodarilo otevrit!" << endl;
        exit(1);
    }
    outputFile.open(_outputFileName);
    if (!outputFile.is_open()) {
        cout << "Vystupni soubor se nepodarilo otevrit!" << endl;
        exit(1);
    }
    while (nextInstruction()) {
        // Vytiskne aktualni instrukci
        cout << hex << setfill('0') << "0x" << setw(4) << instruction << "\t";
        //-------------------- Zacatek Tveho kodu ------------------------------------------------
        //registrova
        int arit = (instruction >> 15) & 1;
        cout << "Aritmeticka " << arit << endl;

        int fourtAndFifth = 2 * ((instruction >> 11) & 1) + ((instruction >> 10) & 1);
        cout << "ctvrty a paty bit " << fourtAndFifth << endl;

        //registrova
        if (!arit) {
            switch (fourtAndFifth) {
                //nacti operand do registru ax
                case 0: ax = 0;
                        for (int i = 9; i >= 2; i--) {
                            ax = ax * 2 + ((instruction >> i) & 1);
                        };
                        cout << "Do registru ax jsem nacetl: " << (int)ax << endl;
                        break;
                //nacti operand do registru bx
                case 1:
                        bx = 0;
                        for (int i = 9; i >= 2; i--) {
                            bx = bx * 2 + ((instruction >> i) & 1);
                        }
                        cout << "do registru bx jsem nacetl " << (int)bx << endl;
                        break;
                // zapis registr ax na vystup
                case 2: outputFile.write((const char *) &ax, 1);
                        cout << "Do souboru zapisuju hodnotu ax: " << (int)ax << endl;
                        break;
                // zapis registr bx na vystup
                case 3: outputFile.write((const char *) &bx, 1);
                        cout << "Do souboru zapisuju hodnotu bx: " << (int)bx << endl;
                        break;
            }
        } else {
            //aritmeticka
            switch (fourtAndFifth) {
                // soucet uloz do ax
                case 0: cout << "ax = ax + bx =>" << (int)ax << " + " << (int)bx;
                        ax = ax + bx; 
                        cout << " = " << (int)ax << endl;
                        break;
                // rozdil uloz do ax
                case 1: cout << "bx = ax - bx =>" << (int)ax << " - " << (int)bx;
                        ax = ax - bx; 
                        cout << " = " << (int)ax << endl;
                        break;
                // negace ax
                case 2: cout << "ax :" << (int)ax << " neguju na:";
                        ax = ~ax; 
                        cout << (int)ax << endl;
                        break;
                // negace bx
                case 3: cout << "bx: " << (int)bx << " neguju na:";
                        bx = ~bx; 
                        cout << (int)bx << endl;
            }
        }

        //-------------------- Konec Tveho kodu ---------------------------------------------------
        cout << endl;
    }
    cout << endl << "Program skoncil, konecny stav registru:" << endl << endl;
    cout << "AX:\t0x" << hex << setw(2) << setfill('0') << (int) ax << endl;
    cout << "BX:\t0x" << hex << setw(2) << setfill('0') << (int) bx << endl;

    programFile.close();
    outputFile.close();
}

bool Pytel::nextInstruction() {
    // Nacteni instrukce, slo by to i lepe, ale kod byl napsan nejdrive v prostredi
    // little endian, a pak portovan pro big endian. Rozdilem je poradi bytu v pro-
    // menne instrukction.
    programFile.read(&((char *) &instruction)[0], 1);
    programFile.read(&((char *) &instruction)[1], 1);
    return programFile.good();
}

int main(int argc, char **argv) {
    // Kontrola parametru
    if (argc < 3) {
        cout << "Pouziti: pytel program output" << endl;
        return 0;
    }
    // Spusteni simulace
    Pytel pytel(argv[1], argv[2]);
}

#include <iostream>
#include <vector>
#include <sstream>
#include <fstream>

using namespace std;

int getIndex(string promenna, vector<string> promenne){
    unsigned int i = 0;
    while(i < promenne.size() && promenne[i].compare(promenna) !=0){
        i++;
    }
    return i;
}

void print(int index, vector< vector<unsigned short > > hodnoty){
    vector<unsigned short> cislo = hodnoty[index];
    cout << hex;
    cout << "0x";
    for (unsigned int i = 0 ; i < cislo.size() ; i++){
        cout << " " << cislo[i];
    }
    cout << endl;
}

void add(int indexModified, int index, vector< vector<unsigned short > > * hodnoty){
    vector<unsigned short> * cisloModified = &((*hodnoty)[indexModified]);
    vector<unsigned short> * cislo = &((*hodnoty)[index]);
    unsigned int i = 0;
    int prenos = 0;
    vector<unsigned short> newNumber;
    unsigned short _cislo, _cisloModified = 0;
    while ( i < cislo->size() && i < cisloModified->size() && prenos != 0){
        if(i < cislo->size()){
            _cislo = (int)(*cislo)[i];
        } else{
            _cislo = 0;
        }
        if( i < cisloModified->size()){
            _cisloModified = (int)(*cisloModified)[i];
        }else{
            _cisloModified = 0;
        }
        
        newNumber.push_back((unsigned short)((prenos + _cislo + _cisloModified)%256));
        prenos = ((prenos + _cislo + _cisloModified)/256);
        
    }
    cisloModified->clear();
    
    for(int i = newNumber.size()-1 ; i >= 0  ; i--){
        cisloModified->push_back(newNumber.at(i));
    }
}

int main (int argc, char** argv){
	//cout << "fu" << endl;
	stringstream ss(stringstream::in | stringstream::out);
	ifstream prikazovySoubor;
    stringstream hex(stringstream::in | stringstream::out);

        prikazovySoubor.open("prikazy.txt");
        string token;
        string radka;
        hex << std::hex;
        short value;
        vector<string> promenne;
        vector<vector<unsigned short> > hodnoty ;
        getline(prikazovySoubor, radka);

        //nacti deklarace
        while(string("").compare(radka)!= 0){
            ss << radka;
            ss >> token;  //let
            ss >> token;  // promenna

            promenne.push_back(token);

            ss >> token; //0x
            ss >> token;

            vector <unsigned short> cislo;

            while(token.compare("") != 0){
                hex << token;
                hex >> value;
                cislo.push_back(value);
                ss >> token;
                if(ss.bad() || ss.eof() || !ss.good()){
                    ss.clear();
                    break;
                }
            }

            hodnoty.push_back(cislo);
            getline(prikazovySoubor, radka);
        }

        getline(prikazovySoubor, radka); //begin

        //nacti prikazy
        getline(prikazovySoubor, radka);
        while(radka.compare("exit") != 1){
            ss << radka;
            ss >> token;
            if(token.compare("print")){
                ss >> token;
                int index = getIndex(token, promenne);
                print(index, hodnoty);
            }else if(token.compare("add"))
                getline(prikazovySoubor, radka);
                ss >> token;
                int indexModified = getIndex(token, promenne);
                ss >> token;
                int index = getIndex(token, promenne);
                add(indexModified, index, & hodnoty);
        }
	return 0;
}


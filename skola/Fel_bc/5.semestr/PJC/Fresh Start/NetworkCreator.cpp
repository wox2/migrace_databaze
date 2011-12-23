/* 
 * File:   NetworkCreator.cpp
 * Author: woxie
 * 
 * Created on 8. leden 2011, 1:37
 */

#include "NetworkCreator.h"
#include <cstddef>
#include <iostream>
#include <string.h>
#include <stdlib.h>

NetworkCreator::NetworkCreator() {
    net = NULL;
}

NetworkCreator::NetworkCreator(const NetworkCreator& orig) {
}

NetworkCreator::~NetworkCreator() {
}

bool NetworkCreator::createNetwork(){
    int cityCount = 0;
    int roadCount = 0;
    cin >> cityCount;
    
    if(cin.bad() || cityCount <= 0 ){
        cout << "Pocet mest musi byt nezaporne cislo" << endl;
        return false;
    }

    char * input = new char[6];
    cin.read(input,5);
    input[5]='\0';
    const char  part [] = " mest\0";
    if(strcmp(part, input)){
        cout << "1 ";
        cout << input;
	delete [] input;    
        return false;
    }
    if(cin.peek() == 'a'){
        cin.get();
    }
    
    delete [] input;
    char * input2 = new char[3];
    input2[2]='\0';
    cin.read(input2, 2);
    const char part2 [] = ", ";
    if(strcmp(part2, input2)){
        cout << input2 << endl;
        cout << "1 a";
	delete [] input2;
        return false;
    };
    delete [] input2;
    if(cin.peek() == 'a'){
        cin.get();
    }
    cin >> roadCount;

    if(!cin.good() || roadCount < 0 ){
        cout << "Pocet cest musi byt kladne cislo" << endl;
        return false;
    }

    if( roadCount > cityCount * (cityCount - 1) ){
        cout << "Pocet cest nesmi byt vetsi nez pocet hran v obycejnem orientovanem grafu";
        return false;
    }

    char * input3 = new char[8];
    input3[7]='\0';
    cin.read(input3,7);
    if(strcmp(" silnic", input3)){
        cout << "2";
        cout << input;
	delete [] input3;
        return false;
    }
    delete [] input3;


    if(cin.peek() == 'e'){
        cin.get();
    }

    
    int start = 0;
    int end = 0;
    net = new ComunicationNetwork(cityCount);

    for(int i = 0 ; i < roadCount ; i++){
        char ch ='a';
        cin.get(ch);
        if ( ch != ','){
            cout << "3 " << ch << endl;
            return false;
        }
        if (cin.get() != ' '){
            cout << "4";
            return false;
        }
        if (cin.get() != '<'){
            cout << "5";
            return false;
        }
        cin >> start;
        if (!cin.good()){
            cout << "6";
            return false;
        }
        if(cin.get() != ','){
            cout << "7";
            return false;
        }
        if(cin.get() != ' '){
            cout << "8";
            return false;
        }
        cin >> end;
        if (!cin.good()){
            cout << "9";
            return false;
        }
        if (cin.get() != '>'){
            cout << "10";
            return false;
        }
        if(cityCount < start) {
            cout << "11: Roadcount:" << roadCount << " start" << start << endl;
            return false;
        }
        if(cityCount < end){
            cout << "12: Roadcount:" << roadCount << " end" << end << endl;
            return false;
        }
        net->addRoad(start,end);
    }

    if(cin.get()!= '.'){
        cout << "13 cities:" << cityCount << " roads " << roadCount << endl;
        return false;
    }

    return true;
}



    ComunicationNetwork * NetworkCreator::getNetwork(){
        return net;
    }

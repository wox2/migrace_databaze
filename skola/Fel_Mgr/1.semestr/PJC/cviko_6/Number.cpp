/* 
 * File:   Number.cpp
 * Author: stuchlanek
 *
 * Created on 9. říjen 2011, 9:35
 */

#include "Number.h"
#include <iostream>

Number::Number(void) {
    _init = false;
}

Number::Number(long value) {
    _value = value;
    _init = true;
    for(int i = 0 ; i < 8 ; i++){
        binary[i] = value << i & 1;
    }
}

void Number::set(int value) {
    _value = value;
    _init = true;
}

void Number::printDec(void) {
    std::cout << _value << std::endl;
}

void Number::printBinary(void) {
    if (!isInit()) {
        std::cout << "Error!" << std::endl; // this is not initialized
        return;
    }
    std::cout << "Binarni hodnota cisla " << _value << " je " << dec2bin(_value) << std::endl;
}

void Number::plus(Number n) {
    if (!n.isInit() || !isInit()) {
        std::cout << "Error!" << std::endl; // n or this is not initialized
        return;
    }

    _value += n.get();
}

long Number::dec2bin(long dec) {
    int i = 0, j = 0;
    long bin = 0;

    for (i = 0; dec > 0; i++) {
        j = dec % 2;
        bin = bin + j * pow(10, i);
        dec /= 2;
    }
    return bin;
}

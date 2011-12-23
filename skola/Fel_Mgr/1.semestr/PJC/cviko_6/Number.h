/* 
 * File:   Number.h
 * Author: stuchlanek
 *
 * Created on 9. říjen 2011, 9:35
 */
#include <cmath>

#ifndef NUMBER_H
#define	NUMBER_H

class Number {
    
private:
    long _value; // actual value of this number
    bool _init; // value initialized or not
    int binary[sizeof(int) * 8];

    // conversion helper
    long dec2bin(long dec); 

public:
    // default constructor
    Number(void);
    
    // constructor with value
    Number(long value);
    
    // --- getters ---
    
    long get(void) {
        return _value;
    }

    bool isInit(void) {
        return _init;
    }
    
    // --- setters ---

    void set(int value);
    
    // --- other members ---
    
    void plus(Number n);
    void printDec(void);
    void printBinary(void);
};
#endif	/* NUMBER_H */

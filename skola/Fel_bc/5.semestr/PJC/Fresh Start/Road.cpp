/* 
 * File:   Road.cpp
 * Author: woxie
 * 
 * Created on 4. leden 2011, 15:25
 */

#include "Road.h"
#include <cstddef>
#include <iostream>
using namespace std;

Road::Road(int start, int end) {
    this->_end = end;
    this->_start = start;
}

Road::Road(const Road& orig) {
    if(this == &orig) {
        return;
    }
    this->_end = orig._end;
    this->_start = orig._start;
}

Road::~Road() {
}

void Road::print(){
    cout << '<' << _start << ", "<<_end << '>';
}

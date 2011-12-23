/* 
 * File:   SHandle.cpp
 * Author: woxie
 * 
 * Created on 23. říjen 2010, 15:17
 */

#include "SHandle.h"
#include <cstddef>
#include <iostream>


SHandle::SHandle(){
    item=SItem();
    next = this;
    konstr++;
    std::cout << konstr;
    std::cout <<". volani konstruktoru - Bezparametricky konstruktor" << std::endl;
}

SHandle::SHandle(SItem value) {
    item = SItem(value);
    next = this;
    konstr++;
    std::cout << konstr;
    std::cout << ". volani konstruktoru - konstruktor SHandle(SItem)" << std::endl;

}


SHandle::SHandle(const SHandle& orig) {
    item = SItem(orig.item);
    next = orig.getNext();
    konstr++;
    std::cout << konstr;
    std::cout << " volani konstruktoru - Kopirovaci konstruktor" << std::endl;
}

SHandle::~SHandle() {
    destr++;
    std::cout << destr;
    std::cout <<  ". volani destruktoru SHandle" << std::endl;
}

SItem SHandle::getItem() const{
    return item;
}

void SHandle::setItem(SItem sItem){
    item = sItem;
}

SHandle * SHandle::getNext()const{
    return next;
}
void SHandle::setNext(SHandle * nextHandle){
    next = nextHandle;
}

int SHandle::destr = 0;
int SHandle::konstr = 0;




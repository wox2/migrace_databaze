/* 
 * File:   SItem.cpp
 * Author: woxie
 * 
 * Created on 23. říjen 2010, 15:17
 */

#include "SItem.h"

SItem::SItem(){
    element = NULL;
}

SItem::SItem(int valElement) {
    element=valElement;
}

SItem::SItem(const SItem& orig) {
    element=orig.element;
}

SItem::~SItem() {
}

void SItem::setElement(int valElement){
    element = valElement;
}

int SItem::getElement() const{
    return element;
}

SItem SItem::operator =(const SItem& orig){
    this->element=orig.element;
	return *this;
}

bool SItem::equals(SItem& item){
    return this->element == item.element;
}


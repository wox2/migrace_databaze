/* 
 * File:   Node.cpp
 * Author: woxie
 * 
 * Created on 4. leden 2011, 0:16
 */

#include "City.h"

City::City(int id) {
    _id = id;
 }

City::City(const City& orig) {
    this->_id = orig._id;
}

City::~City() {
}

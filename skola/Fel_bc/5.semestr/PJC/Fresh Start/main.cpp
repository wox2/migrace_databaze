/* 
 * File:   main.cpp
 * Author: woxie
 *
 * Created on 4. leden 2011, 0:16
 */

#include <cstdlib>
#include <iostream>
#include "Algorithm.h"

using namespace std;

/*
 * 
 */



int main(int argc, char** argv) {
    Algorithm algo;
    algo.getDataAndCreateNetwork();
    
    algo.process();
    return 0;
}


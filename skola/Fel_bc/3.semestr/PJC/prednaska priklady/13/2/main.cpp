/* main.cpp */
#include <iostream>
#include <iomanip>
using namespace std;

#include "sqr.h"

int main ( int argc, char * argv [] )
 {
   double x;

   cin . clear ();
   cin >> x;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    } 
   cout << x << "^2 = " << sqr ( x ) << endl;
   return ( 0 );
 }

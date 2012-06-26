// Vypocet faktorialu Stirlingovym vzorcem (approx.)
#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

int main ( int argc, char * argv [] )
 {              
   int    n;
   double fact;

   cout << "Zadej n:" << endl;
   cin . clear ();
   cin >> n;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   fact = pow ( (double)n, (double)n ) / exp ( n ) * sqrt ( 2 * M_PI * n );
   cout << n << "! = " << setw ( 20 ) << fact << endl;
   return ( 0 ); 
 }

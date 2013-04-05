// Vypocet faktorialu iteraci
#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {              
   int i, n, fact = 1;

   cout << "Zadej n:" << endl;

   cin . clear ();
   cin >> n;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   for ( i = 1; i <= n; i ++) 
    fact *= i;
   
   cout << n << "! = " << fact << endl;
   return ( 0 ); 
 }

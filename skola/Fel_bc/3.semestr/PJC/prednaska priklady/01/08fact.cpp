// Vypocet faktorialu rekurzi
#include <iostream>
#include <iomanip>
using namespace std;

static int fact ( int n )
 {
   return ( n > 1 ? n * fact ( n - 1 ) : 1 );
 }

int main ( int argc, char * argv [] )
 {              
   int n;

   cout << "Zadej n:" << endl;

   cin . clear ();
   cin >> n;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   cout << n << "! = " << fact ( n ) << endl;
   return ( 0 ); 
 }

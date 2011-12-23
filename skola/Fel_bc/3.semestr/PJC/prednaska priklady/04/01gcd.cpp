#include <iostream>
#include <iomanip>
using namespace std;


unsigned int gcd ( unsigned int a, unsigned int b )
 {
   while ( a != b )
    if ( a > b ) 
      a -= b;
     else 
      b -= a;
   return ( a );
 }

unsigned int scm ( unsigned int a, unsigned int b )
 {
   return ( a * b / gcd ( a, b ) );
 }
 
int main ( int argc, char * argv [] )
 {
   unsigned int a, b;
   
   cout << "Zadej dve kladna cisla" << endl;
   
   cin . clear ();
   cin >> a >> b;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   if ( !a || !b )
    {
      cout << "Cisla nejsou kladna !" << endl;
      return 1;
    }

   cout << "NSD (" << a << ", " << b << ") = " << gcd ( a, b ) << endl;
   cout << "NSN (" << a << ", " << b << ") = " << scm ( a, b ) << endl;

   return ( 0 );
 }

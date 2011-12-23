#include <iostream>
#include <iomanip>
using namespace std;

void foo ( double a, int b = 10 ) { }
void foo ( long int a )           { }


int main ( int argc, char * argv[] )
 {
   foo ( 12 );   // chyba - nejednoznacne
   foo ( 'z' );  // chyba - nejednoznacne
   foo ( 15.3 ); // foo ( double, int )
   foo ( 3.0f ); // foo ( double, int )

   return ( 0 );
 }

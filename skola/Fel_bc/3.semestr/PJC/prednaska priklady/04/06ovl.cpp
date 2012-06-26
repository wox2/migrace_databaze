#include <iostream>
#include <iomanip>
using namespace std;

void foo ( int a, double b ) { }
void foo ( double a, int b ) { }
void bar ( float a )         { }
void bar ( long double b )   { }


int main ( int argc, char * argv[] )
 {
   foo ( 1,2 );      // chyba
   foo ( 2.0, 3.0 ); // chyba
   foo ( 'a', 5 );   // chyba
   
   bar ( 10 );       // chyba
   bar ( 12.0 );     // chyba
   bar ( 12.0f );    // ok, bar ( float )

   return ( 0 );
 }

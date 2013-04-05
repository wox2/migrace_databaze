#include <iostream>
#include <iomanip>
using namespace std;


int a = 10;      // globalni prom inic. na 10
int b;           // globalni prom inic. na 0
int main ( int argc, char * argv [] )
 {
   int c;        // lok prom, není inic.
   static int d; // staticky alokovana, inic. na 0

   for ( int i = 0; i < 10; i ++ )
    {
      int e = 20;// lok. prom s inicializaci 
      d = d + e;
      b ++; e++;
    }
   cout << d << " " << c << " " << b << endl;
   return ( 0 );
 }

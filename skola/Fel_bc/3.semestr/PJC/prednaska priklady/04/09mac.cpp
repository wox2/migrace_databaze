#include <iostream>
#include <iomanip>
using namespace std;


#define MAX      100 ;   // pozor, zde je ;
#define SQR1(X)  X*X
#define SQR2(X)  (X)*(X)
#define SQR3(X)  ((X)*(X))
#define MAX2(X,Y) ((X)>(Y)?(X):(Y))

int main ( int argc, char * argv[] )
 {
   int a, b, c, d, i, m;


   a = MAX + 200;     // a = 100, syntaxe OK
   b = SQR1(10+10);   // b = 120 ne 400
   c = ~ SQR2(10);    // c = -110 ne -101
   d = ~ SQR3(10+10); // ok, d = -401
   i = 10;
   m = MAX2(i++,5);   // m = 11, i = 12

   cout << "a = " << a << endl;
   cout << "b = " << b << endl;
   cout << "c = " << c << endl;
   cout << "d = " << d << endl;
   cout << "i = " << i << endl;
   cout << "m = " << m << endl;

   return ( 0 );
 }

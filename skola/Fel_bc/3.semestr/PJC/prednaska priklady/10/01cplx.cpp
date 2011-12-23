#include <iostream>
#include <iomanip>
#include <cmath>
#include <cstring>
using namespace std;

struct TCplx 
 { 
   double re, im; 
 };

TCplx operator + ( TCplx a, TCplx b )
 {
   TCplx c;
   c . re = a . re + b . re;
   c . im = a . im + b . im;
   return c;
 }



int main ( int argc, char * argv [] )
 {
   TCplx x = { 1, 0 }, y = { 2, 1 }, z;
   z = x + y;
   z = operator + ( x, y ); // jiny zapis volani op.
   
   return ( 0 );  
 }

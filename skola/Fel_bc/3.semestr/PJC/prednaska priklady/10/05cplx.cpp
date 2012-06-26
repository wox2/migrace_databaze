#include <iostream>
#include <iomanip>
#include <cmath>
#include <cstring>
using namespace std;

class CCplx
 {
    double re, im;
   public:
    CCplx ( double r = 0, double i = 0 ) : re(r), im(i) {}
    CCplx operator - ( void ) const;
    CCplx operator + ( const CCplx & x ) const;  
    friend CCplx operator + ( double a, const CCplx & b );
    
 };

CCplx CCplx::operator - ( void ) const
 { // unarni minus – 0 parametru
   return ( CCplx ( -re, -im ) );
 }
 
CCplx CCplx::operator + ( const CCplx & x ) const
 { // binarni plus – 1 parametr
   return ( CCplx ( re + x. re, im + x . im ) );
 }

CCplx operator + ( double a, const CCplx & x )
 { // binarni plus funkci – 2 parametry
   return ( CCplx ( a + x. re, x . im ) );
 }

int main ( int argc, char * argv [] )
 {
   CCplx a (3, 4), b (2);
   
   CCplx c = a + b; 
     // c = a . operator + ( b );
   
   CCplx d = a + 4; 
     // ok, d = a . operator + ( CCplx ( 4 ) );
   
   CCplx e = 5 + a; 
     // ok, firend funkce

   return ( 0 );  
 }

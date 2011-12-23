// Trida pro reprezentaci desetinnych cisel
#include <iostream>
#include <iomanip>
using namespace std;

class CRat 
 {
   public:
    CRat ( int n = 0, int d = 1 ) 
     { num = n; den = d; simplify (); }
    friend CRat operator+ ( const CRat & a, 
                            const CRat & b );
    friend ostream & operator << ( ostream & os,
                                   const CRat & x );
   private:
    int num, den;
    static int gcd ( int a, int b );
    void simplify ( void )
     { int cd = gcd ( abs (num), abs (den) );
       num /= cd; den /= cd; }
 };
 
CRat operator+ ( const CRat & a, const CRat & b )
 {
   CRat r ( a . num * b . den + a . den * b . num,
            a . den * b . den );
   r . simplify ();
   return r;
 }

ostream & operator << ( ostream &os, const CRat &x )
 {
   os << "(" << x . num << "/" << x . den << ")";
   return os;
 }

int CRat::gcd ( int a, int b )
 {
   if ( !a || !b ) return 1;
   while ( a != b ) 
    if ( a > b )  a -= b; else b -= a;
   return a; 
 }

int main ( int argc, char * argv [] )
 {
   CRat a ( 6, 23 ), b ( 8, 15 );
   CRat c;

   c = a + b + 8;

   cout << a << " + " << b << " + 8 = " << c << endl;
   return ( 0 );
 }

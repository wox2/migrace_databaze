#include <iostream>
#include <iomanip>
using namespace std;


//-------------------------------------------------------------------------------------------------
double             power1                        ( double a, unsigned int n )
 {
   double        res = 1;
   unsigned int  i;
   
   for ( i = 0; i < n; i ++ )
    res = res * a;
    
   return ( res );
 }
//-------------------------------------------------------------------------------------------------
double             power2                        ( double a, unsigned int n )
 {
   double        res = 1;
   unsigned int  i;
   
   for ( i = 1; i <= n && i; i <<= 1 )
    {
      if ( n & i )
       res = res * a;
       
      a *= a;
    } 
    
   return ( res );
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   double         a;
   unsigned int   n;
   
   cout << "Zadej zaklad a exponent" << endl;
   cin . clear ();
   cin >> a >> n;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }   
   
   cout << setprecision ( 10 ) << a << "^" << n << " = " << power1 ( a, n ) << endl;
   cout << setprecision ( 10 ) << a << "^" << n << " = " << power2 ( a, n ) << endl;
  
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

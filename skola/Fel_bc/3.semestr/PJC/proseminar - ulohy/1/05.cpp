#include <iostream>
#include <iomanip>
#include <cmath>
#include <ctime>
using namespace std;


//-------------------------------------------------------------------------------------------------
static int         isPrime0                      ( int a )
 {
   int i, res;
   
   if ( a < 2 ) return ( 0 );
   res = 1;
   
   for ( i = 2; i < a; i ++ )
    if ( a % i == 0 )
     res = 0;
     
   return ( res );
 }
//-------------------------------------------------------------------------------------------------
static int         isPrime1                      ( int a )
 {
   int i;
   
   if ( a < 2 ) return ( 0 );
   for ( i = 2; i < a; i ++ )
    if ( a % i == 0 )
     return ( 0 );
     
   return ( 1 );
 }
//-------------------------------------------------------------------------------------------------
static int         isPrime2                      ( int a )
 {
   int i, max;
   
   if ( a < 2 ) return ( 0 );
   max = (int) sqrt ( a );
   
   for ( i = 2; i <= max; i ++ )
    if ( a % i == 0 )
     return ( 0 );
     
   return ( 1 );
 }
//-------------------------------------------------------------------------------------------------
static int         isPrime3                      ( int a )
 {
   int i, max;

   if ( a < 2 ) return ( 0 );
   if ( a == 2 ) return ( 1 );
   if ( a % 2 == 0 ) return ( 0 );
   
   max = (int) sqrt ( a );
   
   for ( i = 3; i <= max; i += 2 )
    if ( a % i == 0 )
     return ( 0 );
     
   return ( 1 );
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   unsigned int   n, i, primes;
   clock_t        start;
   
   cout << "Zadej cislo" << endl;
   cin . clear ();
   cin >> n;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }   
   
   cout << n << " " << ( isPrime0 ( n ) ? "je" : "neni" ) << " prvocislo" << endl;
   
   i = n + 1;
   while ( 1 ) 
    {
      if ( isPrime3 ( i ) ) 
       {
         cout << "Nejblizsi vyssi prvocislo je: " << i << endl;
         break;
       } 
      i ++; 
    }  

   cout << "Odhad poctu prvocisel < " << n <<": " << (int)(n / log ( n )) << endl;

   start  = clock ();
   primes = 0; 
   for ( i = 2; i < n;  i ++ )
    if ( isPrime3 ( i ) )
     primes ++;
     
   
   
   cout << "Pocet prvocisel < " << n << ": " << primes << endl;  
   

   start = clock () - start;
   cout << "Cas: " << start << " cyklu = " << (double)start / CLOCKS_PER_SEC << " s" << endl;

   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

#include <iostream>
#include <iomanip>
using namespace std;





//-------------------------------------------------------------------------------------------------
static void        eratosthenes0                 ( int limit )
 {
   bool    * sieve;
   int       i, j;
    
   sieve = new bool [limit];
   
   for ( i =0; i < limit; i ++ )  
    sieve[i] = true;
    
   sieve[0] = sieve[1] = false; // not primes
   
   for ( i = 2; i < limit; i ++ )
    if ( sieve[i] )
     {
       cout << " " << i;
       
       for ( j = 2 * i; j < limit; j += i )
        sieve[j] = false;
     } 
     
   cout << endl;
   delete [] sieve;
 }         
//-------------------------------------------------------------------------------------------------
static void        eratosthenes1                 ( int limit )
 {
   int     * sieve;
   int       i, j, max;
   const     int INT_SHIFT = 5;
   const     int INT_BITS  = (1 << INT_SHIFT ); // also defined in <climits>
   const     int INT_MASK  = INT_BITS - 1; // also defined in <climits>

   
   max = ( limit + INT_BITS - 1 ) >> INT_SHIFT;  
    
   sieve = new int [max];
   
   for ( i = 0; i < max; i ++ )  
    sieve[i] = -1;
    
   sieve[0] &= ~ 3; // 0 and 1 are not primes
   
   for ( i = 2; i < limit; i ++ )
    if ( sieve[i >> INT_SHIFT] & (1 << ( i & INT_MASK )) )
     {
       cout << " " << i;
       
       for ( j = 2 * i; j < limit; j += i )
        sieve[j >> INT_SHIFT] &= ~ ( 1 << ( j & INT_MASK ) );
     } 
     
   cout << endl;
   delete [] sieve;
 }         
//-------------------------------------------------------------------------------------------------
static void        eratosthenes2                 ( int limit )
 {
   int     * sieve;
   int       i, j, max, num;
   const     int INT_SHIFT = 5;
   const     int INT_BITS  = (1 << INT_SHIFT ); // also defined in <climits>
   const     int INT_MASK  = INT_BITS - 1; // also defined in <climits>

   
   max = ( limit / 2 + INT_BITS - 1 ) >> INT_SHIFT;  
    
   sieve = new int [max];
   
   for ( i = 0; i < max; i ++ )  
    sieve[i] = -1;
    
   sieve[0] &= ~ 1; // 0 and 1 are not primes

   cout << 2;
   
   for ( i = 0; i < limit/2; i ++ )
    if ( sieve[i >> INT_SHIFT] & (1 << ( i & INT_MASK )) )
     {
       num = 2 * i + 1;
       cout << " " << num;
       
       for ( j = num * num; j < limit; j += 2 * num )
        sieve[j/2 >> INT_SHIFT] &= ~ ( 1 << ( j/2 & INT_MASK ) );
     } 
     
   cout << endl;
   delete [] sieve;
 }         
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   int n;
   
   cout << "Zadej limit" << endl;
   cin . clear ();
   cin >> n;
   if ( ! cin . good () || n < 2 ) 
    {
      cout << "Chybny vstup" << endl;
      return ( 1 );
    }
   
   eratosthenes2 ( n );
   
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

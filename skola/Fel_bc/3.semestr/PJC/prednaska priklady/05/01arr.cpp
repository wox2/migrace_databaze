#include <iostream>
#include <iomanip>
using namespace std;


 
int main ( int argc, char * argv [] )
 {
   const int MAX = 100;
   int i, n;
   int pole [MAX];

   cin . clear ();
   cin >> n; 
   if ( ! cin .good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   if ( n > MAX ) 
    {
      cout << "Chyba - max. delka je " << MAX << endl;
      return 1;
    }
    
   for ( i = 0; i < n; i ++ )
    {
      while ( 1 )
       {
         cin . clear ();
         cin >> pole[i];
         if ( cin .good () ) break; // inner loop
         cout << "Chyba formatu - opakuj zadani" << endl;
       }  
    }  
   
   cout << "pozpatku: " << endl; 
   for ( i = n-1; i >= 0; i -- ) 
    cout << pole[i] << endl;
 
   return ( 0 );
 }

#include <iostream>
#include <iomanip>
using namespace std;


 
int main ( int argc, char * argv [] )
 {
   int i, n;
   cin >> n; 
   int pole[n];                // !!
   
   for ( i = 0; i <= n; i ++ ) // !!
    cin >> pole[i];
   
   cout << "pozpatku: " << endl; 
   for ( i = n; i >= 0; i -- ) // !! znovu
    cout << pole[i] << endl;
 
 // int kopie[n];               // !! znovu
 // kopie = pole;               // !! syntax
   return ( 0 );
 }

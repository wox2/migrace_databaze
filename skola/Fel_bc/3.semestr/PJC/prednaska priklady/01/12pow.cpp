// Celociselne mocneni ("logaritmicky" algoritmus)
#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

int main ( int argc, char * argv [] )
 {              
   int    n, i;
   double a, res = 1;

   cout << "Zadej a, n:" << endl;
   cin . clear ();
   cin >> a >> n;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   cout << a << "^" << n << " = ";
   for ( i = 1; i < n; i <<= 1 )
    {
      if ( n & i ) res *= a;
      a *= a;
    }
   cout << res << endl;
   return ( 0 ); 
 }

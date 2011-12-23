// Celociselne mocneni ("linearni" algoritmus)
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
   
   for ( i = 0; i < n; i ++ )
    res *= a;
   
   cout << a << "^" << n << " = " << res << endl;
   return ( 0 ); 
 }

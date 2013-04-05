#include <iostream>
#include <iomanip>
using namespace std;

void timeToHMS ( int t, int H, int M, int S ) // !!
 {
   H = t / 3600;
   M = ( t / 60 ) % 60;
   S = t % 60;
 }

int main ( int argc, char * argv[] )
 {
   int t, h, m, s;
   
   cout << "Zadej pocet sekund" << endl;

   cin . clear ();
   cin >> t;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }

   timeToHMS ( t, h, m, s );
   cout << h << ":" << m << ":" << s <<endl;
   return ( 0 );
 }

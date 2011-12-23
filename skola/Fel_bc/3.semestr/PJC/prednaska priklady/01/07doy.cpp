// Vypocet poctu dni od zacatku roku
#include <iostream>  
#include <iomanip>   
using namespace std; 
int main ( int argc, char * argv [] )
 {
   int  d, m, y, sum;

   cout << "Zadej den, mesic a rok" << endl;

   cin . clear ();
   cin >> d >> m >> y;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   sum = d + 31 * (m-1) - 10 * m / 23 - 2 * ( m > 2) + 
         ((m > 2) && (! ( y % 4 ) && ( y % 100) || !( y % 400 )));
   cout << d << "." << m <<  " byl " << sum << "-ty den" 
        " v roce " << y << endl;
   return ( 1 );
 }

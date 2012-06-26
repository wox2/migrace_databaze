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

   switch ( m )
    {
      case 1: sum  = d;       break;
      case 2: sum  = 31  + d; break;
      case 3: sum  = 59  + d; break;
      case 4: sum  = 80  + d; break;
      case 5: sum  = 120 + d; break;
      case 6: sum  = 151 + d; break;
      case 7: sum  = 181 + d; break;
      case 8: sum  = 212 + d; break;
      case 9: sum  = 242 + d; break;
      case 10: sum = 273 + d; break;
      case 11: sum = 303 + d; break;
      case 12: sum = 334 + d; break;
      default: cout << "Spatny mesic" << endl; return 1;
    }
   if ( m > 2 && ( y % 4 == 0 && y % 100 != 0 || y % 400 == 0 )) sum ++;
   cout << d << "." << m << " byl " << sum << "-ty den" 
        " v roce " << y << endl;
   return ( 1 );
 }

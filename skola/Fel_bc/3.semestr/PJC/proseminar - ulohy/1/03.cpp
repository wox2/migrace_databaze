#include <iostream>
#include <iomanip>
using namespace std;


//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] ) 
 {
   int d, m, y, valid;
   
   cout << "Zadej den mesic a rok" << endl;
   cin . clear ();
   cin  >> d >> m >> y;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
    
   switch ( m )
    {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
       valid = d >= 1 && d <= 31;
       break;
      
      case 4:
      case 6:
      case 11:
       valid = d >= 1 && d <= 30;
       break;
      
      case 2:
       if ( y <= 1752 )
         valid = d >= 1 && d <= ((y % 4 == 0 ) ? 29 : 28 );
        else
         valid = d >= 1 && d <= ((y % 4 == 0 && y % 100 != 0 || y % 400 == 0 ) ? 29 : 28 ); 
       break;
       
      case 9:
       if ( y == 1752 )
         valid = (d >= 1 && d <= 2) || ( d >= 14 && d <= 30 );
        else
         valid = d >= 1 && d <= 30;
       break;  
      default:
       valid = 0;
       break; 
    }
   
   cout << "Datum: " << d << "." << setw(2) << setfill ( '0' ) << m << "." << setw(4) << setfill ( '0' ) 
        << y << " " << ( valid ? "je platne" : "neni platne" ) << endl; 
   
   return ( 0 );
 } 
//-------------------------------------------------------------------------------------------------

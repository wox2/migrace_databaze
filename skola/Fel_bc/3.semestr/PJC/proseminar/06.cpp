#include <iostream>
#include <iomanip>
#include <sstream>
#include <cmath>

using namespace std;


bool parseRadians ( string & s, double & res )
 {
   char dummy, mark;
   istringstream is ( s );
   
   is >> res;
   
   if ( is . fail () ) return ( false );
   if ( is . eof () ) return ( true );
   
   is >> mark;
   
   if ( is . eof () ) return ( true );
   if ( is . fail () ) return ( false );
   if ( mark != 'r' ) return ( false );
   
   is >> dummy;
   if ( is . eof ()) return ( true );
   
   return ( false );
 }

bool parseDegrees ( string & s, double & res )
 {
   char c1,c2,c3, dummy;
   int deg, min = 0;
   double  sec = 0;
   istringstream is ( s );
   
      
   is >> deg >> c1 ;
   if ( is . fail () || c1 != 's' ) return ( false );
   is >> min;
   if ( is . good () )
    {
      is >> c2;
      if ( ! is . good () || c2 != 'm' ) return ( false );
      is >> sec ;
      if ( is . good () )
       {
         is >> c3;
         if ( ! is . good () || c3 != 's' ) return ( false );
       }
    } 
/* 
   is >> deg >> c1 >> min >> c2 >> sec >> c3;    
   if ( is . fail () || c1 != 's' || c2 != 'm' || c3 != 's' ) return ( false );
*/   
   res = ( deg + min / 60.0 + sec / 3600 ) * M_PI / 180;
   
   
   if ( is . eof () ) return ( true );
   is >> dummy;
   if ( is . eof () ) return ( true );
   return ( false );
 }


int main ( int argc, char * argv [] )
 {
   int n, cnt;
   double angle, sum = 0;
   string s;
   
   cout << "Zadejte pocet vrcholu:" << endl;
   
   while ( 1 )
    {
      cin >> n;
      if ( ! cin . good () )
       {
         cin . clear ();
         cin . ignore ( INT_MAX, '\n' );
         cout << "Zadejte cele cislo." << endl;
       }             
      else if ( n < 3 )
       {
         cout << "Cislo musi byt alespon 3." << endl;
       }
      else 
       break;
    }
   cin . ignore ( INT_MAX, '\n' );   
   cnt = n;
   while ( cnt )
    {
      if ( ! getline ( cin, s ) )
       {
         cout << "Malo vstupnich udaju." << endl;
         return ( 1 );
       }
      
      if ( parseRadians ( s, angle ) )
        {
          sum += angle;
          cnt --;
        }
       else if ( parseDegrees ( s, angle ) )
        {
          sum += angle;
          cnt --;
        }
       else
        {
          cout << "Nespravny vstup. Zadejte prosim znovu." << endl; 
        }
    }
        
   if ( fabs ( sum -  ( n - 2 ) * M_PI ) < 1e-8 )
     cout << "Soucet uhlu souhlasi." << endl;
    else
     cout << "Soucet uhlu nesouhlasi (o "<< scientific << fabs ( sum - (n-2) * M_PI  ) << ")." << endl;
  
   return ( 0 );
 }

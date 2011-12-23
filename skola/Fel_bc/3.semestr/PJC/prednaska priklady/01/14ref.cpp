// Prevod casu na hodiny/minuty/sekundy
#include <iostream>
#include <iomanip>
using namespace std;

void  TimetoHMS ( int Time, int & H, int & M, int & S )
 {
   H = Time / 3600;
   M = ( Time / 60 ) % 60;
   S = Time % 60;
 }
 
int main ( int argc, char * argv [] )
 {              
   int t, h, m, s;
   
   cout << "Zadej cas v sec" << endl;
   cin . clear ();
   cin >> t;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   TimetoHMS ( t, h, m, s );
   cout << t << "=" << h << ":" << m << ":" << s << endl;
  
   return ( 0 );
 } 

#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {
   unsigned int i;
   
   cout << "Zadej pocet" << endl;
   cin . clear (); 
   cin >> i;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   while ( i >= 0 )     // !! always true
    {      
      cout << "Hi" << endl;
      i --;
    } 
              
   return ( 0 );
 }

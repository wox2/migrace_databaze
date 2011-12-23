#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {
   char x;             
   
   cout << "Zadej znak" << endl;
   cin . clear (); 
   cin >> x;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   if ( x = 'a' ) // !! ==
    { 
      cout << "Zadanu znak je 'a': " << x << endl;
    } 
  
   if ( x = 'b' ) // !! ==
    { 
      cout << "Zadanu znak je 'b': " << x << endl;
    } 


   return ( 0 );
 }

#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {
   int x;
              
   cout << "Zadej cislo" << endl;           
   cin . clear (); 
   cin >> x;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }
   
   switch ( x )
    {
      case 0:
       cout << "varianta 0" << endl;    // !!
      case 1:
       cout << "varianta 1" << endl;    // !!
      default:
       cout << "jina varianta" << endl; // !!
    }
   return ( 0 );
 }

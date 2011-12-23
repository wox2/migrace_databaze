// Test, zda je rok prestupny
#include <iostream>  
#include <iomanip>   
using namespace std; 

int main ( int argc, char * argv [] )
 {
   int  y;

   cout << "Zadej rok" << endl;
   cin . clear ();
   cin >> y;
   if ( !cin . good () )
    {
      cout << "Chyba formatu" << endl;
      return ( 1 );
    }

   cout << "Rok " << y << ((y % 4==0 && (y % 100!=0
        || y % 400==0)) ? " je" : " neni") <<
        " prestupny" << endl;      
   return ( 0 );
 }

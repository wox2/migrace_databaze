#include <iostream>
#include <iomanip>
#include <cstdlib>      // malloc/free
using namespace std;


 
int main ( int argc, char * argv [] )
 {
   int i, cnt, *data;
   do 
    {
      cout << "Zadej pocet prvku" << endl;
      cin . clear ();
      cin >> cnt;
      if ( ! cin .good () )
       {
         cout << "Chyba formatu" << endl;
         return ( 1 );
       }
    } while ( cnt <= 0 );

   data = (int*) malloc ( cnt * sizeof ( *data ) );
   for ( i = 0; i < cnt; i ++ )
    {
      while ( 1 )
       {
         cin . clear ();
         cin >> data[i];
         if ( cin .good () ) break; // inner loop
         cout << "Chyba formatu - opakuj zadani" << endl;
       }  
    }  

   cout << "Reverzovane:" << endl;
   for ( i = cnt - 1; i >= 0; i-- )
    cout << data[i] << endl;
   free ( data );
   return ( 0 );
 } 

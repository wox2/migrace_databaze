#include <iostream>
#include <iomanip>
using namespace std;



int main ( int argc, char * argv[] )
 {
   int i;
   
   if ( argc  )
     cout << "Jmeno programu: " << argv[0] << endl;
    else
     {              
       cout << "no args !!" << endl; 
       return ( 1 );
     } 
   
   for ( i = 1; i < argc; i ++ )
    cout << "Parametr " << i << " = " << argv[i] << endl;

   return ( 0 );
 }

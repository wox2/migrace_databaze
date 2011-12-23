#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {
   int i;
   
   for ( i = 0; i < 10; i ++ )
    cout << i << endl;         
    
   for ( i = 9; i >= 0; i -- )
    cout << i << endl;         
    
   for ( i = 9; i >= 0; i ++ ) // !!
    cout << i << endl;                       
 
   return ( 0 );
 }

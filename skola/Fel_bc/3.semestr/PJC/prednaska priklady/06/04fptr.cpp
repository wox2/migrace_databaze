#include <iostream>
#include <iomanip>
using namespace std;


int max ( int param, int item ) 
 { 
   return ( item > param ? item : param ); 
 }

int sum ( int param, int item ) 
 { 
   return param + item; 
 }

int prod ( int param, int item ) 
 { 
   return param * item; 
 }


int injectInto ( const int * array, int arrayLen, 
                 int (*func)( int, int ), int start )
 {
   int res = start, i;
   for ( i =0; i < arrayLen; i ++ ) 
    res = func ( res, *array ++ );
   return ( res );
 }
 
int main ( int argc, char * argv [] )
 {
   int arr [5] = { 1, 2, 3, 4, 5 };
   
   cout << "Maximum: " << 
           injectInto ( arr, 5, max, arr[0] ) << endl;
   
   cout << "Soucet: " << 
           injectInto ( arr, 5, sum, 0 ) << endl;
   
   cout << "Soucin: " << 
           injectInto ( arr, 5, prod, 1 ) << endl;
   
   return ( 0 );
 } 

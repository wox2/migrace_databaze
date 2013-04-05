#include <iostream>
#include <iomanip>
using namespace std;

enum EDays { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY };


int main ( int argc, char * argv )
 {
   EDays a;

   a = MONDAY;
   cout << a << endl; // zobrazi 1
   
   cout << (a + 1)  << endl; // zobrazi 2
   return ( 1 );
 }  

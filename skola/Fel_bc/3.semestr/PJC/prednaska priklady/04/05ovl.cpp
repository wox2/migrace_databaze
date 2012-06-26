#include <iostream>
#include <iomanip>
using namespace std;

int abs ( int x )
 { return ( x >= 0 ? x : -x ); }

double abs ( double x )
 { return ( x >= 0 ? x : -x ); }


int main ( int argc, char * argv[] )
 {
   cout << abs ( - 10 ) << " " << abs ( - 10.5 ) <<endl;
   return ( 0 );
 }

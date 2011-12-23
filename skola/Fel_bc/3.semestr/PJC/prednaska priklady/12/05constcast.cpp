#include <iostream>
#include <iomanip>
using namespace std;

class A 
 {  
   public:
    operator int ( void ) { return 0; }  
 };
 
class B : public A 
 { 
   public:
     B ( int x ) { } 
 };

class C : public A 
 { 
  
 };

int main ( int argc, char * argv []  )
 {
   int i1 = 10;
   const int *iptr = &i1;
   int * i3 = const_cast<int *> ( iptr ); 
   
   return ( 0 );
 }

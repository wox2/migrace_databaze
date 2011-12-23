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
   B t1   = reinterpret_cast<B> ( 4 );
   int i1 = reinterpret_cast<int> ( t1 );
   i1     = reinterpret_cast<int> ( 25.89 );
   A * t2 = reinterpret_cast<A*> ( &t1 );
   B * t3 = reinterpret_cast<B*> ( t2 );
   
   
   const int i2 = 10, *iptr = &i2;
   int * i3 = reinterpret_cast<int *> ( iptr ); 
   
   C * cptr = new C;
   B * bptr = reinterpret_cast<B*> ( cptr ); 
   
   char * c = reinterpret_cast<char*> ( &i1 );

   return ( 0 );
 }

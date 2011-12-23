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
   B t1   = static_cast<B> ( 4 );
   int i1 = static_cast<int> ( t1 );
   i1     = static_cast<int> ( 25.89 );
   A * t2 = static_cast<A*> ( &t1 );
   B * t3 = static_cast<B*> ( t2 );
   
   
   const int *iptr = &i1;
   int * i3 = static_cast<int *> ( iptr );  // chyba
   
   C * cptr = new C;
   B * bptr = static_cast<B*> ( cptr );     // chyba
   
   char * c = static_cast<char*> ( &i1 );   // chyba

   return ( 0 );
 }

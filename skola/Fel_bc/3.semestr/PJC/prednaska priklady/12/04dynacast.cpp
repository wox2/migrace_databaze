#include <iostream>
#include <iomanip>
using namespace std;

class A 
 {  
   public:
    operator int ( void ) { return 0; }  
    virtual ~A ( void ) { } 
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
   A a,        * aptr = &a, *ta;
   B b ( 10 ), * bptr = &b, *tb;
   
   ta = static_cast<A*> ( bptr );
   tb = static_cast<B*> ( aptr );
   cout << ta << "  " << tb << endl; // ale tb nebude pracovat spravne
   
   ta = dynamic_cast<A*> ( bptr );
   tb = dynamic_cast<B*> ( aptr );
   cout << ta << "  " << tb << endl; // tb je NULL

   return ( 0 );
 }

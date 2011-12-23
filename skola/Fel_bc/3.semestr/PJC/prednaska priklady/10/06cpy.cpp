#include <iostream>
#include <iomanip>
#include <cmath>
#include <cstring>
using namespace std;

class CStr 
 { 
    char * str;
    int    len;
   public:
                 CStr   ( const char * str = "" )
     { 
       len         = strlen ( str );
       this -> str = new char [len + 1];
       strncpy     ( this -> str, str, len + 1 );
     }

                 CStr   ( const CStr & src )
     { 
       len     = src . len;
       str     = new char [len + 1];
       strncpy ( this -> str, src . str, len + 1 );
     }
 
                ~CStr   ( void ) 
     { delete [] str; }

     
    void         SetChar ( int idx, char c )
     { if ( idx >= 0 && idx < len ) str[idx] = c; }
     
    friend ostream & operator << ( ostream & os, const CStr & x );  
 };

ostream & operator << ( ostream & os, const CStr & x )
 { 
   os << x . str;
   return ( os );
 } 
 
int main ( int argc, char * argv [] )
 {
   CStr a ( "test" ), b = a;

   a . SetChar ( 2, 'z' );
   b . SetChar ( 3, 'u' );
   cout << a  << " " << b  << endl;    

   a = b;                          // chyba
   b . SetChar ( 3, 'o' );
   cout << a  << " " << b  << endl;      

   return ( 0 );  
 }

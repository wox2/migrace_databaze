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

    CStr ( const char * str, int len )
     {
       this -> len = len;
       this -> str = new char [len+1];
       strncpy ( this -> str, str, len + 1 );
       this -> str [len] = 0;
     }
     
 
                ~CStr   ( void ) 
     { delete [] str; }

     
    void         SetChar ( int idx, char c )
     { if ( idx >= 0 && idx < len ) str[idx] = c; }
     
    CStr &       operator= ( const CStr & src )
     {
       if ( this != &src )
        {
          delete [] str;
          len     = src . len;
          str     = new char [len + 1];
          strncpy ( str, src . str, len + 1 );
        }  
       return *this;      
     }
     
    char & operator [] ( int idx ) 
     {
       if ( idx < 0 || idx >= len ) throw "mimo meze";
       return str[idx];
     }     
    
    CStr   operator () ( int from, int to ) const
     {
       if ( from > to || from < 0 || to >= len ) throw "mimo meze";
       return ( CStr ( str + from, to - from ) );
     }
    
    
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
     
   a = b;
   b . SetChar ( 3, 'o' );           // ok
   cout << a  << " " << b  << endl;      
     
   a = a;                            // ok
   a[0] = b[1];
   cout << a  << " " << b  << endl;      

   a = "Test dlouheho retezce";
   CStr c = a(3, 10); // "t dlouh"
   cout << c << endl;
   
     
   return ( 0 );  
 }

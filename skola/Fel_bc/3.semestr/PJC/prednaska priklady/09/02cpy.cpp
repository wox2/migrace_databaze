#include <iostream>
#include <iomanip>
#include <cmath>
#include <cstring>
using namespace std;

class CFoo 
 { 
    char * str;
    int    len;
   public:
                 CFoo   ( const char * str = "" )
     { 
       len         = strlen ( str );
       this -> str = new char [len + 1];
       strncpy     ( this -> str, str, len + 1 );
     }

                 CFoo   ( const CFoo & src )
     { 
       len     = src . len;
       str     = new char [len + 1];
       strncpy ( this -> str, src . str, len + 1 );
     }
 
                ~CFoo   ( void ) 
     { delete [] str; }

    const char * GetStr ( void ) const   
     { return str; }                        
     
    void         SetChar ( int idx, char c )
     { if ( idx >= 0 && idx < len ) str[idx] = c; }
 };
 
int main ( int argc, char * argv [] )
 {
   CFoo a ( "test" ), b ( "test" );

   a . SetChar ( 2, 'z' );
   b . SetChar ( 3, 'u' );
   cout << a . GetStr () << " " << b . GetStr () << endl;    


   CFoo c ( "test" ), d = c;        // !!

   c . SetChar ( 2, 'z' );
   d . SetChar ( 3, 'u' );
   cout << c . GetStr () << " " << d . GetStr () << endl;    

   return ( 0 );  
 }

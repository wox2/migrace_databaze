#include <iostream>
#include <iomanip>
using namespace std;



//-------------------------------------------------------------------------------------------------
// Reseni s referencemi
//-------------------------------------------------------------------------------------------------
static int         parseNum                      ( const char * & src, int & dst )
 {
   dst = 0;
   
   if ( ! isdigit ( *src ) ) return ( 0 );
   
   while ( isdigit ( *src ) )
    {
      dst = 10 * dst + * src - '0';
      src ++;
    }
    
   return ( 1 );
 }
//-------------------------------------------------------------------------------------------------
static int         parseHMS                      ( const char * src, int & H, int & M, int & S )
 {
   if ( ! parseNum ( src, H ) ) return ( 0 ); // failed
   
   if ( * src != ':' ) return ( 0 );
   src ++; 
   
   if ( ! parseNum ( src, M ) ) return ( 0 ); 
   
   if ( * src != ':' ) return ( 0 );
   src ++;

   if ( ! parseNum ( src, S ) ) return ( 0 ); 

   return ( *src == 0 ) && ( H >= 0 && H < 24 ) && ( M >= 0 && M < 60 ) && ( S >= 0 && S < 60 );
 }
//-------------------------------------------------------------------------------------------------
// Reseni s ukazateli
//-------------------------------------------------------------------------------------------------
static int         parseNum                      ( const char * * src, int * dst )
 {
   *dst = 0;
   
   if ( ! isdigit ( **src ) ) return ( 0 );
   
   while ( isdigit ( **src ) )
    {
      *dst = 10 * *dst + * * src - '0';
      (*src) ++;
    }
    
   return ( 1 );
 }
//-------------------------------------------------------------------------------------------------
static int         parseHMS                      ( const char * src, int * H, int * M, int * S )
 {
   if ( ! parseNum ( &src, H ) ) return ( 0 ); // failed
   
   if ( * src != ':' ) return ( 0 );
   src ++; 
   
   if ( ! parseNum ( &src, M ) ) return ( 0 ); 
   
   if ( * src != ':' ) return ( 0 );
   src ++;

   if ( ! parseNum ( &src, S ) ) return ( 0 ); 

   return ( *src == 0 ) && ( *H >= 0 && *H < 24 ) && ( *M >= 0 && *M < 60 ) && ( *S >= 0 && *S < 60 );
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   char input [100];
   int  H, M, S;
   
   cout << "Zadej cas h:m:s" << endl;
   cin >> setw ( sizeof ( input ) ) >> input;
   
   
   if ( parseHMS ( input, H, M, S ) )  // if ( parseHMS ( input, &H, &M, &S ) )
     cout << "Format ok, h = " << H << ", m = " << M << ", s = " << S << endl;
    else
     cout << "Chyba formatu" << endl;
  
   return ( 0 );
 }      
//------------------------------------------------------------------------------------------------- 

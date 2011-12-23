#include <iostream>
#include <iomanip>
using namespace std;

class CStack 
 {
   public:
                             CStack              ( int maxSize = 100 );
                             CStack              ( const CStack & src );
                            ~CStack              ( void );
    int                      Push                ( int X );
    int                      Read                ( int & X ) const;
    int                      Pop                 ( int & X );
    int                      IsEmpty             ( void ) const;
   protected:
    int                      dataNr;             // first free index
    int                      dataMax;            // max. size
    int                    * data;               // dyn. alloc data
 };
//-------------------------------------------------------------------------------------------------
                   CStack::CStack                ( int maxSize )
 {
   dataNr  = 0;
   dataMax = maxSize;
   data    = new int [maxSize];
 }                    
//-------------------------------------------------------------------------------------------------
                   CStack::CStack                ( const CStack & src )
 {
   int i;
   
   dataNr  = src . dataNr;
   dataMax = src . dataMax;
   data    = new int [dataMax];     // deep copy
   for ( i = 0; i < dataNr; i ++ ) 
    data[i] = src . data[i]; 
 }                    
//-------------------------------------------------------------------------------------------------
                   CStack::~CStack               ( void )
 {
   delete [] data;
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Push                  ( int X )
 {
   if ( dataNr >= dataMax ) return ( 0 ); // full - failed
   data[dataNr++] = X;
   return ( 1 ); // success
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Read                  ( int & X ) const
 {
   if ( IsEmpty () ) return ( 0 ); // empty - failed
   X = data[dataNr - 1];
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Pop                   ( int & X )
 {
   if ( Read ( X ) )
    {
      dataNr --;
      return ( 1 );
    }
   return ( 0 ); 
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::IsEmpty               ( void ) const
 {
   return ( dataNr == 0 );
 }                    
//-------------------------------------------------------------------------------------------------
int main ( int argc, char * argv [] )
 {
   CStack x;
   int y;
   
   x . Push ( 10 );
   x . Push ( 20 );
   x . Push ( 30 );
   
   CStack z = x;
    // jak program probehne pokud nebude definovan copy konstruktor ?   


   x . Pop  ( y );
   x . Push ( 50 );
   
   cout << "Stack  x" << endl;
   while ( ! x . IsEmpty () )
    {
      x . Pop ( y );
      cout << y << endl;
    }              
                   
   cout << "Stack  z" << endl;   
   while ( ! z . IsEmpty () )
    {
      z . Pop ( y );
      cout << y << endl;
    }              
   return ( 0 );
 } 
//-------------------------------------------------------------------------------------------------

#include <iostream>
#include <iomanip>
using namespace std;

class CStack 
 {
   public:
                             CStack              ( int maxSize = 100 );
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
   dataNr = 0;
   dataMax = maxSize;
   data    = new int [maxSize];
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
   
   while ( ! x . IsEmpty () )
    {
      x . Pop ( y );
      cout << y << endl;
    }
   return ( 0 );
 } 
//-------------------------------------------------------------------------------------------------

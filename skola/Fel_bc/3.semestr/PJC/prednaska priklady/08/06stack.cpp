#include <iostream>
#include <iomanip>
using namespace std;

class CStack 
 {
   public:
                             CStack              ( void );
                            ~CStack              ( void );
    int                      Push                ( int X );
    int                      Read                ( int & X ) const;
    int                      Pop                 ( int & X );
    int                      IsEmpty             ( void ) const;
   protected:
    struct TItem 
     {
       TItem  * Next;
       int      Data;
     };
    TItem                  * top;               // linked list
 };
//-------------------------------------------------------------------------------------------------
                   CStack::CStack                ( void )
 {
   top = NULL;
 }                    
//-------------------------------------------------------------------------------------------------
                   CStack::~CStack               ( void )
 {
   TItem * tmp;
   
   while ( top )
    {
      tmp = top -> Next;
      delete top;
      top = tmp;
    }
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Push                  ( int X )
 {
   TItem * n;
   
   n         = new TItem;
   n -> Data = X;
   n -> Next = top;
   top = n;
   return ( 1 ); // success
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Read                  ( int & X ) const
 {
   if ( IsEmpty () ) return ( 0 ); // empty - failed
   X = top -> Data;
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::Pop                   ( int & X )
 {
   if ( Read ( X ) )
    {
      TItem * tmp = top -> Next;
      delete top;
      top = tmp;
      return ( 1 );
    }
   return ( 0 ); 
 }                    
//-------------------------------------------------------------------------------------------------
int                CStack::IsEmpty               ( void ) const
 {
   return ( top == NULL );
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

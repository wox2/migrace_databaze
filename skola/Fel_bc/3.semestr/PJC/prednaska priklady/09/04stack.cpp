#include <iostream>
#include <iomanip>
using namespace std;

class CStack 
 {
   public:
                             CStack              ( void );
                             CStack              ( const CStack & src );
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
/*                   CStack::CStack                ( const CStack & src ) // varinta 1
 {
   TItem * n, *prev = NULL, *tmp;
   // tmp prochazi zdrojovy spoj. seznam.
   // prev ukazuje na poslední vlozeny prvek
   // vytvareneho seznamu.

   for ( tmp = src . top; tmp; tmp = tmp -> Next )
    {
      n = new TItem;
      n -> Data = tmp -> Data;
      if ( prev )
        prev -> Next = n; 
       else 
        top = n;
      prev = n;
    }
   if ( prev ) 
     prev -> Next = NULL; 
    else 
     top = NULL;
   // ukoncit vytvareny seznam.
 } 
*/
                   CStack::CStack                ( const CStack & src ) // varianta 2
 {
   TItem * n, **wr = &top, *tmp;
   // tmp prochazi zdrojovy spoj. seznam.
   // wr ukazuje na misto, kam ma byt zapsana adresa
   // nove pridavaneho prvku vytvareneho spojoveho 
   // seznamu
   for ( tmp = src . top; tmp; tmp = tmp -> Next )
    {
      n = new TItem;
      n -> Data = tmp -> Data;
     *wr = n;
      wr = &n -> Next;       
    }
  *wr = NULL;   
   // ukoncit vytvareny seznam.
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

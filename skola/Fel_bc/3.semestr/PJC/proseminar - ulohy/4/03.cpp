#include <iostream>
#include <iomanip>
using namespace std;

class CQueue3
 {
   public:
                             CQueue3             ( void );
                             CQueue3             ( const CQueue3 & a );
                            ~CQueue3             ( void ); 
    int                      Enqueue             ( int x );
    int                      Read                ( int & x );
    int                      Length              ( void ) const;
    void                     Clear               ( void );
    void                     Print               ( ostream & os ) const;
   protected:
    struct TElem
     {
       TElem               * Next;
       int                   Val;
     };
    TElem                  * m_First;
    TElem                  * m_Last;
    int                      m_Len; 
 };
 


//-------------------------------------------------------------------------------------------------
                   CQueue3::CQueue3              ( void )
 {
   m_Len   = 0;
   m_First = NULL;
   m_Last  = NULL;
 }                    
//-------------------------------------------------------------------------------------------------
                   CQueue3::CQueue3              ( const CQueue3 & x )
 {
   TElem * tmp, * prev = NULL, * n;
   
   m_Len = x . m_Len;
   
   for ( tmp = x . m_First; tmp; tmp = tmp -> Next )
    {
      n = new TElem ;
      n -> Val = tmp -> Val;
      
      if ( prev ) 
        prev -> Next = n;
       else
        m_First = n;

      prev   = n;
    }
    
   if ( prev ) 
     prev -> Next = NULL;
    else
     m_First = NULL;
   
   m_Last = prev; 
 }
//-------------------------------------------------------------------------------------------------
/* alternativni reseni kopirujiciho konstruktoru pro fajnsmekry */ 
/*
                   CQueue3::CQueue3              ( const CQueue3 & x )
 {
   TElem * tmp, ** wr, * n = NULL;
   
   m_Len = x . m_Len;
   wr = & m_First;
   
   for ( tmp = x . m_First; tmp; tmp = tmp -> Next )
    {
      n = new TElem ;
      n -> Val = tmp -> Val;
      
     *wr = n;
      wr = & n -> Next; 
    }
    
  *wr = NULL;
   m_Last = n; 
 }           
*/ 
//-------------------------------------------------------------------------------------------------
                   CQueue3::~CQueue3             ( void )
 {
   Clear ();
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue3::Enqueue              ( int x )
 {
   TElem * n;
   
   n         = new TElem;
   n -> Next = NULL;
   n -> Val  = x;
   
   if ( m_Last ) 
     {
       m_Last -> Next = n;
       m_Last = n;
     }
    else
     m_First = m_Last = n;
    
   m_Len ++;
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue3::Read                 ( int & x )
 {
   TElem * tmp;
   if ( ! m_First ) return ( 0 ); // empty
   
   x       = m_First -> Val;
   tmp     = m_First;
   m_First = m_First -> Next;
   delete tmp;
   m_Len --;
   if ( ! m_First ) m_Last = NULL;  
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue3::Length               ( void ) const
 {
   return ( m_Len );
 }
//-------------------------------------------------------------------------------------------------
void               CQueue3::Clear                ( void ) 
 {
   TElem * tmp;
   
   while ( m_First )
    {
      tmp = m_First -> Next;
      delete m_First;
      m_First = tmp;
    }
   m_First = NULL;
   m_Last  = NULL;
   m_Len   = 0; 
 }
//-------------------------------------------------------------------------------------------------
void               CQueue3::Print                ( ostream & os ) const
 {
   TElem * tmp;
   
   cout << "[";
   
   for ( tmp = m_First; tmp; tmp = tmp -> Next )
    {
      if ( tmp != m_First ) cout << " ";
      cout << tmp -> Val;
    }
   cout << "]" << endl; 
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   int     x, i;
   CQueue3 A ;
   
   for ( i = 0; i < 20; i ++ )
    A . Enqueue ( i );
   
   cout << "A = ";
   A . Print ( cout );
   
   CQueue3 B = A;
   
   B . Read ( x );
   B . Enqueue ( 60 );
   
   cout << "A = ";
   A . Print ( cout );
   
   cout << "B = ";
   B  . Print ( cout );
   
   A . Read ( x );
   cout << x << endl;
  
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

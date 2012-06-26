#include <iostream>
#include <iomanip>
using namespace std;

class CQueue2
 {
   public:
                             CQueue2             ( int size );
                             CQueue2             ( const CQueue2 & a );
                            ~CQueue2             ( void ); 
    int                      Enqueue             ( int x );
    int                      Read                ( int & x );
    int                      Length              ( void ) const;
    void                     Clear               ( void );
    void                     Print               ( ostream & os ) const;
   protected:
    void                     resize              ( int newSize );
    int                    * m_Data;    
    int                      m_Max;
    int                      m_Len;
    int                      m_Rd;
    int                      m_Wr;
 };
 


//-------------------------------------------------------------------------------------------------
                   CQueue2::CQueue2              ( int size )
 {
   m_Max  = size;
   m_Data = new int [m_Max];
   m_Len  = 0;
   m_Rd   = 0;
   m_Wr   = 0;
 }                    
//-------------------------------------------------------------------------------------------------
                   CQueue2::CQueue2              ( const CQueue2 & x )
 {
   int i;
   
   m_Max  = x . m_Max;
   m_Data = new int [m_Max];
   m_Len  = 0;
   m_Rd   = 0;
   m_Wr   = 0;
   for ( i = 0; i < x . m_Len; i ++ )
    Enqueue ( x . m_Data [ ( i + x . m_Rd ) % x . m_Max ] );
 }
//-------------------------------------------------------------------------------------------------
                   CQueue2::~CQueue2             ( void )
 {
   delete [] m_Data;
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue2::Enqueue              ( int x )
 {
   if ( m_Len >= m_Max ) 
    resize ( m_Max * 3 / 2 );
   m_Data [m_Wr ++] = x;
   m_Wr %= m_Max;
   m_Len ++;
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue2::Read                 ( int & x )
 {
   if ( ! m_Len ) return ( 0 ); // empty
   x = m_Data [m_Rd ++];
   m_Rd %= m_Max;
   m_Len --;
   if ( m_Len < m_Max / 2 && m_Max > 10 ) 
    resize ( m_Max * 2 / 3 );
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue2::Length               ( void ) const
 {
   return ( m_Len );
 }
//-------------------------------------------------------------------------------------------------
void               CQueue2::Clear                ( void ) 
 {
   m_Len = 0;
   m_Rd  = 0;
   m_Wr  = 0;
 }
//-------------------------------------------------------------------------------------------------
void               CQueue2::Print                ( ostream & os ) const
 {
   int i, idx = m_Rd;
   
   cout << "[";
   
   for ( i = 0; i < m_Len; i ++ )
    {
      if ( i ) cout << " ";
      cout << m_Data[idx++];
      idx %= m_Max;
    }
   cout << "]" << endl; 
 }
//-------------------------------------------------------------------------------------------------
void               CQueue2::resize               ( int newSize )
 {
   int * tmp, i;
   
   tmp = new int [newSize];
   
   for ( i = 0; i < m_Len; i ++ )
    tmp[i] = m_Data[( m_Rd + i ) % m_Max];
    
   m_Max = newSize;
   m_Rd  = 0;
   m_Wr  = m_Len;
   delete [] m_Data;
   m_Data = tmp;
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   int     x, i;
   CQueue2 A ( 5 );
   
   for ( i = 0; i < 20; i ++ )
    A . Enqueue ( i );
   
   cout << "A = ";
   A . Print ( cout );
   
   CQueue2 B = A;
   
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

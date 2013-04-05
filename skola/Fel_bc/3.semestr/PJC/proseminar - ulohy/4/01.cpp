#include <iostream>
#include <iomanip>
using namespace std;

class CQueue1
 {
   public:
                             CQueue1             ( int size );
                             CQueue1             ( const CQueue1 & a );
                            ~CQueue1             ( void ); 
    int                      Enqueue             ( int x );
    int                      Read                ( int & x );
    int                      Length              ( void ) const;
    void                     Clear               ( void );
    void                     Print               ( ostream & os ) const;
   protected:
    int                    * m_Data;    
    int                      m_Max;
    int                      m_Len;
    int                      m_Rd;
    int                      m_Wr;
 };
 


//-------------------------------------------------------------------------------------------------
                   CQueue1::CQueue1              ( int size )
 {
   m_Max  = size;
   m_Data = new int [m_Max];
   m_Len  = 0;
   m_Rd   = 0;
   m_Wr   = 0;
 }                    
//-------------------------------------------------------------------------------------------------
                   CQueue1::CQueue1              ( const CQueue1 & x )
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
                   CQueue1::~CQueue1             ( void )
 {
   delete [] m_Data;
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue1::Enqueue              ( int x )
 {
   if ( m_Len >= m_Max ) return ( 0 ); // full
   m_Data [m_Wr ++] = x;
   m_Wr %= m_Max;
   m_Len ++;
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue1::Read                 ( int & x )
 {
   if ( ! m_Len ) return ( 0 ); // empty
   x = m_Data [m_Rd ++];
   m_Rd %= m_Max;
   m_Len --;
   return ( 1 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CQueue1::Length               ( void ) const
 {
   return ( m_Len );
 }
//-------------------------------------------------------------------------------------------------
void               CQueue1::Clear                ( void ) 
 {
   m_Len = 0;
   m_Rd  = 0;
   m_Wr  = 0;
 }
//-------------------------------------------------------------------------------------------------
void               CQueue1::Print                ( ostream & os ) const
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
int                main                          ( int argc, char * argv [] )
 {
   int     x;
   CQueue1 A ( 5 );
   
   A . Enqueue ( 10 );
   A . Enqueue ( 20 );
   A . Enqueue ( 30 );
   A . Enqueue ( 40 );
   A . Enqueue ( 50 );
   
   cout << "A = ";
   A . Print ( cout );
   
   CQueue1 B = A;
   
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

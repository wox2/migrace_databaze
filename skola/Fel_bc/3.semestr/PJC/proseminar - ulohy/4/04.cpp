#include <iostream>
#include <iomanip>
using namespace std;

class CSet1
 {
   public:
                             CSet1               ( void );
                             CSet1               ( const CSet1 & a );
                            ~CSet1               ( void ); 
    void                     Set                 ( int x );
    void                     Reset               ( int x );
    int                      IsSet               ( int x ) const;
    void                     Clear               ( void );
    void                     Print               ( ostream & os ) const;
   protected:
    void                     resize              ( int newSize );
    int                      findPos             ( int x ) const; 
    int                    * m_Data;
    int                      m_Max; 
    int                      m_Len; 
 };
 


//-------------------------------------------------------------------------------------------------
                   CSet1::CSet1                  ( void )
 {
   m_Len   = 0;
   m_Max   = 0;
   m_Data  = NULL;
 }                    
//-------------------------------------------------------------------------------------------------
                   CSet1::CSet1                  ( const CSet1 & x )
 {
   int i;
   
   m_Len  = x . m_Len;
   m_Max  = x . m_Max;
   m_Data = new int [m_Max]; 
   
   for ( i =0 ; i < m_Len; i ++ )
    m_Data[i] = x . m_Data[i];
 }
//-------------------------------------------------------------------------------------------------
                   CSet1::~CSet1                 ( void )
 {
   delete [] m_Data;
 }                    
//-------------------------------------------------------------------------------------------------
void               CSet1::Set                    ( int x )
 {
   int idx;
   
   idx = findPos ( x );
   if ( idx < m_Len ) return; // already set
   
   if ( m_Len >= m_Max )
    resize ( m_Max * 3 / 2 );
   m_Data[m_Len ++] = x;
 }                    
//-------------------------------------------------------------------------------------------------
void               CSet1::Reset                  ( int x )
 {
   int idx;
   
   idx = findPos ( x );
   if ( idx >= m_Len ) return; // does not exist
   
   m_Data [idx] = m_Data[--m_Len];
   
   if ( m_Len < m_Max / 2 && m_Max > 10 ) 
    resize ( m_Max * 2 / 3 );
 }                    
//-------------------------------------------------------------------------------------------------
int                CSet1::IsSet                  ( int x ) const
 {
   int idx;
   
   idx = findPos ( x );
   return ( idx < m_Len && m_Data[idx] == x );
 }
//-------------------------------------------------------------------------------------------------
void               CSet1::Clear                  ( void ) 
 {
   m_Len = 0;
 }
//-------------------------------------------------------------------------------------------------
void               CSet1::Print                  ( ostream & os ) const
 {
   int i;
   
   cout << "{";
   
   
   for ( i = 0; i < m_Len; i ++ )
    {
      if ( i ) cout << ", ";
      cout << m_Data[i];
    }
   cout << "}" << endl; 
 }
//-------------------------------------------------------------------------------------------------
void               CSet1::resize                 ( int newSize )
 {
   int * tmp, i;

   if ( newSize < 10 ) newSize = 10;
   if ( newSize == m_Max ) return;
   
   tmp = new int [newSize];
   
   for ( i = 0; i < m_Len; i ++ )
    tmp[i] = m_Data[i];
    
   m_Max = newSize;
   delete [] m_Data;
   m_Data = tmp;
 }  
//-------------------------------------------------------------------------------------------------
int                CSet1::findPos                ( int x ) const
 {
   int i;
   
   for ( i = 0; i < m_Len; i ++ )
    if ( m_Data[i] == x )
     return ( i );
   return ( m_Len );
 } 
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   CSet1 A;
   
   A . Set ( 10 );
   A . Set ( 20 );
   A . Set ( 5 );
   A . Set ( 30 );
   A . Set ( 8 );
   
   cout << "A = ";
   A . Print ( cout );
   
   A . Reset ( 30 );
   A . Reset ( 5 );
   A . Set ( 3 );
   
   cout << A . IsSet ( 3 ) << " " << A . IsSet ( 20 ) << " " << A . IsSet ( 17 ) << endl;

   CSet1 B = A;

   B . Set ( 25 );

   A . Reset ( 10 );
   
   cout << "A = ";
   A . Print ( cout );
   
   cout << "B = ";
   B . Print ( cout );
   
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

#include <iostream>
#include <iomanip>
#include <sstream>

using namespace std;


class InvalidOperandException { };

class CRat
 {
   public:
                             CRat                ( int num = 0, int denom = 1 );
                             CRat                ( const char * str );
                            ~CRat                ( void )  { } // neni potreba
    CRat                     operator -          ( void ) const;
                             operator  double    ( void ) const;
                             
    CRat                     operator +          ( int x ) const; 
    CRat                     operator -          ( int x ) const; 
    CRat                     operator +          ( const CRat & b ) const;
    CRat                     operator -          ( const CRat & b ) const;
    friend CRat              operator +          ( int a, const CRat & b );
    friend CRat              operator -          ( int a, const CRat & b );

    friend ostream         & operator <<         ( ostream & os, const CRat & x );
   
   protected:
    void                     simplify            ( void );
    
    int                      m_Num;
    int                      m_Denom;
 };
 
//------------------------------------------------------------------------------------------------- 
                   CRat::CRat                    ( int num, int denom )
 {
   if ( denom == 0 ) throw InvalidOperandException();
   m_Num   = num;
   m_Denom = denom;
   
   simplify ();
 }                     
//------------------------------------------------------------------------------------------------- 
                   CRat::CRat                    ( const char * str )
 {
   char          c;
   istringstream tok  ( str );
   
   tok . clear ();
   tok >> m_Num >> c >> m_Denom;
   
   if ( tok . fail () || m_Denom == 0 || c != '/' ) 
    throw InvalidOperandException();

   simplify ();
 }                     
//------------------------------------------------------------------------------------------------- 
CRat               CRat::operator -              ( void ) const
 {
   return ( CRat ( - m_Num, m_Denom ) );
 }
//------------------------------------------------------------------------------------------------- 
                   CRat::operator  double        ( void ) const
 {
   return ( (double) m_Num / m_Denom );
 }
//------------------------------------------------------------------------------------------------- 
void               CRat::simplify                ( void )
 {
   int a, b, tmp;

   a = abs ( m_Num );
   b = abs ( m_Denom );
   
   if ( !a || !b ) return;
   
   if ( a < b ) 
    {
      tmp = a;
      a = b;
      b = tmp;
    }
    
   while ( b )
    {
      tmp = a % b;
      a = b;
      b = tmp;
    } 
    
   m_Num   /= a;
   m_Denom /= a; 
 }  
//------------------------------------------------------------------------------------------------- 
CRat               CRat::operator +              ( const CRat & b ) const
 {
   CRat   res ( m_Num * b . m_Denom + b . m_Num * m_Denom, m_Denom * b . m_Denom );

   res . simplify ();
   return ( res );
 }
//------------------------------------------------------------------------------------------------- 
CRat               CRat::operator -              ( const CRat & b ) const
 {
   CRat   res ( m_Num * b . m_Denom - b . m_Num * m_Denom, m_Denom * b . m_Denom );

   res . simplify ();
   return ( res );
 } 
//------------------------------------------------------------------------------------------------- 
CRat               CRat::operator +              ( int b ) const
 {
   CRat   res ( m_Num + b * m_Denom, m_Denom );

   res . simplify ();
   return ( res );
 }
//------------------------------------------------------------------------------------------------- 
CRat               CRat::operator -              ( int b ) const
 {
   CRat   res ( m_Num - b * m_Denom, m_Denom  );

   res . simplify ();
   return ( res );
 } 
//------------------------------------------------------------------------------------------------- 
ostream          & operator <<                   ( ostream & os, const CRat & x )
 {
   os << x . m_Num << "/" << x . m_Denom;
   return ( os );
 }
//------------------------------------------------------------------------------------------------- 
CRat               operator +                    ( int a, const CRat & b )
 {
   return b + a;
 }
//------------------------------------------------------------------------------------------------- 
CRat               operator -                    ( int a, const CRat & b )
 {
   return -b + a;
 }
//------------------------------------------------------------------------------------------------- 
int                main                          ( int argc, char * argv [] )
 {
   CRat a ( 3, 8 ), b ( 9, 8 );
   CRat c ( "2 / 8" );
   double z;
   
   
   cout << a << " + " << b << " = " << (a + b) << endl;
   cout << c << endl;
   
   c = c - 3; // what about c = c * 3
   
   z = c;
   
   cout << z << endl;
  
  
   return ( 0 );
 } 
//-------------------------------------------------------------------------------------------------

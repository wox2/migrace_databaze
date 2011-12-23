


#include <cstring>
#include <iostream>
using namespace std;

class CStrRef
 {
   public:
                             CStrRef             ( const char * Str = "" );
    explicit                 CStrRef             ( double X );
    explicit                 CStrRef             ( int X );
    void                     AddRef              ( void );
    void                     Release             ( void );
                                                 
    int                      Length              ( void ) const;
    CStrRef                & Append              ( const CStrRef & x );
    CStrRef                & Append              ( const char * x );
    CStrRef                & Append              ( int x );
    CStrRef                & Append              ( double x );
                             operator const char * ( void ) const;
    char                   & operator []         ( int idx );

   protected:
    int                      m_RefCnt;
    int                      m_Len;
    int                      m_Max;
    char                   * m_Data;
    

                             CStrRef             ( const CStrRef & x );
                            ~CStrRef             ( void );
    CStrRef                & operator =          ( const CStrRef & x );
                                                 
    void                     append              ( const char * src, int srclen );
                                                 
    friend std::ostream    & operator <<         ( std::ostream & os, const CStrRef & x );
 };

std::ostream    & operator <<                    ( std::ostream & os, const CStrRef & x );


class CSmartPtr
 {
   public:
                             CSmartPtr           ( CStrRef * X );
                             CSmartPtr           ( const CSmartPtr & X );
                            ~CSmartPtr           ( void );
    CSmartPtr              & operator =          ( const CSmartPtr & X );
                                                
    CStrRef                * operator ->         ( void );
    CStrRef                & operator *          ( void );
   protected:                                   
    CStrRef                * m_Cont;              
    friend std::ostream    & operator <<         ( std::ostream & os, const CSmartPtr & x );
 };
std::ostream    & operator <<                    ( std::ostream & os, const CSmartPtr & x ); 

//-------------------------------------------------------------------------------------------------
                   CStrRef::CStrRef              ( const char * Str )
 {
   m_RefCnt  = 1;
   m_Len     = strlen ( Str );
   m_Max     = m_Len + 1;
   m_Data    = new char[m_Max];
   memcpy    ( m_Data, Str, m_Len + 1 );
 }
//-------------------------------------------------------------------------------------------------
                   CStrRef::CStrRef              ( double X )
 {
   m_RefCnt  = 1;
   m_Max     = 20;
   m_Data    = new char [m_Max];
   m_Len     = 0;
  *m_Data    = 0;
   Append    ( X );
 }
//-------------------------------------------------------------------------------------------------
                   CStrRef::CStrRef              ( int X )
 {
   m_RefCnt  = 1;
   m_Max     = 20;
   m_Data    = new char [m_Max];
   m_Len     = 0;
  *m_Data    = 0;
   Append    ( X );
 }           
//-------------------------------------------------------------------------------------------------
void               CStrRef::AddRef               ( void )
 {
   m_RefCnt ++;
 }
//-------------------------------------------------------------------------------------------------
void               CStrRef::Release              ( void )
 {
   if ( -- m_RefCnt == 0 ) delete this;
 }
//-------------------------------------------------------------------------------------------------
                   CStrRef::CStrRef              ( const CStrRef & x )
 {
 }
//-------------------------------------------------------------------------------------------------
                   CStrRef::~CStrRef             ( void )
 {
   delete [] m_Data;
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CStrRef::operator =           ( const CStrRef & x )
 {
   return ( *this );
 }
//-------------------------------------------------------------------------------------------------
int                CStrRef::Length               ( void ) const
 {
   return ( m_Len );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CStrRef::Append               ( const CStrRef & x )
 {
   append ( x . m_Data, x . m_Len );
   return ( * this );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CStrRef::Append               ( const char * x )
 {
   append ( x, strlen ( x ) );
   return ( * this );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CStrRef::Append               ( int x )
 {
   char tmp [20];

   snprintf ( tmp, sizeof ( tmp ), "%d", x );
   append ( tmp, strlen ( tmp ) );
   return ( *this );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CStrRef::Append               ( double x )
 {
   char tmp [20];

   snprintf ( tmp, sizeof ( tmp ), "%f", x );
   append ( tmp, strlen ( tmp ) );
   return ( *this );
 }
//-------------------------------------------------------------------------------------------------
                   CStrRef::operator const char * ( void ) const
 {
   return ( m_Data );
 }
//-------------------------------------------------------------------------------------------------
char             & CStrRef::operator []          ( int idx )
 {
   if ( idx < 0 || idx >= m_Len )
    throw "index out of range";

   return ( m_Data[idx] );
 }
//-------------------------------------------------------------------------------------------------
void              CStrRef::append                ( const char * src, int srclen )
 {
   char * tmp;
   
   if ( m_Len + srclen + 1 >= m_Max )
    {
      m_Max    = m_Len + srclen + 1;
      tmp      = new char [m_Max];
      memcpy   ( tmp, m_Data, m_Len );
      delete [] m_Data;
      m_Data   = tmp;
    }
   memcpy         ( m_Data + m_Len, src, srclen );
   m_Len         += srclen;
   m_Data[m_Len]  = 0;
 }
//-------------------------------------------------------------------------------------------------
std::ostream     & operator <<                   ( std::ostream & os, const CStrRef & x )
 {
   os << x . m_Data;
   return ( os );
 }
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
                   CSmartPtr::CSmartPtr          ( CStrRef * X )
 {
   m_Cont = X;
 }
//-------------------------------------------------------------------------------------------------
                   CSmartPtr::CSmartPtr          ( const CSmartPtr & X )
 {
   m_Cont = X . m_Cont;
   m_Cont -> AddRef ();
 }
//-------------------------------------------------------------------------------------------------
                   CSmartPtr::~CSmartPtr         ( void )
 {
   m_Cont -> Release ();
 }
//-------------------------------------------------------------------------------------------------
CSmartPtr        & CSmartPtr::operator =         ( const CSmartPtr & X )
 {
   if ( &X != this )
    {
      m_Cont -> Release ();
      m_Cont = X . m_Cont;
      m_Cont -> AddRef ();
    }
   return ( *this );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          * CSmartPtr::operator ->        ( void )
 {
   return ( m_Cont );
 }
//-------------------------------------------------------------------------------------------------
CStrRef          & CSmartPtr::operator *         ( void )
 {
   return ( *m_Cont );
 }
//-------------------------------------------------------------------------------------------------
std::ostream     & operator <<                   ( std::ostream & os, const CSmartPtr & x )
 {
   os << *x . m_Cont;
   return ( os );
 }
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   CSmartPtr a (new CStrRef ( 5 )), b (new CStrRef ( "Pokusny retezec" ));
   int       i;
   
   a -> Append ( " test " );
   
   
   a -> Append ( 12.5 );                               // testy prettizeni op +=
   a -> Append ( *b );
   
   cout << "a = " << a << endl <<
           "b = " << b << endl;
   
   for ( i = a -> Length  () - 1; i >= 0; i -- )  // test op []
    cout << (*a)[i];
   cout << endl;

   CSmartPtr  c = a;
   
   
   const char * pokus = (const char*)  *c;       // test op pretypovani
   
   cout << pokus << endl;
   
   cout << c << endl;

   return ( 0 );
 }
//------------------------------------------------------------------------------------------------- 

#include <iostream>
#include <iomanip>
using namespace std;
#include "Expr.h"
#include "Tree.h"


    
//-------------------------------------------------------------------------------------------------
CNode            * CParser::Parse                ( const char * Str )
 {
   CParser  a ( Str );
   
   return  a . parse ();
 }
//-------------------------------------------------------------------------------------------------
                   CParser::CParser              ( const char * Str )
 {
   m_Str = Str;
 }                    
//-------------------------------------------------------------------------------------------------
CNode            * CParser::parse                ( void )
 {
   CNode * res;
   
   try 
    {
      m_Token = nextToken ();
      
      res = n_e ();
      
      if ( m_Token != LEX_EOF )
       {
         delete res;
         throw "Neocekavane znaky za koncem vyrazu";
       }  
       
      return ( res );
    }
   catch ( const char * x )
    {
      cout << "Chyba pri zpracovani retezce: " << x << endl;
      return ( NULL );
    }
 }
//-------------------------------------------------------------------------------------------------
int                CParser::nextToken            ( void )
 {
   m_Int = 0;
   
   while ( isspace ( *m_Str ) ) m_Str ++;
   
   if ( * m_Str == 0 ) return ( LEX_EOF );
   switch ( * m_Str ) 
    {
      case '+':
       m_Str ++;
       return ( LEX_ADD );
      case '-':
       m_Str ++;
       return ( LEX_SUB );
      case '*':
       m_Str ++;
       return ( LEX_MUL );
      case '/':
       m_Str ++;
       return ( LEX_DIV );
      case '(':
       m_Str ++;
       return ( LEX_LPA );
      case ')':
       m_Str ++;
       return ( LEX_RPA );
    }  
    
   if ( toupper ( *m_Str ) == 'S' && toupper ( m_Str[1] ) == 'Q' && 
        toupper ( m_Str[2] ) == 'R' && toupper ( m_Str[3] ) == 'T' )
    {
      m_Str += 4;
      return ( LEX_SQRT );
    }     
      
   if ( ! isdigit ( *m_Str ) ) return ( LEX_ERR );
    
   m_Int =  *m_Str ++ - '0';
    
   while ( isdigit ( * m_Str ) )
    m_Int = 10 * m_Int + *m_Str ++ - '0';

   return ( LEX_INT );
 }
//-------------------------------------------------------------------------------------------------
CNode            * CParser::n_e                 ( void )
 {
   CNode * op;
   
   op = n_t ();

   while ( 1 )
    switch ( m_Token )
     {
       case LEX_ADD:
        m_Token = nextToken ();
        op = new CAddNode ( op, n_t () );
        break;
       case LEX_SUB:
        m_Token = nextToken ();
        op = new CSubNode ( op, n_t () );
        break;
       default:
        return ( op );
     }
 }
//-------------------------------------------------------------------------------------------------
CNode            * CParser::n_t                 ( void )
 {
   CNode * op;
  
   op = n_f ();

   while ( 1 )
    switch ( m_Token )
     {
       case LEX_MUL:
        m_Token = nextToken ();
        op = new CMulNode ( op, n_f () );
        break;
       case LEX_DIV:
        m_Token = nextToken ();
        op = new CDivNode ( op, n_f () );
        break;
       default:
        return ( op );
     }
 }
//-------------------------------------------------------------------------------------------------
CNode            * CParser::n_f                 ( void )
 {
   CNode * res;
   
   switch ( m_Token )
    {
      case LEX_INT:
       res = new CNumNode ( m_Int );
       m_Token = nextToken ();
       return ( res );

      case LEX_LPA:
       m_Token = nextToken ();
       
       res = n_e ();
       
       if ( m_Token != LEX_RPA )
        {
          delete res;
          throw "Chybi zaviraci zavorka";
        }
        
       m_Token = nextToken ();
       return new CParNode ( res );
      
      case LEX_SQRT:
       m_Token = nextToken (); 

       if ( m_Token != LEX_LPA )
        throw "Ocekavam zavorku za sqrt";
       m_Token = nextToken ();
       
       res = n_e ();
       
       if ( m_Token != LEX_RPA )
        {
          delete res;
          throw "Chybi zaviraci zavorka";
        }
        
       m_Token = nextToken ();
       return new CSqrtNode ( res );

      default:
       throw "Ocekavam cislo nebo zavorku"; 
    }
 }
//-------------------------------------------------------------------------------------------------


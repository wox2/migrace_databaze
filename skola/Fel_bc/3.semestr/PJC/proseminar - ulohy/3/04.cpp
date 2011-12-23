#include <iostream>
#include <iomanip>
using namespace std;


const int LEX_EOF   = 0;
const int LEX_ERR   = 1;
const int LEX_INT   = 2;
const int LEX_ADD   = 3;
const int LEX_SUB   = 4;
const int LEX_MUL   = 5;
const int LEX_DIV   = 6;



//-------------------------------------------------------------------------------------------------
static int         nextToken                     ( const char * & str, int & attr )
 {
   while ( *str == ' ' ||  *str == '\t' || *str == '\n' ) str ++;
   
   switch ( *str )
    {
      case '+': 
       str ++;
       return ( LEX_ADD );
       
      case '-':           
       str ++;
       return ( LEX_SUB );
       
      case '*': 
       str ++;
       return ( LEX_MUL );
       
      case '/': 
       str ++;
       return ( LEX_DIV );
       
      case 0:   
       return ( LEX_EOF );
      
      default:
       break;
    }
    
   if ( isdigit  ( * str ) )
    {
      attr = *str ++ - '0';
      while ( isdigit ( *str ) )
       attr = 10 * attr + *str++ - '0';

      return ( LEX_INT );
    }
   return ( LEX_ERR ); 
 } 
//-------------------------------------------------------------------------------------------------
static int         eval                          ( const char * str, int & res )
 {
   int  token;
   int  mul = 0, attr;
   int  op = LEX_ADD;
   
   res = 0;
   
   token = nextToken ( str, attr );
   if ( token != LEX_INT ) return ( 0 ); // err
   
   mul = attr;
   
   
   while ( 1 )
    {
      op    = nextToken ( str, attr );
      if ( op == LEX_EOF )
       {
         res += mul;
         return ( 1 );
       }
      token = nextToken ( str, attr );
      if ( token != LEX_INT ) return ( 0 ); // err
      
      switch ( op )
       {
         case LEX_ADD:
          res += mul;
          mul = attr;
          break;
         case LEX_SUB:
          res += mul;
          mul = -attr;
          break;
         case LEX_MUL:
          mul *= attr;
          break;
         case LEX_DIV:
          mul /= attr;
          break;
         default:
          return ( 0 ); 
       }
    }
 }
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv )
 {
   char expr[200];
   int  val;
   
   cout << "Zadej vyraz" << endl;
   cin . getline ( expr, sizeof ( expr ) );

   if ( cin . fail () )
    {
      cout << "Prilis dlouhy vstup" << endl;
      return ( 1 );
    }
   
   if ( eval ( expr, val ) )
     cout << "vysledek: " << val << endl;
    else
     cout << "chyba v zapisu" << endl;
   
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

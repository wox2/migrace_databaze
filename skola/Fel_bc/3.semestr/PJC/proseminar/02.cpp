#include <iostream>
#include <iomanip>
#include <fstream>
#include <cctype>
using namespace std;


//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )      
 {
   ifstream in;
   int      ch, words = 0, lines = 0, chars = 0, inWord = 0;

   
   if ( argc != 2 ) 
    {
      cout << "wc <file>" << endl;
      return ( 1 );
    }
    
   in . open ( argv[1], ios::in );
   
   if ( ! in . good () )
    {
      cout << "Chyba cteni " << argv[1] << endl;
      return ( 1 );
    }
   
   while ( 1 )
    {
      ch = in . get ();
      if ( in . eof () ) break;
      if ( in . fail () )
       {
         cout << "Chyba cteni " << argv[1] << endl;
         return ( 1 );
       }
      chars ++;
      if ( ch == '\n' ) lines ++;
      
      if ( inWord )
        {
          if ( isspace ( ch ) ) inWord = 0;
        }
       else
        {
          if ( ! isspace ( ch ) )
           {
             words ++;
             inWord = 1;
           }
        }
    }

   if ( chars ) lines ++;
   cout << argv[1] << ": " << chars << " znaku, " << words << " slov, " << lines << " radku" << endl;

   in . close ();
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

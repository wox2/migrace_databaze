#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;

const int BYTE_PER_LINE = 16;

//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )      
 {
   ifstream in;
   int      ofs = 0, charIdx = 0, ch;
   char     chars[BYTE_PER_LINE + 1];
   
   if ( argc != 2 ) 
    {
      cout << "hexdump <file>" << endl;
      return ( 1 );
    }
    
   in . open ( argv[1], ios::binary | ios::in );
   
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
         cout << "Chyba cteni souboru" << endl;
         in . close ();
         return ( 1 );
       }
      
      if ( ofs % BYTE_PER_LINE == 0 )
       cout << hex << setw ( 8 ) << setfill ( '0' ) << ofs;
       
      cout << " " << hex << setw ( 2 ) << setfill ( '0' ) << ch;
      
      chars[charIdx ++ ] = ch >= ' ' ? ch : '?';
      
      if ( ofs % BYTE_PER_LINE == BYTE_PER_LINE - 1 )
       {
         chars[charIdx] = 0;
         cout << " " << chars << endl;
         charIdx = 0;
       }
      ofs ++; 
    }

   if ( charIdx != 0 )
    {
      chars[charIdx] = 0;
      cout << setfill ( ' ' ) << setw ( 1 + ( BYTE_PER_LINE - charIdx ) * 3 ) << " " << chars << endl;
    }

   in . close ();
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

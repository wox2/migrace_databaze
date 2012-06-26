#include <iostream>
#include <iomanip>
using namespace std;


//-------------------------------------------------------------------------------------------------
static void         genParentheses               ( char * str, int pos, int remainsLeft, int remainsRight, int & cnt )
 {
   if ( remainsLeft )
    {
      str[pos] = '(';
      genParentheses ( str,  pos + 1, remainsLeft - 1, remainsRight, cnt );
    }
   
   if ( remainsRight && remainsRight > remainsLeft )
    {
      str[pos] = ')';
      genParentheses ( str,  pos + 1, remainsLeft, remainsRight-1, cnt );
    } 
   if ( ! remainsLeft && ! remainsRight )
    {
      str[pos] = 0;
      cout << str << endl;
      cnt ++;
    } 
 }
//-------------------------------------------------------------------------------------------------
int                 main                         ( int argc, char * argv [] )
 {
   int    n, cnt;
   char * str;
   
   cout << "Zadej n " << endl;
   cin . clear ();
   cin >> n;
   
   if ( ! cin . good () || n < 1 )
    {
      cout << "Nespravny format" << endl;
      return ( 1 );
    }
   
   str = new char [2 * n + 1];
   
   cnt = 0;
   genParentheses ( str, 0, n, n, cnt );
   
   cout << "Celkem: " << cnt << endl;
  
   delete [] str;
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

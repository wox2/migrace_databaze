#include <iostream>
#include <iomanip>
using namespace std;

// Verze bez kontroly vstupu. 
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] ) 
 {
   double a, b, c;
   
   cout << "Zadej cisla a b" << endl;
   cin  >> a >> b;
   
   c = a + b;
   cout << a << " + " << b << " = " << c << endl;
   cout << a << " - " << b << " = " << (a-b) << endl;
   cout << a << " * " << b << " = " << (a*b) << endl;
   cout << a << " / " << b << " = " << (a/b) << endl;
  
   return ( 0 );
 } 
//-------------------------------------------------------------------------------------------------



// Verze s kontrolou vstupu. 
//-------------------------------------------------------------------------------------------------
/*
int                main                          ( int argc, char * argv [] ) 
 {
   double a, b, c;
   
   cout << "Zadej cisla a b" << endl;
   cin . clear ();  // smazame (pripadny) predchozi priznak chyby
   cin  >> a >> b;
   if ( ! cin . good () )
    {
      cout << "Chyba formatu vstupnich dat" << endl;
      return ( 1 ); // chyba
    }
   
   
   c = a + b;
   cout << a << " + " << b << " = " << c << endl;
   cout << a << " - " << b << " = " << (a-b) << endl;
   cout << a << " * " << b << " = " << (a*b) << endl;
   cout << a << " / " << b << " = " << (a/b) << endl;
  
   return ( 0 ); // ok
 }
*/  
//-------------------------------------------------------------------------------------------------

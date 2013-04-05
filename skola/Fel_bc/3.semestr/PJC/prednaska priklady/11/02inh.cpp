#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

class CEmployee
 {
    string name;
    int    salary;
   public:
    CEmployee ( string _name, int _salary ) : 
               name (_name), salary (_salary) {}
    int MonthSalary ( int Hours ) const 
     { return salary * Hours; }
 };
 
class CBoss : public CEmployee
 {
    int subordNr;
   public:
    CBoss ( string _name, int _salary, int _subord ): 
           CEmployee ( _name, _salary ), 
           subordNr ( _subord ) { }  
   int MonthSalary ( int Hours ) const 
    { return CEmployee::MonthSalary ( Hours ) +
             subordNr * 500; }
 };
 
int main ( int argc, char * argv [] )
 {
   CEmployee * dept [3];
   int i;
   
   dept[0] = new CBoss ( "Novak", 200, 2 );
   dept[1] = new CEmployee ( "Novotny", 100 );
   dept[2] = new CEmployee ( "Novotna", 100 );
   
   for ( i = 0; i < 3; i ++ )
    cout << i << ". " << dept[i] -> MonthSalary ( 170 ) << endl;  // !! nebude ok
   
   for ( i = 0; i < 3; i ++ )
    delete dept[i];
   
   return ( 0 );  
 }
 


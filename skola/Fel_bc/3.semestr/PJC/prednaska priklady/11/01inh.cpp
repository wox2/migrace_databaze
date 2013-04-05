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
   CEmployee x ( "Novotny", 100 );
   CBoss     y ( "Novak", 200, 2 );
   
   cout << x . MonthSalary ( 170 ) << endl;
   cout << y . MonthSalary ( 170 ) << endl;    
   return ( 0 );  
 }
 


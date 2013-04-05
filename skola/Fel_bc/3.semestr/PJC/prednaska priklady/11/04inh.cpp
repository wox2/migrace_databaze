#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

class CEmployee
 {
   protected:
    string name;
    int    salary;
   public:
    CEmployee ( string _name, int _salary ) : 
               name (_name), salary (_salary) {}

    virtual ~CEmployee (void) { }           

    virtual int MonthSalary ( int Hours ) const 
     { return salary * Hours; }

   friend ostream & operator << ( ostream & os,
                         const CEmployee & x );

 };
 
class CBoss : public CEmployee
 {
   protected:
    int subordNr;
   public:
    CBoss ( string _name, int _salary, int _subord ): 
           CEmployee ( _name, _salary ), 
           subordNr ( _subord ) { }  
   virtual int MonthSalary ( int Hours ) const 
    { return CEmployee::MonthSalary ( Hours ) +
             subordNr * 500; }

   friend ostream & operator << ( ostream & os,
                         const CBoss & x );

 };


ostream & operator << ( ostream & os, const CEmployee & x )
 { 
   os << "Employee " << x . name; 
   return os; 
 }

ostream & operator << ( ostream & os, const CBoss & x )
 { 
   os << "Boss " << x . name << ", ridi:" << x . subordNr; 
   return os; 
 }
 
int main ( int argc, char * argv [] )
 {
   CEmployee * dept [3];
   int i;
   
   dept[0] = new CBoss ( "Novak", 200, 2 );
   dept[1] = new CEmployee ( "Novotny", 100 );
   dept[2] = new CEmployee ( "Novotna", 100 );
   
   for ( i = 0; i < 3; i ++ )
    cout << i << ". " << *dept[i] << endl;  // !! chyba
    
    
   for ( i = 0; i < 3; i ++ )
    delete dept[i];
   
   return ( 0 );  
 }
 


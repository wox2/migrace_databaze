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

    virtual void print (ostream & os ) const
     { os << "Employee " << name; }

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

    virtual void print (ostream & os ) const
     { 
       os << "Boss " << name << ", ridi:" << subordNr; 
     }
 };


ostream & operator << ( ostream & os, const CEmployee & x )
 { 
   x . print ( os ); 
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
    cout << i << ". " << *dept[i] << endl;  // ok


   CEmployee * a = dept[0];
   CEmployee & b = *dept[0];
   CEmployee c   = *dept[0];
   
   cout << *a << endl;
    
   cout << b << endl;
   cout << c << endl;    // !! Employee !!
   
   // experiment - zmenime odkaz na VMT v instanci c (to se normalne 
   // nedeje, jen ukazka proc se to nedeje).
   
   // Zmena odkazu na VMT neni portabilni konstrukce, vychazime z apriorni 
   // informace, ze ukazatel na VMT je v instanci na prvnim miste.
   
   *(void**) &c = *(void**) &b;   
   cout << c << endl;    // !! Boss, ale kolik ma nyni podrizenych ??
   
   for ( i = 0; i < 3; i ++ )
    delete dept[i];
   
   return ( 0 );  
 }
 


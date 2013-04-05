#include <iostream>
#include <iomanip>
#include <typeinfo>
using namespace std;

class CPerson
 {
   protected: 
    string name;
    int    born;
   public:
    CPerson ( string _name, int _born ) : name (_name), born(_born) { }
    virtual ~CPerson ( void ) { }
    virtual int retired ( int year ) = 0; 
 };
 
class CMan : public CPerson
 {
    int milSvc;
   public:
    CMan ( string _name, int _born, int _milSvc ) : CPerson ( _name, _born ), milSvc(_milSvc) { }
    virtual int retired ( int year )
     { return year > born + 65 - milSvc; }
 };
 
class CWoman : public CPerson
 {
    int childs;
   public:
    CWoman ( string _name, int _born, int _childs ) : CPerson ( _name, _born ), childs(_childs) { }
    virtual int retired ( int year )
     { return year > born + 63 - 2 * childs; }
 };
 

int main ( int argc, char * argv [] )
 {
   CPerson * people [2];
   int i;
   
   people[0] = new CMan   ( "Novak",  1948, 2 );
   people[1] = new CWoman ( "Novakova", 1948, 3 );
   
   for ( i = 0; i < 2; i ++ ) 
    cout << i << ". " << (people[i] -> retired ( 2005 ) ? "ano" : "ne") << endl;
   
   for ( i = 0; i < 2; i ++ ) 
    delete people[i];
   
   return ( 0 );
 }

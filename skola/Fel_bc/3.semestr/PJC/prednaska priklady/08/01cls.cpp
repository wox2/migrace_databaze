#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

class CCplx
 {
   public:
           CCplx(double r, double i=0) {re=r; im=i;}
           CCplx(const char * str)
     {
       if ( sscanf ( str, "%lf %lfi", &re, &im ) != 2 )
        {
          re = 0;
          im = 0;
        }
     }            
            
          ~CCplx(void) { }
    double Re   (void) {return re;}
    double Im   (void) {return im;}
    double Abs  (void) {return sqrt (re*re+im*im);}
    double Phi  (void) {return atan2 (im, re);}
   private:
    double re, im;
 };
 
int main ( int argc, char * argv [] )
 {
   CCplx  a ( 3, 4 );
   CCplx  b ( "5.25 -3.17i" );

   cout << a.Re()  << "+" << a.Im () << "i = " << 
           a.Abs() << "<" << a.Phi() << endl;  

   cout << b.Re()  << "+" << b.Im () << "i = " << 
           b.Abs() << "<" << b.Phi() << endl;  
  
   return ( 0 );  
 }

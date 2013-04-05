#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

class CCplx
 {
   public:
           CCplx(double r, double i=0); 
           CCplx(const char * str);
          ~CCplx(void);
    double Re   (void);
    double Im   (void);
    double Abs  (void);
    double Phi  (void);
   private:
    double re, im;
 };        
 
//------------------------------------------------------------------------------------------------- 
                   CCplx::CCplx                  (double r, double i)
 {
   re=r; 
   im=i;
 } 
//------------------------------------------------------------------------------------------------- 
                   CCplx::CCplx                  (const char * str)
 {
   if ( sscanf ( str, "%lf %lfi", &re, &im ) != 2 )
    {
      re = 0;
      im = 0;
    }
 }            
//------------------------------------------------------------------------------------------------- 
                   CCplx::~CCplx                 (void) 
 { 
  
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Re                     (void) 
 {
   return re;
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Im                     (void) 
 {
   return im;
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Abs                    (void) 
 {
   return sqrt (re*re+im*im);
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Phi                    (void) 
 {
   return atan2 (im, re);
 }
//------------------------------------------------------------------------------------------------- 
int main ( int argc, char * argv [] )
 {
   CCplx  * a = new CCplx ( 3, 4 );
   CCplx  * b = new CCplx( "10+2i" );

   cout << a -> Re()  << "+" << a -> Im () << "i = " << 
           a -> Abs() << "<" << a -> Phi() << endl;  

   cout << b -> Re()  << "+" << b -> Im () << "i = " << 
           b -> Abs() << "<" << b -> Phi() << endl;  
  
   return ( 0 );  
 }

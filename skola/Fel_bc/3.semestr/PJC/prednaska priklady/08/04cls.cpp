#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

class CCplx
 {
   public:                  
    static CCplx Add ( const CCplx & a, const CCplx & b ); 
    
           CCplx(double r=0, double i=0); 
           CCplx(const char * str);
          ~CCplx(void);
    double Re   (void) const;
    double Im   (void) const;
    double Abs  (void) const;
    double Phi  (void) const;
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
double             CCplx::Re                     (void) const
 {
   return re;
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Im                     (void) const 
 {
   return im;
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Abs                    (void) const
 {
   return sqrt (re*re+im*im);
 }
//------------------------------------------------------------------------------------------------- 
double             CCplx::Phi                    (void) const
 {
   return atan2 (im, re);
 }
//------------------------------------------------------------------------------------------------- 
CCplx              CCplx::Add                    ( const CCplx & a, const CCplx & b )
 {
   return CCplx ( a.re + b.re, a.im + b.im );
 }
//------------------------------------------------------------------------------------------------- 
int main ( int argc, char * argv [] )
 {
   CCplx  a ( 3, 4 );
   CCplx  b ( "10+2i" );
   CCplx  c;
   
   c = CCplx::Add ( a , b );
   
   cout << c.Re()  << "+" << c.Im () << "i = " << 
           c.Abs() << "<" << c.Phi() << endl;  
  
   return ( 0 );  
 }

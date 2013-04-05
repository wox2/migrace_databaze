#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

//-------------------------------------------------------------------------------------------------
double             integrateRectangle            ( double l, double h, int segments, double (*fn)(double) )
 {
   double sum = 0, w;
   int    i;
   
   w = ( h - l ) / segments;
   
   for ( i = 0; i < segments; i ++ )
    sum += fn ( l + i * w + w/2 ) * w;
    
   return ( sum ); 
 }
//-------------------------------------------------------------------------------------------------
double             integrateTrapezoid            ( double l, double h, int segments, double (*fn)(double) )
 {
   double sum = 0;
   int    i;
   double prev, w, now;
   
   prev = fn ( l );
   w = ( h - l ) / segments;
   
   for ( i = 0; i < segments; i ++ )
    {
      now = fn ( l + w * (i+1) );
      
      sum += ( prev + now ) * w / 2;
      prev = now;
    }  
    
   return ( sum ); 
 }
//-------------------------------------------------------------------------------------------------
double             integrateSimpson              ( double l, double h, int segments, double (*fn)(double) )
 {
   double sum = 0, w;
   int    i;
   double prev, now, mid;
   
   prev = fn ( l );
   w = ( h - l ) / segments;
   
   for ( i = 0; i < segments; i ++ )
    {
      mid = fn ( l + w * i + w /2 );
      now = fn ( l + w * (i+1) );
      
      sum += (prev + 4 * mid + now) / 6 * w;
      prev = now;
    }  
   return ( sum ); 
 }
//-------------------------------------------------------------------------------------------------
static void        integrateTest                 ( const char * str, double lo, double hi, int segments, double (*fn)( double ), double (*fnInteg)( double ) )
 {
   double exact, approx;
   
   cout << "Funkce " << str << endl;
   
   exact = fnInteg ( hi ) - fnInteg ( lo );
   cout << "Analyticke reseni:     " << exact << endl;
   approx = integrateRectangle ( lo, hi, segments, fn );
   cout << "Obdelnikova metoda:    " << approx << " (" << 100.0 * fabs ( exact - approx ) / (exact ? exact : 1) << ") %" << endl;
   approx = integrateTrapezoid ( lo, hi, segments, fn );
   cout << "Lichobeznikova metoda: " << approx << " (" << 100.0 * fabs ( exact - approx ) / (exact ? exact : 1) << ") %" <<endl;       
   approx = integrateSimpson   ( lo, hi, segments, fn );
   cout << "Simpsonova metoda:     " << approx << " (" << 100.0 * fabs ( exact - approx ) / (exact ? exact : 1) << ") %" <<endl;
 }
//-------------------------------------------------------------------------------------------------
static double      fnSquare                      ( double x )
 {
   return ( x * x );
 } 
//-------------------------------------------------------------------------------------------------
static double      fnCube3                       ( double x )
 {                         
   return ( x * x * x / 3 );
 } 
//-------------------------------------------------------------------------------------------------
static double      fnCosInv                      ( double x )
 {                         
   return ( - cos ( x ) );
 } 
//-------------------------------------------------------------------------------------------------
static double      fnxlogx                       ( double x )
 {                         
   return ( x * log ( x ) - x );
 } 
//-------------------------------------------------------------------------------------------------
int                main                          ( int argc, char * argv [] )
 {
   double lo, hi;
   int    segments;
   
   cout << "Zadej interval a pocet iteraci: a b n" << endl;
   cin . clear ();
   
   cin >> lo >> hi >> segments;
   if ( ! cin . good () )
    {
      cout << "Nespravny format" << endl;
      return ( 1 );
    }
   
   integrateTest ( "x^2", lo, hi, segments,     fnSquare, fnCube3 );
   integrateTest ( "e^x", lo, hi, segments,     exp,      exp  );
   integrateTest ( "sin (x)", lo, hi, segments, sin,      fnCosInv );
   integrateTest ( "ln (x)", lo, hi, segments,  log,      fnxlogx );
  
   return ( 0 );
 }
//-------------------------------------------------------------------------------------------------

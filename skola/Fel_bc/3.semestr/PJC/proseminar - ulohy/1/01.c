#include <stdio.h>

/* Verze bez kontroly vstupu. */
/*-----------------------------------------------------------------------------------------------*/
int                main                          ( int argc, char * argv [] ) 
 {
   double a, b, c;
   
   printf ( "Zadej cisla a b\n" );
   scanf  ( "%lf %lf", &a, &b );
   
   c = a + b;
   printf ( "%f + %f = %f\n", a, b, c );
   printf ( "%f - %f = %f\n", a, b, a-b );
   printf ( "%f * %f = %f\n", a, b, a*b );
   printf ( "%f / %f = %f\n", a, b, a/b );
  
   return ( 0 );
 } 
/*-----------------------------------------------------------------------------------------------*/

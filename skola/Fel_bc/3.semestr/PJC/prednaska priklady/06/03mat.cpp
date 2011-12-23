#include <iostream>
#include <iomanip>
using namespace std;


double ** allocMatrix ( int rows, int cols )
 {
   int i;
   double ** mat;

   mat = new double * [ rows ];
   for ( i = 0; i < rows; i ++ )
    mat[i] = new double [cols];
 
   return ( mat );
 }

void freeMatrix ( double ** mat, int rows )
 {
   int i;

   for ( i = 0; i < rows; i ++ )
    delete [] mat[i];

   delete [] mat;
 }

void printMatrix ( double ** mat, 
                   int rows, int cols )
 {
   int i, j;

   for ( i = 0; i < rows; i ++ )
    {
      for ( j = 0; j < cols; j ++ ) 
       cout << mat[i][j] << " ";
      cout << endl;
    }
 }
 
int main ( int argc, char * argv [] )
 {
   int       i, j, r, c;
   double ** mat;
   
   do 
    {
      cout << "Zadej velikost matice" << endl;
      cin . clear ();
      cin >> r >> c;
      if ( ! cin .good () )
       {
         cout << "Chyba formatu" << endl;
         return ( 1 );
       }
    } while ( r <= 0 || c <= 0 );
   
   mat = allocMatrix ( r, c );
   
   for ( i =0; i < r; i ++ )
    for ( j = 0; j < c; j ++ )
     {
       while ( 1 )
        {
          cin . clear ();
          cout << "Prvek ["<< (i+1) << "," << (j + 1) << "] = ";
          cin >> mat[i][j];
          if ( cin .good () ) break; // inner loop
          cout << "Chyba formatu - opakuj zadani" << endl;
        }  
     }  

   printMatrix ( mat, r, c );
   freeMatrix ( mat, r );
   
   return ( 0 );
 } 

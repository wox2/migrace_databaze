#include <iostream>
#include <iomanip>
using namespace std;

int main ( int argc, char * argv [] )
 {
   unsigned int u;
   int v;

   // pokud jsou jodnoty obou operandu zobrazitelne jak v 
   // signed tak v unsigned variante, je vse ok. Je ale hlaseno varovani.
   u = 10; v = 20;
   if ( u < v )  
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl; 

   if ( (int) u < v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  
                      
   if ( u < (unsigned int) v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  
   
   
   // pokud jsou oba operandy zobrazitelne v signed int, pomuze 
   // typecast na signed int. 
   u = 10; v = -1;
   if ( u < v )  // !!
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl; 

   if ( (int) u < v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  
                      
   if ( u < (unsigned int) v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  


   // pokud jsou oba operandy zobrazitelne v unsigned int, pomuze 
   // typecast na unsigned int (deje se implicitne, explicitni pretypovani
   // odstranuje varovani).
   u = 3000000000u;
   v = 200000;
   if ( u < v )  
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl; 

   if ( (int) u < v ) // !
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  
                      
   if ( u < (unsigned int) v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  

   // pokud oba operandy nejsou zobrazitelne ani v jednom 
   // ze signed/unsigned zaroven, je snaha marna.
   u = 3000000000u;
   v = -1;
   if ( u < v )  // !!
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl; 

   if ( (int) u < v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  
                      
   if ( u < (unsigned int) v ) 
     cout << u << " < " << v << endl;
    else
     cout << u << " >= " << v << endl;  

   return ( 0 );
 }

#include <iostream>
#include <iomanip>
using namespace std;

static int zbytek(int number){
   return (number-(number/10) *10);
}

int main ( int argc, char * argv [] ){
cout << "Zadejte dve nezaporna cisla:" << endl;
int x,y;
cin . clear ();
cin >> x >> y;
int help = x;
if ( (! cin . good ()) | x < 0 | y < 0 ) {cout << "Nespravny vstup." << endl; return (1);}
int length=0, y_length=0,x_length=0;
int soucet=0;
while (help>0){help/=10; x_length++;}
help = y;
while ( help > 0 ){ help /= 10 ; y_length++ ; }
length = x_length + y_length;
cout << setw ( length ) << x << endl << setw ( length - y_length ) << "." << y << endl;
for ( int i=0 ; i < length ; i++ ) cout << "-" ;
int mocnina = 1;
     for( int i=0 ; i<y_length ; i++ ){
		 cout << endl;
	     soucet += zbytek ( y ) * x * mocnina ;
	     cout << setw(length -i) << zbytek(y) *x;
	     mocnina *= 10;
		 y/=10;
   }
cout << endl;
for(int i=0;i<length;i++)cout << "-";
cout << endl << setw ( length ) << soucet << endl;
return (0);
}

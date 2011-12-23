#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

int mocnina_velikosti_vektoru(int prvni_x,int prvni_y){
    return prvni_x* prvni_x + prvni_y * prvni_y ;
} 
 
 int skalarni_soucin(int prvni_x,int prvni_y, int druhy_x, int druhy_y){
	return prvni_x * druhy_x + prvni_y * druhy_y;
}

 void najdi_prusecik (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int vektor1_x, int vektor1_y, int vektor2_x, int vektor2_y)
 {
			double l = (double)(vektor1_x * (y1-y3) + (x3 - x1)* vektor1_y)/(vektor1_x * vektor2_y - vektor2_x * vektor1_y); 
			if (l >= 0 && l <= 1 ) 
					{
						double k = (x3 + l * vektor2_x - x1)/ (double)vektor1_x;
						if (k >= 0 && k <= 1 ) 
							{ 
								cout << "Prusecik: " << x1 + k * vektor1_x<< ", " << y1 + k * vektor1_y << endl;
								return;
							}
					}
			cout << "Prusecik neexistuje." << endl;
			return;
 }

int main ( int argc, char * argv [] )
{
	cout << "Zadejte souradnice koncovych bodu prvni usecky:" << endl;
	int x1,y1, x2, y2, x3, y3, x4, y4;
	double mocnina_cos_fi;
	cin . clear ();
	cin >> x1 >> y1 >> x2 >> y2;
	if ( (! cin . good ()) | (x1 == x2 && y1 == y2))
		{
		cout << "Nespravny vstup." << endl; return (1);
		}
	cout << "Zadejte souradnice koncovych bodu druhe usecky:" << endl;
	cin >> x3 >> y3 >> x4 >> y4; 
	
	if ( (! cin . good ()) || ( x3 == x4 && y3 == y4 ))
				{
					cout << "Nespravny vstup." << endl; return (1);
				}
      
	int vektor1 [] = {x2-x1,y2-y1};
 	int vektor2 [] = {x4-x3,y4-y3};
	int soucin = skalarni_soucin( vektor1[0], vektor1[1], vektor2[0], vektor2[1] );
	mocnina_cos_fi =  (double)(soucin * soucin) / (( mocnina_velikosti_vektoru( vektor1[0], vektor1[1] ) * mocnina_velikosti_vektoru( vektor2[0], vektor2 [1]) )) ;
if (mocnina_cos_fi == 1 ) 
   {
	   int  vektor3 [] = {x3 - x1, y3 - y1 };
		if ( vektor3 [0]*vektor1[1] != vektor1[0]*vektor3[1] )
				{
				   cout << "Usecky jsou rovnobezne." << endl;
				}
				else 
				{
					int vektor4 [] = {x4 - x1, y4 - y1 };
					if ( (vektor3[0] == 0 && vektor3[1] == 0) || ((double) vektor3[0] / vektor1[0] <=1 && (double) vektor3[0]/vektor1[0] >= 0 ) ||((double) vektor4[0]/vektor1[0] <=1 && (double)vektor4[0]/vektor1[0] >= 0 ) || (double)vektor4[0]/vektor3[0]<0)
						{
							cout << "Usecky lezi na spolecne primce a prekryvaji se." << endl;
						}
							else 
							{
								cout << "Usecky lezi na spolecne primce a neprekryvaji se." << endl;
							}
				}
	}

if ( mocnina_cos_fi == 0 ) 
    {
			cout << "Usecky jsou na sebe kolme." << endl;
			najdi_prusecik(x1,y1,x2,y2,x3,y3,x4,y4,vektor1[0],vektor1[1],vektor2[0],vektor2[1]);
	}
if ( mocnina_cos_fi !=0 && mocnina_cos_fi != 1 ) 
	{
	   cout << "Usecky jsou ruznobezne." << endl;
	   najdi_prusecik(x1,y1,x2,y2,x3,y3,x4,y4,vektor1[0],vektor1[1],vektor2[0],vektor2[1]);
	}
return (0);
}

#include <iomanip>
#include <iostream>
#include <string>
using namespace std;

void del_2(int posl_c, string *vysl){
	if(posl_c%2==0) *vysl+=" 2,";
}

void del_3(int cif_soucet, string *vysl){
	if(cif_soucet%3==0) *vysl+=" 3,";
}


void del_4(int posl_dvojcisli, string *vysl){
	if(posl_dvojcisli%4==0) *vysl+=" 4,";
}

void del_5 (int posl_c, string *vysl){
	if (posl_c%5==0) *vysl+=" 5,";
}

void del_6 (int cif_soucet, int posl_c, string *vysl){
	if (cif_soucet%3==0 && posl_c%2==0) *vysl+=" 6,";
}

void del_7 (int zbytek, string *vysl){
	if (zbytek%7==0) *vysl+=" 7,";
}

void del_8 (int posl_trojcisli, string *vysl){
	if (posl_trojcisli%8==0) *vysl+=" 8,";
}

void del_9 (int cif_soucet, string *vysl){
	if ( cif_soucet % 9 == 0 ) *vysl+=" 9,";
}

void del_10 (int posl_cislo, string *vysl){
	if (posl_cislo%10==0) *vysl+=" 10,";
}

int main(int argc, char *argv){
	int ciferny_soucet=0;
	int posledni_cislo=0;
	int druhy_od_konce=0;
	int treti_od_konce=0;
	int zbytek_po_del_7=0;
	char ch=0;
	string vysl;
	signed int znaku = 0;
	while(1)
	{
		ch = cin . get();
		if (ch>='0' && ch <='9' && cin.good()) 
			{	
				znaku++;
				ch-='0';
				zbytek_po_del_7=(zbytek_po_del_7*10+ch)%7;
				ciferny_soucet+=ch;
				treti_od_konce = druhy_od_konce;
				druhy_od_konce=posledni_cislo;
				posledni_cislo=ch;
			} else
			{
				if ( ch=='\n') 
					{
					if(znaku!=0)
						break; else 
						{
						cout << "Nespravny vstup." << endl; 
						#ifndef __PROGTEST__
						system ( "pause" );
						#endif /* __PROGTEST__ */
						return(1);
						}
					} else
				cout << "Nespravny vstup." << endl; 
				#ifndef __PROGTEST__
					system ( "pause" );
				#endif /* __PROGTEST__ */
				return(1);
			}
			if (cin.eof()) {cout << "Nespravny vstup." << endl; return(1);}
	}
	vysl+="Cislo je delitelne: 1,";
	del_2(posledni_cislo,&vysl);
	del_3(ciferny_soucet,&vysl);
	del_4(posledni_cislo+druhy_od_konce*10, &vysl);
	del_5(posledni_cislo,&vysl);
	del_6(ciferny_soucet, posledni_cislo, &vysl);
	del_7(zbytek_po_del_7, &vysl);
	del_8( posledni_cislo + druhy_od_konce * 10 + treti_od_konce * 100 , &vysl);
	del_9(ciferny_soucet,&vysl);
	del_10(posledni_cislo,&vysl);
	int sz=vysl.length();
	vysl.resize(sz-1);
	cout << vysl <<endl;
	#ifndef __PROGTEST__
	system ( "pause" );
	#endif /* __PROGTEST__ */
}

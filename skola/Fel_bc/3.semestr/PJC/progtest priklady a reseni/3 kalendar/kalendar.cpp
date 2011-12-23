#include <iomanip>
#include <iostream>
#include <string>
using namespace std;

int posun_od_r_1752 (int year){
	/*int pocetPrestupnych=0;
	for (int k = 1 ; 1752 + 4 * k < year ; k++){
		pocetPrestupnych++;
	}
	for (int k = 1 ; 1700 + k * 100 < year ; k++){
		pocetPrestupnych--;
	}
	for (int k=1;1600+k*400<year;k++){
		pocetPrestupnych++;
	}
	return (pocetPrestupnych + ( year - 1752 ) * 365 ) % 7; */ 
	return (year-1752)*365 - (year/100-17)+ year/400 - 4;
}

int days_since_1_1 ( int month, int year){
	switch(month){
		case 1: printf("Leden %i\n", year);return 0;
		case 2: printf("Unor %i\n", year); return 31;
		case 3: printf("Brezen %i\n", year); return 59 + ( year%4== 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 4: printf("Duben %i\n", year); return 90 + (int)( year % 4 == 0 && ( year%100 != 0 || year % 400 == 0 ));
		case 5: printf("Kveten %i\n", year); return 120 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 6: printf("Cerven %i\n", year); return 151 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 7: printf("Cervenec %i\n", year); return 181 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 8: printf("Srpen %i\n", year); return 212 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 9: printf("Zari %i\n", year); return 243 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 10: printf("Rijen %i\n", year); return 273 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 11: printf("Listopad %i\n", year); return 304 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
		case 12: printf("Prosinec %i\n", year); return 334 + ( year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 ));
	}
	return 0;
}

void print_day (int first_day_in_month, int actual_day, int days_in_month){
	string day="";
	switch (actual_day){
		case 1: cout << "Po "; break;
		case 2: cout << "Ut "; break;
		case 3: cout << "St "; break;
		case 4: cout << "Ct "; break;
		case 5: cout << "Pa "; break;
		case 6: cout << "So "; break;
		case 7: cout << "Ne "; break;
	}
	if (actual_day < first_day_in_month ) cout << "   ";
	for (int i= 1 + actual_day - first_day_in_month ; i <= days_in_month ; i += 7 ){
		if(i >0)cout << " " << setw(2) << setfill('0') << i << setfill(' ');
	}
	cout << endl;
}

void print_month(int first_day_in_month, int days_in_month){
	print_day(first_day_in_month,1,days_in_month);
	print_day(first_day_in_month,2,days_in_month);
	print_day(first_day_in_month,3,days_in_month);
	print_day(first_day_in_month,4,days_in_month);
	print_day(first_day_in_month,5,days_in_month);
	print_day(first_day_in_month,6,days_in_month);
	print_day(first_day_in_month,7,days_in_month);
}

int main (int argc, char *argv) {
	int diff_to_beggining = 0 , year = 0 , month = 0, first_day_in_month = 0, days_in_month = 0 ; 
	cout << "Zadejte mesic a rok:" << endl;
	cin >> month >> year;

	if ( ( month >= 1 && month <= 12 && year >= 1752 ) && cin.good () )
	{
		diff_to_beggining = ( ( year - 1752 ) * 365 + (year-1)/4 - (1751/4) - ( (year-1) / 100 - 1751/100 ) + ((year-1)/ 400 - 1751 / 400)   + days_since_1_1( month , year ) ) % 7;
		switch (diff_to_beggining){
			case 0: first_day_in_month = 6; break;
			case 1: first_day_in_month = 7; break;
			case 2: first_day_in_month = 1; break;
			case 3: first_day_in_month = 2; break;
			case 4: first_day_in_month = 3; break;
			case 5: first_day_in_month = 4; break;
			case 6: first_day_in_month = 5; break;
		}
		switch (month){
			case 1: days_in_month = 31; break;
			case 2: days_in_month = 28 + (year % 4 == 0 && ( year % 100 != 0 || year % 400 ==0 )) ; break;
			case 3: days_in_month = 31; break;
			case 4: days_in_month = 30; break;
			case 5: days_in_month = 31; break;
			case 6: days_in_month = 30; break;
			case 7: days_in_month = 31; break;
			case 8: days_in_month = 31; break;
			case 9: days_in_month = 30; break;
			case 10: days_in_month = 31; break;
			case 11: days_in_month = 30; break;
			case 12: days_in_month = 31; break;
		}
		print_month(first_day_in_month,days_in_month);
	} 
	else{
		cout << "Nespravny vstup." << endl ;
		return(1);
		}
#ifndef __PROGTEST__
	system("pause");
#endif /*__ PROGTEST__ */
	return(0);
}

#include <iostream>
using namespace std;

int abs(int value){
	return (value>0?value:-value);
}

int main (){
	cout << abs(-2) << endl;
	cout << abs(2) << endl;
	cout << abs(0) << endl;
	return 0;
}
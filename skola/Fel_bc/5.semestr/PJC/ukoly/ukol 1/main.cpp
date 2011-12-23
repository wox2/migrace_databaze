/* 
 * File:   main.cpp
 * Author: woxie
 *
 * Created on 5. říjen 2010, 15:25
 */

#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

void bubblesort(vector <int> &v);
void printVector(const vector<int> &v);
bool binarySearch(const vector<int>&v, int value);
void loadNumbers(vector <int> &v);

int main(int argc, char** argv) {
    int value;
    vector<int> v1;
    loadNumbers(v1);
    cin >> value;
    bubblesort(v1);
   // printVector(v1);
    cout << binarySearch(v1,value) << endl;
   return 0;
}

/**
 *  sortovani od nejmensiho po nejvetsi
*/
void bubblesort(vector <int> &v){
    int temp=0;
    for(int i = v.size()-1; i > 0 ; i--){
        for (int j=0 ; j < i ;j++){
            if(v[j]>v[j+1]){
                temp = v[j];
                v[j]=v[j+1];
                v[j+1]=temp;
            }   
        }
    }
}
/**
 * vytiskne Vector
 */
void printVector(const vector<int>& v){
    for (unsigned int i=0;i<v.size();i++)cout << v[i] << " ";
    cout << endl;
}
/**
 * Prohleda Vector operaci binarniho puleni
 */
bool binarySearch(const vector<int> &v, int value){
    int high = v.size()-1;
    int low = 0;
    int temp=0;
    while(true){
        temp = (high + low + 1)/2;
        if(low > high) break;
        if (v[temp] == value) return true;
        if (v[temp] < value) {
            low = temp + 1;
        }
        else{
            high = temp - 1;
        }
    }
    return false;
}
/**
 * Nacte cisla do Vectoru
 * @param v
 */
void loadNumbers(vector <int> &v){
    char ch=' ';
    int temp=0;
    while(cin.get(ch) && ch!='\n'){
        if(ch==' '){
            v.insert(v.end(),temp);
            temp = 0;
        }
        else{
            temp = temp * 10 + ch - '0';
        }
    }
    v.insert(v.end(),temp);
}

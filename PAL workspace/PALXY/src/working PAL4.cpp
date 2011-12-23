//#include <string>
//#include <string.h>
//#include <iostream>
//#include <stdlib.h>
//#include <fstream>
//#include <vector>
//#include <queue>
//using namespace std;
//
//struct Gen{
//    /** Sequence of distances on DNA END Position, -1 if not find*/
//    int * errorsInDNA;
//    /**Characters sequence of Gen    */
//    string value;
//    /**Minimal error, could be helpful in minimalizing finding results */
//    int minimalError;
//    /** length of gen **/
//    int length;
//};
//
///**
// * List of used characters. Not needed during execution.
// */
//string alphabet;
//string dna;
//Gen *gens;
//int genCount;
//int temporaryMaxError;
//int maxError;
//bool hasSolution;
//int sum;
//
//void readData(){
//    sum = 0;
//    hasSolution = false;
//    cin >> alphabet;
//    cin >> dna;
//    cin >> genCount;
//    gens = new Gen[genCount + 1];
//    for(int i = 1 ; i < genCount + 1; i++){
//        gens[i].errorsInDNA = new int [dna.length() + 1];
//        cin >> gens[i].value;
//        gens[i].minimalError = -1;
//        gens[i].length = gens[i].value.length();
//        for(unsigned int j = 0 ; j < dna.length() + 1 ; j++){
//            gens[i].errorsInDNA[j]= -1;
//        }
//        sum +=gens[i].length;
//    }
//
//    cin >> temporaryMaxError;
//    maxError = temporaryMaxError;
//}
//
//void findPositions(Gen & gen){
//        unsigned int currentStartPosition = 0;
//        int distance;
//        bool findpos;
//        while(currentStartPosition + gen.length -1 < dna.length()){
//            findpos = true;
//            distance = 0;
//            for(int i = 0 ; i < gen.length ; i++){
//                if(dna[i + currentStartPosition] != gen.value[i]){
//                    distance++;
//                    if(distance > temporaryMaxError){
//                        findpos = false;
//                        break;
//                    }
//                }
//            }
//            if(findpos && distance <= temporaryMaxError){
//                gen.errorsInDNA[currentStartPosition + gen.length ] = distance;
//                if(distance < gen.minimalError){
//                    gen.minimalError = distance;
//                }
//                if(gen.minimalError == -1 ){
//                    gen.minimalError = distance;
//                }
//            }
//            currentStartPosition++;
//        }
//        if(gen.minimalError > 0){
//            temporaryMaxError -= gen.minimalError;
//        }
//}
//
//void process(){
//    for(int i = 1 ; i < genCount + 1 ; i++ ){
//        findPositions(gens[i]);
//    }
//}
//
//bool isInStack(int value, const vector<int>& stack){
//    for(unsigned int i = 0 ; i < stack.size() ; i++){
//        if(stack[i] == value){
//            return true;
//        }
//    }
//    return false;
//}
//
//void print(int sequenceStart, vector<int> stack){
//    cout << sequenceStart;
//    for(unsigned int i = 0 ; i < stack.size() ; i++){
//        cout << " " << stack[i];
//    }
//    cout << endl;
//}
//
//void DFS(vector<int> stack, int processedGen, int remainingErrors, int endOfProcessedGenPosition, int sequenceStart){
//    stack.push_back(processedGen);
//    if(stack.size() == (unsigned int)genCount){
//        //cout << "E:" <<  remainingErrors << " ";
//        if(remainingErrors >= 0){
//            hasSolution = true;
//            print(sequenceStart, stack);
//        }
//        return;
//    }
//    for(int i = 1 ; i < genCount + 1 ; i++){
//        if(i != processedGen && gens[i].errorsInDNA[gens[i].length + endOfProcessedGenPosition] != -1 &&
//                (unsigned int)(sequenceStart + sum -1) <= dna.length() &&
//                gens[i].errorsInDNA[gens[i].length + endOfProcessedGenPosition] <= remainingErrors && !isInStack(i, stack)){
//            DFS(stack, i, remainingErrors - gens[i].errorsInDNA[gens[i].length + endOfProcessedGenPosition], gens[i].length + endOfProcessedGenPosition, sequenceStart);
//        }
//    }
//}
//
//void checkConnectionsBetweenGens(){
//    vector<int> stack;
//    for(unsigned int i = 0 ; i < dna.length(); i++){
//        for(int j = 1 ; j < genCount + 1 ; j++ ){
//            if(gens[j].errorsInDNA[i]!= -1){
//                DFS(stack, j, maxError - gens[j].errorsInDNA[i], i, i - gens[j].length + 1);
//            }
//        }
//    }
//    if(!hasSolution){
//        cout << "0" << endl;
//    }
//}
//
//void writeResults(){
//    for (int i = 1 ; i < genCount + 1; i++){
//        cout << gens[i].value << endl;
//        for(unsigned int j = 0 ; j < dna.length() ; j++){
//            cout << gens[i].errorsInDNA[j] << " ";
//        }
//        cout << gens[i].minimalError << endl;
//    }
//}
//
//int main(){
//    readData();
//    process();
//    checkConnectionsBetweenGens();
////  writeResults();
//    return 0;
//}

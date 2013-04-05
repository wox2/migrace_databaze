
#include <iostream>
#include <vector>
#include <queue>
#include <climits> // definovany LONG_MAX
using namespace std;

// -------------------------------------------------------------------------
// GLOBALNE PREMENNE
// -------------------------------------------------------------------------

bool* visited;
long componentNumber, maxDistance;

long** adjMatrix;
long N1, N2, D, HD, nodeCount;

// -------------------------------------------------------------------------
// POMOCNE FUNKCIE
// -------------------------------------------------------------------------

// tato metoda naplni maticu susednosti hranami podla zadaneho vstupu
void fillAdjMatrix(long** adjMatrix, long N1, long N2, long D, long HD, long nodeCount) {
	
	// generujem vsetky kombinacie cisel od N1 do N2
	for (long i = 0; i < nodeCount; i++) {
		for (long j = i+1; j < nodeCount; j++) {
            
            // spocitam XOR tychto cisel - jednicky potom znamenaju rozdielnu 
            // hodnotu na danej pozicii
            long xorred = (i+N1) ^ (j+N1);
            
            int hammingDistance = 0;
            
            // spocitam jednicky na prvych D poziciach tak, ze ich postupne 
            // posuvam doprava a pocitam jednicky na poslednej pozicii (ich 
            // pocet je rovny hammingovej vzdialenosti)
            for (int k = 0; k < D; k++) {
                hammingDistance += (xorred & 1);
                xorred = xorred >> 1; 
            }
            
            // hranu musim zapisat v oboch smeroch (graf je neorientovany), 
            // inak bude mat DFS/BFS problem 
			if (hammingDistance <= HD) {
				adjMatrix[i][j] = 1;
				adjMatrix[j][i] = 1;
			}
		}
	}

}

// standardny rekurzivny DFS
void DFS_WALK(long u) {
	  visited[u] = true;
      
      for (long v = 0; v < nodeCount; v++) {
          if (adjMatrix[u][v]) {
              if (!visited[v]) {
                   DFS_WALK(v);             
              }
          }
      }
}

// BFS prechod grafu na najdenie priemeru. Zacne v startNode, do furthestNode 
// ulozi uzol, ktory je najvzdialenejsi a vrati jeho vzdialenost. Pracuje v 
// 2 krokoch:
//     1. krok - zacne v startNode (lubovolne zvoleny) a najde uzol, ktory je
//               od neho najvzdialenejsi. jeho index ulozi do furthestNode
//     2. krok - zacne vo furthestNode, ktory nasiel v 1. kroku a hlada 
//               najvzdialenejsi od neho. Jeho vzdialenost vrati ako priemer gr.
// Premenna firstStep urcuje, ktory krok bude vykonavat. Prvy krok automaticky
// vola druhy krok.
long getDistance(long startNode, long &furthestNode, bool firstStep) {
      queue<long> to_visit;
      
      // pole vzdialenosti, pre kazdy uzol potrebujem jeho najmensiu
      // vzdialenost od pociatocneho uzla
      long* distances = new long[nodeCount];
      long maxDistance = 0;
    
      for (long i = 0; i < nodeCount; i++) {
          visited[i] = false; 
          distances[i] = LONG_MAX; 
      }
    
      distances[startNode] = 0;
    
      to_visit.push(startNode);
    
      while(!to_visit.empty()) {
        long v = to_visit.front();  
        to_visit.pop();    
        
        if (!visited[v]) {
             visited[v] = true;
             
             for (long i = 0; i < nodeCount; i++) {
                 
                 // ak sa do uzla i da dostat nejakou hranou, a zaroven
                 // je to kratsia cesta nez doteraz najdena ...
                 if (adjMatrix[v][i] && distances[i] > distances[v]) {
                     distances[i] = distances[v] + 1;
                     
                     if (distances[i] > maxDistance) {
                        maxDistance = distances[i]; 
                        furthestNode = i;             
                     }
                     
                     to_visit.push(i);
                 }
             }            
        }               
      }
      
      delete[] distances;
    
      // ak sme v prvom kroku vypoctu, tak rekurzivne spustime vypocet
      // od najvzdialenejsieho uzla, ktory sme nasli
      if (firstStep) {
         return getDistance(furthestNode, furthestNode, false);               
      }
      
      // v druhom kroku vratime najdeny priemer grafu
      return maxDistance;
} 

// -------------------------------------------------------------------------
// MAIN
// -------------------------------------------------------------------------
int main( int argc, const char* argv[] )
{
    // -------------------------------------------------------------------------
    // nacitanie vstupu
    // -------------------------------------------------------------------------   
    cin >> N1 >> N2 >> D >> HD;
    
	nodeCount = N2-N1+1;

    // alokujem maticu susednosti
    adjMatrix = new long*[nodeCount];
        
    for (long i = 0; i < nodeCount; i++) {
            adjMatrix[i] = new long[nodeCount];

            for (long j = 0; j < nodeCount; j++) {
                 adjMatrix[i][j] = 0;
            }    
    } 
  
    // zavolame metodu na naplnenie matice susednosti hranami podla zadania
    fillAdjMatrix(adjMatrix, N1, N2, D, HD, nodeCount);
    
    // -------------------------------------------------------------------------
    // vypocet poctu komponent (DFS)
    // -------------------------------------------------------------------------
    
    // budeme potrebovat korene jednotlivych komponent (v pripade, ze graf
    // bude nesuvisly)
    vector<int> roots;
    
    visited = new bool[nodeCount];
    
    for (long i = 0; i < nodeCount; i++) {
        visited[i] = false; 
    }
    
    componentNumber = 0;
    
    // rekurzivny DFS na najdenie poctu komponent a ich rootov
    for (long i = 0; i < nodeCount; i++) {

		if (!visited[i]) {
			componentNumber++;
			
			// zapamatam si roota tejto komponenty
			roots.push_back(i);
			
            DFS_WALK(i);		 
        }
    }

	// -------------------------------------------------------------------------
    // vypocet premeru grafu
    // -------------------------------------------------------------------------

    // temp premenna na zasobniku
    long furthest;
    
    // spustim BFS od vsetkych rootov na najdenie priemeru grafu
    for (long i = 0; i < roots.size(); i++) {
        //zapamatam si najvyssiu hodnotu (spomedzi priemerov vsetkych komponent)
        maxDistance = max(maxDistance, getDistance(roots[i], furthest, true));    
    }
    
    // -------------------------------------------------------------------------
    // vypis vysledkov
    // -------------------------------------------------------------------------

	cout << componentNumber << " " << maxDistance;
    
	return 0;
}

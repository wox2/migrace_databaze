package pal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class Main {
	static final int MAX_ROZMER_FRONTY = 1000;
	
	static int [] strongComponents;
	static int [] strongComponentsRanks;
	
	static int [] weakComponents;
	static int [] weakComponentsRanks;
	
	
	static int nodesCount; 
	
	static Edge[] edges;
	static int edgesCount;
	static Edge[] adeptEdges;
	static int adeptEdgesCount;
	
	static Integer[] rset; //rooti vyslednych stromu Tarjanovy implementace
	
	/**
	 * Vstupni hrany do silnych komponent - prubezne udrzovane pro zisk maxima cyklu
	 */
	static Edge[] enterEdges;
	/**
	 * Fronty hran DO komponenty
	 */
	static BinomialHeap [] queues;
	/**
	 * Uzly, do kterych vede hrana
	 */
	
	static List<Integer> roots;
	
	static Edge maxInCycle;
	
	static List<List<Edge>> cycles; //objevene cykly 
	
	static List<Edge> forest;
	static List<Edge> leaf;   //leafes of F
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
        readData();
        process();
        writeResults();
        
    }
	
	private static void readData() throws NumberFormatException, IOException{
		edges = new Edge[MAX_ROZMER_FRONTY];
//		BufferedReader br = new BufferedReader(new FileReader(new File("edmondsData/soubor.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 nodesCount = Integer.parseInt(br.readLine());
		 edgesCount = 0;
		 while(readEdge(br)){			 
		 };
	}
	
	private static boolean readEdge(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int inputEdgeVal1 = Integer.parseInt(st. nextToken());
        int inputEdgeVal2 = Integer.parseInt(st. nextToken());
        int weight = Integer.parseInt(st. nextToken());
        if(inputEdgeVal1 == 0 && inputEdgeVal2 == 0 && weight == 0){
            return false;
        }
        edges[edgesCount] = new Edge(inputEdgeVal1, inputEdgeVal2, weight, nodesCount);
        edgesCount++;
        return true;
    }

	private static void process(){
		init();
		while(roots.size() > 0){
			//System.out.println("root:" + roots.get(0));
//			for(int i = 0 ; i < nodesCount; i++){
//				System.out.println("node:" + i + " weak:" + find(i, weakComponents) + " strong:" + find(i, strongComponents));
//			}
			
			Integer processedPseudoNode = roots.get(0);
			roots.remove(0);
			Edge processedEdge = queues[processedPseudoNode.intValue()].accessMin();
			if(processedEdge.equals(Edge.getDummyEdge())){
//				System.out.println("success");
				break;
			}
			
			// insert (u, v) as a node in f with any edges in cycle[r] as children
			 forest.add(processedEdge);
			 if(cycles.size() > processedPseudoNode && cycles.get(processedPseudoNode) != null && cycles.get(processedPseudoNode).size() > 0){
				 for(int i = 1 ; i < cycles.get(processedPseudoNode).size() ; i++){
				 	processedEdge.fChildren[i - 1] = cycles.get(processedPseudoNode).get(i);
			 	}
			 }
			 
			 if(cycles.size() == 0 || cycles.size() < processedPseudoNode || cycles.get(processedPseudoNode) == null){
				 leaf.add(processedEdge.to, processedEdge); 
			 }
//			 System.out.println("processedEdge:" + processedEdge.toString());
			 queues[processedPseudoNode.intValue()].deleteMin();
				
				// pokud jsou ve stejne silne komponente
				if(find(processedEdge.from, strongComponents) == find(processedEdge.to, strongComponents)){
					roots.add(processedPseudoNode);
//					System.out.println("skip");
					continue;
				}
				
				adeptEdges[adeptEdgesCount] = processedEdge;
				adeptEdgesCount++;
				
				//pokud jsou ve stejne slabe komponente
				if(find(processedEdge.from, weakComponents) == find(processedEdge.to, weakComponents)){
					List<Edge> cycleEdges = createCycle(processedEdge);
					cycles.add(cycleEdges);
					//prolezeni cyklu, cycleEdges[0] je processedEdge
					for(int i = 0 ; i < cycleEdges.size() ; i++){
						Edge iterateEdge = cycleEdges.get(i);
//						System.out.println("decrease: " + (iterateEdge.algorithmWeight - maxInCycle.algorithmWeight));
						int k = find(iterateEdge.to, strongComponents);
						decreaseKeys(k, iterateEdge.algorithmWeight - maxInCycle.algorithmWeight);
						queues[k].print();
						
						
						mergeQueues(processedPseudoNode, k);
//						System.out.println("union: " + processedPseudoNode + " s " + find(cycleEdges.get(i).to, strongComponents));
						union(k, processedPseudoNode ,strongComponents, strongComponentsRanks);
					}
					
					int m = find(maxInCycle.from, strongComponents);
					rset[processedPseudoNode]=m;
					roots.add(processedPseudoNode);
				}else{
					union(find(processedEdge.from, weakComponents), find(processedEdge.to, weakComponents), weakComponents, weakComponentsRanks);
					enterEdges[processedPseudoNode] = processedEdge;
					continue;
				}
					
			}
		
			deleteEdges();
		}
	
	static void deleteEdges(){
		for(int i = 0 ; i < cycles.size(); i++){
			
		}
	}
	
	/**
	 * 	Vytvori cyklus z hran, ktery uzavira hrana edge
	 * @param edge
	 * @return
	 */
	private static List<Edge> createCycle(Edge edge){
		 List<Edge> cycle = new ArrayList<Edge>();
			Edge cycleEdge = edge;
			maxInCycle = edge;
			while(cycleEdge != null){
//				System.out.println("edge in cycle:" + cycleEdge.toString());
				cycle.add(cycleEdge);
				int from = find(cycleEdge.from, strongComponents);
				cycleEdge = enterEdges[from];
				enterEdges[from] = null;
				if(cycleEdge != null && cycleEdge.algorithmWeight > maxInCycle.algorithmWeight){
					maxInCycle = cycleEdge;
				}
			}
//			System.out.println("max in cycle: " + maxInCycle.algorithmWeight);
			return cycle;
	}
	
	private static void mergeQueues(int destination, int source){
		queues[destination].mergeHeaps(queues[source]);
	}
	
	private static void decreaseKeys(int node, int value){
		queues[node].decreaseKeys(value);
	}
	
	private static void writeResults(){
//		for(int i = 0 ; i < adeptEdgesCount ; i ++){
//			System.out.println(adeptEdges[i]);
//		}
		
		
//		for(int i = ){
//			
//		}
//		
		
//		for(int i = 0 ; i < cycles.size() ; i++){
//			List<Edge> cycle = cycles.get(i);
//			boolean del = false;
//			
//			Edge maxEdge = null;
//			for(Edge edge : cycle){
//				if(edge.deleted ){
//					del = true;
//					break;
//				}
//				if(maxEdge == null){
//					maxEdge = edge;
//					continue;
//				}
//				if(edge.algorithmWeight> maxEdge.algorithmWeight){
//					maxEdge = edge;
//				}
//			}
//			if(!del){
//				maxEdge.deleted = true;
//			}
//		}
		
		int sum = 0;
		
		boolean isRoot[] = new boolean [nodesCount];
		for(int i = 0 ; i < nodesCount ; i++){
			isRoot[i] = true;
		}
		for(int i = 0 ; i < adeptEdgesCount ; i++){
//			System.out.println(adeptEdges[i].toString());
			if(!adeptEdges[i].deleted){
				if(adeptEdges[i].weight > 0){
					sum+= adeptEdges[i].weight;
				}
				isRoot[adeptEdges[i].to] = false;
			}
		}
		
		int rootCount = 0;
		int root= -500;
		for(int i = 0 ; i < nodesCount ; i++){
			if(isRoot[i]){
				rootCount++;
				root = i; 
//				System.out.println("i je root:"+ i);
			}
		}
		if(rootCount == 1){
			System.out.println(sum);
			System.out.println(root);
			for(int i = 0 ; i < adeptEdgesCount; i++){
				if(!adeptEdges[i].deleted){
					System.out.println(adeptEdges[i].from + " " + adeptEdges[i].to +" " + adeptEdges[i].weight);
				}
			}
			System.out.println("0 0 0");
		}else{
			System.out.println("-1");
			for(int i = 0 ; i < adeptEdgesCount; i++){
				if(!adeptEdges[i].deleted){
//					System.out.println(adeptEdges[i].from + " " + adeptEdges[i].to +" " + adeptEdges[i].weight);
				}
			}
		}
	}
		
	private static void init(){
		strongComponents = new int [nodesCount];
		strongComponentsRanks = new int [nodesCount];
		weakComponents = new int [nodesCount];
		weakComponentsRanks = new int [nodesCount];
		roots = new ArrayList<Integer> (nodesCount);
		adeptEdges = new Edge [edgesCount];
		enterEdges = new Edge [nodesCount];
		cycles = new ArrayList<List<Edge>>();
		forest = new ArrayList<Edge>();
		leaf = new ArrayList<Edge>();
		
		for(int i = 0 ; i < nodesCount; i++){
			makeSet(i, strongComponents, strongComponentsRanks);
			makeSet(i, weakComponents, weakComponentsRanks);
			roots.add(i);
		}
		
		queues = new BinomialHeap[nodesCount];
		for(int i = 0 ; i < nodesCount ; i++){
			queues[i] = new BinomialHeap(MAX_ROZMER_FRONTY);
		}
		for(int i = 0 ; i < edgesCount; i++){
			queues[edges[i].to].insert(edges[i]);
		}
		
		rset = new Integer[nodesCount];
		for(int i = 0 ; i < nodesCount ; i++){
			rset[i] = i;
		}
	}
	
	private static void makeSet(int x, int sets[], int[]ranks){
//      if(sets.length != ranks.length || sets.length < x){
//          throw new ArrayIndexOutOfBoundsException("too high value of x:" + x);
//      }
      sets[x] = x;
      ranks[x] = 0;
  }
  
  private static int find(int x, int [] setParents){
//      if( x > setParents.length || x <=0){
//          throw new ArrayIndexOutOfBoundsException();
//      }
      if(setParents[x]== x){
          return x;
      }
      setParents[x] = find(setParents[x], setParents);
      return setParents[x];
  }    

  private static void union(int source, int destination, int [] setParents, int [] ranks){
//      if( destination > setParents.length || target > setParents.length || destination <= 0 || target <= 0){
//          throw new IndexOutOfBoundsException();
//      }
      int sourceRoot = find( source, setParents);
      int destinationRoot = find(destination, setParents);
      if (sourceRoot == destinationRoot){
          return;
      }
      setParents[sourceRoot] = destinationRoot;
  }
	
	
	
}
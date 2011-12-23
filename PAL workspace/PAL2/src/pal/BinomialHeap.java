package pal;

public class BinomialHeap {
	public static final int MAX = 1000;
    private Node[] trees;
    private Node root; //minimum
    private int rank;

    
    public BinomialHeap(int rozsah){
    	trees = new Node[rozsah];
    }
    
    public BinomialHeap(Edge edge){
    	init();
    	Node node = new Node(edge);
    	root = node;
    	trees[0] = node;
    	rank = 0;
    }
    
    public void print(){
//    	System.out.println("----------------------------------------------------------");
    	for(int i = 0 ; i <= rank ; i++){
    		if(trees[i] != null){
    			trees[i].print();
    		}
    	}
    }
    
    public void decreaseKeys(int value){
    	for(int i = 0 ; i < rank ; i++){
    		if(trees[i] != null){
    			trees[i].decreaseKey(value);
    		}
    	}
    }
    
    
    
    public void init(){
    	trees = new Node[MAX];
    }
    
    public void insert(Edge edge){
    	BinomialHeap newHeap = new BinomialHeap(edge);
    	this.mergeHeaps(newHeap);
    }
    
    public void mergeHeaps(BinomialHeap heap){
    	int i = 0;
    	if(heap == null){
    		throw new RuntimeException("Pokus o merge heapu s null");
    	}
    	
    	if(heap == this){
    		//throw new RuntimeException("Pokus o merge heapu se sebou samotnou");
    		return;
    	}
    	Node iterateTree = null;
    	while(i <= this.rank || i <= heap.rank ){
    		if( this.trees[i] != null){
    			if(heap.trees[i] != null){
    				if(iterateTree != null){
    					//iterateTree != null ,heap.trees != null, this.trees != null
    					iterateTree = heap.trees[i].merge(iterateTree);
    					heap.trees[i] = null;
    				}else{
    					//iterateTree = null ,heap.trees != null, this.trees != null
    					iterateTree = heap.trees[i].merge(this.trees[i]);
    					heap.trees[i] = null;
    					this.trees[i] = null;
    				}
    			}else{
    				if(iterateTree != null){
    					//iterateTree != null ,heap.trees = null, this.trees != null
    					iterateTree = this.trees[i].merge(iterateTree);
    					this.trees[i] = null;
    				}else{
    					//iterateTree = null ,heap.trees = null, this.trees != null
    				}
    			}		
    		}else {
    			if(heap.trees[i] != null){
    				if(iterateTree != null){
    					//iterateTree != null ,heap.trees != null, this.trees = null
    					iterateTree = iterateTree.merge(heap.trees[i]);
    					heap.trees[i] = null;
    				}else{
    					//iterateTree = null ,heap.trees != null, this.trees = null
    					this.trees[i] = heap.trees[i];
    					heap.trees [i] = null;
    				}
    			} else{
    				if(iterateTree != null){
    					//iterateTree != null ,heap.trees = null, this.trees = null
    					this.trees[i] = iterateTree;
    					iterateTree = null;
    				}else{
    					//iterateTree = null ,heap.trees = null, this.trees = null
    				}
    			}
    		}
    		i++;
    	}
    	
    	if(iterateTree != null){
    		this.trees[i] = iterateTree;
    		this.rank = i;
    	} else{
    		this.rank = Math.max(this.rank, heap.rank);
    	}
    	//check it tohle ted nevidim
    	actualizeMinimum();
    }
    
    public Edge accessMin(){
    	if(root == null){
    		return Edge.getDummyEdge();
    	}
    	return root.getEdge();
    }
    
    public void deleteMin(){
    	if(root == null){
    		return;
    	}
    	if(root.rank == 0){
    		trees[0]= null;
    	}else{
    		if(this.rank == root.rank){
    			this.rank = root.rank - 1 ;
    		}
    		Node deletedNode = root;
    		trees[deletedNode.rank] = null;
    		
    		BinomialHeap newHeap = new BinomialHeap(MAX);
    		
    		for(int i = 0 ; i < deletedNode.rank ; i++){
    			newHeap.trees[i] = deletedNode.children[i];
    		}
    		newHeap.rank = deletedNode.rank - 1;
    		this.mergeHeaps(newHeap);
    	}
    	root = null;
    	actualizeMinimum();
    }
    
    private void actualizeMinimum(){
    	root = null;
    	for(int i = 0 ; i < rank + 1; i++){
    		if(trees[i] != null){
    			if(root == null){
    				root = trees[i];
    			}else{
    				if(((Node)root).compareTo((Node) trees[i]) == 1){
    					root = trees[i];
    				}
    			}
    		}
    	}
    }
    
}

class Node{
	private Edge edge; 
	public Edge getEdge() {
		return edge;
	}

	public Node parent;
	public Node []children;
	public int rank;
	
	public Node(Edge entity){
		this.children = new Node[BinomialHeap.MAX];
		this.edge = entity;
		parent = null;
		rank = 0;
	}
	
	public Node merge (Node merged){
		if(this.edge.compareTo(merged.edge) == -1){
			this.children[rank] = merged;
			merged.parent = this;
			this.rank++;
			return this;
		}
		merged.children[rank] = this;
		this.parent = merged;
		merged.rank++;
		return merged;
	}

	public int compareTo(Object o) {
		return this.edge.compareTo( ((Node)o).edge);
	}
	
	public void decreaseKey(int value){
		this.edge.algorithmWeight -= value;
		
		for(int i = 0 ; i < rank ; i++){
			children[i].decreaseKey(value);
		}
	}
	
	public void print(){
//		System.out.println("e:" + this.edge + " r:" + rank);
		for(int i = 0 ; i < rank ; i++){
			if(children[i] != null){
				children[i].print();
			}
		}
	}
	
	
}
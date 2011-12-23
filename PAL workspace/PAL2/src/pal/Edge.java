package pal;



class Edge implements Comparable<Edge>{
	public int from ;
	public int to ;
	public int weight ;
	public int algorithmWeight;
	
	public Edge fParent;
	public Edge [] fChildren;
	public boolean deleted;
	
	private static Edge dummyEdge;
	
	//dummyEdge
	public static Edge getDummyEdge(){
		if(dummyEdge == null){
			dummyEdge = new Edge(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0);
		}
		return dummyEdge;
	}
	
	public Edge(int input, int output, int weight, int maxChildren){
		this.from = input;
		this.to = output;
		this.weight = weight;
		this.algorithmWeight = weight;
		fChildren = new Edge[maxChildren];
	}

	@Override
	public int compareTo(Edge o) {
		if(this.algorithmWeight < o.algorithmWeight){
			return -1;
		}
		if(this.algorithmWeight > o.algorithmWeight){
			return 1;
		}
		return 0;
	}
	
	public String toString(){
		return "from:" + this.from + " to:" + this.to + " w:" + weight + " aw:"+algorithmWeight;
	}
	
	
}
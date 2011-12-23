package pal;

public class SimpleTest {
	public static void main(String args[]){
		BinomialHeap binomialHeap = new BinomialHeap(new Edge(0, 0, 5, 5));
		BinomialHeap binomialHeap2 = new BinomialHeap(100);
		binomialHeap.print();
		binomialHeap.insert(new Edge(0, 0, 3, 3));
		binomialHeap.print();
		binomialHeap.insert(new Edge(0, 0, 16, 16));
		binomialHeap.print();
		binomialHeap.insert(new Edge(0, 0, 19, 19));
		binomialHeap.print();
		binomialHeap.insert(new Edge(0, 0, 9, 9));
		binomialHeap.print();
		binomialHeap.insert(new Edge(0, 0, 8, 8));
		binomialHeap.print();
		binomialHeap2.insert(new Edge(0, 0, 23, 23));
		binomialHeap2.print();
		binomialHeap2.insert(new Edge(0, 0, 46, 46));
		binomialHeap2.print();
		binomialHeap2.insert(new Edge(0, 0, 11, 11));
		binomialHeap2.print();
		binomialHeap2.insert(new Edge(0, 0, 2, 2));
		binomialHeap2.print();
		binomialHeap2.insert(new Edge(0, 0, 1, 1));
		
		binomialHeap.mergeHeaps(binomialHeap2);
		Edge deleted = null;
		while(true){
			deleted = binomialHeap.accessMin();
			binomialHeap.deleteMin();
			if(deleted == Edge.getDummyEdge()){
				break;
			}
			System.out.println(deleted);
		}
		System.out.println("success");
	}
}


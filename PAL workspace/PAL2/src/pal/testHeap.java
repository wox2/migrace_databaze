package pal;

public class testHeap {
	public static void main(String args[]){
		final int NUMBER_OF_HEAPS = 3;
		final int nodes = 100;
		BinomialHeap heap[] = new BinomialHeap[NUMBER_OF_HEAPS];
		for(int i = 0 ; i < NUMBER_OF_HEAPS ; i++){
			heap[i] = new BinomialHeap((NUMBER_OF_HEAPS + 1)*nodes);
		}
		
		int i = 0;
		for(int k = 0 ; k < NUMBER_OF_HEAPS ; k++){
			for( ; i < (k+1)*nodes ; i++){
				heap[k].insert(new Edge(0, 0, i, i));
				heap[k].print();
			}
		}
		
		heap[0].mergeHeaps(heap[0]);
		
		for(int k = 1 ; k < NUMBER_OF_HEAPS ; k++){
			heap[0].mergeHeaps(heap[k]);
			heap[0].print();
		}
		
		for(int k = 0 ; k < NUMBER_OF_HEAPS * nodes; k++){
			if(heap[0].accessMin().algorithmWeight != k){
				System.out.println("error " + heap[0].accessMin());
				System.exit(1);
			} else{
				System.out.println(heap[0].accessMin());
				heap[0].deleteMin();
				heap[0].print();
			}
		}
	}
}

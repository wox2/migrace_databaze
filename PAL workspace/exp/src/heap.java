import java.util.Comparator;
import java.util.PriorityQueue;


public class heap {
	public static void main (String args[]){
		PriorityQueue<Integer> fronta = new PriorityQueue<Integer>(
				100, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1.intValue() > o2.intValue()){
					return -1;
				}
				if(o1.intValue() < o2.intValue()){
					return 1;
				}
				return 0;
			}
			
		}
				);
		
		fronta.add(new Integer(1));
		fronta.add(new Integer(2));
		fronta.add(new Integer(3));
		fronta.add(new Integer(4));
		fronta.add(new Integer(5));
		Object [] arr = fronta.toArray();
		for(int i = 0 ; i < arr.length ; i++){
			System.out.println(arr[i]);
		}
		System.out.println("---------------------------------------------------");
		while(fronta.peek() != null){
			System.out.println(fronta.poll());
		}

	}
}

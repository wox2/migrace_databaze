package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static int a;
	public static int b;
	public static int c;
	public static int [] max;
	public static int [] min;
	public static int [] t;
	public static int [] v;
	public static int [] r;
	public static int providersCount;
//	public static boolean queueCreated;
	public static List providerList[];
	public static MyPriorityQueue<Node>queue;	
	public static int wrong;	
	public static Map<Key, MyPriorityQueue<Node>> map;
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		process(br);
		printResults();
	}

	public static void printResults(){		
		System.out.println(wrong);
	}
	
	public static void process(BufferedReader br) throws IOException{
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int order = Integer.parseInt(tokenizer.nextToken());
//		queueCreated = false;
		while(order != 3){
			if(order == 0){
				a = Integer.parseInt(tokenizer.nextToken());
				b = Integer.parseInt(tokenizer.nextToken());
				c = Integer.parseInt(tokenizer.nextToken());
				Key key = new Key(a, b, c);
				
				if(!map.containsKey(key)){
					queue = new MyPriorityQueue<Node>(10, 
					new MyComparator(key), key);
					for(int i = 1 ; i < providersCount + 1 ; i++){
						providerList[i].inserted = false;
					}
					for(int i = 1 ; i < providersCount+1 ; i++){
						Node head = providerList[i].head;
						if(head != null && providerList[i].inserted == false){
							queue.add(head);
							providerList[i].inserted = true;
						}
					}					
					map.put(key, queue);
				} else{
					queue = map.get(key);
				}
			} else if(order == 2){
				
				int toProduction = Integer.parseInt(tokenizer.nextToken());
				Node top;
				while(toProduction > 0){
					top = queue.peek();
					if(top.removed){
						queue.remove();
						continue;
					}
					if(top.count <= toProduction){
						toProduction -= top.count;
						
						if(isWrong(top)){
							wrong += top.count;
						}
						
						providerList[top.provider].removeTop();
						top.removed = true;
						queue.poll();
						if(providerList[top.provider].head != null ){
							queue.add(providerList[top.provider].head);
						}						
					}else{
						queue.peek().count -= toProduction;
						if(isWrong(queue.peek())){
							wrong += toProduction;
						}
						toProduction = 0;
					}
				}
			} else if(order == 1){
				int provider = Integer.parseInt(tokenizer.nextToken());
				Node formerTop = providerList[provider].head;
						
				int amountTotal = Integer.parseInt(tokenizer.nextToken());
				tokenizer.nextToken();
				while(amountTotal > 0){
					int amount = Integer.parseInt(tokenizer.nextToken());
					int rate = Integer.parseInt(tokenizer.nextToken());
					amountTotal -= amount;
					providerList[provider].insert(rate, amount, provider);
				}
				
				if(formerTop != providerList[provider].head){
					Set<Key> keys = map.keySet();
					Object clonedKeys [] = keys.toArray();
					
					
					for(Object key: clonedKeys){
						MyPriorityQueue<Node> iterateOver = map.get((Key)key);
						
						//map.remove(iterateOver.key);
						iterateOver.add(providerList[provider].head);
						//map.put((Key)key, iterateOver);
					}
				}
			}
			tokenizer = new StringTokenizer(br.readLine());
			order = Integer.parseInt(tokenizer.nextToken());
		}
	}
	
	public static boolean isWrong(Node node){
		return node.rate < (max[node.provider] + min[node.provider] + 1)/2;
	}
	
	public static void init(BufferedReader br) throws IOException{		
		wrong = 0;
		providersCount = Integer.parseInt(br.readLine());
		max = new int[providersCount+1];
		min = new int[providersCount+1];
		t = new int[providersCount+1];
		v = new int[providersCount+1];
		r = new int[providersCount+1];
		providerList = new List[providersCount+1];
		for(int i = 1 ; i < providersCount+1 ; i++){
			providerList[i] = new List();
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(tokenizer.nextToken());
			v[i] = Integer.parseInt(tokenizer.nextToken());
			r[i] = Integer.parseInt(tokenizer.nextToken());
			min[i] = Integer.parseInt(tokenizer.nextToken());
			max[i] = Integer.parseInt(tokenizer.nextToken());
		}
		map = new TreeMap<Key, MyPriorityQueue<Node>>();
		
	}	
}

final class Node{
	public Node next;
	public int rate;
	public int count;
	public int provider;
	
	public Node(){
		removed = false;
	}
	
	public boolean removed;
	
	public void print(){
		System.out.println("rate:" + rate + " count:" + count + " provider:" +provider + " a" );
		if(next != null){
			next.print();
		}

	}
}

final class Key implements Comparable<Key>{
	public int a;
	public int b;
	public int c;
	
	public Key(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Key)){
			return false;
		}
		Key kObj = (Key)obj;
		return kObj.a == this.a && kObj.b == this.b && kObj.c == this.c;
	}

	@Override
	public int compareTo(Key o) {
		if(o.a < this.a || (o.a == this.a && o.b < this.b) || (o.a == this.a && o.b == this.b && o.c < this.c)){
			return -1;
		}
		if(o.a > this.a || (o.a == this.a && o.b > this.b) || (o.a == this.a && o.b == this.b && o.c > this.c)){
			return 1;
		}
		return 0;
	}
}

final class MyComparator implements Comparator<Node>{
	public final Key key;
	public MyComparator(Key key) {
		super();
		this.key = key;
		}
		
		@Override
		public int compare(Node o1, Node o2) {
			int quality = key.a * Main.t[o1.provider] + key.b * Main.v[o1.provider] + key.c * Main.r[o1.provider];
			int qualityCompared = key.a * Main.t[o2.provider] + key.b * Main.v[o2.provider] + key.c * Main.r[o2.provider];
			if(quality < qualityCompared){
				return 1;
			} else if(quality > qualityCompared){
				return -1;
			}
			
				return o1.provider > o2.provider ? -1: 1;
		}

					
	
}

final class List{
	public Node head;
	public boolean inserted;
	public List (){
	}
	
	public void insert(int rate, int count, int provider){
		Node newNode = new Node();
		newNode.count = count;
		newNode.provider = provider;
		newNode.rate = rate;
		if(head == null){
			head = newNode;
			return;
		}
		Node iterateNode = head;
		while( iterateNode.next != null && iterateNode.next.rate > rate ){
			iterateNode = iterateNode.next;
		}
		if(head.rate < rate){
			newNode.next = head;
			head = newNode;
			return;
		}

		Node successor = iterateNode.next;
		iterateNode.next = newNode;
		newNode.next = successor;		
	}
	
	public void removeTop(){
		if(head.next == null){
			head = null;
			return;
		}
		Node temp = head.next;
		head.next = null;
		head = temp;
	}
	
	public void print(){
		if(head != null){
			head.print();
		}
	}
}

final class MyPriorityQueue<E> extends PriorityQueue<E>{
	public Key key;
	public MyPriorityQueue(int size, Comparator<? super E> comp, Key key){
		super(size, comp);
		this.key = key;
	}
	
}
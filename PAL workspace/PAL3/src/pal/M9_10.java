package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
	public static boolean queueCreated;
	public static List providerList[];
	public static PriorityQueue<Node>queue;	
	public static int wrong;	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		process(br);
//		for(int i = 1 ; i < providersCount+1 ; i++){
//			providerList[i].print();
//		}
		printResults();
	}

	public static void printResults(){		
		System.out.println(wrong);
	}
	
	public static void process(BufferedReader br) throws IOException{
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int order = Integer.parseInt(tokenizer.nextToken());
		queueCreated = false;
		while(order != 3){
//			printQueues();
//			System.out.println("order:" + order+ "----------------------------------");
			if(order == 0){
				a = Integer.parseInt(tokenizer.nextToken());
				b = Integer.parseInt(tokenizer.nextToken());
				c = Integer.parseInt(tokenizer.nextToken());
				queueCreated = false;
//				queue.clear();
//				queue = null;
			} else if(order == 2){
//				System.out.println(a +" " + b + " " + c);
//				if(!queueCreated){
				if(true){
					queue = new PriorityQueue<Node>(10, new Comparator<Node>() {
						@Override
						public int compare(Node o1, Node o2) {
							int quality = a * t[o1.provider] + b * v[o1.provider] + c * r[o1.provider];
							int qualityCompared = a * t[o2.provider] + b * v[o2.provider] + c * r[o2.provider];
							if(quality < qualityCompared){
								return 1;
							} else if(quality > qualityCompared){
								return -1;
							}
							
//							if(quality == qualityCompared && min[o1.provider] == min[o2.provider] && max[o1.provider] == max[o2.provider]){
//								if(o1.rate > o2.rate) return -1;
//								if(o1.rate < o2.rate) return  1;
//							}
							
							
								return o1.provider > o2.provider ? -1: 1;
//							return 0;
						}
						
					});
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
					queueCreated = true;
				}
				
				int toProduction = Integer.parseInt(tokenizer.nextToken());
				Node top;
				while(toProduction > 0){
					
					top = queue.peek();
					if(top.count <= toProduction){
						toProduction -= top.count;
						
						if(isWrong(top)){
							wrong += top.count;
//							top.print();
//							System.out.println("Flooded " + top.count);
						}
						
						providerList[top.provider].removeTop();
						queue.poll();
						if(providerList[top.provider].head != null ){
							queue.add(providerList[top.provider].head);
						}
//						if( providerList[top.provider].head != null){
//							
//							top.count = providerList[top.provider].head.count;
//							top.rate = providerList[top.provider].head.rate;
//							top.next = providerList[top.provider].head.next;
//							providerList[top.provider].head = top;
//						}else{
//							queue.poll();
//						}
						
					}else{
						queue.peek().count -= toProduction;
						if(isWrong(queue.peek())){
							wrong += toProduction;
//							System.out.println("Flooded " + toProduction);							
						}
						toProduction = 0;
					}
				}
			} else if(order == 1){
				int provider = Integer.parseInt(tokenizer.nextToken());
				boolean insert = providerList[provider].head == null;
						
				int amountTotal = Integer.parseInt(tokenizer.nextToken());
				tokenizer.nextToken();
				while(amountTotal > 0){
					int amount = Integer.parseInt(tokenizer.nextToken());
					int rate = Integer.parseInt(tokenizer.nextToken());
					amountTotal -= amount;
					providerList[provider].insert(rate, amount, provider);
				}
				if(insert && queueCreated){
					queue.add(providerList[provider].head);
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
		
//		mergeProviders();
	}
	
	private static void printQueues(){
		for(int i = 1 ; i < providersCount ; i++){
				providerList[i].print();
		}
	}
	
	private static void mergeProviders(){
		boolean chosen [] = new boolean [providersCount + 1];
		
		for(int i = 0 ; i < providersCount + 1;i++){
			chosen[i] = false;
		}
		
		chosen[0] = true;
		for(int j = 1 ; j < providersCount + 1; j++){
			if(chosen[j] == true){
				continue;
			}
			for(int i = j + 1 ; i < providersCount + 1; i++){
				if(chosen[i] == false && max[i]== max [j] && r[i] == r[j] && t[i] ==  t[j] && v[i] == v[j]){
					while(providerList[i].head != null){
						providerList[j].insert(providerList[i].head.rate, providerList[i].head.count, providerList[j].head.provider);
						providerList[j].removeTop();
					}
					chosen[i] = true;
					providerList[i] = providerList[j];
				}
				
			}
		}
	}
}

final class Node{
	public Node next;
	public int rate;
	public int count;
	public int provider;
	
	public void print(){
		System.out.println("rate:" + rate + " count:" + count + " provider:" +provider + " a" );
		if(next != null){
			next.print();
		}

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

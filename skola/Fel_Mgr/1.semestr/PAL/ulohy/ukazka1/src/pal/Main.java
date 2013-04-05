package pal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int crossCount;
	static int [][] crossMatrix;
	static int roadCount;
	static boolean success;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		read();
		for(int i = 0 ; i < crossCount ; i++){
			if(success){
				break;
			}
			ArrayList <Integer> sequence = new ArrayList<Integer>();
			sequence.add(i);
			dfs(crossMatrix, i, 0, sequence);
		}
		if(! success){
			System.out.println(-1);
		}
	}
	
	public static void read() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		crossCount = Integer.parseInt(br.readLine());
		crossMatrix = new int [crossCount][crossCount];
		roadCount = 0;
		success = false;
		int from =  -1;
		int to = -1;
		while(true){
			StringTokenizer tok = new StringTokenizer(br.readLine());
			from = Integer.parseInt(tok.nextToken());
			to = Integer.parseInt(tok.nextToken());
			if(from == 0 && to == 0){
				return;
			}
			crossMatrix[from][to]++;
			roadCount++;
		}
	}
	
	public static void dfs(int [][] matrix, int actualCrossroad, int actualRoadCount, ArrayList<Integer> order){
		if(success == true){
			return;
		}
		for(int i = 0 ; i < matrix.length ; i ++){
			if(matrix[actualCrossroad][i] > 0){
				int [][] copyMatrix = copyArray(matrix);

				copyMatrix[actualCrossroad][i]= copyMatrix[actualCrossroad][i] - 1;
				ArrayList<Integer> copy = copyList(order);
				copy.add(i);
				dfs(copyMatrix, i, actualRoadCount + 1, copy);
			}
		}
		if(actualRoadCount == roadCount){
			success = true;
			printResult(order);
			return;
		}
	}
	
	public static ArrayList<Integer> copyList(ArrayList<Integer> list){
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for(int i = 0 ; i < list.size() ; i ++){
			copy.add(list.get(i));
		}
		return copy;
	}
	
	public static void printResult(ArrayList<Integer> order){
		StringBuilder builder = new StringBuilder(crossCount);
		builder.append("\n");
		for(int i = 0 ; i < order.size() -1 ; i++){
			builder.append(order.get(i));
			builder.append(" ");
			builder.append(order.get(i + 1));
			builder.append("\n");
		}
		builder.append("0 0\n");
		System.out.println(builder.toString());
	}

	public static int[][] copyArray(int [][] matrix){
		int [][] copyArr = new int [matrix.length][matrix[0].length];
		for(int i = 0 ; i < matrix.length ; i++){
			for(int j = 0 ; j < matrix[i].length ; j++){
				copyArr[i][j] = matrix[i][j];
			}
		}
		return copyArr;
	}
}

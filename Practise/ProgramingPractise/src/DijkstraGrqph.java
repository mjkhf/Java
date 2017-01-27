//Dijkstras Algorithm plus running time and the proof that the algo does not work for negative edge weights
import static java.lang.Integer.MAX_VALUE;

import java.util.*;

public class DijkstraGrqph{
	static long startTime = System.currentTimeMillis();
	public static void main(String args[]){
		
		
		int matrix[][] = new int[4][4]; // declare the vertices matrix
		int [] dist = new int[4];
		int[] parsed = new int[4];
		int[] previous = new int[4];
		int minimum;
		int nextNode = 0;
		
		for (int i=0; i<4;i++){ //initialize parent and visited vertices
			parsed[i] = 0;
			previous[i]=0;
		}
		
		for(int i = 0; i<4; i++){  // for all vertices in matrix
	   		for(int j = 0; j<4; j++)
	   		{
	   			if( i!=j && matrix[i][j]!=MAX_VALUE )  // create random edge weights for the graph
	   			{
	   				Random rand = new Random();
	   				matrix[i][j] = matrix[j][i]=(rand.nextInt(100)-20);
	   			}
	   			
	   		}}
		
		for (int i = 0; i < 4; i++) {  // print the matrix
		    for (int j = 0; j < 4; j++) {

		        System.out.print(matrix[i][j] + " ");

		    }
		    System.out.println();
		    
		}
		
		dist = matrix[0];
		dist[0] = 0;
		parsed[0]=1;
		for (int i = 0; i<4;i++){
			minimum = 999;							//Initializing with infinity
			for (int j=0;j<4;j++){
				if(minimum > dist[j] && parsed[j] != 1  ){
					minimum = dist[j];
					nextNode = j;
				}
			}
			parsed[nextNode] = 1;
			
			for (int a = 0; a<4;a++){
				if (parsed[a] != 1){
					if(minimum+matrix[nextNode][a] < dist[a]){
						dist[a] = minimum+matrix[nextNode][a];
						previous[a] = nextNode;
						
					}
		}

			}
		}
		
		for (int i =0;i<4;i++){
			if(dist[i]>0)
			System.out.print("   " + dist[i]);
			else 
				System.out.println("");
		}
		System.out.println("|");
		
		for(int i= 0; i<4;i++){
			int j;
			if(dist[i]>=0){
			System.out.print("Visited Path = "+i);
			j = i;
			
			do{												//Detecting negative edge weight cycle
				j=previous[j];
				System.out.print(" <-- " +j);
			}while(j != 0);}
			else
				System.out.println("Negative edge weight = yes, exit!!!!");
			System.out.println();
			long endTime   = System.currentTimeMillis();
		 	long totalTime = endTime - startTime;
		 
		 	System.out.println("\nThe running time for shortest path is " + totalTime + " ms");
			
			
		}
		
	}
	
}
// kruskal

import java.util.*;

public class KruskalGraph {
	static long startTime = System.currentTimeMillis();    // calculate running time of the program
	
	
  public static void main(String[] args){
	  
//	Scanner scan = new Scanner(System.in);
	 // int[][] matrix = new int[5][5];
	  int[] ancestor = new int[5];
	  int minimum;
	  int u=0;
	  int v=0;
	  int Edge_Count = 1;
	  int total = 0;
	  

	  int matrix[][] = new int[][] {{0, 1, 0, 0, 0},  //starting from the first node
          {1, 0, 0, 0, 0},
          {0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0},
         };
         
         int w= 0;
 	for(int i=1;i<4;i++) //generating the random edge weights
 	{
 		Random rand = new Random();

 		int a11 = rand.nextInt(i)+1;
 		w = rand.nextInt(5)+1;
 		matrix[i+1][a11] = w;
 		matrix[a11][i+1] = w;
 		
 	}

 	System.out.println( " ");
 	for(int i = 0; i<5; i++){
 		for(int j = 0; j<5; j++)
 		{
 			if(matrix[i][j] == 0 && i!=j)
 			{
 				Random rand = new Random();
 				matrix[i][j] = matrix[j][i]=rand.nextInt(100)+20;
 			}
 		}}
 	
 	for (int i = 0; i < 5; i++) { //print the adjacency matrix
	    for (int j = 0; j < 5; j++) {
	        System.out.print(matrix[i][j] + " ");
	    }
	    System.out.println();
	    
	}
 	
	  
	  while (Edge_Count <5 ){
		  minimum = 999; //intially allocate infinite values
		  for (int i =0;i<5;i++){
			  for (int j=0;j<5;j++){
				  if (matrix[i][j] < minimum){
					  minimum = matrix[i][j];
					  u = i;
					  v = j;
				  }
			  }
		  }
		  while(ancestor[u]!=0){
			  u=ancestor[u];
		  }
		  while(ancestor[v]!=0){
			  v=ancestor[v];
		  }
		  if (v!=u){   								// find edges while parsing the vertices
			  Edge_Count++;
			  System.out.println("Edge between vertices" + u + "-->" + v + " Min " +minimum );
			  total+=minimum;
			  ancestor[v]=u;
		  }
		  matrix[u][v] = matrix[v][u] = 999;
	  }
	  	System.out.println("------------------------");
		  System.out.println("The Weight of the created Minimum Spanning tree is "+total);		//Print Weight for minimum spanning tree
		  long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("------------------------");
			System.out.println("Run time of the program is " + totalTime + " ms");			//report the running time of program
  }

}
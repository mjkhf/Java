//Transitive Closure plus running time

import java.util.*;


class TransitiveClosure
{
	long startTime = System.currentTimeMillis();
 final static int V = 4; //Number of vertices in a graph
 void transitiveClosure(int graph[][])			//Print transitive closure
 {

     int reach[][] = new int[V][V];			//Shortest distance between pair of matrices
     int  i, j, k;


     for (i = 0; i < V; i++)				//initialize the matrix
         for (j = 0; j < V; j++)
             reach[i][j] = graph[i][j];

 
     for (k = 0; k < V; k++)				//parse matrices one by one
     {
         // Pick all vertices as source one by one
         for (i = 0; i < V; i++)
         {
             for (j = 0; j < V; j++)		//take all the vertices as destinations
             {
 
                 reach[i][j] = (reach[i][j]!=0) ||		//if we find a path to some vertice from another we enter 1 in corresponding place
                          ((reach[i][k]!=0) && (reach[k][j]!=0))?1:0;
             }
         }
     }

     
     printSolution(reach);				//print matrix
 }


 void printSolution(int reach[][])
 {
     System.out.println("transitive closure"+
                        " for the graph");
     for (int i = 0; i < V; i++)
     {
         for (int j = 0; j < V; j++)
             System.out.print(reach[i][j]+" ");
         System.out.println();
     }
     long endTime   = System.currentTimeMillis();
 	long totalTime = endTime - startTime;
 
 	System.out.println("\nThe run time of program is... " + totalTime + " ms");
 }

 // Driver program to test above function
 public static void main (String[] args)
 {
	 int graph[][] = new int[][] {
		 {0, 1, 0, 0, 0},
         {1, 0, 0, 0, 0},
         {0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0},
        };
        //int a11 = 0; 
        int w= 0;
	for(int i=1;i<4;i++)
	{
		Random rand = new Random();

		int a11 = rand.nextInt(i)+1;
		w = rand.nextInt(5)+1;
		graph[i+1][a11] = w;
		graph[a11][i+1] = w;
		
	}
	
	System.out.println( " ");
	Random rand = new Random();
	for(int i = 0; i<5; i++){
		for(int j = 0; j<5; j++)
		{
			if(graph[i][j] == 0 && i!=j)
			{
				
				graph[i][j] = rand.nextInt(100)+20;
				
			}
		}}
	int x=rand.nextInt(5);
	for (int i=0;i<5;i++){
		for(int j = 0; j<rand.nextInt(5); j++)
	{
		graph[x][j]=0;
	}}
	for (int i=0;i<5;i++){
		for(int j = 0; j<rand.nextInt(5); j++)
	{
		graph[i][x]=0;
	}}
	for (int i = 0; i < 5; i++) {
	    for (int j = 0; j < 5; j++) {
	        System.out.print(graph[i][j] + " ");
	    }
	    System.out.println();
	}
      
    TransitiveClosure g = new TransitiveClosure();    	//transitive matrix
      g.transitiveClosure(graph);
 }
}
//Prims algorithm plus running time
 
import java.util.*;

 

class Prims
{
	static long startTime = System.currentTimeMillis();
       private static final int V=4;		// Total  vertices in the graph

    int min(int key[], Boolean mstSet[])  // Find Min Key value
    {
        
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int a = 0; a < V; a++)
            if (mstSet[a] == false && key[a] < min)
            {
                min = key[a];
                min_index = a;
            }
 
        return min_index;
    }
 
    
    void printMST(int parent[], int n, int graph[][]) //Function to print constructed Minimum Spanning tree
    {
        System.out.println("Edge    Weight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i]+" to "+ i+"    "+
                               graph[i][parent[i]]);
    }
 
   
    void primMST(int graph[][]) // Print the adjacency matrix for Minimum spanning tree
    {
        
        int parent[] = new int[V]; // Array to store tree
 
        
        int key[] = new int [V]; // pick minimum edge weight
 
        
        Boolean mstSet[] = new Boolean[V]; // Vertices not yet considered in minimum spanning tree
 
        
        for (int i = 0; i < V; i++) // Enter infinite initially
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        
        key[0] = 0;     // Key declared as 0
                        
        parent[0] = -1; // Root will have no parent
 
        
        for (int count = 0; count < V-1; count++) // For all the vertices of tree
        {
            
            int u = min(key, mstSet); //Choose minimum value and continue searching
 
           
            mstSet[u] = true; // add the vertex to set
 
            
            for (int v = 0; v < V; v++) //considering vertices which are not already visited
 
               
                if (graph[u][v]!=0 && mstSet[v] == false && // update the key value only when the condition follows
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 
        
        printMST(parent, V, graph); // print function
    }
    private static double[][] RandomArray(int n) {
        double[][] randomMatrix = new double [n][n];

        Random rand = new Random(); 
        rand.setSeed(System.currentTimeMillis()); 
        for (int i = 0; i < n; i++) {     
            for (int j = 0; j < n; j++) {
                Integer r = rand.nextInt()% 100; 
                randomMatrix[i][j] = Math.abs(r);
            }

        }
        System.out.println("  "+randomMatrix);
        return randomMatrix;
    }
 
    public static void main (String[] args)
    {
        
        Prims t = new Prims();
    	int graph[][] = new int[][] {{0, 1, 0, 0}, //creating the first node [0 <---> 1]
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
           };
           int w= 0;
   	for(int i=1;i<3;i++)
   	{
   		Random rand = new Random();

   		int a11 = rand.nextInt(i)+1;
   		w = rand.nextInt(5)+1;
   		graph[i+1][a11] = w;
   		graph[a11][i+1] = w;
   		
   	}

   	System.out.println( " ");
   	for(int i = 0; i<4; i++){
   		for(int j = 0; j<4; j++)
   		{
   			if(graph[i][j] == 0 && i!=j)
   			{
   				Random rand = new Random();
   				
   				graph[i][j] = graph[j][i]=rand.nextInt(100)+20;
   			}
   		}}
   	
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 4; j++) {
	        System.out.print(graph[i][j] + " ");
	    }
	    System.out.println();
	    
	}
	
   	
	
	System.out.println("------------------------");
        t.primMST(graph);
        long endTime   = System.currentTimeMillis();
    	long totalTime = endTime - startTime;
    	System.out.println("------------------------");
    	System.out.println("Run time of the program is " + totalTime + " ms");
    }
}
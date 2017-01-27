

import java.util.*;

class FordFulkersonMaxFlow {
	private int VerticesCount; // Number of vertices in graph
	private int graph[][];

	public FordFulkersonMaxFlow(int graph[][]) {
		this.graph = graph;
		VerticesCount = graph.length;
	}

	/*
	 * Returns true if there is a path from source 's' to sink 't' in residual
	 * graph. Also fills parent[] to store the path
	 */
	private boolean bfs(int rGraph[][], int s, int t, int parent[]) {
		// Create a visited array and mark all vertices as not visited
		boolean visited[] = new boolean[VerticesCount];

		// Create a queue, enqueue source vertex and mark source vertex
		// as visited
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;

		// Standard BFS Loop
		while (queue.size() != 0) {
			int u = queue.poll();
			for (int v = 0; v < VerticesCount; v++) {
				if (visited[v] == false && rGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		// If we reached sink in BFS starting from source, then return
		// true, else false
		return (visited[t]);
	}

	// A DFS based function to find all reachable vertices from s. The function
	// marks visited[i] as true if i is reachable from s. The initial values in
	// visited[] must be false. We can also use BFS to find reachable vertices
	private void dfs(int rGraph[][], int s, boolean visited[]) {
		visited[s] = true;
		for (int i = 0; i < VerticesCount; i++)
			if (rGraph[s][i] > 0 && !visited[i])
				dfs(rGraph, i, visited);
	}

	// Returns the maximum flow from s to t in the given graph
	int fordFulkersonMaxFlow(int s, int t)

	{
		int u, v;

		/*
		 * Create a residual graph and fill the residual graph with given
		 * capacities in the original graph as residual capacities, where
		 * rGraph[i][j] indicates residual capacity of edge from i to j (if
		 * there is an edge. If rGraph[i][j] is 0, then there is not)
		 */
		int rGraph[][] = new int[VerticesCount][VerticesCount];

		for (u = 0; u < VerticesCount; u++)
			rGraph[u] = graph[u].clone();
	//	for (v = 0; v < VerticesCount; v++)
		//	rGraph[u][v] = graph[u][v];

		int parent[] = new int[VerticesCount]; // filled by BFS and to store
												// path
		parent[s] = -1;

		int max_flow = 0; // There is no flow initially

		// Augment the flow while there is path from source to sink
		while (bfs(rGraph, s, t, parent)) {
			/*
			 * Find minimum residual capacity of the edges along the path filled
			 * by BFS. Or we can say find the maximum flow through the path
			 * found.
			 */
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}

			/*
			 * update residual capacities of the edges and reverse edges along
			 * the path
			 */
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}

			/* Add path flow to overall flow */
			max_flow += path_flow;
		}

		/* Return the overall flow */
		return max_flow;

	}

	// Returns the maximum flow from s to t in the given graph
	int fordFulkersonMaxFlowWithMinCut(int s, int t)

	{
		int u, v;

		/*
		 * Create a residual graph and fill the residual graph with given
		 * capacities in the original graph as residual capacities, where
		 * rGraph[i][j] indicates residual capacity of edge from i to j (if
		 * there is an edge. If rGraph[i][j] is 0, then there is not)
		 */
		int rGraph[][] = new int[VerticesCount][VerticesCount];

		for (u = 0; u < VerticesCount; u++)
			rGraph[u] = graph[u].clone();
		int parent[] = new int[VerticesCount]; // filled by BFS and to store
												// path

		int max_flow = 0; // There is no flow initially

		// Augment the flow while there is path from source to sink
		while (bfs(rGraph, s, t, parent)) {
			/*
			 * Find minimum residual capacity of the edges along the path filled
			 * by BFS. Or we can say find the maximum flow through the path
			 * found.
			 */
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}

			/*
			 * update residual capacities of the edges and reverse edges along
			 * the path
			 */
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}

			/* Add path flow to overall flow */
			max_flow += path_flow;
		}

		// Flow is maximum now, find vertices reachable from s
		boolean visited[] = new boolean[VerticesCount];
		/*
		 * dfs will generate visited array such that it will have true for all
		 * the vertex for P and false for complement of P So for cut edge will
		 * be in min cut if vertex a belongs to P and vertex b belongs to
		 * complement of P.
		 */
		dfs(rGraph, s, visited);

		// Print all edges that are from a reachable vertex to
		// non-reachable vertex in the original graph
		for (int i = 0; i < VerticesCount; i++)
			for (int j = 0; j < VerticesCount; j++)
				if (visited[i] && !visited[j] && graph[i][j] > 0)
					System.out.printf("\n%d - %d", i, j);
		/* Return the overall flow */
		return max_flow;

	}
}

public class FordFulkersonMaxFlowTest {
	// Driver program to test above functions
	public static void main(String[] args) throws java.lang.Exception {
		// Let us create a graph shown in the above example
		int size;
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the size of graph matrix");
		size = reader.nextInt();
		System.out.println("Size of matriz is "+size);
		System.out.printf("\nTest1");
		test1(size);
	//	System.out.printf("\nTest2");
	//	test2();
	}

	static void test1(int size) {
		long startTime = System.currentTimeMillis();
	//	int graph[][] = new int[][] { { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 }, { 0, 4, 0, 0, 14, 0 },
		//		{ 0, 0, 9, 0, 0, 20 }, { 0, 0, 0, 7, 0, 4 }, { 0, 0, 0, 0, 0, 0 } };
		  Random rand = new Random();
			int sink = rand.nextInt(size-1)+1;
			int source = rand.nextInt(size-1);
			while(source == sink){
				sink = rand.nextInt(size-1)+1;
				source = rand.nextInt(size-1);
			}
		int graph[][] = new int[size+1][size+1];
	for (int k =0;k<size+1;k++){
			for (int l=0;l<size+1;l++){
				graph[k][l] = 0;
			}
		}
        int w= 0;
        
 //Inserting Random Numbers in matrix       
/*	for(int i=1;i<size;i++)
	{
		Random rand = new Random();

		int a = rand.nextInt(i)+1;
		w = rand.nextInt(5)+1;
		graph[i+1][a] = w;
		graph[a][i+1] = w;
		
	}*/

	System.out.println( " ");
	for(int i = 0; i<size; i++){
		for(int j = 0; j<size; j++)
		{
			
			if(graph[i][j] == 0 && i!=sink && j!=source && i!=j)
			{
				//Random Number Generator
				Random rand1 = new Random();   				
				graph[i][j] =rand1.nextInt(100);
			}
		}
	}
		FordFulkersonMaxFlow m = new FordFulkersonMaxFlow(graph);
		System.out.println("The graph is ");
        for (int i = 0; i < size; i++) {
    	    for (int j = 0; j < size; j++) {
    	        System.out.print(graph[i][j] + " ");
    	    }
    	    System.out.println();}
        
      
		System.out.println("The Source and sink are "+source +" and " +sink +" respectively");
		System.out.printf("\nThe maximum possible flow is %d", m.fordFulkersonMaxFlowWithMinCut(source, sink));
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.printf("\nTotal Execution time %d milliseconds", totalTime);
	}

	static void test2() {
		long startTime = System.currentTimeMillis();
		int graph[][] = new int[][] { { 0, 16, 13, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0, 8, 0, 0, 0 },
				{ 0, 4, 0, 0, 14, 0, 0, 0, 0, 0 }, { 0, 0, 9, 0, 0, 20, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 4, 0, 0, 0, 8 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 7, 0, 0, 9, 0, 0 }, { 0, 0, 0, 6, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 12, 0, 0, 10, 0 } };
		FordFulkersonMaxFlow m = new FordFulkersonMaxFlow(graph);
		System.out.printf("\nThe maximum possible flow is %d", m.fordFulkersonMaxFlowWithMinCut(0, 5));
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.printf("\nTotal Execution time %d milliseconds", totalTime);
	}

}
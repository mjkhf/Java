//Floyd Warshall plus running time

package oct2016;

import static java.lang.Integer.MAX_VALUE;

import java.util.Random;

class GraphUtil {
	public static void printGraph(int graph[][]) {
		for (int row = 0; row < graph.length; row++) {
			for (int column = 0; column < graph.length; column++) {
				if (graph[row][column] == MAX_VALUE) {
					System.out.printf("INF\t");
				} else {
					System.out.printf(graph[row][column] + "\t");
				}
			}
			System.out.printf("\n");
		}
	}
}

class FloydAlgorithm {

	private int distance[][];
	private int vertex_count = 0;

	public FloydAlgorithm(int[][] graph) {
		vertex_count = graph.length;
		/*
		 * creating distance matrix which will hold the shortest distances
		 */
		distance = new int[vertex_count][vertex_count];
		/*
		 * initializing the distance matrix which will hold the shortest
		 * distances with default values i.e. current distance between nodes
		 */
		for (int row = 0; row < vertex_count; row++) {
			for (int column = 0; column < vertex_count; column++) {
				distance[row][column] = graph[row][column];
			}
		}

	}

	public int[][] distanceMatrix() {

		/*
		 * traverse each vertex and determine the intermediate node [node] for
		 * which distance between current vertex [row] and destination
		 * vertex[column] is shortest
		 */
		for (int node = 0; node < vertex_count; node++) {
			for (int row = 0; row < vertex_count; row++) {
				for (int column = 0; column < vertex_count; column++) {
					/*
					 * distance[row][node] != INF && distance[node][column] !=
					 * INF to avoid arithmetic overflow
					 */
					if (distance[row][node] != MAX_VALUE && distance[node][column] != MAX_VALUE
							&& distance[row][column] > (distance[row][node] + distance[node][column])) {
						distance[row][column] = distance[row][node] + distance[node][column];
					}
				}
			}
		}
		return distance;
	}

	public boolean hasNegativeCycle() {
		/*
		 * if diagonal node in distance matrix has a negative value then that
		 * node is part of negative cycle in graph
		 */
		for (int node = 0; node < vertex_count; node++) {
			if (distance[node][node] < 0)
				return true;
		}
		return false;
	}

	public void printDistancMatrix() {
		GraphUtil.printGraph(distance);
	}

}

public class FloydTest {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.printf("Test 1: \n");
		test1();
		System.out.printf("\n \n Test 2: \n");
		test2();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("------------------------");
		System.out.println("Run time of the program is " + totalTime + " ms");
	}

	static void test1() {
		/*
		 * directed graph such that weight indicates distance from one vertex to
		 * other and INF indicates no direct path from one vertex to other.
		 */
		int graph[][] = { 
							{     0    ,      5   , MAX_VALUE,    10 	 },
							{ MAX_VALUE, 	  0   ,    3     , MAX_VALUE }, 
							{ MAX_VALUE, MAX_VALUE,	   0     ,     1 	 },
							{ MAX_VALUE, MAX_VALUE, MAX_VALUE,     0     } 
						};
		for(int i = 0; i<4; i++){
	   		for(int j = 0; j<4; j++)
	   		{
	   			if( i!=j && graph[i][j]!=MAX_VALUE )  //graph[i][j] == 0s
	   			{
	   				Random rand = new Random();
	   				//graph[i][j] = ;//int randomNumber=(rand.nextInt(65536)-32768);
	   				graph[i][j] = graph[j][i]=(rand.nextInt(100)-20);
	   			}
	   		}}
		System.out.printf("Original Graph : \n");
		GraphUtil.printGraph(graph);
		FloydAlgorithm algorithm = new FloydAlgorithm(graph);
		algorithm.distanceMatrix();
		System.out.printf("\n\nDistince matrix  Graph : \n");
		algorithm.printDistancMatrix();
		System.out.printf("Has Negative Cycle : %s", algorithm.hasNegativeCycle() ? "Yes" : "No");
		System.out.println("");
	}

	static void test2() {
		/*
		 * directed graph such that weight indicates distance from one vertex to
		 * other and INF indicates no direct path from one vertex to other.
		 */
		int graph[][] = { 
						{ 0, 1, MAX_VALUE, MAX_VALUE, MAX_VALUE }, 
						{ MAX_VALUE, 0, -1, 1, MAX_VALUE },
						{ MAX_VALUE, -1, 0, MAX_VALUE, MAX_VALUE }, 
						{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 0, MAX_VALUE },
						{ 1, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0 }
						};
		//++++++++++++++++++++++++++++++++++ Random graph generation
		for(int i = 0; i<5; i++){
	   		for(int j = 0; j<5; j++)
	   		{
	   			if( i!=j && graph[i][j]!=MAX_VALUE )  //graph[i][j] == 0
	   			{
	   				Random rand = new Random();
	   				graph[i][j] = graph[j][i]=(rand.nextInt(100)-20);
	   			}
	   		}}
		//++++++++++++++++++++++++++++++++++
		
		System.out.printf("Original Graph : \n");
		GraphUtil.printGraph(graph);
		FloydAlgorithm algorithm = new FloydAlgorithm(graph);
		algorithm.distanceMatrix();
		System.out.printf("\n\nDistince matrix  Graph : \n");
		algorithm.printDistancMatrix();
		System.out.printf("Has Negative Cycle : %s", algorithm.hasNegativeCycle() ? "Yes" : "No");
		System.out.println("");
	}
}
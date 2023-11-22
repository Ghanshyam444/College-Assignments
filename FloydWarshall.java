import java.util.Scanner;

public class FloydWarshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of offices (vertices)
        System.out.println("Enter the number of offices:");
        int n = sc.nextInt();

        // Initialize the adjacency matrix and INF constant
        int[][] graph = new int[n][n];
        final int INF = Integer.MAX_VALUE;

        // Input the cost of connecting each pair of offices
        System.out.println("Enter the cost of connecting offices (-1 for unconnected pairs):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cost = sc.nextInt();
                // Replace -1 with INF if there's no direct connection
                if (cost == -1) {
                    graph[i][j] = INF;
                } else {
                    graph[i][j] = cost;
                }
            }
        }

        // Call the Floyd-Warshall algorithm function
        floydWarshall(graph, n);

        // Display the minimum cost of connecting all offices
        System.out.println("Minimum cost to connect all offices:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(graph[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // Function to perform the Floyd-Warshall algorithm
    public static void floydWarshall(int[][] graph, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
                        // Check if a shorter path is found through vertex k
                        if (graph[i][k] + graph[k][j] < graph[i][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
        }
    }
}

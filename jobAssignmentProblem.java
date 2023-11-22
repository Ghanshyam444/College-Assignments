import java.util.PriorityQueue;
import java.util.Scanner;

public class jobAssignmentProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of workers/jobs (N): ");
        int N = scanner.nextInt();
        int[][] costMatrix = new int[N][N];
        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Optimal Cost is " + findMinCost(costMatrix, N));
        scanner.close();
    }

    static class Node {
        Node parent;
        int pathCost;
        int cost;
        int workerID;
        int jobID;
        boolean[] assigned;

        Node(int x, int y, boolean[] assigned, Node parent, int N) {
            this.assigned = new boolean[N];
            for (int j = 0; j < N; j++) {
                this.assigned[j] = assigned[j];
            }
            if (x != -1 && y != -1) {
                this.assigned[y] = true;
            }
            this.parent = parent;
            this.workerID = x;
            this.jobID = y;
        }
    }

    static int calculateCost(int[][] costMatrix, int x, int y, boolean[] assigned, int N) {
        int cost = 0;
        boolean[] available = new boolean[N];
        for (int j = 0; j < N; j++) {
            available[j] = true;
        }
        for (int i = x + 1; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < N; j++) {
                if (!assigned[j] && available[j] && costMatrix[i][j] < min) {
                    minIndex = j;
                    min = costMatrix[i][j];
                }

            }
            cost += min;
            available[minIndex] = false;
        }
        return cost;
    }

    static class NodeComparator implements java.util.Comparator<Node> {
        public int compare(Node lhs, Node rhs) {
            return lhs.cost - rhs.cost;
        }
    }

    // static void printAssignments(Node min, int N) {
    //     if (min.parent == null) {
    //         return;
    //     }
    //     printAssignments(min.parent, N);
    //     System.out.println("Assign Worker " + (char) (min.workerID + 'A') + " to Job " + (min.jobID
    //             + 1));
    // }

    static int findMinCost(int[][] costMatrix, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        boolean[] assigned = new boolean[N];
        Node root = new Node(-1, -1, assigned, null, N);
        root.pathCost = root.cost = 0;
        root.workerID = -1;
        pq.add(root);
        while (!pq.isEmpty()) {
            Node min = pq.poll();
            int i = min.workerID + 1;
            if (i == N) {
           System.out.println("Assign Worker " + (char) (min.workerID + 'A') + " to Job " + (min.jobID
                + 1));
                return min.cost;
            }
            for (int j = 0; j < N; j++) {
                if (!min.assigned[j]) {
                    Node child = new Node(i, j, min.assigned, min, N);
                    child.pathCost = min.pathCost + costMatrix[i][j];
                    child.cost = child.pathCost + calculateCost(costMatrix, i, j, child.assigned, N);
                    pq.add(child);
                }
            }
        }
        return -1;
    }
}
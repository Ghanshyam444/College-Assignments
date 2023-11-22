import java.util.Scanner;

public class jobscheduling {
    public static void main(String args[]) {
        System.out.println("Enter the number of jobs to be scheduled");
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        String[] jobs = new String[n];
        int[] deadlines = new int[n];
        int[] profits = new int[n];

        // Input job details (name, profit, and deadline) from the user
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Job " + (i + 1));
            jobs[i] = sc.next();
            System.out.println("Enter Profit ");
            profits[i] = sc.nextInt();
            System.out.println("Enter Deadline");
            deadlines[i] = sc.nextInt();
        }

        // Sort the jobs in descending order of profit
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (profits[i] < profits[j]) {
                    // Swap profits, deadlines, and job names to maintain consistency
                    int temp = profits[j];
                    profits[j] = profits[i];
                    profits[i] = temp;

                    temp = deadlines[j];
                    deadlines[j] = deadlines[i];
                    deadlines[i] = temp;

                    String temp1 = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp1;
                }
            }
        }

        // Display the sorted order of jobs, profits, and deadlines
        System.out.println("Sorted Order is");
        System.out.println("Jobs:");
        for (int i = 0; i < n; i++) {
            System.out.print(jobs[i] + " ");
        }
        System.out.println("\nProfits:");
        for (int i = 0; i < n; i++)
            System.out.print(profits[i] + " ");
        System.out.println("\nDeadlines:");

        int max = deadlines[0];
        for (int i = 0; i < n; i++) {
            System.out.print(deadlines[i] + " ");
        }

        // Find the maximum deadline to determine the size of the final job array
        for (int i = 0; i < n; i++) {
            if (deadlines[i] > max)
                max = deadlines[i];
        }

        String fjobs[] = new String[max];
        int fprofits[] = new int[max];
        int fdeadlines[] = new int[max];
        int maxprofit = 0;

        // Perform job scheduling based on deadlines and profit
        for (int i = 0; i < n; i++) {
            int dl = deadlines[i];
            dl = dl - 1;
            if (fjobs[dl] == null) {
                fjobs[dl] = jobs[i];
                maxprofit += profits[i];
            } else {
                while (dl != -1) {
                    if (fjobs[dl] == null) {
                        fjobs[dl] = jobs[i];
                        maxprofit += profits[i];
                        break;
                    }
                    dl = dl - 1;
                }
            }
        }

        // Display the scheduled jobs and maximum profit
        System.out.println("\nThe Jobs Scheduling is as follows :");
        for (int i = 0; i < max; i++) {
            System.out.print(fjobs[i] + " ");
        }
        System.out.println(" The maximum Profit gained is " + maxprofit);
    }
}

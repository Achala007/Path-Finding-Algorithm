/**
 * Created by Achala Piyarathna on 3/30/2017.
 */

import java.awt.*;
import java.util.*;

public class PathFinder {

    static ArrayList<Node> pathChecked = new ArrayList<>();
    public static void main(String[] args) {

        // Generate a 20x20 squared grid with 10% of obstacles in it
        boolean[][] randomMatrix = random(10, 0.6);

        //This prints the stdArray in the console
        StdArrayIO.print(randomMatrix);

        //It displays the stating and ending points
       show(randomMatrix, true);

        //Taking the input from the user for starting and ending points
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter x1 for A > ");
        int Aj = in.nextInt();
        
        System.out.println("Enter y1 for A > ");
        int Ai = in.nextInt();
        
        System.out.println("Enter x2 for B > ");
        int Bj = in.nextInt();

        System.out.println("Enter y2 for B > ");
        int Bi = in.nextInt();

        // Start the clock ticking in order to capture the time being spent on inputting the coordinates
        Stopwatch timerFlow = new Stopwatch();

        //ArrayList<Node> path1 = new PathFinder().distance(randomMatrix, Ai, Aj, Bi, Bj, Manhattan(), "Manhattan",true);
        //System.out.println(timerFlow.elapsedTime());
        //ArrayList<Node> path2 = new PathFinder().distance(randomMatrix, Ai, Aj, Bi, Bj, Euclidean(), "Euclidean", false);
        ArrayList<Node> path3 = new PathFinder().distance(randomMatrix, Ai, Aj, Bi, Bj, Chebyshev(), "Chebyshev",false);

       show(randomMatrix, true, Ai, Aj, Bi, Bj, path3);


    }


    Node start;
    Node end;
    Node[][] grid;

    // Horizontal and Vertical Distance
    double hAndVDistance = 1.0;

    // Diagonal Distances
    //Manhattan values
    //Here the diagonal distance is 2
    public static double Manhattan() {

        double dDistance = 2;
        return dDistance;
    }

    //Euclidean values.
    //Here the diagonal distance is 1.4
    public static doubn() {
        double dDistance = 1.4;
        return dDistance;
    }

    //Chebyshev values
    //Here the diagonal distance is 1
    public static double Chebyshev() {
        double dDistance = 1;
        return dDistance;
    }

    /**
     * @param matrix The boolean matrix from the framework given
     * @param starti     start x value
     * @param startj     start y value
     * @param endi     end x value
     * @param endj     end x value
     * @return The path nodes
     */
    ArrayList<Node> distance(boolean[][] matrix, int starti, int startj, int endi, int endj, double dDistance, String name, boolean isManhat) {

        int size = matrix.length;

        start = new Node(starti, startj);
        end = new Node(endi, endj);

        // The grid that is used to store nodes
        grid = new Node[size][size];

        // Creating nodes and finding blocked cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                grid[i][j] = new Node(i, j);
                if (matrix[i][j] == false) {
                    grid[i][j].blocked = true;

                }
            }
        }

        // setting initial distance to 0.
        // All other nodes will have infinity distance at the beginning
        start.distance = 0;

        // a comparator object to operate with Priority Queue
        Comparator<Node> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;

            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<Node> PriorityQueue = new PriorityQueue(size, adjacencyComparator);

        //adding the starting node to the priority queue
        PriorityQueue.add(start);

        //Checks the adjacent nodes and operates the test node
        while (PriorityQueue .size() > 0) {
            Node current = PriorityQueue .remove();
            //test node is to save the adjacent cells temporarily
            Node testNode;
            //breaks the loop when the end node becomes the current node
            if(current.x==end.x && current.y==end.y){
                break;
            }
            if(!isManhat){
            //checking Top Left Node
            if (current.x - 1 >= 0 && current.y - 1 >= 0) {
                testNode = grid[current.x - 1][current.y - 1];
                double newDistance = current.distance + dDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }}


            //Checking Top Node
            if (current.x - 1 >= 0) {
                testNode = grid[current.x - 1][current.y];
                double newDistance = current.distance + hAndVDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }


            if(!isManhat){
            //checking Top Right Node
            if (current.x - 1 >= 0 && current.y + 1 < size) {
                testNode = grid[current.x - 1][current.y + 1];
                double newDistance = current.distance + dDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }}


            //checking Left Node
            if (current.y - 1 >= 0) {
                testNode = grid[current.x][current.y - 1];
                double newDistance = current.distance + hAndVDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }


            //checking Right Node
            if (current.y + 1 < size) {
                testNode = grid[current.x][current.y + 1];
                double newDistance = current.distance + hAndVDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }


            if(!isManhat){
            //checking Bottom Left Node
            if (current.x + 1 < size && current.y - 1 >= 0) {
                testNode = grid[current.x + 1][current.y - 1];
                double newDistance = current.distance + dDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }}

            //checking Bottom Node
            if (current.x + 1 < size) {
                testNode = grid[current.x + 1][current.y];
                double newDistance = current.distance + hAndVDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }

            if(!isManhat){
            //checking Bottom Right Node
            if (current.x + 1 < size && current.y + 1 < size) {
                testNode = grid[current.x + 1][current.y + 1];
                double newDistance = current.distance + dDistance;
                if (!testNode.blocked && !testNode.visited && testNode.distance > newDistance) {
                    testNode.distance = newDistance;
                    testNode.parent = current;
                    PriorityQueue.add(testNode);
                }
            }}
            current.visited = true;
            pathChecked.add(current);
        }

        ArrayList<Node> path = new ArrayList<>();

        // Checking if a path exists
        if (!(grid[end.x][end.y].distance == Integer.MAX_VALUE || grid[start.x][start.y].distance == Integer.MAX_VALUE)) {
            //Trace back the path
            Node current = grid[end.x][end.y];
            System.out.println(name + ":" + current.distance);
            while (current.parent != null) {
                path.add(current.parent);

                current = current.parent;
            }
        } else System.out.println("No possible path");


        return path;
    }



   //Displays the Grid lines and the blocked cells
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                    StdDraw.square(j, N - i - 1, .5);
                else StdDraw.filledSquare(j, N - i - 1, .5);
    }

    // draw the N-by-N boolean matrix to standard draw, including the points A (x1, y1) and B (x2,y2) to be marked by a circle
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, ArrayList<Node> path) {
        int N = a.length;
        int s = path.size();
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                    if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
                        StdDraw.setPenColor(Color.GREEN);
                        StdDraw.filledCircle(j, N - i - 1, .5);

                    }

       for (Node node : path) {

           if (s - count == 1) {
            return;
           }
           count++;


            StdDraw.setPenColor(Color.red);

            StdDraw.filledCircle(node.y, N - node.x - 1, .5);

        }
    }

    // return a random N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = StdRandom.bernoulli(p);
        return a;
    }

}
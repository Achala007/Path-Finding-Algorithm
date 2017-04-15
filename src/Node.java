/**
 * Created by Achala on 4/1/2017.
 */

    class Node {
        int x;
        int y;
        //Set all distances of the nodes to infinity
        double distance = Integer.MAX_VALUE;
        Node parent = null;
        boolean visited;
        boolean blocked;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
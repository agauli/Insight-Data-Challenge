public class BreadthFirstSearch {
    private static final int MAXDEGREE = 4;
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] visited; // visited[v] = Has v been already visited. 
    private int[] distanceTo;  // distanceTo[v] = number of edges shortest s-v path
    private boolean[] present; // present[i] = reached destination vertex at i degree
    private int c;

    /**
     * Computes the shortest path between the source vertex and the destination 
     * vertex if present in the graph within 4th degree.
     *
     * @param G the graph
     * @param s the source vertex
     * @param d the destination vertex
     */

    /* Constructor
    */
    
    public BreadthFirstSearch(Network G, int s, int d) {
        visited = new boolean[G.V()];
        distanceTo = new int[G.V()]; 
        present = new boolean[MAXDEGREE];
        bfs(G, s, d);
    }


    /*breadth-first search from a source s to destination d
     *
    */

    private void bfs(Network G, int s, int d) {
        QueueLinkedList<Integer> q = new QueueLinkedList<Integer>();
        for (int vertex = 0; vertex < G.V(); vertex++)
             distanceTo[vertex] = INFINITY;
        distanceTo[s] = 0;
        visited[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            c = distanceTo[v];
            if (c > (MAXDEGREE - 1)) break;     // do not go beyond 4th degree. 
            for (int nb : G.adj(v)) {
                if (!visited[nb]) {
                    if (nb == d){
                        present[c] = true;  
                        return;                 // if destination reached, do not go further
                    }
                    distanceTo[nb] = distanceTo[v] + 1 ;
                    visited[nb] = true;
                    q.enqueue(nb);
                }
            } 
            
        }
    }

    /**
     * Returns array with true at the index representing the degree at which
     * destination vertex was reached. But, last index represents the 4th degree. otherwise all false. 
     */

    public boolean[] getPosition(){
        boolean [] degree = new boolean[3];
        degree[0] = present[0];
        degree[1] = present[1];
        degree[2] = present[3];
        return degree;
    }

}


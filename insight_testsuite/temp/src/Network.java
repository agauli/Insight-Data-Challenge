import java.util.NoSuchElementException;
import java.util.Set;

public class Network {
    private final int V;
    private int E;
    private Bag<Integer>[] neighbor;
    
    /**  
     * Construct a undirected social network graph 
     * from vertices and the edges. 
     * @param  V number of vertices in the graph
     * @param  edges set of edges "source-destination"
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     */

    public Network(int V, Set <String> edges) {
        this.V = V;
        neighbor = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            neighbor[v] = new Bag<Integer>();
        }   
        for (String edge : edges){
            String[] splitted = edge.split("-");
            int a = Integer.parseInt(splitted[0]);
            int b = Integer.parseInt(splitted[1]);
            addEdge(a, b); 
        }
    }

    /**
     * Adds an undirected edge v-w to the graph network. 
     * @param  v one vertex in the edge
     * @param  w another vertex in the edge
     * @throws IndexOutOfBoundsException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        neighbor[v].add(w);
        neighbor[w].add(v); 
        E++;     
    }


    /**
     * Returns the number of vertex on the network.
     * @return the number of vertex on the network.
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges on the network.
     * @return the number of edges on the network.
     */
    public int E() {
        return E;
    }

    // throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    // this has been implemented since initial data analysis showed contigeous vert number.
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Returns the vertices that are neighbours of vertex v. 
     * @param  v the vertex. 
     * @return the vertices adjacent to the vertex. 
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return neighbor[v];
    }

}

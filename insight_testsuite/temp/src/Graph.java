import java.util.NoSuchElementException;
import java.util.Set;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    

    /**  
     * Construct a graph from the input stream. 
     *
     * @param  in input stream
     * @param  V number of vertices in the graph
     * @param  edges set of edges "source-destination"
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */


    public Graph(In in, int V, Set <String> edges) {
        try {
            this.V = V;

            //int vertex_num = p.numVertex();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            
            for (String edge : edges){
                String[] splitted = edge.split("-");
                int a = Integer.parseInt(splitted[0]);
                int b = Integer.parseInt(splitted[1]);
                addEdge(a, b); 
            }

        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }


    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IndexOutOfBoundsException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);    
        if (intheBag(v, adj(w))){
            return;
        }
        else {
            E++;
            adj[v].add(w);
            adj[w].add(v);
        }            
    }

    public boolean intheBag(int x, Iterable<Integer> bog){
        for (int in : bog){
            if (in == x) return true;
        }
        return false;
    }


    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

    


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IndexOutOfBoundsException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

}

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Antifraud{
	private static Set <Integer> vertices = new HashSet<Integer>();
	private static Set <String> edges = new HashSet<String>();

	public static void scanBatch(In input){
        input.readLine();       //skip header
        while (input.hasNextLine()) {
            String str = input.readLine();
            String[] parts = str.split(", ");
            String first = (parts[0].substring(0,2));
            if (!first.equals("20")){                   // since some lines doesnot comply the format. 
                continue;
            }
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            vertices.add(a);
            vertices.add(b);
            edges.add(a + "-" + b);
        }
    }


    public static void main(String[] args) {
    	List<Out> writers;   // 
    	boolean[] feature_search;
    	String trust;

        In batch = new In(args[0]);                    
        scanBatch(batch);
        int num_vertices = Collections.max(vertices);
		Graph G = new Graph(batch, num_vertices + 1, edges);
        In stream = new In(args[1]);                 
        
        writers = new ArrayList<Out>(); 
        writers.add(new Out(args[2]));
        writers.add(new Out(args[3]));
        writers.add(new Out(args[4]));

        
        stream.readLine();  //skip header

        while (stream.hasNextLine()) {
            String streamline = stream.readLine();
            String[] streamline_parts = streamline.split(", ");
            String first_c = (streamline_parts[0].substring(0,3));
            if (!first_c.equals("201")){
                continue;
            }
            int source = Integer.parseInt(streamline_parts[1]);
            int destination = Integer.parseInt(streamline_parts[2]);
            if (vertices.contains(source)){
            	BreadthFirstSearch bfs = new BreadthFirstSearch(G, source, destination);
            	feature_search = bfs.getPosition();
            } else {
            	feature_search = new boolean[]{false,false,false};
            }
            trust = "unverified";
            for (int i = 0; i < feature_search.length; i++){
            	if (feature_search[i] == true) trust = "trusted";
            	writers.get(i).println(trust);
            }
        }
		long stopTime = System.currentTimeMillis();
        writers.get(0).close();
        writers.get(1).close();
        writers.get(2).close();
    }
}


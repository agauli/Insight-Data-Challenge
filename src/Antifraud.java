import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 *  Compilation:  javac Antifraud.java 
 *  Execution:    java Antifraud batch_payment.txt stream_payment.txt 
 *                output1.txt output2.txt output3.txt
 *  Dependencies: BreadthFirstSearch.java Bag.java QueueLinkedList.java 
 *                In.java Out.java Network.java
 *************************************************************************
 *   
 */

public class Antifraud{
	private static Set <Integer> vertices = new HashSet<Integer>();
	private static Set <String> edges = new HashSet<String>();


    /**
     * read input batch payment datastream, scans for vertices and edges, 
     * and add to Set vertices and edges.
     * @param  In inputstream
     */

	public static void scanBatch(In input){
        input.readLine();       //skip header
        while (input.hasNextLine()) {
            String str = input.readLine();
            String[] parts = str.split(", ");
            String first = (parts[0].substring(0, 2));
            if (!first.equals("20")){    //lines doesnot comply the format. 
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
		Network G = new Network(num_vertices + 1, edges);
        In stream = new In(args[1]);                 
        
        writers = new ArrayList<Out>(); 
        writers.add(new Out(args[2]));
        writers.add(new Out(args[3]));
        writers.add(new Out(args[4]));
        stream.readLine();  //skip header

        // read stream from stream_payment.txt, identify the fradulent
        // payment and write to output .txt

        while (stream.hasNextLine()) {
            String streamline = stream.readLine();
            String[] streamline_parts = streamline.split(", ");
            String first_c = (streamline_parts[0].substring(0, 2));
            if (!first_c.equals("20")){        //lines doesnot comply the format.
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

        writers.get(0).close();
        writers.get(1).close();
        writers.get(2).close();
    }
}


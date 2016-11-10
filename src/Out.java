import java.io.OutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *  This class provides methods for writing strings to file output. 
 */
public class Out {
    private PrintWriter out;

   /**
     * Constructer ~ an output stream from a file.
     * @param  filename the name of the file
     */
   
    public Out(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Closes the output stream.
     */
    public void close() {
        out.close();
    }

   /**
     * Print an object and terminate the line.
     *
     * @param x is the object to be printed
     */
    public void println(Object x) {
        out.println(x);
    }

   

}

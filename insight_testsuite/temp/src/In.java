import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *  This class provides methods for reading files input from local paymo_input directory.
 */

public final class In {
    
    private Scanner scanner;
   /**
     * Initializes an input stream from a filename.
     *
     * @param  name  string filename.  
     * @throws IllegalArgumentException if cannot open the file. 
     * @throws IllegalArgumentException if name is null. 
     */
    public In(String filename) {
        if (filename == null) throw new IllegalArgumentException("argument is null");
        try {
            // read file from local file system
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis));
            }
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }

   /** 
     * Returns true if this input stream has a next line.
     * @return true if this input stream has more input otherwise false. 
     */

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

   /**
     * Reads and returns the next line in the file stream.
     * @return the next line in the file stream.
     */

    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
}

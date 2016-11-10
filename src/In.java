import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *  This class provides methods for reading files input from local paymo_input directory.
 */

public final class In {
    
    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";


    private Scanner scanner;
   /**
     * Initializes an input stream from a filename or web page name.
     *
     * @param  name the filename or web page name
     * @throws IllegalArgumentException if cannot open {@code name} as
     *         a file or URL
     * @throws IllegalArgumentException if {@code name} is {@code null}
     */
    public In(String name) {
        if (name == null) throw new IllegalArgumentException("argument is null");
        try {
            // read file from local file system
            File file = new File(name);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            }
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + name, ioe);
        }
    }

   /** 
     * Returns true if this input stream has a next line.
     * Use this method to know whether the
     * next call to {@link #readLine()} will succeed.
     * This method is functionally equivalent to {@link #hasNextChar()}.
     *
     * @return {@code true} if this input stream has more input (including whitespace);
     *         {@code false} otherwise
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

   /**
     * Reads and returns the next line in this input stream.
     *
     * @return the next line in this input stream; {@code null} if no such line
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

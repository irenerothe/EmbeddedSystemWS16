package algds;
// Definition der Klassen im Pfad mit selben Namen, also algds
import java.io.*;
import java.util.Vector;
public class IOUtils {  // Dateiname muss Klassennamen entsprechen
    public static int readInt () { 
        // statisch, damit ohne new aufrufbar, z.B. int i = Utils.readInt();
        int result = 0;
        BufferedReader reader =
            new BufferedReader (new InputStreamReader (System.in));
        try {
            result = Integer.parseInt (reader.readLine ());
        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }
        catch (NumberFormatException e) {
            System.err.println ("Format Error: " + e.getMessage ());
        }
        return result;
    }
    
    public static float readFloat () {
        float result = 0;
        BufferedReader reader =
            new BufferedReader (new InputStreamReader (System.in));
        try {
            result = new Float (reader.readLine ()).floatValue ();
        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }
        catch (NumberFormatException e) {
            System.err.println ("Format Error: " + e.getMessage ());
        }
        return result;
    }
    
    public static char readChar () {
        char result = '\u0000';
        BufferedReader reader =
            new BufferedReader (new InputStreamReader (System.in));
        try {
            result = (char)reader.read ();
        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }
        return result;
    }
    
    public static String readString () {
        String result = null;
        BufferedReader reader =
            new BufferedReader (new InputStreamReader (System.in));
        try {
            result = reader.readLine ();
        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }
        return result;
    }

    /* Liest ein Folge von Integer-Werten aus der angegebenen Datei 
     * und gibt diese in einem Feld zurueck.
     * Im Fehlerfall wird null geliefert.
     * @param filename: Name (inkl. Pfad) der einzulesenden Datei
     * @return das int-Feld mit den Werten
     */
    public static int[] readIntArray (String filename) {
        int[] result = null;

        Vector values = new Vector ();
        try {
            BufferedReader reader =
                new BufferedReader (new FileReader (filename));
            while (reader.ready ()) {
                Integer value = Integer.valueOf (reader.readLine ());
                values.addElement (value);
            }
            reader.close ();
            result = new int[values.size ()];
            for (int i = 0; i < values.size (); i++)
                result[i] = ((Integer) values.elementAt (i)).intValue ();

        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }
        return result;
    }
    public static String[] readStringArray (String filename) {
        String[] result = null;

        Vector values = new Vector ();
        try {
            BufferedReader reader =
                new BufferedReader (new FileReader (filename));
            while (reader.ready ()) {
                String value = reader.readLine ();
                values.addElement (value);
            }
            reader.close ();
            result = new String[values.size ()];
            for (int i = 0; i < values.size (); i++)
                result[i] = ((String) values.elementAt (i)).toString ();

        }
        catch (IOException e) {
            System.err.println ("I/O Error: " + e.getMessage ());
        }

        return result;
    }
}



import java.io.*;
import java.util.*;

public class passwordDB {
    private File file;
    private ArrayList<String> strings;

    public passwordDB(String filename) throws IOException {
        file = new File(filename);
        strings = new ArrayList<>();

        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            reader.close();
        }
    }

    public void addString(String str) throws IOException {
        strings.add(str);
        writeToFile();
    }

    public void deleteString(String str) throws IOException {
        strings.remove(str);
        writeToFile();
    }

    public void viewAllContent() {
        for (String str : strings) {
            System.out.println(str);
        }
    }

    private void writeToFile() throws IOException {
        PrintWriter writer = new PrintWriter(file);
        for (String str : strings) {
            writer.println(str);
        }
        writer.flush(); 
        writer.close();
    }    

}
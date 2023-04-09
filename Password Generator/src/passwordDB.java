import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.*;

public class passwordDB {
     static TreeSet<String> timestampedStrings;
     DateTimeFormatter formatter;
    
    public passwordDB() {
        this.timestampedStrings = new TreeSet<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    public void addTimestampedString(String str) {
        String timestamp = LocalDateTime.now().format(formatter);
        timestampedStrings.add(timestamp + " " + str);
    }
    
    public String getTimestampedString() {
        if (timestampedStrings.isEmpty()) {
            return null;
        }
        String timestampedString = timestampedStrings.first();
        String[] parts = timestampedString.split(" ", 2);
        String str = parts[1];
        timestampedStrings.remove(timestampedString);
        return str;
    }

    static void printTimestampedStrings() {
        Iterator<String> itr = timestampedStrings.descendingIterator();
        while (itr.hasNext()) {
            String timestampedString = itr.next();
            String[] parts = timestampedString.split(" ", 2);
            String timestamp = parts[0];
            String str = parts[1];
            System.out.println(timestamp + " " + str);
        }
    }

    public void addItemtoDatabase(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string to add to the database :");
        String str=sc.nextLine();
        addTimestampedString(str);
    }
    
    public void printDatabase() {
        System.out.println();
        System.out.println("Database Entries");
        System.out.println("-----------------");
        System.out.println();
        String retrievedString = getTimestampedString();
        while (retrievedString != null) {
            System.out.println(retrievedString);
            retrievedString = getTimestampedString();
        }
        printTimestampedStrings();

    }

    public void retriveAString(){

    }
}

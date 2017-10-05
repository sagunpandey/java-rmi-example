import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By Sagun Pandey
 */
public class HistoryProvider {

    String fileName = "history";

    Pattern pattern;

    public HistoryProvider() {
        // RegEx to match and extract month, date and event description
        pattern = Pattern.compile("^(\\d{2})\\s(\\d{2})\\t(.*)$");

        // Create a new history file if not present
        try {
            File file = new File(fileName);
            if(!file.exists()) {
                boolean created = file.createNewFile();

                if(created) {
                    System.out.println("---- Server: New history file '" + fileName + "' created");
                }
            }
        } catch (IOException e) {
            System.err.println("---- Server: Error creating history file");
        }
    }

    public void addEvent(Event event) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(
                    String.format("%02d", event.getMonth()) + " " +
                            String.format("%02d", event.getDate()) + "\t" +
                            event.getDescription()
            );
            writer.newLine(); // Append a newline after writing event

            writer.close();
        } catch (IOException e) {
            System.out.println("---- Server: Error writing to file");
            e.printStackTrace();
        }
    }

    public List<Event> query(int month, int date) {
        List<Event> events = new ArrayList<>();

        String line;
        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader reader = new BufferedReader(fileReader);

            // Traverse through each line and pick those that match month and date
            // Temporarily store them in a list
            while((line = reader.readLine()) != null) {
                Event event = parseEvent(line);
                if(event != null) {
                    if(event.getMonth() == month && event.getDate() == date) {
                        events.add(event);
                    }
                }
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("---- Server: File '" + fileName + "' not found!");
            ex.printStackTrace();
        } catch (IOException e) {
            System.err.println("---- Server: Error reading file '" + fileName + "'");
            e.printStackTrace();
        }

        return events;
    }

    private Event parseEvent(String line) {
        Matcher matcher = pattern.matcher(line);

        // If the regular expression matches
        if(matcher.matches()) {
            int month = Integer.valueOf(matcher.group(1)); // get month
            int date = Integer.valueOf(matcher.group(2)); // get date
            String description = matcher.group(3); // get event description

            return new Event(month, date, description);
        }

        return null;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryProvider {

    String fileName = "./../history";

    Pattern pattern;

    public HistoryProvider() {
        pattern = Pattern.compile("^(\\d{2})\\s(\\d{2})\\t(.*)$");
    }

    public void addEvent(Event event) {
        try {
            File file = new File(fileName);
            if(!file.exists()) {
                boolean created = file.createNewFile();

                if(created) {
                    System.out.println("New history file '" + fileName + "' created");
                }
            }

            FileWriter fileWriter = new FileWriter(fileName, true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(
                    String.format("%02d", event.getMonth()) + " " +
                            String.format("%02d", event.getDate()) + "\t" +
                            event.getDescription()
            );
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
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
            System.err.println("File '" + fileName + "' not found!");
            ex.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading file '" + fileName + "'");
            e.printStackTrace();
        }

        return events;
    }

    private Event parseEvent(String line) {
        Matcher matcher = pattern.matcher(line);

        if(matcher.matches()) {
            int month = Integer.valueOf(matcher.group(1));
            int date = Integer.valueOf(matcher.group(2));
            String description = matcher.group(3);

            return new Event(month, date, description);
        }

        return null;
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextHandler {
    static synchronized void write(String text, String fn) {
        try (FileWriter fw = new FileWriter(fn, true)) {
            fw.write("\n" + text);
        } catch (IOException e) {
            System.out.println("Failed to write '" + text + "'");
        }
    }
    static int[] read(String fn) {
        ArrayList<Integer> deckList = new ArrayList<>();
        File deckFile = new File(fn);
        try (Scanner deckScanner = new Scanner(deckFile)) {
            while (deckScanner.hasNextLine()) {
                deckList.add(Integer.valueOf(deckScanner.nextLine()));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Failed to read " + fn);
        }
        return deckList.stream().mapToInt(i -> i).toArray();
    }
}

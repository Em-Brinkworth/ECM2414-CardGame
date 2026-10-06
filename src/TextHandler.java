import org.w3c.dom.ranges.RangeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextHandler {
    static int[] read(String fp, int playerCount) throws RangeException {
        ArrayList<Integer> deckList = new ArrayList<>();
        File deckFile = new File(fp);
        try (Scanner deckScanner = new Scanner(deckFile)) {
            while (deckScanner.hasNextLine()) {
                deckList.add(Integer.valueOf(deckScanner.nextLine()));
            }
            if (deckList.size() != playerCount) {
                throw new RangeException((short) 1, "File is wrong size.");
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Failed to read " + fp);
        }
        return deckList.stream().mapToInt(i -> i).toArray();
    }

    static synchronized void write(String text, String fp) {
        try (FileWriter fw = new FileWriter(fp, true)) {
            fw.write("\n" + text);
        } catch (IOException e) {
            System.out.println("Failed to write '" + text + "'");
        }
    }
}

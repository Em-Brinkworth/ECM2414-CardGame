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
                deckList.add(Integer.parseInt(deckScanner.nextLine()));
            }
            if (deckList.size() != playerCount*8) {
                throw new RangeException((short) 1, "File is wrong size.");
            }
            return deckList.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            System.out.println(fp + " does not exist.");
            return new int[]{};
        } catch (NumberFormatException e) {
            System.out.println(fp + " had invalid formatting.");
            return new int[]{};
        }
    }

    static synchronized void write(String text, String fp) {
        try (FileWriter fw = new FileWriter(fp, true)) {
            fw.write( text + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write '" + text + "'");
        }
    }

    static void delete(String[] fileBaseNames, int playerCount) {
        // Deletes all players up to the player count
        for (int i =1; i <= playerCount; i++) {
            for (String j : fileBaseNames) {
                new File(j + i + ".txt").delete();
            }
        }
    }
}
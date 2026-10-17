import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ranges.RangeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerTest {

    @Test
    void read() {

        File DeckM8 = new File ("Decks/CardDeckMoreThan8.txt");
        File DeckL8 = new File ("Decks/CardDeckLessThan8.txt");
        File DeckL = new File ("Decks/CardDeckLessThan8.txt");
        DeckL8.delete();
        DeckM8.delete();
        DeckL.delete();
        try (FileWriter w1 = new FileWriter("Decks/CardDeckLessThan8.txt", true)) {
            w1.write( "7" + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }
        try (FileWriter w2 = new FileWriter("Decks/CardDeckMoreThan8.txt", true)) {
           w2.write( "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n" + "2" + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }
        try (FileWriter w3 = new FileWriter("Decks/CardDeckLanguageTest.txt", true)) {
            w3.write( "A");
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }

        Exception exception = assertThrows(RangeException.class, () -> {TextHandler.read("Decks/CardDeckLessThan8.txt", 4);});
        Exception exception2 = assertThrows(RangeException.class, () -> {TextHandler.read("Decks/CardDeckMoreThan8.txt", 4);});
        Exception exception3 = assertThrows(NumberFormatException.class, () -> {TextHandler.read("Decks/CardDeckLanguageTest.txt", 4);});


    }

    @Test
    void write() {


    }

    @Test
    void delete() {
    }
}
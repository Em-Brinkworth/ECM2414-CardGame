import org.junit.jupiter.api.Test;
import org.w3c.dom.ranges.RangeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerTest {

    @Test
    void readTest() {
        try (FileWriter w1 = new FileWriter("test/Files/CardDeckLessThan8.txt", false)) {
            w1.write( "7" + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }
        try (FileWriter w2 = new FileWriter("test/Files/CardDeckMoreThan8.txt", false)) {
           w2.write("""
                   2
                   2
                   2
                   2
                   2
                   2
                   2
                   2
                   2
                   """);
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }
        try (FileWriter w3 = new FileWriter("test/Files/CardDeckLanguageTest.txt", false)) {
            w3.write( "A");
        } catch (IOException e) {
            System.out.println("Failed to write '" + "7" + "'");
        }

        assertThrows(RangeException.class, () -> TextHandler.read("test/Files/CardDeckLessThan8.txt", 4));
        assertThrows(RangeException.class, () -> TextHandler.read("test/Files/CardDeckMoreThan8.txt", 4));
        assertArrayEquals(new int[]{}, TextHandler.read("test/Files/CardDeckLanguageTest.txt", 4));
    }

    @Test
    void writeTest() {
        String fn = "test/files/deckWriteTest.txt";
        new File(fn).delete();
        // Create test file
        TextHandler.write("1", fn);
        TextHandler.write("2", fn);
        TextHandler.write("3", fn);
        TextHandler.write("4", fn);
        TextHandler.write("5", fn);
        TextHandler.write("6", fn);
        TextHandler.write("7", fn);
        TextHandler.write("8", fn);
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8}, TextHandler.read(fn,1));
    }

    @Test
    void deleteTest() {
        String fn11 = "test/files/deckDeleteTest11.txt"; File f11 = new File(fn11);
        TextHandler.write(" ", fn11);
        String fn12 = "test/files/deckDeleteTest12.txt"; File f12 = new File(fn12);
        TextHandler.write(" ", fn12);
        String fn13 = "test/files/deckDeleteTest13.txt"; File f13 = new File(fn13);
        TextHandler.write(" ", fn13);
        String fn21 = "test/files/deckDeleteTest21.txt"; File f21 = new File(fn21);
        TextHandler.write(" ", fn21);
        String fn22 = "test/files/deckDeleteTest22.txt"; File f22 = new File(fn22);
        TextHandler.write(" ", fn22);
        String fn23 = "test/files/deckDeleteTest23.txt"; File f23 = new File(fn23);
        TextHandler.write(" ", fn23);
        if (f11.exists() && f12.exists() && f13.exists() && f21.exists() && f22.exists() && f23.exists()) {
            TextHandler.delete(new String[]{"test/files/deckDeleteTest1%s.txt", "test/files/deckDeleteTest2%s.txt"}, 3);
            assert (!f11.exists());
            assert (!f12.exists());
            assert (!f13.exists());
            assert (!f21.exists());
            assert (!f22.exists());
            assert (!f23.exists());
        } else {
            assert false;
        }
    }
}
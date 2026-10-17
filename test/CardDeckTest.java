import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    @Test
    void draw() {

        Card[] cardarray = new Card[4];
        cardarray[0] = new Card(4);
        cardarray[1] = new Card(7);
        cardarray[2] = new Card(3);
        cardarray[3] = new Card(6);
        CardDeck testdeck = new CardDeck(cardarray, 1);
        Card drawncard = testdeck.draw();
        Assertions.assertEquals(testdeck.getDeckAsString(), "4 7 3 ");
        Assertions.assertEquals(drawncard.getValue(), 6);
        //Checks last card drawn and remaining cards are correct

    }

    @Test
    void discardToDeck() {
        Card[] cardarray = new Card[4];
        cardarray[0] = new Card(4);
        cardarray[1] = new Card(7);
        cardarray[2] = new Card(3);
        cardarray[3] = new Card(6);
        CardDeck testdeck = new CardDeck(cardarray, 1);
        Card addedcard = new Card(8);
        testdeck.discardToDeck(addedcard);
        Assertions.assertEquals(testdeck.getDeckAsString(), "8 4 7 3 6 ");
        //Check added card is discarded to top

    }

    @Test
    void getDeckName() {
    }

    @Test
    void getDeckAsString() {
    }
}
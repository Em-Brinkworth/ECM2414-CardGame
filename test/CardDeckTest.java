import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    @Test
    void draw() {
        Card[] cardArray = new Card[4];
        cardArray[0] = new Card(4);
        cardArray[1] = new Card(7);
        cardArray[2] = new Card(3);
        cardArray[3] = new Card(6);
        CardDeck testDeck = new CardDeck(cardArray, 1);
        Card drawncard = testDeck.draw();
        Assertions.assertEquals("4 7 3 ", testDeck.getDeckAsString());
        Assertions.assertEquals(6, drawncard.getValue());
        //Checks last card drawn and remaining cards are correct
    }

    @Test
    void discardToDeck() {
        Card[] cardArray = new Card[4];
        cardArray[0] = new Card(4);
        cardArray[1] = new Card(7);
        cardArray[2] = new Card(3);
        cardArray[3] = new Card(6);
        CardDeck testDeck = new CardDeck(cardArray, 1);
        Card addedcard = new Card(8);
        testDeck.discardToDeck(addedcard);
        Assertions.assertEquals("8 4 7 3 6 ", testDeck.getDeckAsString());
        //Check added card is discarded to top
    }

    @Test
    void getDeckAsString() {
        CardDeck testDeck = new CardDeck(new Card[]{
                new Card(1),
                new Card(2),
                new Card(3),
                new Card(4)
        },1);
        assertEquals("1 2 3 4 ", testDeck.getDeckAsString());
    }
}
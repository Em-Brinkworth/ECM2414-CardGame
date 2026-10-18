import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player testPlayer;
    CardDeck testDeck;

    @BeforeEach
    void beforeEach() {
        testPlayer = new Player(new Card[]{
                new Card(1),
                new Card(2),
                new Card(3),
                new Card(4)
        }, 1);
        testDeck = new CardDeck(new Card[]{
                new Card(1),
                new Card(1),
                new Card(1),
                new Card(1)
        },1);
    }

    @Test
    void getHandAsString() {
        assertEquals("1 2 3 4 ", testPlayer.getHandAsString());
    }

    @Test
    void checkIfWon() {
        Player testPlayerWhoHasWon = new Player(new Card[]{
                new Card(1),
                new Card(1),
                new Card(1),
                new Card(1)
        }, 1);
        Player testPlayerWhoHasNotWon = new Player(new Card[]{
                new Card(1),
                new Card(1),
                new Card(1),
                new Card(2)
        }, 2);
        assert testPlayerWhoHasWon.checkIfWon();
        assert !testPlayerWhoHasNotWon.checkIfWon();
    }

    @Test
    void draw() {
        testPlayer.draw(testDeck);
        assertEquals("1 2 3 4 1 ", testPlayer.getHandAsString());
        assertEquals("1 1 1 ", testDeck.getDeckAsString());

        CardDeck emptyTestDeck = new CardDeck(new Card[]{}, 2);
        testPlayer.draw(emptyTestDeck);
        assertEquals("1 2 3 4 1 ", testPlayer.getHandAsString());
    }

    @Test
    void discard() {
        testPlayer.discard(testDeck);
        assertEquals(3, testPlayer.getHand().size());
        assertEquals(5, testDeck.getDeck().size());
    }

    @Test
    void maintainingFourCardsAfterEachDrawDiscard() {

    }

    @Test
    void getCardToDiscard() {
        Card[] cardarray = new Card[4];
        cardarray[0] = new Card(4);
        cardarray[1] = new Card(1);
        cardarray[2] = new Card(1);
        cardarray[3] = new Card(1);
        Player player = new Player(cardarray, 4);

        Assertions.assertEquals(1, player.getCardToDiscard().getValue());
        //Tests that even with multiple of the same card and one of the desired card the card being removed is still
        //one of the non-desired kind
    }
}
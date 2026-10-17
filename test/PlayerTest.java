import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getId() {
    }

    @Test
    void getName() {
    }

    @Test
    void getFp() {
    }

    @Test
    void getHandAsString() {
    }

    @Test
    void getHand() {
    }

    @Test
    void checkIfWon() {
    }

    @Test
    void draw() {
    }

    @Test
    void discard() {

    }

    @Test
    void testGetId() {
    }

    @Test
    void testGetName() {
    }

    @Test
    void testGetFp() {
    }

    @Test
    void testGetHandAsString() {
    }

    @Test
    void testGetHand() {
    }

    @Test
    void testCheckIfWon() {
    }

    @Test
    void testDraw() {
    }

    @Test
    void testDiscard() {
    }

    @Test
    void getCardToDiscard() {
        Card[] cardarray = new Card[4];
        cardarray[0] = new Card(4);
        cardarray[1] = new Card(1);
        cardarray[2] = new Card(1);
        cardarray[3] = new Card(1);
        Player player = new Player(cardarray, 4);

        Assertions.assertEquals(player.getCardToDiscard().getValue(), 1);
        //Tests that even with multiple of the same card and one of the desired card the card being removed is still
        //one of the non-desired kind


        Card[] cardarray = new Card[4];
        cardarray[0] = new Card(4);
        cardarray[1] = new Card(3);
        cardarray[2] = new Card(2);
        cardarray[3] = new Card(1);
        Player player2 = new Player(cardarray, 4);
        //Test for everything being eventually discarded and preferred card not being discarded - how??
        //1234 then for 1,2,3,4 each show that they are all, bar 4, eventually selected with refills.
        //This is only a selector so enough to show that all are selected.




    }
}
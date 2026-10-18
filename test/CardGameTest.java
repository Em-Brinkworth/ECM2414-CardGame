import org.junit.jupiter.api.*;
import java.util.concurrent.CountDownLatch;

class CardGameTest {
    CardGame testCardGame;

    @BeforeEach
    void beforeEach() {
        testCardGame = new CardGame();
    }

    @Nested
    class WinFlagChecks {
        @Test
        void winDetectionWorks() {
            CardGame.PlayerBehaviour behaviour = testCardGame.new PlayerBehaviour(new Player(new Card[]{
                    new Card(1),
                    new Card(1),
                    new Card(1),
                    new Card(1)
            }, 1));
            // Test Winning
            Assertions.assertEquals(1, behaviour.hasWon());
        }
        @Test
        void checkImmediateWinDetection() {
            CardGame.PlayerBehaviour behaviour = testCardGame.new PlayerBehaviour(new Player(new Card[]{
                    new Card(1),
                    new Card(1),
                    new Card(1),
                    new Card(2)
            }, 1));
            // Give it a deck with instant win, check it only goes once
            testCardGame.decksList.add(new CardDeck(new Card[]{
                    new Card(1),
                    new Card(1)
            }, 1));
            testCardGame.decksList.add(new CardDeck(new Card[]{
            }, 1));
            testCardGame.waitForAllPlayersToFinaliseWinner = new CountDownLatch(1);
            behaviour.start();
            Assertions.assertEquals(1, testCardGame.decksList.get(0).getDeck().size());
            Assertions.assertEquals(1, testCardGame.decksList.get(1).getDeck().size());
        }
    }
}
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    ArrayList<Card> hand = new ArrayList<>();

    public Player(Card[] initHand) {
        Arrays.asList(initHand).addAll(hand);
        // TODO: Print hand to output text file
    }

    public void draw(CardDeck deck) {
        // TODO: Implement
    }

    public void discard(CardDeck deck) {
        // TODO: Implement
    }

    public void write() {
        // TODO: Implement
    }
}
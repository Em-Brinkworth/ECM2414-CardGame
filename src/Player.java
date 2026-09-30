import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String playerName;

    public Player(Card[] initHand, String name) {
        hand.addAll(Arrays.asList(initHand));
        playerName = "player " + name;
        TextHandler.write(playerName + " initial hand " + hand.toString());
    }

    public void draw(CardDeck deck) {
        Card cardDrawn = deck.draw();
        hand.add(cardDrawn);
        TextHandler.write(playerName + " draws a " + cardDrawn.getValue() + " from " + deck.getDeckName());
    }

    public void discard(CardDeck deck, Card card) {
        // TODO: Implement
        deck.discardToDeck(card);
        hand.remove(card);
        TextHandler.write(playerName + " discards a " + card.getValue() + " to " + deck.getDeckName());
    }
}
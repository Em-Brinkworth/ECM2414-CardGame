import java.util.ArrayList;
import java.util.Arrays;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<>();

    public CardDeck (Card[] initDeck) {
        Arrays.asList(initDeck).addAll(deck);
    }

    public Card draw() {
        // Returns the top card of the deck and removes add
        return deck.removeLast();
    }

    public void discardToDeck(Card card) {
        // Adds a card to the bottom of the deck
        deck.addFirst(card);
    }
}

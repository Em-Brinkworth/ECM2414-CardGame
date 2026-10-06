import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {
    private ArrayList<Card> deck = new ArrayList<>();
    private final String deckName;

    public CardDeck (Card[] initDeck, int deckName) {
        deck.addAll(List.of(initDeck));
        this.deckName = "deck " + deckName;
    }

    public synchronized Card draw() {
        // Returns the top card of the deck and removes it
        return deck.removeLast();
    }

    public synchronized void discardToDeck(Card card) {
        // Adds a card to the bottom of the deck
        deck.addFirst(card);
    }

    public String getDeckName() {
        return deckName;
    }
}

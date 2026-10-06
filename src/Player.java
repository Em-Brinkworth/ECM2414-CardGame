import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String playerName;
    private final String fp;
    private final int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return playerName;
    }

    public String getFp() {
        return fp;
    }

    public String getHandAsString() {
        StringBuilder handAsString = new StringBuilder();
        for (Card card: hand) {
            handAsString.append(card).append(" ");
        }
        return handAsString.toString();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Player(Card[] initHand, int id, String fpinit) {
        hand.addAll(Arrays.asList(initHand));
        playerName = "player " + id;
        fp = fpinit;
        TextHandler.write(playerName + " initial hand " + getHandAsString(), fp);
        this.id = id;
    }

    public boolean checkIfWon() {
        //Checks if all cards in hand are same value, by checking if any differ from the first card.
        for (Card i : hand){
            if(i.getValue()!=hand.getFirst().getValue()){
                return false;
            }
        }
        return true;
    }

    public void draw(CardDeck deck) {
        try {
            Card cardDrawn = deck.draw();
            hand.add(cardDrawn);
            TextHandler.write(playerName + " draws a " + cardDrawn.getValue() + " from " + deck.getDeckName(), fp);
        } catch (NoSuchElementException e) {
            TextHandler.write(playerName + " fails to draw a card.", fp);
        }
    }

    public void discard(CardDeck deck) { //removed card input as card to be discarded is determined here
        Card card = getCardToDiscard();
        //gets the value of a random position in positionsOfDiscardableCards and picks that card in the hand to discard.
        //we use an arraylist for the positions of discardable cards as there may be any number of cards one can discard
        deck.discardToDeck(card);
        hand.remove(card);
        TextHandler.write(playerName + " discards a " + card.getValue() + " to " + deck.getDeckName(), fp);
    }

    private Card getCardToDiscard() {
        ArrayList<Integer> positionsOfDiscardableCards = new ArrayList<>();
        Card card;
        for(int i = 0; i<hand.size();i++ ) {
            if(hand.get(i).getValue()!= id){
                positionsOfDiscardableCards.add(i);
            }
        }
        Random randomiser = new Random();
        try {
            card = hand.get(positionsOfDiscardableCards.get(randomiser.nextInt(positionsOfDiscardableCards.size() - 1)));
        } catch (IllegalArgumentException e) {
            card = hand.get(randomiser.nextInt(hand.size()-1));
        }
        return card;
    }
}
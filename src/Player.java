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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Player(Card[] initHand, int id, String fpinit) {
        hand.addAll(Arrays.asList(initHand));
        playerName = "player " + id;
        fp = fpinit;
        TextHandler.write(playerName + " initial hand " + hand, fp);
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
        ArrayList<Integer> positionsofdiscardablecards = new ArrayList<>();
        for(int i = 0; i<hand.size();i++ ) {
            if(hand.get(i).getValue()!= id){
                positionsofdiscardablecards.add(i);
            }
        }
        Random randomiser = new Random();
        Card card = hand.get(positionsofdiscardablecards.get(randomiser.nextInt(positionsofdiscardablecards.size()-1)));
        //gets the value of a random position in positionsofdiscardablecards and picks that card in the hand to discard.
        //we use an arraylist for the positions of discardable cards as there may be any number of cards one can discard
        deck.discardToDeck(card);
        hand.remove(card);
        TextHandler.write(playerName + " discards a " + card.getValue() + " to " + deck.getDeckName(), fp);
    }
}
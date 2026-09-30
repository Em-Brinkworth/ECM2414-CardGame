import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String playerName;
    private final String fn;
    private final int RelativePosition;

    public Player(Card[] initHand, String name, String fninit, int PositionInitialiser) {
        hand.addAll(Arrays.asList(initHand));
        playerName = "player " + name;
        fn = fninit;
        TextHandler.write(playerName + " initial hand " + hand.toString(), fn);
        RelativePosition = PositionInitialiser;





    }

    public boolean CheckIfWon() {
        //Checks if all cards in hand are same value, by checking if any differ from the first card.
        boolean hasWon = true;
        for (Card i : hand){
            if(i.getValue()!=hand.get(0).getValue()){
                hasWon = false;
            }
        }
        return hasWon;
    }

    public void draw(CardDeck deck) {
        Card cardDrawn = deck.draw();
        hand.add(cardDrawn);
        TextHandler.write(playerName + " draws a " + cardDrawn.getValue() + " from " + deck.getDeckName(), fn);
    }

    public void discard(CardDeck deck) { //removed card input as card to be discarded is determined here
        ArrayList<Integer> positionsofdiscardablecards = new ArrayList<Integer>();
        for(int i =0; i<hand.size();i++ ) {
            if(hand.get(i).getValue()!=RelativePosition){
                positionsofdiscardablecards.add(i);
            }
        }
        Random randomiser = new Random();
        Card card = hand.get(positionsofdiscardablecards.get(randomiser.nextInt(positionsofdiscardablecards.size()-1)));
        //gets the value of a random position in positionsofdiscardablecards and picks that card in the hand to discard.
        //we use an arraylist for the positions of discardable cards as there may be any number of cards one can discard
        deck.discardToDeck(card);
        hand.remove(card);
        TextHandler.write(playerName + " discards a " + card.getValue() + " to " + deck.getDeckName(), fn);
    }

}
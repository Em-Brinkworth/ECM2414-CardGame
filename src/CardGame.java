import java.util.ArrayList;

public class CardGame extends Thread{
    ArrayList<Integer> winFlags = new ArrayList<>();
    ArrayList<CardDeck> deckList;
    void main() {
        // TODO
        // Input Players
        // Input file
        // Create n threads

        //

        // Lock decks
        // Lock I/O
    }

    public void run(Player player) {
        while (true) {
            // Check if won
            if (player.checkIfWon()) {
                winFlags.add(player.getId());
            }
            // Check if someone else won
            if (!winFlags.isEmpty()) {
                for (Integer i: winFlags) {
                    TextHandler.write("player " + i + " has informed " + player.getName() + "that player " + i + " has won.", player.getFn());
                }
                TextHandler.write(player.getName() + " has left.", player.getFn());
                TextHandler.write(player.getName() + " final hand " + player.getHand().toString(), player.getFn());
                return;
            }
            // Pick up a card
            player.draw(deckList.get(player.getId() - 1));
            // Discard a card
            player.discard(deckList.get(player.getId()));
        }
    }
}
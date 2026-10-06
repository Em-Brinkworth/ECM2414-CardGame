import org.w3c.dom.ranges.RangeException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame extends Thread{
    volatile ArrayList<Integer> winFlags = new ArrayList<>();
    ArrayList<CardDeck> decksList;
    void main() {
        // Input Players
        int playerCount;
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a number of players.");
            String playerCountString = inputScanner.nextLine();
            try{
                playerCount = Integer.parseInt(playerCountString);
                if (!(playerCount>0)) throw new RangeException((short) 0, "That's not a valid integer. Please try again");
                break;
            } catch (NumberFormatException | RangeException e) {
                System.out.println("That's not a valid integer. Please try again ");
            }
        }
        // Input file
        int[] packContents;
        while(true){
            System.out.println("Enter the filename of the pack you want to use.");
            String fnString = inputScanner.nextLine();
            try{
                String filepath = "../Decks/" + fnString;
                packContents = TextHandler.read(filepath);
                if (packContents.length != playerCount*8) throw new RangeException((short) 1, "File length is incorrect");
                break;
            } catch (RangeException e) {
                System.out.print("File length is incorrect");
            }
        }
        // Deal initial hands and decklists
        // Create Decks
        for (int i = 0; i < playerCount; i++) {
            decksList.add(new CardDeck(new Card[]{
                    new Card(packContents[playerCount * 4 + i]),
                    new Card(packContents[playerCount * 5 + i]),
                    new Card(packContents[playerCount * 6 + i]),
                    new Card(packContents[playerCount * 7 + i])
            }, "" + i + 1
            ));
        }
        // TODO
        // Create players and initialise threads
        for (int i = 0; i < playerCount; i++) {
            new PlayerBehaviour(new Player(
                    new Card[]{
                            new Card(packContents[i]),
                            new Card(packContents[playerCount * 1 + i]),
                            new Card(packContents[playerCount * 2 + i]),
                            new Card(packContents[playerCount * 3 + i]),
                    },
                    i+1,
                    "../Games/Player" + (i + 1) + ".txt"
            )).start();
        }
    }
    public class PlayerBehaviour extends Thread {
        private final Player player;

        public PlayerBehaviour(Player player) {
            this.player = player;
        }

        public void run() {
            while (true) {
                // Check if won
                if (player.checkIfWon()) {
                    winFlags.add(player.getId());
                }
                // Check if someone else won
                if (!winFlags.isEmpty()) {
                    for (Integer i : winFlags) {
                        if (i != player.getId()) {
                            TextHandler.write("player " + i + " has informed " + player.getName() + "that player " + i + " has won.", player.getFp());
                        } else {
                            TextHandler.write("player " + i + " has won.", player.getFp());
                        }
                    }
                    TextHandler.write(player.getName() + " has left.", player.getFp());
                    TextHandler.write(player.getName() + " final hand " + player.getHand().toString(), player.getFp());
                    return;
                }
                // Pick up a card
                player.draw(decksList.get(player.getId() - 1));
                // Discard a card
                player.discard(decksList.get(player.getId()));
            }
        }
    }
}

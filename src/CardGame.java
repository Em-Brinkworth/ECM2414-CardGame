import org.w3c.dom.ranges.RangeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class CardGame extends Thread{
    volatile ArrayList<Integer> winFlags = new ArrayList<>();
    volatile ArrayList<CardDeck> decksList = new ArrayList<>();
    CountDownLatch waitForAllPlayersToFinaliseWinner;
    int playerCount;
    void main() {
        // Input Players
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
        TextHandler.delete(playerCount);
        // Input file
        int[] packContents;
        while(true){
            System.out.println("Enter the filename of the pack you want to use.");
            String fnString = inputScanner.nextLine();
            try{
                packContents = TextHandler.read("Decks/" + fnString, playerCount);
                if (!Arrays.equals(packContents, new int[]{})) {
                    break;
                }
            } catch (RangeException e) {
                System.out.print("File length is incorrect\n");
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
            }, i + 1
            ));
        }
        // Create players and initialise threads (and initialise CountDownLatch)
        waitForAllPlayersToFinaliseWinner = new CountDownLatch(playerCount);
        for (int i = 0; i < playerCount; i++) {
            new PlayerBehaviour(new Player(
                    new Card[]{
                            new Card(packContents[i]),
                            new Card(packContents[playerCount + i]),
                            new Card(packContents[playerCount * 2 + i]),
                            new Card(packContents[playerCount * 3 + i]),
                    },
                    i+1,
                    "Games/player" + (i + 1) + ".txt"
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
                    waitForAllPlayersToFinaliseWinner.countDown();
                    try {
                        waitForAllPlayersToFinaliseWinner.await();
                        for (Integer i : winFlags) {
                            if (i != player.getId()) {
                                TextHandler.write("player " + i + " has informed " + player.getName() + " that player " + i + " has won.", player.getFp());
                            } else {
                                TextHandler.write("player " + i + " has won.", player.getFp());
                            }
                        }
                        TextHandler.write(player.getName() + " has left.", player.getFp());
                        TextHandler.write(player.getName() + " final hand " + player.getHandAsString(), player.getFp());
                        return;
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                // Pick up a card
                if (player.draw(decksList.get(player.getId() - 1))) {
                    // Discard a card
                    if (player.getId() != playerCount) {
                        player.discard(decksList.get(player.getId()));
                    } else {
                        player.discard(decksList.getFirst());
                    }
                }
            }
        }
    }
}

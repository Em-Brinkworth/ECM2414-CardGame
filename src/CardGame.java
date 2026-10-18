import org.w3c.dom.ranges.RangeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class CardGame extends Thread{
    volatile int winFlag = 0;
    volatile ArrayList<CardDeck> decksList = new ArrayList<>();
    CountDownLatch waitForAllPlayersToFinaliseWinner;
    int playerCount;
    void main() {
        // Input Players
        System.out.println("Please enter a number of players.");
        playerCount = getPlayerCount();
        TextHandler.delete(new String[]{"Games/player%s.txt", "Games/Deck%s_output.txt"}, playerCount);
        // Input file
        System.out.println("Enter the filename of the pack you want to use.");
        int[] packContents = getFileContents();
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
                    i+1
            )).start();
        }
    }

    protected int getPlayerCount() {
        Scanner inputScanner = new Scanner(System.in);
        int tempPlayerCount;
        while (true) {
            String playerCountString = inputScanner.nextLine();
            try{
                tempPlayerCount = Integer.parseInt(playerCountString);
                if (!(tempPlayerCount>0)) throw new RangeException((short) 0, "That's not a valid integer. Please try again");
                break;
            } catch (NumberFormatException | RangeException e) {
                System.out.println("That's not a valid integer. Please try again.");
            }
        }
        return tempPlayerCount;
    }

    protected int[] getFileContents() {
        int[] packContents;
        while(true){
            Scanner inputScanner = new Scanner(System.in);
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
        return packContents;
    }

    public class PlayerBehaviour extends Thread {
        private final Player player;

        public PlayerBehaviour(Player player) {
            this.player = player;
        }

        public void run() {
            while (true) {
                // Check if won
                if (hasWon() != 0) {
                    winFlag = hasWon();
                }
                // Check if someone else won
                if (winFlag != 0) {
                    runGameEndProcedure();
                    return;
                }
                drawDiscard();
            }
        }
        protected int hasWon() {
            if (player.checkIfWon()) {
                return player.getId();
            }
            return 0;
        }
        protected void runGameEndProcedure() {
            waitForAllPlayersToFinaliseWinner.countDown();
            try {
                waitForAllPlayersToFinaliseWinner.await();
                if (winFlag != player.getId()) {
                    TextHandler.write("player " + winFlag + " has informed " + player.getName() + " that player " + winFlag + " has won.", player.getFp());
                } else {
                    TextHandler.write("player " + winFlag + " has won.", player.getFp());
                }
                TextHandler.write(player.getName() + " has left.", player.getFp());
                TextHandler.write(player.getName() + " final hand " + player.getHandAsString(), player.getFp());

                TextHandler.write("Deck " + player.getId() + " final contents is " + decksList.get(player.getId()-1).getDeckAsString(), "Games/Deck" + player.getId() + "_output.txt");
            } catch (InterruptedException _) {}
        }
        protected synchronized void drawDiscard() {
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

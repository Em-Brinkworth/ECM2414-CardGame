import org.w3c.dom.ranges.RangeException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame extends Thread{
    ArrayList<Integer> winFlags = new ArrayList<>();
    ArrayList<CardDeck> deckList;
    void main() {
        // TODO
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
            };
        }
        // Input file
        int[] packContents;
        while(true){
            System.out.println("Enter the filename of the pack you want to use.");
            String fnString = inputScanner.nextLine();
            try{
                Path filepath = Paths.get("../Decks/" + fnString);
                packContents = TextHandler.read(filepath);
                if (packContents.length != playerCount*8) throw new RangeException((short) 1, "File length is incorrect");
                break;
            }catch(FileNotFoundException e){
                System.out.println("That's not a valid file in the decks folder. Please enter another filename");
            } catch (RangeException e) {
                System.out.print("File length is incorrect");
            };
        }
        // Create n threads
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
                    TextHandler.write("player " + i + " has informed " + player.getName() + "that player " + i + " has won.", player.getFp());
                }
                TextHandler.write(player.getName() + " has left.", player.getFp());
                TextHandler.write(player.getName() + " final hand " + player.getHand().toString(), player.getFp());
                return;
            }
            // Pick up a card
            player.draw(deckList.get(player.getId() - 1));
            // Discard a card
            player.discard(deckList.get(player.getId()));
        }
    }
}
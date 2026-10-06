# ECM2414-CardGame

## How to Run

## Card Game

The main function from which the program is run

### Internal Variables

`ArrayList<Integer> winFlags = new ArrayList<>();` stores the id of any players who have won.  
`ArrayList<CardDeck> decksList;` Stores the deck of cards being used to play the game with.  

### Main

`void main()`  
Takes the user input for the number of players and filename of the pack used.  
The number of players is validated to check if it is a) an integer and b) above 0, and the user is prompted until they enter something valid.  
The filepath is checked for if it exists and if it contains 8n results, where n is the number of players, the contents of the file are then stored to `deckList`.  

The decks are then created and stored in `decksList`, with the cards being dealt in a round-robin fashion.  
Then the players are created, assigned their initial hands in a round-robin fashion, and their threads are started.  

### Player Behaviour

Extends `Thread`  
Stores the behaviour of a player's thread, so that multiple player instances can be created.  

#### Internal Variables

`private final Player player` stores the player the thread is representing.  

#### Run

`public void run()`
Defines the behaviour of a player in its thread.  
First checks if it has won, and if so sets itself as a winner in `winFlags`.  
Then checks if another player has won, if so, the thread writes the relevant output lines, then terminates.  
It then takes a turn, drawing a card then discarding a card.  
A card is not discarded if a player failed to draw a card.  

## Card

Stores the value of a card and provides a setter for it.

### Internal variables

`private final int value` stores the value of the card.  

### Constructor

`public Card(int value)`  
`int value` contains the value of the card.  
Assigns a value to the card.  

## Player

Holds a hand and provides methods for editing said hand.  

### Internal Variables

`private ArrayList<Card> hand` is where the player's hand is stored.  
`private final String playerName` is the name of the player, stored as "player" + the player ID.  
`private final String fn` is the name of the file where the player info is stored.  
`private final int id` is the player's id.  

### Constructor
`public Player(Card[] initHand, int id, String fpinit)`  
`Card[] initHand` is the starting player hand.  
`int id` is the ID of the player.  
`String fpinit` is the path to the file which holds the player info.  
The function assigns the variables to appropriate internal variables and writes the starting hand to the player's file.  

### Draw

`public boolean draw(CardDeck deck)`  
`CardDeck deck` is the deck the player is drawing from.  
The function removes the top element of the 'deck' (The last element of the array) and returns true.  
It then writes the drawn card to the player's file.   
If there is no deck to draw from the function instead writes this to the output file and returns false.  

### Get Card to Discard

`private Card getCardToDiscard()`  
Returns a random card to discard not matching the player's id.  
If all cards match then a random card is returned.  

### Discard

`public void discard(CardDeck deck)`  
`CardDeck deck` is the deck the player is discarding to.
Finds the card the player is to discard with `getCardToDiscard` and then removes that card from the hand, and discards it to `deck` using `discardToDeck`.  
The card discarded is then written to the player file.  

### Get Hand as String

`public String getHandAsString()`  

Returns a string in the format `"n1 n2 n3 ..."` where `nx` is the xth card in a player's hand.  

## Card Deck

Stores the deck list and contains methods to draw from the top and discard to the bottom of the deck.  

### Internal Variables

`private ArrayList<Card> deck` stores the current contents of the deck, with index 0 being the bottom of the deck and the final element being the top.  
`private final String deckName` stores the decks 'name', stored as "deck" + the deck ID.  

### Constructor

`public CardDeck (Card[] initDeck, String deckName)`
`Card[] initDeck` defines the initial starting deck.  
`int deckName` contains the deck ID.  

### Draw

`public synchronized Card draw()`  
Removes the top card of the deck and removes it.  
Is synchronised to avoid thread collisions

### Discard

`public synchronized void discardToDeck(Card card)`  
`Card card` the card being discarded to the deck.  
Adds the specified card to the bottom of the deck.  
Is synchronised to avoid thread collisions.  

## Text Handler

Deals with I/O operations to and from the files in the Decks and Games directory.  

### Read

`static int[] read(String fp, int playerCount) throws RangeException`  
`String fp` is the filepath of the file being read from.  
`int playerCount` is the number of players in the game.  
Reads the file at `fp` and returns an `int[]` containing the deck located in that file.  
Throws a range exception is the `int[]` that would be returned does not have length 8*`playerCount` exactly.  

### Write

`static synchronized void write(String text, String fp)`    
`String text` is the text to be written to the file.  
`String fp` is the filepath of the file being written to.  
Writes the message `text` to the file at location `fp`.  
Is synchronized to prevent collisions from multiple players trying to access the same file.  

### Delete
`static void delete(int playerCount)`  
`int playerCount` is the number of players in the game.  
Deletes the first n player logs, where n is the `playerCount`.  
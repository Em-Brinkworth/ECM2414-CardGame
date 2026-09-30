# ECM2414-CardGame

## How to Run

## Card Game

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
`fn` is the name of the file where the player info is stored.  

### Constructor
`public Player(Card[] initHand, String name)`  
`Card[] initHand` is the starting player hand.  
`String name` is the ID of the player.  
The function assigns the variables to appropriate internal variables and writes the starting hand to the player's file.  

### Draw

`public void draw(CardDeck deck)`  
`CardDeck deck` is the deck the player is drawing from.  
The function removes the top element of the 'deck' (The last element of the array) and returns the `Card` removed.  
It then writes the drawn card to the player's file.   

### Discard

`public void discard(CardDeck deck, Card card)`  
`CardDeck deck` is the deck the player is discarding to.  
`Card card` is the card being discarded.  
Removes `card` from the players hand and adds it to the 'bottom' (0th index) of the deck specified.  
It then writes the discarded card to the player's file.  

## Card Deck

Stores the deck list and contains methods to draw from the top and discard to the bottom of the deck.  

### Internal Variables

`private ArrayList<Card> deck` stores the current contents of the deck, with index 0 being the bottom of the deck and the final element being the top.  
`private final String deckName` stores the decks 'name', stored as "deck" + the deck ID.  

### Constructor

`public CardDeck (Card[] initDeck, String deckName)`
`Card[] initDeck` defines the initial starting deck.  
`String deckName` contains the deck ID.  

### Draw

`public synchronized Card draw()`  
Removes the top card of the deck and removes it.  
Is synchronised to avoid thread collisions

### Discard

`public synchronized void discardToDeck(Card card)`  
`Card card` the card being discarded to the deck.  
Adds the specified card to the bottom of the deck.  
Is synchronised to avoid thread collisions.  
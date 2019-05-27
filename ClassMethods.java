/*  This file is not really a Java file as much as it is an easily editable documentation for the project
    Note with public constants: since other classes can use them and they are immutable, they should be public
    Considering we have three hours on one day to finish this, you may want to put in work on your own time
    This list is dynamic; feel free to add, change, or remove as you see fit, but do so logically
     
Public Methods, Constructors and Constants For Each Class:
  - Card:
     - Card(String i, Color c) ❌ Make sure the card also stores the appropriate image in a private BufferedImage
     - Color getColor() ✅
     - String getID() ✅
     - void setColor() ❌ for wild card effects; I don't know what selectColor attempts to do
     - boolean isUseful(Card topCard) ❌ the function should check the parameter, not whatever deck.getTopCard() is
     - void render(Graphics g, int x, int y) ❌ 
  - Deck:
     - Deck() ❌ The deck should shuffle() after adding all the cards
     - static final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}; ❌ Move to Card and make public please.
     - void shuffle() ✅
     - void add(Card c) ✅ seems a little inefficient, but whatever
     - Card draw() ✅ Only Game should be calling this!
     - int size() ✅
  - Hand:
     - Non-Stub Classes in Hand: 
        - Hand() ❌ I'm not sure what you are trying; just create an empty hand 
        - ArrayList<Card> getHand() ✅
        - int size() ❌
        - void add(Card drawnCard) ❌ Think about it like poker; you ask the dealer to give you a card rather than grab it yourself
        - Card remove(int index) ❌ The equivalent of playing the card by giving it to the dealer
        - boolean getVisibility() ❌ 
        - void setVisibility(boolean v) ❌ As you'll realize, a new private variable goes with these two
        - ArrayList<Integer> getUsefulIndices(Card topCard) ❌ I figured this would help more since remove(int)
        - void render(Graphics g, int x, int y) ❌ the visibility determines whether to show faces or backs
     - Stub Classes in Hand:
        - boolean getUnoCall() ❌ This function returns whether a player said "Uno!" since their last turn.
        - int findPlay(Card topCard) ❌ Likely starts by calling getUsefulIndices(topCard)
	- boolean playDrawnCard() ❌ Similar to findPlay(), but only in consideration to the drawn card
        - Color selectColor() ❌ 
        - boolean challengeDrawFour(>some parameters may help with decision making<) ❌
     - PlayerHand:
        - PlayerHand() ❌ This hand should start visible
        - boolean getUnoCall() ❌ All these will need to communicate with the GUI in some way
        - int findPlay(Card topCard) ❌ 
	- boolean playDrawnCard() ❌
        - Color selectColor() ❌ I imagine a pop-up box with this
        - boolean challengeDrawFour(>I'll give parameters even if it doesn't matter<) ❌
     - ComputerHand:
        - ComputerHand() ❌ This hand should start not visible
        - boolean getUnoCall() ❌ RNG?
        - int findPlay(Card topCard) ❌ Your findPlay() was a nice start :)
	- boolean playDrawnCard() ❌ This probably just calls isUseful() on the new Card
        - Color selectColor() ❌ Whatever the computer has the most of, I'd assume
        - boolean challengeDrawFour(>some parameters may help with decision making<) ❌
  - Game:
     - Game(int players) ✅ 
     - static void main(String args[]) ❌ Does not loop the game's events (a few private functions for game logic are incomplete)
  - UnoGUI:
     - UnoGUI() ❌ if UnoGUI extends JFrame, then super("Title"); this will also make paint(g) easier to use
     - static final double CARD_CONVERSION_FACTOR = 3/2; ❌ This stuff helps with the Card's and Hand's render() functions
     - static final int CARD_HEIGHT = (int) 68 * CARD_CONVERSION_FACTOR; ❌ Possibly also move these three to Card
     - static final int CARD_WIDTH = (int) 44 * CARD_CONVERSION_FACTOR; ❌ All my images are 44x68, if you're wondering
     - void paint(Graphics g) ❌ Takes over paintComponent(). Start with super.paint(g);
     - void takeCard(Card card, Hand hand) ❌ If you plan on cutting out animations, you may need need neither of these functions
     - void placeCard(Card card, Hand hand) ❌
     - void unoGUI2(>parameters still needed<) ❌ May we assume this calls paint(g)?
     - ❌ Additional functions to communicate with PlayerHand are also likely needed
*/

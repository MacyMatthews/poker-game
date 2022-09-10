/* Macy Matthews
 * mlm2363
 * deck class
 */

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	
    // make a 52 card deck here
	public Deck(){
        top = 0;
        cards = new Card[52];
        for(int suit = 1; suit <= 4; suit++){
            for(int rank = 1; rank <= 13; rank++){
                this.cards[top] = new Card(suit , rank);
                top++;
            }
        }
         top = 0;
	}
    
	// shuffle the deck here
	public void shuffle(){
		
        for(int i = 0; i < cards.length - 1; i++){
            int rand = (int)(Math.random() * (i+1));
            Card temp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = temp;
        }
	}
	
    // deal the top card in the deck
	public Card deal(){
        return cards[top++];
        
	}
	

}

/* Macy Matthews
 * mlm2363
 * player class
 */

import java.util.ArrayList;

public class Player {
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	
	// create a player here	
	public Player(){		
        hand = new ArrayList<Card>();
        bankroll = 50;
        bet = 0;
	}

    // add the card c to the player's hand
	public void addCard(Card c){
        hand.add(c);
	}

    // remove the card c from the player's hand
	public void removeCard(Card c){
        hand.remove(c);
        
    }
		
    public void bets(double amt){
        bet = amt;
        bankroll -= amt;
                
    }

    //	adjust bankroll if player wins
    public void winnings(double odds){
        bankroll += odds;
        
    }

    // return current balance of bankroll
    public double getBankroll(){
        return bankroll;
        
    }

}



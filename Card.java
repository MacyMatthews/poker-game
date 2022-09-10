/* Macy Matthews
 * mlm2363
 * card class
 */

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
    
	public Card(int s, int r){
		//make a card with suit s and value v
        this.suit = s;
        this.rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
        if(this.suit < c.suit){
            return -1;
        }
        else if(this.suit > c.suit){
            return 1;
        }
        else if(this.suit == c.suit){
            return 0;
        }
        if(this.rank > c.rank){
            return 1;
        }
        else if(this.rank < c.rank){
            return -1;
        }
        else if(this.rank == c.rank){
            return 0;
        }
        
        return 2;
	}
	
    //method to assign a suit and rank to each card
	public String toString(){
        String s = "";
        String r = "";
		if(suit == 1){
            s = "spades";
        }
        else if(suit == 2){
            s = "hearts";
        }
        else if(suit == 3){
            s = "diamonds";
        }
        else if(suit == 4){
            s = "clubs";
        }
        if(rank == 1){
            r = "ace";
        }
        else if(rank == 2){
            r = "two";
        }
        else if(rank == 3){
            r = "three";
        }
        else if(rank == 4){
            r = "four";
        }
        else if(rank == 5){
            r = "five";
        }
        else if(rank == 6){
            r = "six";
        }
        else if(rank == 7){
            r = "seven";
        }
        else if(rank == 8){
            r = "eight";
        }
        else if(rank == 9){
            r = "nine";
        }
        else if(rank == 10){
            r = "ten";
        }
        else if(rank ==11){
            r = "jack";
        }
        else if(rank == 12){
            r = "queen";
        }
        else if(rank == 13){
            r = "king";
        }
        
        return r + " of " + s;
	}
   
    public int getRank(){
        return rank;
    }
    
    public int getSuit(){
        return suit;
    }

}

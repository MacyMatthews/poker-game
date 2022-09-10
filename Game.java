/* Macy Matthews
 * mlm2363
 * game class
 */

import java.util.Scanner;
import java.util.*;

public class Game {
	
	private Player p;
	private Deck cards;
    private String[] testHand;
    private ArrayList<Card> hand = new ArrayList<>();
    private int pair;
    private int bankroll;
   
	
	public Game(String[] testHand){
        p = new Player();
        cards = new Deck();
        for(String tester : testHand){
            char getSuit = tester.charAt(0);
            int suit = 0;
            switch(getSuit){
                case 's':
                    suit = 1;
                    break;
                    
                case 'h':
                    suit = 2;
                    break;
                
                case 'd':
                    suit = 3;
                    break;
                        
                case 'c':
                    suit = 4;
                    break;
            }
            
            int rank = Integer.parseInt(tester.substring(1));
            Card newCard = new Card(suit, rank);
            hand.add(newCard);
        }
        
    }        
    
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
        p = new Player();
        cards = new Deck();
	}
   
    //play the game
	public void play(){
        
        System.out.println("Welcome to Virtual Poker!");
            p.bets(0);
            double bet;
            Scanner in1 = new Scanner(System.in);
            System.out.println("Bet 1-5 tokens");
            bet = in1.nextDouble();
            bankroll -= bet;
                
            if(bet < 1 || bet > 5){
                System.out.println("Please bet a number between 1-5 tokens");
            }
            
            p.bets(bet);
        
            //prevent having more cards added to the hand if already using testhand
            if(hand.size() < 5){
                
            for(int i = 0; i <= 5; i++){
                cards.shuffle();
                hand.add(cards.deal());
            } 
            }
            
            System.out.println("Your hand is " + hand);

            System.out.println("Would you like to exchange any cards? (yes/no)");
            Scanner in2 = new Scanner(System.in);        
            String exchange = in2.nextLine();
            
            if(exchange.equals("no")){
                System.out.println("Your hand is " + hand);
            }  
            
            //call the exchCard() method
            else if(exchange.equals("yes")){
                this.exchCard();
                System.out.println("Your new hand is: " + hand);
            }
        
                //evaluate hand   
                System.out.println("Let's see what type of hand you have");
                String typeOfHand = checkHand(hand);
                System.out.println("Your hand is: " + typeOfHand);
                double tokensWon = bet * getWinnings(typeOfHand);
                System.out.println("You won: " + tokensWon + " tokens");
                p.winnings(tokensWon);
                System.out.println("You now have " + p.getBankroll() + " tokens");
                System.out.println("Thanks for playing!");                  
            }
        
    
    //method for exchanging cards
    private void exchCard(){
        
            int cardPos = 0;
            System.out.println("How many cards would you like to exchange? (1-5)");
            Scanner in2 = new Scanner(System.in); 
            int numOfExch = in2.nextInt();
        
            if(numOfExch < 1 || numOfExch > 5){
              System.out.println("Please enter a valid amount of cards (1-5)");  
                
            }else{
                
                int i = 0;
                while(i < numOfExch){
                
                    System.out.println("Which card do you want to exchange? (0-4, 0 being the first card)");
                    System.out.println("Don't exchange the same card more than once!");
                    Scanner in3 = new Scanner(System.in); 
                    cardPos = in3.nextInt();
                
                    hand.remove(cardPos);
                    hand.add(cards.deal());
                    i++;
                
                }
            
            }
    }
                

    //determine how many pairs are in the hand
    private int pairs(ArrayList<Card> hand){
        int pair = 1;
        
        for(int i = 0; i < 5; i++){
            
            for(int j = i + 1; j < 5; j++){

                if(hand.get(i).getRank() == hand.get(j).getRank()){
                   
                     pair++;
                    
                }
            }
        } 
        
        return pair;
    }
        
    //below the hand is checked for each type of poker hand

    private boolean isNoPair(ArrayList<Card> hand){    
        
        if(this.pairs(hand) == 1){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isOnePair(ArrayList<Card> hand){
        
        if(pairs(hand) == 2){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isTwoPairs(ArrayList<Card> hand){
        
        if(pairs(hand) == 3){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isThreeOfAKind(ArrayList<Card> hand){
        
        if(pairs(hand) == 4){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isStraight(ArrayList<Card> hand){
        
        boolean r = true;
        for(int i = 0; i < 4; i++){
            
          int card1 = hand.get(i).getRank();
          int card2 = hand.get(i+1).getRank();
            
            if(card1 != card2 - 1){
                return false;
            }else{
                return true;
            }
        }
        return r;
    }
    
    private boolean isFlush(ArrayList<Card> hand){
        
        boolean r = true;
        for(int i = 0; i < 4; i++){
            
          int suit1 = hand.get(i).getSuit();
          int suit2 = hand.get(i+1).getSuit();
            
            if(suit1 != suit2 - 1){
                return false;
            }else{
                return true;
            }
        }
        return r;
    }
    
    private boolean isFullHouse(ArrayList<Card> hand){
        
        if(pairs(hand) == 5){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isFourOfAKind(ArrayList<Card> hand){
        
        if(pairs(hand) == 7){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isStraightFlush(ArrayList<Card> hand){
        
        if(isStraight(hand) && isFlush(hand)){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean isRoyalFlush(ArrayList<Card> hand){
        
        int first = hand.get(0).getRank();
        int last = hand.get(4).getRank();
        
        if(first == 1 && isStraight(hand) && isFlush(hand) && last == 13){
            return true;
        }else{
            return false;
        }
    }

    //lets the user know what kind of hand they have
    public String checkHand(ArrayList<Card> hand){
        String r = "";
        if(isNoPair(hand)){
            return "No Pairs";
        }
        else if(isOnePair(hand)){
            return "One Pair";
        }
        else if(isTwoPairs(hand)){
            return "Two Pairs";
        }
        else if(isThreeOfAKind(hand)){
            return "Three of a Kind";
        }
        else if(isFourOfAKind(hand)){
            return "Four of a Kind";
        }
        else if(isStraight(hand)){
            return "Straight";
        }
        else if(isFlush(hand)){
            return "Flush";
        }
        else if(isFullHouse(hand)){
            return "Full House";
        }
        else if(isStraightFlush(hand)){
            return "Straight Flush";
        }
        else if(isRoyalFlush(hand)){
            return "Royal Flush";
        }
        
        return r;
    }

    //determines what the user's bet will be multiplied with based on the type of hand they have
    private int getWinnings(String typeOfHand){
        
        if(typeOfHand.equals("One Pair")){
            return 1;
        }
        else if(typeOfHand.equals("Two Pairs")){
            return 2;
        }
        else if(typeOfHand.equals("Three of a Kind")){
            return 3;
        }
        else if(typeOfHand.equals("Four of a Kind")){
            return 25;
        }
        else if(typeOfHand.equals("Straight")){
            return 4;
        }
        else if(typeOfHand.equals("Flush")){
            return 5;
        }
        else if(typeOfHand.equals("Full House")){
            return 6;
        }
        else if(typeOfHand.equals("Straight Flush")){
            return 50;
        }
        else if(typeOfHand.equals("Royal Flush")){
            return 250;
        }
        else{
            return 0;
        }
    }
}










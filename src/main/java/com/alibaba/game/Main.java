package com.alibaba.game;

import com.alibaba.game.texasholdem.Card;
import com.alibaba.game.texasholdem.Dealer;
import com.alibaba.game.texasholdem.Player;

import java.util.List;

public class Main {
	
	public static Player bestPokerHand(Player initPlayer, Player community){
        List<Card> communityCards = community.getCards(); 
        List<Card> initCards = initPlayer.getCards();
//        now let's get the best poker hand
        Player playerWithBestPoker = new Player();
        Dealer bestPokerDealer = new Dealer();
        for(int m = 0; m < 5; m++) {
        	for(int n = m+1; n < 5; n++) {
        		//give initial 3 cards 2 other cards from the community cards
        		Player freshup = new Player();
//        		loaded with the 3 init cards
        		for(int i = 0; i< 3; i++) {
        			freshup.addCard(initCards.get(i));
        		}
        		freshup.addCard(communityCards.get(m));
        		freshup.addCard(communityCards.get(n));
        		bestPokerDealer.join(freshup);
        		playerWithBestPoker = bestPokerDealer.getRankingPlayers().get(0);
        	}
        }
        return playerWithBestPoker;
	}


    public static void main(String[] args) {
//    	initiate 5 player with 3 cards
        Dealer dealer = new Dealer();
        Player community = new Player();
        dealer.join(community);
        dealer.start();
        
        Player one = new Player();
        Player two = new Player();
        Player three = new Player();
        Player four = new Player();
        Player five = new Player();
        
        one.setPlayerName("one");
        two.setPlayerName("two");
        three.setPlayerName("three");
        four.setPlayerName("four");
        five.setPlayerName("five");
            
        dealer.join(one);
        dealer.join(two);
        dealer.join(three);
        dealer.join(four);
        dealer.join(five);
        dealer.start2();
        //rewrite start2() to ignore the community, only give 3 cards to the reset players.
        //and the index=0 in the player list is comminity card\
//        initiate 1 community player holds the 5 community cards
        // five player all have best poker hand
        Player bestPokerHandOne = bestPokerHand(one,community);
        Player bestPokerHandTwo = bestPokerHand(two,community);
        Player bestPokerHandThree = bestPokerHand(three,community);
        Player bestPokerHandFour = bestPokerHand(four,community);
        Player bestPokerHandFive = bestPokerHand(five,community);

        bestPokerHandOne.setPlayerName("one");
        bestPokerHandTwo.setPlayerName("two");
        bestPokerHandThree.setPlayerName("three");
        bestPokerHandFour.setPlayerName("four");
        bestPokerHandFive.setPlayerName("five");
        
        Dealer getWinnerDealer = new Dealer();
        getWinnerDealer.join(bestPokerHandOne);
        getWinnerDealer.join(bestPokerHandTwo);
        getWinnerDealer.join(bestPokerHandThree);
        getWinnerDealer.join(bestPokerHandFour);
        getWinnerDealer.join(bestPokerHandFive);
        
        getWinnerDealer.showHand();
        
//        the players' rank
        List<Player> players = getWinnerDealer.getRankingPlayers();
        System.out.println("CommunityCards:"+ community.getCards());
        
        for (int n = 0; n < players.size(); n++) {
            System.out.println(" Player: "+players.get(n).getPlayerName()+"'s "+" Cards:"+ players.get(n).getCards());
        }
        for(int m = 1; m < 6; m++) {
        	System.out.println("  Player: "+dealer.getPlayerList().get(m).getPlayerName()+"'s Origin Cards:"+ dealer.getPlayerList().get(m).getCards());
        }
        Player winner = getWinnerDealer.getRankingPlayers().get(0);
        
        System.out.println("WINNER IS: Player " + winner.getPlayerName() + "; Detail: " + winner);
        System.out.println("Restart:");
        System.out.println(restart(community,one));
        int winTimes = 0;
        for(int z = 0; z < 20; z++) {
        	if (restart(community,one)=="win") {
        		winTimes++;
        	}
        }
        
        System.out.println(winTimes*0.05);
/*	things to do
 * 	1. add a new feature to player class-----name
 * 	2. add a checkpoint to check if the winner's name is mine
 * 	3. then can calculate the winning chance..
 * 	4. other results calculation
 * 
 * */        
    }
    
    public static String restart(Player community, Player one) {
    	List<Card> playerOneCards = one.getCards();
        List<Card> communityCards = community.getCards();
        Dealer newGameDealer = new Dealer();
        Player twoNew = new Player();
        Player threeNew = new Player();
        Player fourNew = new Player();
        Player fiveNew = new Player();
        newGameDealer.join(community);
        newGameDealer.join(one);
        newGameDealer.join(twoNew);
        newGameDealer.join(threeNew);
        newGameDealer.join(fourNew);
        newGameDealer.join(fiveNew);
        newGameDealer.restartRest3(communityCards, playerOneCards);
        newGameDealer.showHand();
        
        List<Player> newPlayers = newGameDealer.getRankingPlayers();
        
//        for (int n = 0; n < newPlayers.size()-1; n++) {
//            System.out.println(newPlayers.get(n).getCards());
//        }
        
        Player bestPokerHandOne = bestPokerHand(one,community);
        Player bestPokerHandTwo = bestPokerHand(twoNew,community);
        Player bestPokerHandThree = bestPokerHand(threeNew,community);
        Player bestPokerHandFour = bestPokerHand(fourNew,community);
        Player bestPokerHandFive = bestPokerHand(fiveNew,community);

        bestPokerHandOne.setPlayerName("one");
        bestPokerHandTwo.setPlayerName("two");
        bestPokerHandThree.setPlayerName("three");
        bestPokerHandFour.setPlayerName("four");
        bestPokerHandFive.setPlayerName("five");
        
        Dealer getWinnerDealer = new Dealer();
        getWinnerDealer.join(bestPokerHandOne);
        getWinnerDealer.join(bestPokerHandTwo);
        getWinnerDealer.join(bestPokerHandThree);
        getWinnerDealer.join(bestPokerHandFour);
        getWinnerDealer.join(bestPokerHandFive);
        
        getWinnerDealer.showHand();
        Player winner = getWinnerDealer.getRankingPlayers().get(0);
//        System.out.println("CommunityCards:"+ community.getCards());
//        System.out.println("play one's cards:"+ one.getCards());
//        System.out.println("WINNER IS: Player " + winner.getPlayerName() + "; Detail: " + winner);
        
        return winner.getPlayerName() == "one" ? "win": "lose";
    }
    
    public static float oneRollOut(int times, int winResult) {
    	int winTimes = 0;
    	for(int i = 0; i < times; i++) {
    		if(winResult == 1) {
    			winTimes++;
    		}
    	}
    	return winTimes/times;
    }

}

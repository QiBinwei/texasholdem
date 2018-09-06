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
        		System.out.println(freshup.getCards() + "||");
        		freshup.addCard(communityCards.get(m));
        		freshup.addCard(communityCards.get(n));
        		System.out.println(communityCards.get(m) + "||");
        		System.out.println(communityCards.get(n) + "||");
        		System.out.println(freshup.getCards() + "||");
        		bestPokerDealer.join(freshup);
        		playerWithBestPoker = bestPokerDealer.getRankingPlayers().get(0);
        	}
        }
        return playerWithBestPoker;
	}


    public static void main(String[] args) {
//    	initiate 5 player with 3 cards
        Dealer dealer = new Dealer();
        Player one = new Player();
        Player two = new Player();
        Player three = new Player();
        Player four = new Player();
        Player five = new Player();
        dealer.join(one);
        dealer.join(two);
        dealer.join(three);
        dealer.join(four);
        dealer.join(five);
        dealer.start2();
//        initiate 1 community player holds the 5 community cards
        Player community = new Player();
        Dealer communityDeal = new Dealer();
        communityDeal.join(community);
        communityDeal.start();
        System.out.println(community.getCards() +"||"+one.getCards()+"||"+two.getCards());
        bestPokerHand(one,community);
        bestPokerHand(two,community);
        bestPokerHand(three,community);
        bestPokerHand(four,community);
        bestPokerHand(five,community);
        dealer.showHand();
//        the players' rank
        List<Player> players = dealer.getRankingPlayers();

        for (int n = 0; n < players.size(); n++) {
            System.out.println(players.get(n).getCards());
            
        }
        System.out.println(dealer.getRankingPlayers().get(0));
/*	things to do
 * 	1. add a new feature to player class-----name
 * 	2. add a checkpoint to check if the winner's name is mine
 * 	3. then can calculate the winning chance..
 * 	4. other results calculation
 * 
 * 
 * */        
    }

}

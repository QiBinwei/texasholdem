package com.alibaba.game.texasholdem;

import com.alibaba.game.texasholdem.ranking.RankingFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class {@code Dealer} 荷官, 负责发牌, 启动游戏.
 */
public class Dealer {

    private Poker poker;
    private List<Player> playerList;

    public Dealer() {
        this.poker = new Poker();
        this.playerList = new ArrayList<Player>();
    }

    public Dealer(Player top, Player... players) {
        this();
        this.playerList.add(top);
        this.playerList.addAll(Arrays.asList(players));
    }

    /**
     * 新增玩家
     *
     * @param player
     */
    public void join(Player player) {
        this.playerList.add(player);
    }

    /**
     * 获得玩家数量
     *
     * @return
     */
    public int getPlayerSize() {
        return this.playerList.size();
    }

    /**
     * 开始游戏, 负责被每个玩家发牌
     * 1.发三张牌
     * 2.发五张牌给community card组
     * 3.从community card组选2张组成最佳牌组
     * 如何组成最佳牌组：
     * 1）5选2 与手牌组成10组牌
     * 2）从10组牌里选择最大的
     * 如何选择最大的
     * 用temp进行循环比较
     */
    public void start() {
        for (int i = 0; i < this.playerList.size(); i++) {
            for (int j = 0; j < Constants.HAND_CARD_NUMERS; j++) {
                this.playerList.get(i).addCard(this.poker.dispatch());
            }
        }
    }
    public void start2() {
    	//i from 1 means the 0 in the playerlist is community card
        for (int i = 1; i < this.playerList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                this.playerList.get(i).addCard(this.poker.dispatch());
            }
        }
    }
    
    public void restartRest3(List<Card> communityCards, List<Card> playerOneCards) {
    	for(Card card:communityCards) {
    		this.poker.removeCard(card);
//    		I printed the reset poker set, the remove works
//    		System.out.println(this.poker);
    	}
    	for(Card card:playerOneCards) {
    		this.poker.removeCard(card);
    	}
    	for (int i = 2; i < this.playerList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                this.playerList.get(i).addCard(this.poker.dispatch());
            }
        }
    }
    /**
     * 计算每个玩家的牌型
     */
    public void showHand() {
        for (int i = 0; i < this.playerList.size(); i++) {
            RankingFacade.getInstance().resolve(this.playerList.get(i));
        }
    }

    public List<Player> getRankingPlayers() {
        Collections.sort(this.playerList);
        return this.playerList;
    }
    public List<Player> getPlayerList(){
    	return this.playerList;
    }
}

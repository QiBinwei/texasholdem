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
     * 1）获得牌组的类型，按照类型存储牌面
     * 2）设置checkpoint，按照皇家同花顺向下，一旦哪种存储的牌型size>1，进行比较，如果=1，返回
     */
    public void start() {
        for (int i = 0; i < this.playerList.size(); i++) {
            for (int j = 0; j < Constants.HAND_CARD_NUMERS; j++) {
                this.playerList.get(i).addCard(this.poker.dispatch());
            }
        }
    }
    public void start2() {
        for (int i = 0; i < this.playerList.size(); i++) {
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

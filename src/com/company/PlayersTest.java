package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class PlayersTest {
    private Players players;

    @Before
    public void init(){
        players = new Players();
    }

    @Test
    public void dropCards() {
        Cards cards1 = new Cards();
        Cards cards2 = new Cards();
        players.addCard(cards1);
        players.addCard(cards2);
        players.DropCards();
        Assert.assertEquals(0,players.yourcards.size());
    }

    @Test
    public void addCard() {
        Cards cards1 = new Cards();
        players.addCard(cards1);
        Assert.assertEquals(1,players.yourcards.size());
    }

    @Test
    public void setDoubleBet() {
        players.resetCash();
        players.setBet(50);
        players.setDoubleBet();
        Assert.assertEquals(100,players.getBet());
    }

    @Test
    public void isBusted() {
        players.setBusted(true);
        Assert.assertTrue(players.busted);
    }

    @Test
    public void setBusted() {
        players.setBusted(false);
        Assert.assertFalse(players.busted);
    }

    @Test
    public void resetCash() {
        players.resetCash();
        Assert.assertEquals(500,players.getCash());
    }

    @Test
    public void getCash() {
        players.resetCash();
        players.setCash(100);
        Assert.assertEquals(600,players.getCash());
    }

    @Test
    public void getBet() {
        players.setBet(500);
        Assert.assertEquals(500,players.getBet());
    }
}
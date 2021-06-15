package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game = new Game();

    @Test
    public void getRounds() {
        Players players = new Players();
        game.Hit(players);
        Assert.assertEquals(2,game.getRounds());
    }

    @Test
    public void resetRounds() {
        Players players = new Players();
        game.Hit(players);
        game.resetRounds();
        Assert.assertEquals(1,game.getRounds());
    }
}
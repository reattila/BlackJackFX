package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerAITest {
    private PlayerAI playerAI = new PlayerAI();

    @Test
    public void isFinish() {
        Assert.assertFalse(playerAI.isFinish());
    }

    @Test
    public void firstCard() {
        Cards cards1 = new Cards();
        playerAI.addCard(cards1);
        String scard = cards1.getCard();
        Assert.assertEquals(scard,playerAI.FirstCard());
    }
}
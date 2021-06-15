package com.company;

import java.util.Arrays;
import java.util.Random;

public class Cards {
    private int[] cardsnum = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
    private String[] cardscolor = new String[]{"clovers","diamonds","hearts","pikes"};
    private int[] card = new int[2];
    public Cards(){
        Random darw = new Random();
        card[0] = darw.nextInt(13);
        card[1] = darw.nextInt(4);
    }
    public String getCard(){
        return cardscolor[card[1]] + Integer.toString(cardsnum[card[0]]);
    }

    public int getCardValue(){ return cardsnum[card[0]]; }
}

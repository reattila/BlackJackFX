package com.company;

import javax.smartcardio.Card;
import java.io.Serializable;
import java.util.ArrayList;
import sample.Main.*;

public class Players implements Serializable {
    private String name;
    private int cash = 0;
    public ArrayList<Cards> yourcards;
    private int bet;
    protected boolean busted = false;

    public Players(){
        yourcards = new ArrayList<>();
    }

    public int SumCards(){
        int sumcards = 0;
        int a = 0;
        int temp = 0;
        for(Cards i : yourcards){
            temp = i.getCardValue();
            if(i.getCardValue() >= 10)
                temp = 10;
            if(i.getCardValue() == 1){
                a++;
                temp = 11;
            }
            sumcards += temp;
        }
        while (a > 0 && sumcards>21){
            sumcards -= 10;
            a--;
        }

        return sumcards;
    }

    public void DropCards(){ yourcards = new ArrayList<>(); }

    public void addCard(Cards c){ yourcards.add(c); }

    public void setBet(int ybet){
        bet = ybet;
        cash -= ybet;
    }

    public void setDoubleBet(){
        cash -= bet;
        bet *= 2;
    }

    public void getYourcards() {
        for(Cards i : yourcards){
            i.getCard();
        }
    }

    public boolean isBusted() { return busted; }

    public void setBusted(boolean busted) { this.busted = busted; }

    public void resetCash(){ cash = 500; }

    public int getCash() { return cash; }

    public void setCash(int c) { cash += c;}

    public int getBet() { return bet; }
}

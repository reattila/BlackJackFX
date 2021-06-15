package com.company;

import static sample.Main.game;
import static sample.Main.player1;

public class PlayerAI extends Players {
    private boolean finish = false;

    public void AITurn(){
        int draw = 0;
        while (this.SumCards() < 16 || player1.SumCards() > this.SumCards() && draw < 3){
            Cards newcard = new Cards();
            this.addCard(newcard);
            game.Busted(this);
            draw++;
            if(player1.SumCards() < this.SumCards() || this.isBusted()){
                break;
            }
        }
        finish = true;
    }

    public boolean isFinish() {
        return finish;
    }

    public int FirstCardValue(){
        int temp = yourcards.get(0).getCardValue();
        if(temp >= 10)
            temp = 10;
        if(temp == 1)
            temp = 11;
        return temp;
    }

    public String FirstCard(){
        return yourcards.get(0).getCard();
    }

}

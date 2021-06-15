package com.company;

import java.util.Scanner;

import static sample.Main.*;

public class Game {
    private boolean startGame = false;
    private boolean botround = false;
    private int rounds = 1;
    Scanner scanner = new Scanner(System.in);
    String command;

    public void Start(String com){
        if(com.equals("newgame"))
            startGame = true;
    }

    public boolean getStartGame(){ return startGame; }

    public void Hit(Players players){
        Cards c1 = new Cards();
        players.addCard(c1);
        rounds++;
    }


    /*public void NextRound(){
        rounds++;
        if(rounds == 1){
            player1.DropCards();
            //System.out.println("Your cash: " + player1.getCash());
            //System.out.println("Take your bet");
            //command = scanner.nextLine();
            player1.setBet(Integer.parseInt(command));
            Cards c1 = new Cards();
            Cards c2 = new Cards();
            player1.addCard(c1);
            player1.addCard(c2);
        }else{
            //System.out.println("Hit? or Stay?");
            //command = scanner.nextLine();
            if(command.equals("hit")){
                Cards c1 = new Cards();
                player1.addCard(c1);
            }else if(command.equals("stay")){
                rounds = 0;
                botbank.AITurn();
            }
        }
    }*/

    public void Busted(Players player){
        if(player.SumCards() > 21){
            player.setBusted(true);
            rounds = 1;
            startGame = false;
        }

    }

    public int HowWin(){
        if(botbank.SumCards() > player1.SumCards() && !botbank.isBusted()) {
            return -1; //bank win
        }
        if (botbank.SumCards() == player1.SumCards()) {
            player1.setCash(player1.getBet());
            return 0; //player win
        }
        if (botbank.SumCards() < player1.SumCards() || botbank.isBusted()) {
            player1.setCash(player1.getBet() * 2);
            return 1; //player win
        }
        return 0;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public int getRounds() { return rounds; }

    public void resetRounds(){ rounds = 1; }

}

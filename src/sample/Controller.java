package sample;

import com.company.Cards;
import com.company.Players;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.*;

public class Controller{
    private Message m = new Message();
    private boolean isScene2 = false;
    public HBox startlay;
    public HBox contlay;
    public Label cash;
    public Label botnumber;
    public Label playnumber;
    public TextField betnum;
    public Button bet;
    public Button stay;
    public Button hit;
    public Button doublehit;
    public Button gamebutton;
    public ImageView botcard1;
    public ImageView botcard2;
    public ImageView botcard3;
    public ImageView botcard4;
    public ImageView botcard5;
    public ImageView playcard1;
    public ImageView playcard2;
    public ImageView playcard3;
    public ImageView playcard4;
    public ImageView playcard5;

    public void TakeBet(){
        if(player1.getCash() == 0){
            NewGameB();
        }

        game.resetRounds();
        player1.setBusted(false);
        botbank.setBusted(false);
        botcard3.setImage(null);
        botcard4.setImage(null);
        botcard5.setImage(null);
        playcard3.setImage(null);
        playcard4.setImage(null);
        playcard5.setImage(null);

        int bettemp = Integer.parseInt(betnum.getText());
        if(bettemp < 0){
            bettemp *= -1;
        }

        if(bettemp > player1.getCash()){
            player1.setBet(player1.getCash());
        }else{
            player1.setBet(bettemp);
        }

        cash.setText(Integer.toString(player1.getCash()) + "$");
        bet.setDisable(true);
        betnum.setDisable(true);

        Cards botc1 = new Cards();
        Cards botc2 = new Cards();
        botbank.addCard(botc1);
        botbank.addCard(botc2);
        Cards c1 = new Cards();
        Cards c2 = new Cards();
        player1.addCard(c1);
        player1.addCard(c2);

        String url = "\\sample\\cards\\" + botbank.FirstCard()+".png";
        Image image = new Image(url);
        botcard1.setImage(image);
        url = "\\sample\\cards\\cardback.png";
        Image image2 = new Image(url);
        botcard2.setImage(image2);
        url = "\\sample\\cards\\" + player1.yourcards.get(0).getCard()+".png";
        Image image3 = new Image(url);
        playcard1.setImage(image3);
        url = "\\sample\\cards\\" + player1.yourcards.get(1).getCard()+".png";
        Image image4 = new Image(url);
        playcard2.setImage(image4);

        botnumber.setText(Integer.toString(botbank.FirstCardValue()));

        playnumber.setText(Integer.toString(player1.SumCards()));

        hit.setDisable(false);
        stay.setDisable(false);
        doublehit.setDisable(false);

        if(player1.SumCards() == 21)
            BlackJack();
    }
    public void Hit(){
        game.Hit(player1);
        playnumber.setText(Integer.toString(player1.SumCards()));
        String url = "\\sample\\cards\\" + player1.yourcards.get(game.getRounds()).getCard()+".png";
        Image image = new Image(url);
        if(game.getRounds() == 2)
            playcard3.setImage(image);
        if(game.getRounds() == 3)
            playcard4.setImage(image);
        if(game.getRounds() == 4)
            playcard5.setImage(image);
        game.Busted(player1);
        if(player1.isBusted()){
            m.Mess("You Lose!");
            bet.setDisable(false);
            betnum.setDisable(false);
            hit.setDisable(true);
            stay.setDisable(true);
            player1.DropCards();
            botbank.DropCards();
        }
    }

    public void DoubleHit(){
        if(player1.getCash() < player1.getBet()){
            m.Mess("You not enough cash!");
        }else {
            player1.setDoubleBet();
            Hit();
            if(!player1.isBusted()){
                Stay();
            }
        }
    }

    public void Stay(){
        botbank.AITurn();
        botnumber.setText(Integer.toString(botbank.SumCards()));
        for (int i = 1; i < botbank.yourcards.size(); i++) {
            String url = "\\sample\\cards\\" + botbank.yourcards.get(i).getCard()+".png";
            Image image = new Image(url);
            if(i == 1)
                botcard2.setImage(image);
            if(i == 2)
                botcard3.setImage(image);
            if(i == 3)
                botcard4.setImage(image);
            if(i == 4)
                botcard5.setImage(image);
        }
        bet.setDisable(false);
        betnum.setDisable(false);
        hit.setDisable(true);
        stay.setDisable(true);
        doublehit.setDisable(true);
        game.Busted(botbank);

        int winner = game.HowWin();
        if(0< winner){
            m.Mess("You Win!"); // Player win
        }
        if(0==winner){
            m.Mess("Tie!");//Tie
        }
        if(0>winner){
            m.Mess("You Lose!");//bank win
        }

        cash.setText(Integer.toString(player1.getCash()) + "$");
        player1.DropCards();
        botbank.DropCards();
    }

    public void BlackJack(){
        m.Mess("You Win!");
        player1.setCash(player1.getBet()*2+player1.getBet()/2);
        cash.setText(Integer.toString(player1.getCash()) + "$");
        player1.DropCards();
        botbank.DropCards();
        bet.setDisable(false);
        betnum.setDisable(false);
        hit.setDisable(true);
        stay.setDisable(true);
        doublehit.setDisable(true);
    }


    public void NewGameB(){
        Stage window = new Stage();
        window.setTitle("New Game");
        window.setMinHeight(100);
        window.setMinWidth(150);
        Label text = new Label();
        text.setText("Start a new game?");
        Button button = new Button("Yes");
        Button button2 = new Button("No");
        button.setOnAction(e -> {
            player1.resetCash();
            cash.setText(Integer.toString(player1.getCash()) + "$");
            window.close();
        });
        button2.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(button,button2);
        layout2.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(text,layout2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void LoadB(){
        try {
            File data = new File("savedata.ser");
            data.createNewFile();
            FileInputStream fileIn = new FileInputStream(data);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player1 = (Players) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Player class not found");
            c.printStackTrace();
            return;
        }
        m.Mess("Data has been loaded");
        if(isScene2)
            cash.setText(Integer.toString(player1.getCash()) + "$");
    }

    public void SaveB(){
        try {
            File data = new File("savedata.ser");
            data.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(data,false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player1);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        m.Mess("Save successful!");
    }

    public void Exit(){
        Stage stage = null;
        if(isScene2){
            stage = (Stage) cash.getScene().getWindow();
        }else{
            stage = (Stage) gamebutton.getScene().getWindow();
        }

        Stage window = new Stage();
        window.setTitle("New Game");
        window.setMinHeight(100);
        window.setMinWidth(150);
        Label text = new Label();
        text.setText("Exit?");
        Button button = new Button("Yes");
        Button button2 = new Button("No");
        Stage finalStage = stage;
        button.setOnAction(e -> {
            window.close();
            finalStage.close();
        });
        button2.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(button,button2);
        layout2.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(text,layout2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public void NewGame() throws Exception{
        Parent board = FXMLLoader.load(getClass().getResource("gameboard.fxml"));
        Scene scene2 = new Scene(board, 800, 600);
        Stage stage = (Stage) gamebutton.getScene().getWindow();
        stage.setScene(scene2);
        stage.show();
        player1.resetCash();
    }

    public void Load() throws Exception{
        Parent board = FXMLLoader.load(getClass().getResource("gameboard.fxml"));
        Scene scene2 = new Scene(board, 800, 600);
        Stage stage = (Stage) gamebutton.getScene().getWindow();
        stage.setScene(scene2);
        stage.show();
        LoadB();
    }

    public void GetStart(){
        contlay.setVisible(true);
        startlay.setVisible(false);
        cash.setText(Integer.toString(player1.getCash()) + "$");
        betnum.setText("50");
        hit.setDisable(true);
        stay.setDisable(true);
        doublehit.setDisable(true);
        isScene2 = true;
    }
}

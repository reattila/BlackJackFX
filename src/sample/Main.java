package sample;

import com.company.Cards;
import com.company.Game;
import com.company.PlayerAI;
import com.company.Players;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    static public Game game = new Game();
    static public Players player1 = new Players();
    static public PlayerAI botbank = new PlayerAI();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("newgame.fxml"));
        Scene scene1 = new Scene(root, 800, 600);
        primaryStage.setTitle("Black Jack");
        primaryStage.setScene(scene1);
        primaryStage.show();

    }
}

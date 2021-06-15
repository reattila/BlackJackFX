package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Message {

    public void Mess(String t){
        Stage window = new Stage();
        window.setTitle("How Win?");
        window.setMinHeight(200);
        window.setMinWidth(200);
        Label text = new Label();
        text.setText(t);
        Button button = new Button("OK");
        button.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

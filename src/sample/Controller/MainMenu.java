package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    /*This is the main menu which allows the user to navigate to the different areas of the program.
    They are able to go make a sandwich or bowl order for ice cream. They are also able to review the reports of
    exit the program depending on the buttons the user chooses to click.
     */

    public void bowlOrder(ActionEvent event) throws IOException {
        Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/bowl.fxml"));
        Scene screen_1_scene = new Scene(screen_1,600, 550);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(screen_1_scene);
        window.show();
    }

    public void sandwichOrder(ActionEvent event) throws IOException {
        Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/sandwich.fxml"));
        Scene screen_1_scene = new Scene(screen_1,600, 550);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(screen_1_scene);
        window.show();
    }

    public void reportsBt(ActionEvent event) throws IOException {
        Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/reports.fxml"));
        Scene screen_1_scene = new Scene(screen_1,650, 400);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(screen_1_scene);
        window.show();
    }

    public void exitBt(ActionEvent event) {System.exit(0);}
}

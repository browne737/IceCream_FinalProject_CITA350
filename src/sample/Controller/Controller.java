package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public TextField usernameTxt;
    public TextField passwordTxt;
    public Label errorMessage;
    String username, password, user, pass;

    /*Logs the user in by checking their password and username it wont
    continue until they put in the correct information!
     */
    public void loginBt(ActionEvent event) throws IOException {
        setVariables();
        errorMessage.setText("");
        if(user.equals(username) && pass.equals(password))
        {
            Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/mainMenu.fxml"));
            Scene screen_1_scene = new Scene(screen_1,600, 420);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(screen_1_scene);
            window.show();
        }
        else
        {
            errorMessage.setText("Username/Password incorrect");
            clearFD();
        }
    }

    public void clearFD()
    {
        usernameTxt.setText("");
        passwordTxt.setText("");
    }
    public void setVariables()
    {
        user = usernameTxt.getText();
        pass = passwordTxt.getText();
        this.username = "";
        this.password = "";

    }

    public void exitBt(ActionEvent event) {
            System.exit(0);
    }
}

package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseConnection;
import sample.SandwichOrder;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class sandwichController implements Initializable {
    public RadioButton vanilla;
    public RadioButton chocolate;
    public RadioButton strawberry;
    public TextField nameFd;
    public TextField qtyFd;
    public RadioButton cookies;
    public RadioButton wafers;
    public RadioButton waffles;
    public ComboBox discount;
    public Label errorMessage;
    String flavor, name, str, bread;
    double tax, disc, disc2;
    int qty;
    private Connection conn = null;
/*This code allows for a person to place a sandwich order. It takes all the given information and sends
it to the sandwich order class. Once it sends the information the user will receive a pop-up information card
that allows thw user to view their receipt. After they see the receipt their order gets saved to the database by
using the insert. ****NOTE: The required fields are the flavor, the name, the bread, and the qty, if they are not filled
properly the data will not be saved!
 */
    ObservableList<String> options = FXCollections.observableArrayList( "0%","5%", "7%","10%","15%");

    public void initialize(URL url, ResourceBundle rb) {
        conn = DatabaseConnection.createConnection();
        discount.setValue("0%");
        discount.setItems(options);
    }

    public void calculateBt(ActionEvent event) {
     try{
            errorMessage.setText("");
            str = discount.getSelectionModel().getSelectedItem().toString();

            disc2 = 0;

            switch(str){
                case "0%":
                    disc2 = 0;
                    break;
                case "5%":
                    disc2 = 0.05;
                    break;
                case "7%":
                    disc2 = 0.07;
                    break;
                case "10%":
                    disc2 = 0.10;
                    break;
                case "15%":
                    disc2 = 0.15;
                    break;
            }


            name = nameFd.getText();
            //CONSTANT!!!!!
            tax = .08;
            disc = disc2;
            qty = Integer.parseInt(qtyFd.getText());

            if(chocolate.isSelected())
                flavor = "c";
            if(strawberry.isSelected())
                flavor = "s";
            if(vanilla.isSelected())
                flavor = "v";
             if(cookies.isSelected())
                 bread = "cookies";
            if(wafers.isSelected())
                bread = "wafers";
            if(waffles.isSelected())
                bread = "waffles";

         //VALIDATION!!!!
         if (name.equals("") || qty <= 0 || (!chocolate.isSelected() && !strawberry.isSelected() && !vanilla.isSelected())
         || (!cookies.isSelected() && !waffles.isSelected() && !wafers.isSelected()))
             errorMessage.setText("You need to enter the correct data! Check for your name, qty, or flavor, or bread!");
         else
             validationChecks(name, tax, disc, qty, flavor, bread);

     } catch (Exception ex) {
            errorMessage.setText("You need to enter a number for qty!");
     }
    }
    public void validationChecks(String name, double tax, double disc, int qty, String flavor, String bread)
    {
        try {
            SandwichOrder sandwich = new SandwichOrder(name, tax, disc, qty, flavor);
            if(cookies.isSelected())
                sandwich.setBreadType(bread);
            if(wafers.isSelected())
                sandwich.setBreadType(bread);
            if(waffles.isSelected())
                sandwich.setBreadType(bread);

            sandwich.calculateTotal();
            //System.out.print(sandwich);
            infoBox(sandwich.toString(), "Ice Cream Receipt");


            String fullName = nameFd.getText();
            double flavor2 = sandwich.getFlavorPrice();
            double bread2 = sandwich.getBreadPrice();
            int qty2 = sandwich.getQuantity();
            double discount = sandwich.getDiscount();
            double tax2 = sandwich.getTaxRate();
            double subtotal = sandwich.getSub();
            double total = sandwich.getTotal();


            String Insert_QUERY = "INSERT INTO sandwichorders (cus_name,breadtype,"
                    + "flavor,qty,tax,discount,subtotal,total) VALUES ('" + fullName + "'," + bread2 + ","
                    + flavor2 + "," + qty2 + "," + tax2 + "," + discount + "," + subtotal + "," + total + ")";
            System.out.println(Insert_QUERY);

            Statement statement = conn.createStatement();
            statement.executeUpdate(Insert_QUERY);
        }
        catch (SQLException sqlException) {sqlException.printStackTrace();}
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
//This will exit/quit the program
    public void exitBt(ActionEvent event) {System.exit(0);}
//This will take you back to the main menu
    public void newOrderBt(ActionEvent event) throws IOException {
        Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        Scene screen_1_scene = new Scene(screen_1,600, 420);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(screen_1_scene);
        window.show();
    }
//This clears all the fields
    public void clearBt(ActionEvent event) {
        nameFd.setText("");
        qtyFd.setText("");
        vanilla.setSelected(false);
        strawberry.setSelected(false);
        chocolate.setSelected(false);
        cookies.setSelected(false);
        wafers.setSelected(false);
        waffles.setSelected(false);
        discount.setValue("O%");
        errorMessage.setText("");
    }
}

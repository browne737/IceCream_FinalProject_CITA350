package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseConnection;
import sample.IceCreamOrder;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class bowlController implements Initializable {
    public ComboBox discount;
    public CheckBox peanuts;
    public CheckBox sprinkles;
    public CheckBox fudge;
    public RadioButton strawberry;
    public RadioButton chocolate;
    public RadioButton vanilla;
    public TextField nameFd;
    public TextField qtyFd;
    public Label errorMessage;
    String flavor, name, str;
    double tax, disc, disc2;
    int qty;
    CheckBox[] toppings = new CheckBox[3];
    String[] toppingsname = new String[3];
    private Connection conn = null;
    //Create a combo box
    ObservableList<String> options = FXCollections.observableArrayList( "0%","5%", "7%","10%","15%");

    /*This code allows for a person to place a ice cream bowl order. It takes all the given information and sends
    it to the ice cream order class. Once it sends the information the user will receive a pop-up information card
    that allows the user to view their receipt. After they see the receipt their order gets saved to the database by
    using the insert. ****NOTE: The required fields are the flavor, the name, and the qty, if they are not filled
    properly the data will not be saved!
     */

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
            //constant value!!!!!!!
            tax = .08;
            disc = disc2;
            qty = Integer.parseInt(qtyFd.getText());
            if(chocolate.isSelected())
                flavor = "c";
            if(strawberry.isSelected())
                flavor = "s";
            if(vanilla.isSelected())
                flavor = "v";
            //VALIDATION!!!!
            if (name.equals("") || qty <= 0 || (!chocolate.isSelected() && !strawberry.isSelected() && !vanilla.isSelected()))
                    errorMessage.setText("You need to enter the correct data! Check for your name, qty, or flavor!");
            else
                    validationChecks(name, tax, disc, qty, flavor);

    }
    catch (Exception ex) {;
        errorMessage.setText("You need to enter a number only for quantity!");}
    }
    //ONLY SAVES THE INFORMATION IF YOU HAVE ALL THE REQUIRED FIELDS!!!
    public void validationChecks(String name,double tax, double disc,int qty, String flavor)
    {
        try {
            IceCreamOrder bowl = new IceCreamOrder(name, tax, disc, qty, flavor);


            toppings[0] = peanuts;
            toppings[1] = sprinkles;
            toppings[2] = fudge;

            toppingsname[0] = "peanuts";
            toppingsname[1] = "sprinkles";
            toppingsname[2] = "fudge";

            for (int i = 0; i <= (toppings.length - 1); i++) {
                if (toppings[i].isSelected()) {
                    if (toppings[i].getText().equals(toppingsname[i])) {
                        String name2 = toppingsname[i];
                        if (name2.equals(peanuts)) {
                            bowl.peanuts = true;
                        }
                        if (name2.equals(sprinkles)) {
                            bowl.sprinkles = true;
                        }
                        if (name2.equals(fudge)) {
                            bowl.fudge = true;
                        }

                    }
                }
            }
            bowl.calculateTotal();
            //System.out.print(bowl);

            infoBox(bowl.toString(), "Ice Cream Receipt");
            //addToDatabase(bowl);


            String fullName = nameFd.getText();
            double flavor2 = bowl.getFlavorPrice();
            double topping = bowl.getToppingPrice();
            int qty2 = bowl.getQuantity();
            double discount = bowl.getDiscount();
            double tax2 = bowl.getTaxRate();
            double subtotal = bowl.getSub();
            double total = bowl.getTotal();


            String Insert_QUERY = "INSERT INTO bowlorders (cus_name,flavor,"
                    + "topping,qty,discount,tax,subtotal,total) VALUES ('" + fullName + "'," + flavor2 + ","
                    + topping + "," + qty2 + "," + discount + "," + tax2 + "," + subtotal + "," + total + ")";
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
//This exits the program
    public void exitBt(ActionEvent event) {System.exit(0);}
//Goes back to the main menu
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
        peanuts.setSelected(false);
        fudge.setSelected(false);
        sprinkles.setSelected(false);
        discount.setValue("O%");
        errorMessage.setText("");
    }
}

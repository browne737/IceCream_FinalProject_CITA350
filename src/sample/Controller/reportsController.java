package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class reportsController implements Initializable {
    public Label label;
    public TableView table;
    private Connection conn = null;
/*The purpose of these two buttons is to create the table views by using the data
from the database. It will clear the data from the table upon clicking and then re-upload the data from the
table you are trying to view. It uses a for loop for accessing the columns and a while loop for accessing the rows.
 */
    public void initialize(URL url, ResourceBundle rb) {

        conn = DatabaseConnection.createConnection();
    }

    public void icecreamReports(ActionEvent event) throws SQLException {
        label.setText("Bowl Orders:");
        table.getColumns().clear();
        ObservableList data = FXCollections.observableArrayList();

        String SQL = "SELECT * from bowlorders";

        ResultSet rs = conn.createStatement().executeQuery(SQL);

        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            table.getColumns().addAll(col);
        }
        while(rs.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rs.getString(i));
            }
            data.add(row);

        }
        table.setItems(data);
    }

    public void sandwichReports(ActionEvent event) throws SQLException {
        label.setText("Sandwich Orders:");
        table.getColumns().clear();
        ObservableList data = FXCollections.observableArrayList();

        String SQL = "SELECT * from sandwichorders";

        ResultSet rs = conn.createStatement().executeQuery(SQL);

        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            table.getColumns().addAll(col);
        }
        while(rs.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rs.getString(i));
            }
            data.add(row);

        }
        table.setItems(data);
    }
    //Goes back to the main menu
    public void back(ActionEvent event) throws IOException {
        Parent screen_1 = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));
        Scene screen_1_scene = new Scene(screen_1,600, 420);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(screen_1_scene);
        window.show();
    }
}

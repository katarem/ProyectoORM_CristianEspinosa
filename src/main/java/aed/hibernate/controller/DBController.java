package aed.hibernate.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class DBController implements Initializable {

    @FXML
    private BorderPane panel;

    public DBController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/DBView.fxml"));
            l.setController(this);
            l.load();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        



    }

    public BorderPane getView(){
        return panel;
    }
    
}


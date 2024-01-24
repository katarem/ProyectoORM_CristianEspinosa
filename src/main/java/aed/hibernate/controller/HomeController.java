package aed.hibernate.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class HomeController implements Initializable {

    @FXML
    private Pane panel;

    public HomeController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
            l.setController(this);
            l.load();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        



    }

    public Pane getView(){
        return panel;
    }
    
}

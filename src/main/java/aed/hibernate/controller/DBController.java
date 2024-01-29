package aed.hibernate.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class DBController implements Initializable {

    @FXML
    private TabPane view;
    
    @FXML
    private Tab cinesTab, tarifasTab, pasesTab, peliculasTab, protagonistasTab; 
    
    private CineController cineController;
    private TarifaController tarifaController;
    private PaseController paseController;
    private PeliculaController peliculaController;
    private ProtagonistaController protagonistaController;

    public DBController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/DBView.fxml"));
            l.setController(this);
            l.load();
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    	cineController = new CineController();
    	cinesTab.setContent(cineController.getView());

    	tarifaController = new TarifaController();
    	tarifasTab.setContent(tarifaController.getView());
    	
    	paseController = new PaseController();
    	pasesTab.setContent(paseController.getView());

    	peliculaController = new PeliculaController();
    	peliculasTab.setContent(peliculaController.getView());
    	
    	protagonistaController = new ProtagonistaController();
    	protagonistasTab.setContent(protagonistaController.getView());
    }

    public TabPane getView(){
        return view;
    }
    
}


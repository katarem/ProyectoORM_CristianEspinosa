package aed.hibernate.controller;

import java.net.URL;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.model.Cine;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import uicomponents.PeliculasComponent;

public class HomeController implements Initializable {

    @FXML
    private BorderPane panel;
    @FXML
    private ChoiceBox<String> cineChoice, tarifaChoice;
    
    @FXML
    private ScrollPane peliculasContainer;
    
    @FXML
    private Button dbButton;
    
    private Cine selectedCine;

    public HomeController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
            l.setController(this);
            l.load();
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    	var cines = App.cineRepository.getAll()
    					.stream().map(c -> c.getNombre())
    					.toList();
    	selectedCine = App.cineRepository.getAll().get(0);
    	cineChoice.setItems(FXCollections.observableArrayList(cines));
    	cineChoice.setValue(cines.get(0));
    	setTarifas();
    	cineChoice.setOnAction(e -> {
    		setCine(cineChoice.getValue());
    		setTarifas();
    	});
    	
    	peliculasContainer.setContent(PeliculasComponent.drawPelis(App.peliculaRepository.getAll()));
    	
    }
    
    public void setCine(String cine) {
    	selectedCine = App.cineRepository.getAll()
    					.stream().filter(c -> c.getNombre().equals(cine))
    					.findFirst().get();
    }
    
    public void setTarifas() {
    	var tarifas = App.tarifaRepository.getAll()
				.stream()
				.filter(t -> t.getCine().getId() == selectedCine.getId())
				.map(t -> t.getDia()).toList();
		tarifaChoice.setItems(FXCollections.observableArrayList(tarifas));
		tarifaChoice.setValue("Elige tarifa");
    }
    
    @FXML
    private void goDB() {
    	App.stage.setScene(new Scene(new DBController().getView()));
    }
    
    public BorderPane getView(){
        return panel;
    }
    
}

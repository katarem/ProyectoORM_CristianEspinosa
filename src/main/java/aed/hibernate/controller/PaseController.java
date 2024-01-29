package aed.hibernate.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.db.CineRepository;
import aed.hibernate.db.PaseRepository;
import aed.hibernate.db.TarifaRepository;
import aed.hibernate.model.Cine;
import aed.hibernate.model.Pase;
import aed.hibernate.model.Pelicula;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class PaseController implements Initializable {

	@FXML
	private BorderPane view;
	
	@FXML
	private TextField idField, fechaField, peliIdField, cineIdField;
	
	@FXML
	private TableView<Pase> tablaView;
	
	private PaseRepository repository = App.paseRepository;
	
	private List<Pase> datos;

	public PaseController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/PaseView.fxml"));
			l.setController(this);
			l.load();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		datos = repository.getAll();
		showAll();
		
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
    private void select(){
        if(!idField.getText().isBlank()) showById();
        else if(!fechaField.getText().isBlank()) showByFecha();
        else showAll();
    }

    @FXML
    private void insert(){
        if(!fechaField.getText().isBlank() && 
           !peliIdField.getText().isBlank() &&
           !cineIdField.getText().isBlank()){
            var fecha = fechaField.getText();
            var peliId = Integer.parseInt(peliIdField.getText());
            var cineId = Integer.parseInt(cineIdField.getText());
            //primero cargamos las entidades padre
            var cine = App.cineRepository.getById(cineId);
            var pelicula = App.peliculaRepository.getById(peliId);
            
            
            repository.insert(new Pase(cine,pelicula,fecha));
            showAll();
        }
    }

    @FXML
    private void delete(){
        if(!idField.getText().isBlank()){
            var id = Integer.parseInt(idField.getText());
            repository.remove(id);
            showAll();
        }
    }

    @FXML
    private void update(){
        if(!idField.getText().isBlank() 
        		&& !fechaField.getText().isBlank() 
        		&& !peliIdField.getText().isBlank()
        		&& !cineIdField.getText().isBlank()){
            var id = Integer.parseInt(idField.getText());
            var fecha = fechaField.getText();
            var peliId = Integer.parseInt(peliIdField.getText());
            var cineId = Integer.parseInt(cineIdField.getText());
            //debo cargar las entidades
            var pelicula = App.peliculaRepository.getById(peliId);
            var cine = App.cineRepository.getById(cineId);
            
            repository.update(id, new Pase(cine,pelicula,fecha));
            showAll();
            clear();
        }
    }


    private void clear(){
        idField.clear();
        fechaField.clear();
        peliIdField.clear();
        cineIdField.clear();
    }


    private void showAll(){
        datos = repository.getAll();
        showTarifas(datos);
        clear();
    }

    private void showById(){
        var id = Integer.parseInt(idField.getText());
        datos = List.of(repository.getById(id));
        showTarifas(datos);
        clear();
    }
    
    private void showByFecha(){
        var fecha = fechaField.getText();
        datos = datos.stream().filter(t -> t.getFecha().equals(fecha)).toList();
        showTarifas(datos);
        clear();
    }
    
    private void fillForm(Pase t) {
    	idField.setText(String.valueOf(t.getId()));
    	fechaField.setText(t.getFecha());
    	peliIdField.setText(String.valueOf(t.getPelicula().getId()));
    	cineIdField.setText(String.valueOf(t.getCine().getId()));
    }
    
    private void showTarifas(List<Pase> datos){
        ObservableList<Pase> pases = FXCollections.observableList(datos);
        TableColumn<Pase, Integer> columnaId = new TableColumn<>("ID");
        TableColumn<Pase, String> columnaFecha = new TableColumn<>("Fecha");
        TableColumn<Pase, Integer> columnaPelicula = new TableColumn<>("Pelicula");
        TableColumn<Pase, Integer> columnaCineId = new TableColumn<>("Cine");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaPelicula.setCellValueFactory(cellData ->
        	new SimpleIntegerProperty(cellData.getValue().getPelicula().getId()).asObject()
        );
        columnaCineId.setCellValueFactory(cellData -> 
        	new SimpleIntegerProperty(cellData.getValue().getCine().getId()).asObject()
        );
        tablaView.getColumns().clear();
        tablaView.getColumns().addAll(columnaId,columnaFecha,columnaPelicula,columnaCineId);
        tablaView.setItems(pases);
        
        tablaView.setOnMouseClicked(e -> {
        	var c = tablaView.getSelectionModel().getSelectedItem();
        	if(c != null) fillForm(c);
        });
    }
}

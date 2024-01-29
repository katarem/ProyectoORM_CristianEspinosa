package aed.hibernate.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.db.CineRepository;
import aed.hibernate.db.PeliculaRepository;
import aed.hibernate.db.TarifaRepository;
import aed.hibernate.model.Cine;
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

public class PeliculaController implements Initializable {

	@FXML
	private BorderPane view;
	
	@FXML
	private TextField idField, tituloField, directorField, edadField, generoField, caratulaField;
	
	@FXML
	private TableView<Pelicula> tablaView;
	
	private PeliculaRepository repository = App.peliculaRepository;
	
	private List<Pelicula> datos;

	public PeliculaController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/PeliculaView.fxml"));
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
        else if(!directorField.getText().isBlank()) showByDirector();
        else showAll();
    }

    @FXML
    private void insert(){
        if(!tituloField.getText().isBlank() && 
           !directorField.getText().isBlank() &&
           !edadField.getText().isBlank() &&
           !generoField.getText().isBlank() &&
           !caratulaField.getText().isBlank()){
        	var titulo = tituloField.getText();
            var director = directorField.getText();
            var categoria = edadField.getText();
            var genero = generoField.getText();
            var caratula = caratulaField.getText();
            repository.insert(new Pelicula(titulo,director,categoria,genero,caratula));
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
        		&& !tituloField.getText().isBlank() 
        		&& !directorField.getText().isBlank()
        		&& !edadField.getText().isBlank()
        		&& !generoField.getText().isBlank()
        		&& !caratulaField.getText().isBlank()){
            var id = Integer.parseInt(idField.getText());
            var titulo = tituloField.getText();
            var director = directorField.getText();
            var categoria = edadField.getText();
            var genero = generoField.getText();
            var caratula = caratulaField.getText();
            repository.update(id, new Pelicula(titulo,director,categoria,genero,caratula));
            showAll();
            clear();
        }
    }


    private void clear(){
        idField.clear();
        tituloField.clear();
        directorField.clear();
        edadField.clear();
        generoField.clear();
    }


    private void showAll(){
        datos = repository.getAll();
        showPeliculas(datos);
        clear();
    }

    private void showById(){
        var id = Integer.parseInt(idField.getText());
        datos = List.of(repository.getById(id));
        showPeliculas(datos);
        clear();
    }
    
    private void showByDirector(){
    	var director = directorField.getText();
        datos = datos.stream().filter(t -> t.getDirector().equals(director)).toList();
        showPeliculas(datos);
        clear();
    }
    
    private void fillForm(Pelicula t) {
    	idField.setText(String.valueOf(t.getId()));
    	tituloField.setText(t.getTitulo());
    	directorField.setText(t.getDirector());
    	edadField.setText(t.getClasificacion());
    	generoField.setText(t.getGenero());
    	caratulaField.setText(t.getCaratula());
    }
    
    private void showPeliculas(List<Pelicula> datos){
        ObservableList<Pelicula> peliculas = FXCollections.observableList(datos);
        TableColumn<Pelicula, Integer> columnaId = new TableColumn<>("ID");
        TableColumn<Pelicula, String> columnaTitulo = new TableColumn<>("Titulo");
        TableColumn<Pelicula, String> columnaDirector = new TableColumn<>("Director");
        TableColumn<Pelicula, String> columnaEdad = new TableColumn<>("Clasificacion");
        TableColumn<Pelicula, String> columnaGenero = new TableColumn<>("Genero");
        TableColumn<Pelicula, String> columnaCaratula = new TableColumn<>("Caratula");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("clasificacion"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columnaCaratula.setCellValueFactory(new PropertyValueFactory<>("caratula"));
        tablaView.getColumns().clear();
        tablaView.getColumns().addAll(columnaId,columnaTitulo,columnaDirector,columnaEdad,columnaGenero,columnaCaratula);
        tablaView.setItems(peliculas);
        
        tablaView.setOnMouseClicked(e -> {
        	var c = tablaView.getSelectionModel().getSelectedItem();
        	if(c != null) fillForm(c);
        });
    }
}

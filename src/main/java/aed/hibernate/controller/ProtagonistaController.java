package aed.hibernate.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.db.CineRepository;
import aed.hibernate.db.ProtagonistaRepository;
import aed.hibernate.model.Cine;
import aed.hibernate.model.Pelicula;
import aed.hibernate.model.Protagonista;
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

public class ProtagonistaController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private TextField idField, nombreField, peliculaIdField, fotoField;

	@FXML
	private TableView<Protagonista> tablaView;

	private ProtagonistaRepository repository = App.protagonistaRepository;

	private List<Protagonista> datos;

	public ProtagonistaController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/ProtagonistaView.fxml"));
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
	private void select() {
		if (!idField.getText().isBlank())
			showById();
		else if (!nombreField.getText().isBlank())
			showByNombre();
		else
			showAll();
	}

	@FXML
	private void insert() {
		if (!nombreField.getText().isBlank() && !peliculaIdField.getText().isBlank()
				&& !fotoField.getText().isBlank()) {
			var nombre = nombreField.getText();
			var peliculaId = Integer.parseInt(peliculaIdField.getText());
			var foto = fotoField.getText();
			var p = new Protagonista(nombre, foto);
			var pelicula = App.peliculaRepository.getById(peliculaId);
			p.setPelicula(pelicula);
			repository.insert(p);
			showAll();
		}
	}

	@FXML
	private void delete() {
		if (!idField.getText().isBlank()) {
			var id = Integer.parseInt(idField.getText());
			repository.remove(id);
			showAll();
		}
	}

	@FXML
	private void update() {
		if (!idField.getText().isBlank() && !nombreField.getText().isBlank() && !fotoField.getText().isBlank()
				&& !peliculaIdField.getText().isBlank()) {
			var id = Integer.parseInt(idField.getText());
			var nombre = nombreField.getText();
			var peliculaId = Integer.parseInt(peliculaIdField.getText());
			var foto = fotoField.getText();
			var p = new Protagonista(nombre, foto);
			var pelicula = App.peliculaRepository.getById(peliculaId);
			p.setPelicula(pelicula);
			repository.update(id, p);
			showAll();
			clear();
		}
	}

	private void clear() {
		idField.clear();
		nombreField.clear();
		fotoField.clear();
		peliculaIdField.clear();
	}

	private void showAll() {
		datos = repository.getAll();
		showProtagonistas(datos);
		clear();
	}

	private void showById() {
		var id = Integer.parseInt(idField.getText());
		datos = List.of(repository.getById(id));
		showProtagonistas(datos);
		clear();
	}

	private void showByNombre() {
		var nombre = nombreField.getText();
		datos = datos.stream().filter(t -> t.getNombre().equals(nombre)).toList();
		showProtagonistas(datos);
		clear();
	}

	private void fillForm(Protagonista t) {
		idField.setText(String.valueOf(t.getId()));
		nombreField.setText(t.getNombre());
		fotoField.setText(String.valueOf(t.getFoto()));
		peliculaIdField.setText(String.valueOf(t.getPelicula().getId()));
	}

	private void showProtagonistas(List<Protagonista> datos) {
		ObservableList<Protagonista> tarifas = FXCollections.observableList(datos);
		TableColumn<Protagonista, Integer> columnaId = new TableColumn<>("ID");
		TableColumn<Protagonista, String> columnaNombre = new TableColumn<>("Nombre");
		TableColumn<Protagonista, String> columnaFoto = new TableColumn<>("Foto");
		TableColumn<Protagonista, Integer> columnaPeliculaId = new TableColumn<>("Pelicula");
		columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
		columnaPeliculaId.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getPelicula().getId()).asObject());
		tablaView.getColumns().clear();
		tablaView.getColumns().addAll(columnaId, columnaNombre, columnaPeliculaId, columnaFoto);
		tablaView.setItems(tarifas);

		tablaView.setOnMouseClicked(e -> {
			var c = tablaView.getSelectionModel().getSelectedItem();
			if (c != null)
				fillForm(c);
		});
	}
}

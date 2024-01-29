package aed.hibernate.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.db.CineRepository;
import aed.hibernate.model.Cine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class CineController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private TextField idField, nombreField, calleField, numeroField, telefonoField;

	@FXML
	private TableView<Cine> tablaView;

	private CineRepository repository = App.cineRepository;

	private List<Cine> datos;

	public CineController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/CineView.fxml"));
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
			showByName();
		else
			showAll();
	}

	@FXML
	private void insert() {
		if (!nombreField.getText().isBlank() && !telefonoField.getText().isBlank()) {
			var nombre = nombreField.getText();
			var calle = calleField.getText();
			var numero = Integer.parseInt(numeroField.getText());
			var telefono = Integer.parseInt(telefonoField.getText());
			repository.insert(new Cine(nombre, calle, numero, telefono));
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
		if (!idField.getText().isBlank() && !nombreField.getText().isBlank() && !telefonoField.getText().isBlank()) {
			var id = Integer.parseInt(idField.getText());
			var nombre = nombreField.getText();
			var calle = calleField.getText();
			var numero = Integer.parseInt(numeroField.getText());
			var telefono = Integer.parseInt(telefonoField.getText());
			repository.update(id, new Cine(nombre, calle, numero, telefono));
			showAll();
			clear();
		}
	}

	private void clear() {
		idField.clear();
		nombreField.clear();
		calleField.clear();
		numeroField.clear();
		telefonoField.clear();
	}

	private void showAll() {
		datos = repository.getAll();
		showCines(datos);
		clear();
	}

	private void showById() {
		var id = Integer.parseInt(idField.getText());
		datos = List.of(repository.getById(id));
		showCines(datos);
		clear();
	}

	private void showByName() {
		var nombre = nombreField.getText();
		datos = datos.stream().filter(c -> c.getNombre().equals(nombre)).toList();
		showCines(datos);
		clear();
	}

	private void fillForm(Cine c) {
		idField.setText(String.valueOf(c.getId()));
		nombreField.setText(c.getNombre());
		calleField.setText(c.getCalle());
		numeroField.setText(String.valueOf(c.getNumero()));
		telefonoField.setText(String.valueOf(c.getTelefono()));
	}

	private void showCines(List<Cine> datos) {
		ObservableList<Cine> cines = FXCollections.observableList(datos);
		TableColumn<Cine, Integer> columnaId = new TableColumn<>("ID");
		TableColumn<Cine, String> columnaNombre = new TableColumn<>("Nombre");
		TableColumn<Cine, String> columnaCalle = new TableColumn<>("Calle");
		TableColumn<Cine, Integer> columnaNumero = new TableColumn<>("Numero");
		TableColumn<Cine, Integer> columnaTelefono = new TableColumn<>("Telefono");
		columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaCalle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		columnaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		tablaView.getColumns().clear();
		tablaView.getColumns().addAll(columnaId, columnaNombre, columnaCalle, columnaNumero, columnaTelefono);
		tablaView.setItems(cines);

		tablaView.setOnMouseClicked(e -> {
			var c = tablaView.getSelectionModel().getSelectedItem();
			if (c != null)
				fillForm(c);
		});
	}
}

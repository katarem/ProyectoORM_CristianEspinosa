package aed.hibernate.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.db.CineRepository;
import aed.hibernate.db.TarifaRepository;
import aed.hibernate.model.Cine;
import aed.hibernate.model.Tarifa;
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

public class TarifaController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private TextField idField, fechaField, precioField, cineIdField;

	@FXML
	private TableView<Tarifa> tablaView;

	private TarifaRepository repository = App.tarifaRepository;

	private List<Tarifa> datos;

	public TarifaController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml/TarifaView.fxml"));
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
		else if (!fechaField.getText().isBlank())
			showByFecha();
		else
			showAll();
	}

	@FXML
	private void insert() {
		if (!fechaField.getText().isBlank() && !precioField.getText().isBlank()) {
			var fecha = fechaField.getText();
			var precio = Double.parseDouble(precioField.getText());
			var cineId = Integer.parseInt(cineIdField.getText());
			var cine = App.cineRepository.getById(cineId);
			repository.insert(new Tarifa(fecha, precio, cine));
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
		if (!idField.getText().isBlank() && !fechaField.getText().isBlank() && !precioField.getText().isBlank()
				&& !cineIdField.getText().isBlank()) {
			var id = Integer.parseInt(idField.getText());
			var fecha = fechaField.getText();
			var precio = Double.parseDouble(precioField.getText());
			var cineId = Integer.parseInt(cineIdField.getText());
			var cine = App.cineRepository.getById(cineId);
			repository.update(id, new Tarifa(fecha, precio, cine));
			showAll();
			clear();
		}
	}

	private void clear() {
		idField.clear();
		fechaField.clear();
		precioField.clear();
		cineIdField.clear();
	}

	private void showAll() {
		datos = repository.getAll();
		showTarifas(datos);
		clear();
	}

	private void showById() {
		var id = Integer.parseInt(idField.getText());
		datos = List.of(repository.getById(id));
		showTarifas(datos);
		clear();
	}

	private void showByFecha() {
		var fecha = fechaField.getText();
		datos = datos.stream().filter(t -> t.getDia().equals(fecha)).toList();
		showTarifas(datos);
		clear();
	}

	private void fillForm(Tarifa t) {
		idField.setText(String.valueOf(t.getId()));
		fechaField.setText(t.getDia());
		precioField.setText(String.valueOf(t.getPrecio()));
		cineIdField.setText(String.valueOf(t.getCine().getId()));
	}

	private void showTarifas(List<Tarifa> datos) {
		ObservableList<Tarifa> tarifas = FXCollections.observableList(datos);
		TableColumn<Tarifa, Integer> columnaId = new TableColumn<>("ID");
		TableColumn<Tarifa, String> columnaFecha = new TableColumn<>("Fecha");
		TableColumn<Tarifa, Double> columnaPrecio = new TableColumn<>("Precio");
		TableColumn<Tarifa, Integer> columnaCineId = new TableColumn<>("Cine");
		columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<>("dia"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnaCineId.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getCine().getId()).asObject());
		tablaView.getColumns().clear();
		tablaView.getColumns().addAll(columnaId, columnaFecha, columnaPrecio, columnaCineId);
		tablaView.setItems(tarifas);

		tablaView.setOnMouseClicked(e -> {
			var c = tablaView.getSelectionModel().getSelectedItem();
			if (c != null)
				fillForm(c);
		});
	}
}

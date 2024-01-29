package aed.hibernate.controller;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import aed.hibernate.App;
import aed.hibernate.model.Pase;
import aed.hibernate.model.Pelicula;
import aed.hibernate.model.Protagonista;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PeliController implements Initializable {

	@FXML
	private Pane panel;

	@FXML
	private ImageView peliculaImageView, prota1ImageView, prota2ImageView, prota3ImageView;

	@FXML
	private Label tituloPelicula, generoPelicula, directorPelicula;

	@FXML
	private Label prota1Label, prota2Label, prota3Label;

	@FXML
	private ListView<Pase> paseView;

	private Dialog dialog = new Dialog();

	private Pelicula pelicula = new Pelicula();

	private Stage stage;

	public PeliController() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/PeliView.fxml"));
			l.setController(this);
			l.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.setOnCloseRequest(e -> stage.close());
	}

	public void setPelicula(Pelicula selectedPelicula) {
		pelicula = selectedPelicula;
		peliculaImageView.setImage(new Image(pelicula.getCaratula()));
		peliculaImageView.setFitWidth(250);
		peliculaImageView.setFitHeight(280);
		tituloPelicula.setText(pelicula.getTitulo());
		generoPelicula.setText(pelicula.getGenero());
		directorPelicula.setText(pelicula.getDirector());
		setProtagonistas(pelicula.getProtagonistas());
		setPases(pelicula.getPases());

		dialog.getDialogPane().setContent(panel);

	}

	private void setPases(List<Pase> pases) {
		paseView.setItems(FXCollections.observableArrayList(pases));
	}

	private void setProtagonistas(List<Protagonista> protas) {

		// prota 1
		var p1 = protas.get(0);
		prota1Label.setText(p1.getNombre());
		prota1ImageView.setImage(new Image(p1.getFoto()));
		prota1ImageView.setFitHeight(100);
		prota1ImageView.setFitWidth(100);

		// prota 2
		p1 = protas.get(1);
		prota2Label.setText(p1.getNombre());
		prota2ImageView.setImage(new Image(p1.getFoto()));
		prota2ImageView.setFitHeight(100);
		prota2ImageView.setFitWidth(100);

		// prota 3
		p1 = protas.get(2);
		prota3Label.setText(p1.getNombre());
		prota3ImageView.setImage(new Image(p1.getFoto()));
		prota3ImageView.setFitHeight(100);
		prota3ImageView.setFitWidth(100);

	}

	public Optional showPelicula() {
		return dialog.showAndWait();
	}

}

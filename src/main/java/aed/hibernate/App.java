package aed.hibernate;

import aed.hibernate.controller.HomeController;
import aed.hibernate.db.CineRepository;
import aed.hibernate.db.PaseRepository;
import aed.hibernate.db.PeliculaRepository;
import aed.hibernate.db.ProtagonistaRepository;
import aed.hibernate.db.TarifaRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	public static CineRepository cineRepository;
	public static PeliculaRepository peliculaRepository;
	public static PaseRepository paseRepository;
	public static ProtagonistaRepository protagonistaRepository;
	public static TarifaRepository tarifaRepository;
	
	public static Stage stage;
	
	void startRepos() {
		cineRepository = new CineRepository();
		peliculaRepository = new PeliculaRepository();
		paseRepository = new PaseRepository();
		protagonistaRepository = new ProtagonistaRepository();
		tarifaRepository = new TarifaRepository();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.stage = primaryStage;
		startRepos();
		HomeController homeController = new HomeController();
		stage.setScene(new Scene(homeController.getView()));
		stage.setTitle("CARTELERA CINESXD");
		stage.setResizable(false);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

package aed.hibernate;

import java.util.ArrayList;
import java.util.List;

import aed.hibernate.db.CineRepository;
import aed.hibernate.db.PeliculaRepository;
import aed.hibernate.db.TarifaRepository;
import aed.hibernate.model.Cine;
import aed.hibernate.model.Pelicula;
import aed.hibernate.model.Protagonista;
import aed.hibernate.model.Tarifa;

public class Prueba {

	public static void main(String[] args) {

		CineRepository cineRep = new CineRepository();
		TarifaRepository tarifaRep = new TarifaRepository();
		PeliculaRepository peliculaRepository = new PeliculaRepository();
		
		Cine cine1 = new Cine("Alcampo cines", "Avenida Trinidad", 30, 333333);
		Cine cine2 = new Cine("Meridiano", "Avenida 3 de Mayo", 3, 222111);
		Cine cine3 = new Cine("La Villa Orotava", "la orotava", 10, 111222);

		Tarifa tarifa1 = new Tarifa("24/01/2024", 2.55, cine1);
		Tarifa tarifa2 = new Tarifa("25/01/2024", 3.55, cine1);
		Tarifa tarifa3 = new Tarifa("26/01/2024", 4.55, cine1);

		Tarifa tarifa4 = new Tarifa("24/01/2024", 2.55, cine2);
		Tarifa tarifa5 = new Tarifa("25/01/2024", 3.55, cine2);
		Tarifa tarifa6 = new Tarifa("26/01/2024", 4.55, cine2);

		Tarifa tarifa7 = new Tarifa("24/01/2024", 5.55, cine3);
		Tarifa tarifa8 = new Tarifa("25/01/2024", 6.55, cine3);
		Tarifa tarifa9 = new Tarifa("26/01/2024", 10.55, cine3);

		var tarifas = new ArrayList<>(List.of(tarifa1, tarifa2, tarifa3));
		cine1.setTarifas(tarifas);
		cineRep.insert(cine1);
		cineRep.insert(cine2);
		cineRep.insert(cine3);
		
		var tarifas2 = new ArrayList<>(List.of(tarifa4,tarifa5,tarifa6,tarifa7,tarifa8,tarifa9));
		tarifas2.forEach(tarifa -> tarifaRep.insert(tarifa));
		
		var peli1 = new Pelicula("Piratas del Caribe","Peter Jackson","mayores de 7 años","Aventura","https://static.wikia.nocookie.net/cine/images/6/62/Piratas_del_Caribe.jpg/revision/latest?cb=20201006154235");
		var peli2 = new Pelicula("El señor de los anillos","Espen Sandberg","mayores de 13 años","Fantasía","https://pics.filmaffinity.com/El_seanor_de_los_anillos_La_comunidad_del_anillo-744631610-large.jpg");
		var peli3 = new Pelicula("Avatar","James Cameron","mayores de 7 años","Ciencia Ficción","https://pics.filmaffinity.com/avatar-208925608-mmed.jpg");
		var peli4 = new Pelicula("Homeless Spiderman","Jon Watts","mayores de 18 años","Drama","https://i.kym-cdn.com/photos/images/original/001/548/008/15c.jpeg");
		var peli5 = new Pelicula("Bee Movie","Simon J. Smith","mayores de 18 años","Gore","https://i1.sndcdn.com/artworks-tNiH97wVJzYKJNVy-wy3ZiQ-t500x500.jpg");
		var peli6 = new Pelicula("Casi 300","Jason Friedberg","mayores de 18 años","Comedia","https://pics.filmaffinity.com/Casi_300-671924747-large.jpg");
		
		var prota1 = new Protagonista("Johnny Depp","https://m.media-amazon.com/images/M/MV5BOTBhMTI1NDQtYmU4Mi00MjYyLTk5MjEtZjllMDkxOWY3ZGRhXkEyXkFqcGdeQXVyNzI1NzMxNzM@._V1_FMjpg_UX1000_.jpg");
		var prota2 = new Protagonista("Keira Knightley","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Pride_and_Prejudice_004_%287271339306%29.jpg/170px-Pride_and_Prejudice_004_%287271339306%29.jpg");
		var prota3 = new Protagonista("Orlando Bloom","https://upload.wikimedia.org/wikipedia/commons/8/81/Orlando_Bloom_Cannes_2013.jpg");
		
		var protas = List.of(prota1,prota2,prota3);
		protas.forEach(prota -> prota.setPelicula(peli1));
		peli1.setProtagonistas(protas);
		
		var pelis = new ArrayList<>(List.of(peli1,peli2,peli3,peli4,peli5,peli6));
		pelis.forEach(p -> {
			peliculaRepository.insert(p);
		});

	}

}

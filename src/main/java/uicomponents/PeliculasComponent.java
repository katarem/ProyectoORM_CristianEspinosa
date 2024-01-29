package uicomponents;

import java.util.List;

import aed.hibernate.App;
import aed.hibernate.controller.PeliController;
import aed.hibernate.model.Pelicula;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class PeliculasComponent {

    private static GridPane grid;


    public static GridPane drawPelis(List<Pelicula> pelis){
        setupGrid();
        var coords = setConstraints(pelis.size(),3);
        var column = coords[0];
        var rows = coords[1];
        var count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (count == pelis.size()) break;
                var peli = pelis.get(count);
                var newJuego = PeliView(peli);
                grid.add(newJuego, j, i);
                count++;
            }
        }
        return grid;
    }


    private static void setupGrid(){
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(5,5,5,5));
        grid.setMaxHeight(GridPane.USE_PREF_SIZE);
        grid.setPrefHeight(GridPane.USE_COMPUTED_SIZE);
        grid.setMinHeight(GridPane.USE_PREF_SIZE);
        grid.setMaxWidth(GridPane.USE_PREF_SIZE);
        grid.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        grid.setMinWidth(GridPane.USE_PREF_SIZE);
    }

    private static int[] setConstraints(int size, int columnCount) {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        for (int i = 0; i < columnCount; i++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setFillWidth(false);
            c.setPrefWidth(140);
            c.setMaxWidth(140);
            c.setMinWidth(140);
            c.setHgrow(Priority.ALWAYS);
            c.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(c);
        }
        var rows = Math.ceilDiv(size,columnCount);
        for (int i = 0; i < rows; i++) {
            RowConstraints r = new RowConstraints();
            r.setValignment(VPos.CENTER);
            r.setPrefHeight(200);
            r.setMaxHeight(200);
            r.setMinHeight(200);
            r.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(r);
        }
        return new int[]{columnCount, rows};
    }

    private static VBox PeliView(Pelicula peli){
        var newPeli = new VBox();
        var nombrePeli = new Label(peli.getTitulo());
        var imagenPeli = new ImageView(new Image(peli.getCaratula()));

        nombrePeli.setWrapText(true);
        nombrePeli.setMaxWidth(120);
        nombrePeli.setPrefHeight(50);
        nombrePeli.setTextAlignment(TextAlignment.CENTER);
        nombrePeli.setAlignment(Pos.CENTER);

        imagenPeli.setFitWidth(120);
        imagenPeli.setFitHeight(140);

        newPeli.setSpacing(5);
        newPeli.setPadding(new Insets(5,5,5,5));
        newPeli.setAlignment(Pos.CENTER);
        newPeli.getChildren().addAll(imagenPeli, nombrePeli);
        newPeli.setMinWidth(VBox.USE_PREF_SIZE);
        newPeli.setPrefWidth(VBox.USE_COMPUTED_SIZE);
        newPeli.setMaxWidth(VBox.USE_PREF_SIZE);
        newPeli.setMaxHeight(VBox.USE_PREF_SIZE);
        newPeli.setPrefHeight(200);
        newPeli.setMinHeight(VBox.USE_PREF_SIZE);

        newPeli.setOnMouseEntered(e -> {
            Border border = new Border(new javafx.scene.layout.BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    null,
                    new BorderWidths(1)));
            newPeli.setBorder(border);
        });

        newPeli.setOnMouseExited(e -> {
            newPeli.setBorder(Border.EMPTY);
        });

        newPeli.setOnMouseClicked(e ->  {
            var g = new PeliController();
            var selectedPelicula = App.peliculaRepository.getAll()
            		.stream()
            		.filter(pelicula -> pelicula.getTitulo().equals(nombrePeli.getText()))
            		.findAny().get();
            g.setPelicula(selectedPelicula);
            g.showPelicula();

        });
        return newPeli;
    }
}
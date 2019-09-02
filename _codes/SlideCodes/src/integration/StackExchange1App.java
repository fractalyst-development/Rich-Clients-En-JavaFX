package integration;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackExchange1App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage scenePrincipal) {
        ListView<Pregunta> listView = new ListView<>();
        listView.setItems(getObservableList());
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Scene scene = new Scene(root, 500, 300);
        scenePrincipal.setTitle("Preguntas: StackOverflow");
        scenePrincipal.setScene(scene);
        scenePrincipal.show();
    }

    ObservableList<Pregunta> getObservableList() {
        ObservableList<Pregunta> preguntas = FXCollections.observableArrayList();
        long ahora = System.currentTimeMillis();
        long hace2Semanas = ahora - (1000 * 60 * 60 * 24) * 14;
        Pregunta p01 = new Pregunta("Ricardo", "¿Cómo consumo un servicio REST desde JavaFX?", ahora);
        Pregunta p02 = new Pregunta("Selene", "¿JavaFX es open source?", hace2Semanas);
        preguntas.addAll(p01, p02);
        return preguntas;
    }
}

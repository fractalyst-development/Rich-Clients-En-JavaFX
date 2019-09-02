package integration;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackExchange4App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage scenePrincipal) {
        ListView<Pregunta> listView = new ListView<>();
        try {
            listView.setItems(getObservableList());
        } catch (IOException ex) {
            System.out.println("Ex:" + ex.getLocalizedMessage());;
        }
        listView.setCellFactory(l -> new PreguntaCell());
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Scene scene = new Scene(root, 500, 300);
        scenePrincipal.setTitle("Preguntas: StackOverflow");
        scenePrincipal.setScene(scene);
        scenePrincipal.show();
    }

    ObservableList<Pregunta> getObservableList() throws IOException {
        String url = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged=javafx&site=stackoverflow";
        Service<ObservableList<Pregunta>> service = new PreguntaProxy4Service(url);
        ObservableList<Pregunta> preguntas = FXCollections.observableArrayList();
        service.stateProperty().addListener((Observable observable) -> {
            System.out.println("Estado del servicio: " + service.getState());
            if (service.getState().equals(Worker.State.SUCCEEDED)) {
                preguntas.addAll(service.getValue());
            }
        });
        System.out.println("INICIANDO EL SERVICIO");
        service.start();
        return preguntas;
    }
}

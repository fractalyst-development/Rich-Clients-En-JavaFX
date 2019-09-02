package integration;

import java.io.IOException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

public class StackExchange3App extends Application {

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
        String strUrl = "http://api.stackexchange.com/2.2/search?tagged=javafx&site=stackoverflow";
        URL apiStackExchange = new URL(strUrl);
        JsonReader jsonReader = Json.createReader(new GZIPInputStream(apiStackExchange.openConnection().getInputStream()));
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray jsonArray = jsonObject.getJsonArray("items");
        ObservableList<Pregunta> preguntas = FXCollections.observableArrayList();
        jsonArray.iterator().forEachRemaining((JsonValue e) -> {
            JsonObject obj = (JsonObject) e;
            JsonString quien = obj.getJsonObject("owner").getJsonString("display_name");
            JsonString que = obj.getJsonString("title");
            JsonNumber cuando = obj.getJsonNumber("creation_date");
            Pregunta q = new Pregunta(quien.getString(), que.getString(), cuando.longValue() * 1000);
            preguntas.add(q);
        });
        return preguntas;
    }
}

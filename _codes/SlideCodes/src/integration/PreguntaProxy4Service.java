package integration;

import java.net.URL;
import java.util.zip.GZIPInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

public class PreguntaProxy4Service extends Service<ObservableList<Pregunta>> {

    private String loc;

    public PreguntaProxy4Service(String loc) {
        this.loc = loc;
    }

    @Override
    protected Task<ObservableList<Pregunta>> createTask() {
        return new Task<ObservableList<Pregunta>>() {
            @Override
            protected ObservableList<Pregunta> call() throws Exception {
                URL host = new URL(loc);
                JsonReader jr = Json.createReader(new GZIPInputStream(host.openConnection().getInputStream()));
                JsonObject jsonObject = jr.readObject();
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
        };
    }

}

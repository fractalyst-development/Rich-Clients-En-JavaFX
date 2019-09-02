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

public class PreguntaProxyService extends Service<ObservableList<Pregunta>> {

    private String loc;

    public PreguntaProxyService(String loc) {
        this.loc = loc;
    }

    @Override
    protected Task<ObservableList<Pregunta>> createTask() {
        return new Task<ObservableList<Pregunta>>() {

            @Override
            protected ObservableList<Pregunta> call() throws Exception {
                System.out.println("QRS start");
                URL host = new URL(loc);
                JsonReader jr = Json.createReader(new GZIPInputStream(host.openConnection().getInputStream()));

                JsonObject jsonObject = jr.readObject();
                JsonArray jsonArray = jsonObject.getJsonArray("items");
                System.out.println("#elements = " + jsonArray.size());

                ObservableList<Pregunta> answer = FXCollections.observableArrayList();

                jsonArray.iterator().forEachRemaining((JsonValue e) -> {
                    JsonObject obj = (JsonObject) e;
                    JsonString name = obj.getJsonObject("owner").getJsonString("display_name");
                    JsonString quest = obj.getJsonString("title");
                    JsonNumber jsonNumber = obj.getJsonNumber("creation_date");
                    Pregunta q = new Pregunta(name.getString(), quest.getString(), jsonNumber.longValue() * 1000);
                    answer.add(q);
                });
                return answer;
            }
        };
    }

}

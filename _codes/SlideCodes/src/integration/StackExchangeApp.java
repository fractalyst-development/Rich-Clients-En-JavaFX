package integration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author johan
 */
public class StackExchangeApp extends Application {

    static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");

    @Override
    public void start(Stage primaryStage) throws IOException {
        TableView<Pregunta> tableView = new TableView<>();
        tableView.setItems(getObservableList());
        TableColumn<Pregunta, String> dateColumn = new TableColumn<>("CUÁNDO");
        TableColumn<Pregunta, String> ownerColumn = new TableColumn<>("QUIÉN");
        TableColumn<Pregunta, String> questionColumn = new TableColumn<>("QUÉ");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("cuando"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("quien"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("que"));

        questionColumn.setPrefWidth(350);
        tableView.getColumns().addAll(dateColumn, ownerColumn, questionColumn);
        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Preguntas: StackOverflow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ObservableList<Pregunta> getObservableList() throws IOException {
        String url = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged=javafx&site=stackoverflow";
        Service<ObservableList<Pregunta>> service = new PreguntaProxyService(url);

        ObservableList<Pregunta> answer = FXCollections.observableArrayList();
        service.stateProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                System.out.println("= ESTADO SERVICIO : " + service.getState());
                if (service.getState().equals(Worker.State.SUCCEEDED)) {
                    answer.addAll(service.getValue());
                }
            }
        });
        System.out.println("= INICIANDO SERVICIO =");
        service.start();
        return answer;
    }

    private String getTimeStampString(long ts) {
        return sdf.format(new Date(ts));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

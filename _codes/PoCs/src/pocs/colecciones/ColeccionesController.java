package pocs.colecciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ColeccionesController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ListView lvw;

    ObservableList<String> datos;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        datos.add("nuevo");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datos = FXCollections.observableArrayList();
        lvw.setItems(datos);
        datos.add("uno");
        datos.add("dos");
        datos.add("tres");

    }

}

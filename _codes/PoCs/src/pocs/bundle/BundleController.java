package pocs.bundle;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BundleController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label lblFxml;
    @FXML
    private Button btnL10N;
    @FXML
    private Label labelBundle;
    @FXML
    private ComboBox cbIdiomasSoportados;
    @FXML
    private GridPane gpnContenido;

    @FXML
    private void cerrar() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void cambiarLocale(Event e) {
        switch (((String) (((ComboBox) e.getSource()).getValue())).toUpperCase()) {
            case "INGLÉS":
                BundleL10N.setLocale(Locale.ENGLISH);
                break;
            case "FRANCÉS":
                BundleL10N.setLocale(Locale.FRENCH);
                break;
            case "ESPAÑOL":
                BundleL10N.setLocale(new Locale("es"));
                break;
            default:
                BundleL10N.setLocale(Locale.ENGLISH);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIdiomasSoportados.getItems().removeAll(cbIdiomasSoportados.getItems());
        cbIdiomasSoportados.getItems().addAll("Español", "Francés", "Inglés");
        lblFxml.setText("En FXML (%llave2) \n No se actualiza al cambiar el Locale de la aplicación");
        label.textProperty().bind(BundleL10N.creaStringBinding("llave2"));
        btnL10N.textProperty().bind(BundleL10N.creaStringBinding("comp.boton", "(ARG 0)"));
    }

}

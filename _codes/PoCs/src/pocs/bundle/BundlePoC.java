package pocs.bundle;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BundlePoC extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle defaulBundle = ResourceBundle.getBundle("pocs/bundle/mensajes");
        Parent root = FXMLLoader.load(getClass().getResource("Bundle.fxml"), defaulBundle);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

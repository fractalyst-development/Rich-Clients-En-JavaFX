package pocs.ventanasdedialogo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author cazucito
 */
public class VentanasDeDialogPoC extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent v01 = FXMLLoader.load(getClass().getResource("FXMLVentana01.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Stage otroStage = new Stage(StageStyle.UTILITY);
        otroStage.initModality(Modality.WINDOW_MODAL);
        otroStage.setScene(new Scene(v01));
        otroStage.initOwner(stage);
        otroStage.show();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

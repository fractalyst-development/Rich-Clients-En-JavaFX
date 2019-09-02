/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embeddedmediaplayer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author cazucito
 */
public class EmbeddedMediaPlayer extends Application {

    private static final String MEDIA_URL
            = "https://download.oracle.com/otndocs/products/javafx/arth_512.flv";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 540);
        // create media player
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);

//        // create mediaView and add media player to the viewer
//        MediaView mediaView = new MediaView(mediaPlayer);
//        ((Group) scene.getRoot()).getChildren().add(mediaView);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

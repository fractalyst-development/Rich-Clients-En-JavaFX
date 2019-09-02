/*
 * The MIT License
 *
 * Copyright 2019 cazucito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package concurrency;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cazucito
 */
public class ProgressBarTaskPoc extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                final int max = 1000;
                for (int i = 1; i <= max; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep problem:" + ex.getLocalizedMessage());
                    }
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, max);
                }
                return null;
            }
        };
        Task taskString = new Task<Void>() {
            @Override
            public Void call() {
                final int max = 1000;
                for (int i = 1; i <= max; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep problem:" + ex.getLocalizedMessage());
                    }
                    if (isCancelled()) {
                        break;
                    }
                    updateMessage((char) i + "");
                }
                return null;
            }
        };
        //
        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());
        Label l = new Label();
        l.textProperty().bind(taskString.messageProperty());
        new Thread(task).start();
        new Thread(taskString).start();
        VBox root = new VBox();
        root.getChildren().addAll(btn, bar, l);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

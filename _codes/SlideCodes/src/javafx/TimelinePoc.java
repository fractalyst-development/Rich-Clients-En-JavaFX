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
package javafx;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimelinePoc extends Application {

    DoubleProperty startXVal = new SimpleDoubleProperty(100.0);

    Button btnIniciar;
    Button btnPausar;
    Button btnContinuar;
    Button btnDetener;
    Line linea;
    Timeline animacion;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        animacion = new Timeline(
                new KeyFrame(new Duration(0.0), new KeyValue(startXVal, 100)),
                new KeyFrame(new Duration(1000.0), new KeyValue(startXVal, 300., Interpolator.LINEAR))
        );
        animacion.setAutoReverse(true);
        animacion.setCycleCount(Animation.INDEFINITE);
        linea = new Line(0, 50, 200, 400);
        linea.setStrokeWidth(4);
        linea.setStroke(Color.BLUE);
        btnIniciar = new Button("start");
        btnIniciar.setOnAction(e -> animacion.playFromStart());
        btnPausar = new Button("pause");
        btnPausar.setOnAction(e -> animacion.pause());
        btnContinuar = new Button("resume");
        btnContinuar.setOnAction(e -> animacion.play());
        btnDetener = new Button("stop");
        btnDetener.setOnAction(e -> animacion.stop());
        HBox commands = new HBox(10, btnIniciar, btnPausar, btnContinuar, btnDetener);
        commands.setLayoutX(60);
        commands.setLayoutY(420);
        Group group = new Group(linea, commands);
        Scene scene = new Scene(group, 400, 500);

        linea.startXProperty().bind(startXVal);
        btnIniciar.disableProperty().bind(animacion.statusProperty().isNotEqualTo(Animation.Status.STOPPED));
        btnPausar.disableProperty().bind(animacion.statusProperty().isNotEqualTo(Animation.Status.RUNNING));
        btnContinuar.disableProperty().bind(animacion.statusProperty().isNotEqualTo(Animation.Status.PAUSED));
        btnDetener.disableProperty().bind(animacion.statusProperty().isEqualTo(Animation.Status.STOPPED));

        stage.setScene(scene);
        stage.setTitle("Metronome 1");
        stage.show();
    }
}

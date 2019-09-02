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

import javafx.animation.PathTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EventPoc extends Application {

    private final Color CLARO = Color.rgb(243, 179, 42);
    private final Color OSCURO = Color.rgb(1, 22, 56);

    @Override
    public void start(Stage stage) {
        // CIRCULO 
        Circle circulo = new Circle();
        circulo.setCenterX(60.0f);
        circulo.setCenterY(60.0f);
        circulo.setRadius(50.0f);
        circulo.setFill(Color.rgb(243, 179, 42));
        // RUTA
        Path ruta = new Path();
        MoveTo moverAOrigen = new MoveTo(60, 60);
        LineTo linea1 = new LineTo(740, 60);
        LineTo linea2 = new LineTo(740, 340);
        LineTo linea3 = new LineTo(60, 340);
        LineTo linea4 = new LineTo(60, 60);
        ruta.getElements().add(moverAOrigen);
        ruta.getElements().addAll(linea1, linea2, linea3, linea4);
        // TRANSICIÓN
        PathTransition transRuta = new PathTransition();
        transRuta.setDuration(Duration.millis(2000));
        transRuta.setNode(circulo);
        transRuta.setPath(ruta);
        transRuta.setCycleCount(3);
        transRuta.setAutoReverse(false);
        // BOTONES
        Button btnReproducir = new Button("REPRODUCIR");
        Button btnDetener = new Button("DETENER");
        HBox btns = new HBox(btnReproducir, btnDetener);
        btns.setLayoutX(320);
        btns.setLayoutY(200);
        // EVENTOS
        circulo.setOnMouseClicked((javafx.scene.input.MouseEvent e) -> {
            if (((Color) circulo.getFill()).equals(CLARO)) {
                circulo.setFill(OSCURO);
            } else {
                circulo.setFill(CLARO);
            }
        });
        btnReproducir.setOnMouseClicked((javafx.scene.input.MouseEvent e) -> {
            transRuta.play();
        });
        btnDetener.setOnMouseClicked((javafx.scene.input.MouseEvent e) -> {
            transRuta.stop();
        });
        // SCENE
        Group raiz = new Group(circulo, btns);
        Scene scene = new Scene(raiz, 800, 400);
        scene.setFill(Color.LAVENDER);
        // STAGE
        stage.setTitle("EVENT PoC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}

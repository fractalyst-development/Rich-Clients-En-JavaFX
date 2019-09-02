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

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class TransJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        //Drawing Circle1 
        Circle circle1 = new Circle(300, 135, 50);

        //Setting the color of the circle 
        circle1.setFill(Color.BLUE);

        //Setting the stroke width of the circle 
        circle1.setStrokeWidth(20);

        //Drawing Circle2 
        Circle circle2 = new Circle(300, 135, 50);

        //Setting the color of the circle 
        circle2.setFill(Color.BURLYWOOD);

        //Setting the stroke width of the circle 
        circle2.setStrokeWidth(20);

        //Creating the scale transformation 
        Scale scale = new Scale();

        //Setting the dimensions for the transformation 
        scale.setX(1.5);
        scale.setY(1.5);

        //Setting the pivot point for the transformation 
        scale.setPivotX(300);
        scale.setPivotY(135);

        //Adding the scale transformation to circle1 
        circle1.getTransforms().addAll(scale);

        //Creating a Group object  
        Group root = new Group(circle1, circle2);

        //Creating a scene object 
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage 
        stage.setTitle("Scaling transformation example");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}

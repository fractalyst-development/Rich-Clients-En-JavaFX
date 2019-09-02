/*
 * The MIT License
 *
 * Copyright 2019 Fractalyst.com.mx.
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

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author fractalyst
 */
public class ScenePoc extends Application {

    double valorMovX;
    double valorMovY;
    DoubleProperty valorR = new SimpleDoubleProperty(255.0);
    DoubleProperty valorG = new SimpleDoubleProperty(255.0);
    DoubleProperty valorB = new SimpleDoubleProperty(255.0);

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // FONDO
        Rectangle fondoLateralIzq = new Rectangle(200, 400, Color.rgb(243, 179, 42));
        // TITULO SECCIÓN
        Label lblTitulo = new Label("Scene PoC");
        lblTitulo.setId("titulo");
        // TEXTO
        Text txtStageX = creaTexto();
        Text txtStageY = creaTexto();
        Text txtStageAlto = creaTexto();
        Text txtStageAncho = creaTexto();
        // RGB SLIDERS
        Slider sldrR = new Slider(0, 255, 255);
        Slider sldrG = new Slider(0, 255, 255);
        Slider sldrB = new Slider(0, 255, 255);
        VBox gpoSldrRGB = new VBox(sldrR, sldrG, sldrB);
        // CHOICEBOX
        ObservableList cursors = FXCollections.observableArrayList(
                Cursor.DEFAULT,
                Cursor.CROSSHAIR,
                Cursor.WAIT,
                Cursor.TEXT,
                Cursor.HAND,
                Cursor.MOVE,
                Cursor.NONE
        );
        ChoiceBox cbxCursor = new ChoiceBox(cursors);
        // RADIOBUTTON
        final ToggleGroup toggleGrp = new ToggleGroup();
        RadioButton rdoEstilo01 = new RadioButton("Estilo 1");
        RadioButton rdoEstilo02 = new RadioButton("Estilo 2");
        rdoEstilo01.setSelected(true);
        rdoEstilo01.setToggleGroup(toggleGrp);
        rdoEstilo02.setToggleGroup(toggleGrp);
        // BOTON
        Button btnCerrar = new Button("Cerrar [close()]");
        // VBOX LATERAL IZQ
        VBox vbxContenidoIzq = new VBox(10,
                lblTitulo, txtStageX, txtStageY, txtStageAncho, txtStageAlto,
                gpoSldrRGB,
                cbxCursor,
                rdoEstilo01, rdoEstilo02,
                btnCerrar
        );
        vbxContenidoIzq.setLayoutX(10);
        vbxContenidoIzq.setLayoutY(10);
        // IMAGEN LATERAL DERECHA
        Image imgFondo = new Image(ScenePoc.class.getResourceAsStream("/mm/fondo_01.jpg"));
        ImageView ivFondo = new ImageView(imgFondo);
        ivFondo.setFitWidth(600);
        ivFondo.setFitHeight(400);
        ivFondo.setPreserveRatio(false);
        //  AGRUPANDO ELEMENTOS
        Group grpIzq = new Group(fondoLateralIzq, vbxContenidoIzq);
        GridPane gpaneRaiz = new GridPane();
        gpaneRaiz.add(grpIzq, 0, 0);
        gpaneRaiz.add(ivFondo, 1, 0);
        // SCENE
        Scene scene = new Scene(gpaneRaiz, 800, 400);
        scene.getStylesheets().add("/mm/ScenePocEstilo01.css");
        // EVENTOS
        btnCerrar.setOnAction(e -> {
            System.out.println("Cerrar");
            stage.close();
        });
        // Cada que un botón del ratón es presionado los valores de x/y se actualizan
        grpIzq.setOnMousePressed((MouseEvent me) -> {
            valorMovX = me.getScreenX() - stage.getX();
            valorMovY = me.getScreenY() - stage.getY();
            System.out.println("setOnMousePressed() = x/y: " + valorMovX + "/" + valorMovY);
        });
        // Al arrastrar la ventana, los valores de x/y se acutalizan
        grpIzq.setOnMouseDragged((MouseEvent me) -> {
            stage.setX(me.getScreenX() - valorMovX);
            stage.setY(me.getScreenY() - valorMovY);
            System.out.println("setOnMouseDragged() = x/y: " + valorMovX + "/" + valorMovY);
        });
        // Binding de variables
        txtStageX.textProperty().bind(new SimpleStringProperty("x: ").concat(stage.xProperty().asString()));
        txtStageY.textProperty().bind(new SimpleStringProperty("y: ").concat(stage.yProperty().asString()));
        txtStageAncho.textProperty().bind(new SimpleStringProperty("Ancho: ").concat(stage.widthProperty().asString()));
        txtStageAlto.textProperty().bind(new SimpleStringProperty("Alto: ").concat(stage.heightProperty().asString()));
        scene.cursorProperty().bind(cbxCursor.getSelectionModel().selectedItemProperty());
        valorR.bind(sldrR.valueProperty());
        valorG.bind(sldrG.valueProperty());
        valorB.bind(sldrB.valueProperty());
        // Recuperación de los calores de los sliders y asignación al fondoLateralIzq
        ChangeListener<? super Number> escuchaColor = (ov, oldValue, newValue) -> {
            Double valR = this.valorR.getValue() / 256.0;
            Double valG = this.valorG.getValue() / 256.0;
            Double valB = this.valorB.getValue() / 256.0;
            fondoLateralIzq.setFill(new Color(valR, valG, valB, 1.0));
        };
        valorR.addListener(escuchaColor);
        valorG.addListener(escuchaColor);
        valorB.addListener(escuchaColor);
        // Limpieza y carga de los nuevos estilos desde el CSS seleccionado
        toggleGrp.selectedToggleProperty().addListener((ov, oldValue, newValue) -> {
            String radioButtonText = ((RadioButton) toggleGrp.getSelectedToggle()).getText();
            scene.getStylesheets().clear();
            String archivoCSS;
            switch (radioButtonText) {
                case "Estilo 2":
                    archivoCSS = "ScenePocEstilo02.css";
                    break;
                default:
                    archivoCSS = "ScenePocEstilo01.css";
            }
            scene.getStylesheets().addAll("/mm/" + archivoCSS);
        });

        // Configuración del Stage
        stage.setTitle("Scene PoC");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> {
            System.out.println("Cerrando 'Stage'");
        });
        Rectangle2D dimensionesPantalla = Screen.getPrimary().getVisualBounds();
        System.out.println("Dimensión Pantalla: " + dimensionesPantalla.toString());
        stage.setX((dimensionesPantalla.getWidth() - stage.getWidth()) / 2);
        stage.setY((dimensionesPantalla.getHeight() - stage.getHeight()) / 4);
        stage.show();
    }

    private Text creaTexto() {
        Text txt = new Text();
        txt.getStyleClass().add("texto-enfatizado");
        return txt;
    }
}

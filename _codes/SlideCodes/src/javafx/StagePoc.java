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

import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author fractalyst
 */
public class StagePoc extends Application {

    StringProperty titulo = new SimpleStringProperty();

    Text txtStageX;
    Text txtStageY;
    Text txtStageAncho;
    Text txtStageAlto;
    Text txtStageFoco;
    CheckBox cbxRedimensionable;
    CheckBox cbxPantallaCompleta;

    double valorMovX;
    double valorMovY;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        StageStyle estiloStage = StageStyle.DECORATED;
        List<String> paramAn = getParameters().getUnnamed();
        if (paramAn.size() > 0) {
            String stageStyleParam = paramAn.get(0);
            if (stageStyleParam.equalsIgnoreCase("TRANSPARENT")) {
                estiloStage = StageStyle.TRANSPARENT;
            } else if (stageStyleParam.equalsIgnoreCase("UNDECORATED")) {
                estiloStage = StageStyle.UNDECORATED;
            } else if (stageStyleParam.equalsIgnoreCase("UTILITY")) {
                estiloStage = StageStyle.UTILITY;
            }
        }
        // BOTONES
        Button btnPasarAlFondo = new Button("Mover al Fondo [toBack()]");
        btnPasarAlFondo.setOnAction(e -> stage.toBack());
        Button btnPasarAlFrente = new Button("Mover al Frente [toFront()]");
        btnPasarAlFrente.setOnAction(e -> stage.toFront());
        Button btnCerrar = new Button("Cerrar [close()]");
        btnCerrar.setOnAction(e -> {
            System.out.println("Cerrar");
            stage.close();
        });
        // FONDO
        Rectangle fondoRectangular = new Rectangle(200, 400, Color.rgb(243, 179, 42));
        // TEXTO
        txtStageX = creaTexto();
        txtStageY = creaTexto();
        txtStageAlto = creaTexto();
        txtStageAncho = creaTexto();
        txtStageFoco = creaTexto();
        // CHECKBOX
        cbxRedimensionable = new CheckBox("Redimensionable");
        cbxRedimensionable.setDisable(estiloStage == StageStyle.TRANSPARENT || estiloStage == StageStyle.UNDECORATED);
        cbxPantallaCompleta = new CheckBox("Pantalla Completa");
        //
        TextField tfldTitulo = new TextField("Stage PoC");
        Label lblTitulo = new Label("Título");
        lblTitulo.setFont(Font.font("Helvetica", 14));

        HBox hbxTituloIzq = new HBox(lblTitulo, tfldTitulo);
        VBox vbxContenidoIzq = new VBox(
                txtStageX, txtStageY, txtStageAncho, txtStageAlto, txtStageFoco,
                cbxRedimensionable, cbxPantallaCompleta,
                hbxTituloIzq,
                btnPasarAlFondo, btnPasarAlFrente, btnCerrar);
        vbxContenidoIzq.setLayoutX(10);
        vbxContenidoIzq.setLayoutY(10);
        vbxContenidoIzq.setSpacing(5);
        //
        Image imgFondo = new Image(StagePoc.class.getResourceAsStream("/mm/fondo_01.jpg"));
        ImageView ivFondo = new ImageView(imgFondo);
        ivFondo.setFitWidth(600);
        ivFondo.setFitHeight(400);
        ivFondo.setPreserveRatio(false);
        Group grpIzq = new Group(fondoRectangular, vbxContenidoIzq);
        GridPane gpaneRaiz = new GridPane();
        gpaneRaiz.add(grpIzq, 0, 0);
        gpaneRaiz.add(ivFondo, 1, 0);
        Scene scene = new Scene(gpaneRaiz, 800, 400);
        scene.setFill(Color.TRANSPARENT);

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
        txtStageFoco.textProperty().bind(new SimpleStringProperty("¿Foco?: ").concat(stage.focusedProperty().asString()));
        cbxRedimensionable.selectedProperty().bindBidirectional(stage.resizableProperty());
        cbxPantallaCompleta.selectedProperty().addListener((ov, valorViejo, valorNuevo) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(ov.getClass().getSimpleName()).append(":").append(ov).append("-");
            sb.append("valorViejo:").append(valorViejo).append("-");
            sb.append("valorNuevo:").append(valorNuevo);
            System.out.println(sb.toString());
            stage.setFullScreen(cbxPantallaCompleta.selectedProperty().getValue());
        });
        titulo.bind(tfldTitulo.textProperty());
        // Configuración del Stage
        stage.setResizable(true);
        stage.setScene(scene);
        stage.titleProperty().bind(titulo);
        stage.initStyle(estiloStage);
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
        txt.setTextOrigin(VPos.TOP);
        txt.setFont(Font.font("Helvetica", 16));
        txt.setFill(Color.rgb(1, 22, 56));
        return txt;
    }
}

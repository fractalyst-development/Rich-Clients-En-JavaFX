/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrencydemo;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author cazucito
 */
public class FXMLDocumentController {

    final int CUANTAS_ITERACCIONES = 20;
    final int PAUSA_EN_MILLIS = 1000;

    @FXML
    private TextField tfldDatoA;
    @FXML
    private TextField tfldDatoB;
    @FXML
    private Label resultadoAmasB;

    @FXML
    private Label resNormal;
    @FXML
    private Label resSinUI;
    @FXML
    private Label resRunLater;
    @FXML
    private Label resThread;
    @FXML
    private Label resThreadTask;
    @FXML
    private Label resTaskReturn;

    @FXML
    private void accionResNormal(ActionEvent event) {
        SimpleStringProperty stringProp = new SimpleStringProperty();
        resNormal.textProperty().bind(stringProp);
        stringProp.set("ESTADO INICIAL");
        for (int i = 0; i < CUANTAS_ITERACCIONES; ++i) {
            stringProp.set("Algo: " + i);
            try {
                Thread.sleep(PAUSA_EN_MILLIS);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void procesoOtroHiloSinActualizarUI(ActionEvent event) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("ESTADO INICIAL");
                for (int i = 0; i < CUANTAS_ITERACCIONES; ++i) {
                    System.out.println("Algo: " + i);
                    try {
                        Thread.sleep(PAUSA_EN_MILLIS);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void procesoOtroHiloRunLater(ActionEvent event) {
        SimpleStringProperty stringProp = new SimpleStringProperty();
        resRunLater.textProperty().bind(stringProp);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                stringProp.set("VALOR INICIAL mj");
                for (int j = 0; j < CUANTAS_ITERACCIONES; ++j) {
                    stringProp.set("Algo: " + j);
                    try {
                        Thread.sleep(PAUSA_EN_MILLIS);
                    } catch (InterruptedException ex) {
                        System.out.println("ex:" + ex.getLocalizedMessage());
                    }
                }
                return null;
            }
        };
        // Update the Label on the JavaFx Application Thread        
        Platform.runLater(task);
    }

    @FXML
    private void procesoOtroHiloThread(ActionEvent event) {
        SimpleStringProperty stringProp = new SimpleStringProperty();
        resThread.textProperty().bind(stringProp);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int j = 0; j < CUANTAS_ITERACCIONES; ++j) {

                    stringProp.set("Algo: " + j);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("ex:" + ex.getLocalizedMessage());
                    }
                }
                return null;
            }
        };
//        lblMuchoTrabajo.textProperty().bind(task.messageProperty());
        // Update the Label on the JavaFx Application Thread        
//        Platform.runLater(task);
        new Thread(task).start();
//        Executors.newCachedThreadPool().execute(task);
    }

    @FXML
    private void procesoOtroHiloThreadTask(ActionEvent event) {
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                for (int j = 0; j < CUANTAS_ITERACCIONES; ++j) {
                    updateMessage("Algo: " + j);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("ex:" + ex.getLocalizedMessage());
                    }
                }
                return null;
            }
        };
        resThreadTask.textProperty().bind(task.messageProperty());
        // Update the Label on the JavaFx Application Thread        
//        Platform.runLater(task);
        new Thread(task).start();
//        Executors.newCachedThreadPool().execute(task);
    }

    @FXML
    private void procesoOtroHiloTaskResultado(ActionEvent event) throws InterruptedException {
        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int total = 0;
                for (int j = 0; j < CUANTAS_ITERACCIONES; ++j) {
                    updateMessage("Algo: " + j);
                    updateProgress(j, CUANTAS_ITERACCIONES);
                    total += j;
                    try {
                        Thread.sleep(PAUSA_EN_MILLIS);
                    } catch (InterruptedException ex) {
                        System.out.println("ex:" + ex.getLocalizedMessage());
                    }
                }
                return total;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // update UI with result               
                resTaskReturn.setText("" + task.getValue()); // result of computation

            }
        });
        Thread th = new Thread(task);
        th.start();
//        Executors.newCachedThreadPool().execute(task);
    }

    @FXML
    private void suma(ActionEvent event) {
        resultadoAmasB.setText("" + (Integer.parseInt(tfldDatoA.getText()) + Integer.parseInt(tfldDatoB.getText())));
    }

}

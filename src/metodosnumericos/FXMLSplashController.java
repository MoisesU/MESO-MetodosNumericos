/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import metodosnumericos.tools.Config;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLSplashController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imgView;
    @FXML
    private ProgressBar progBar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            String cmd = "set PATH=\"C:\\Program Files\\scilab-5.5.2\\bin\";%PATH%";
//            Runtime.getRuntime().exec(cmd); 
//        } catch (IOException ioe) {
//            System.err.println (ioe);
//        }
        new SplashScreen().start();
    }    
    class SplashScreen extends Thread{
        @Override
        public void run(){
            try {
                imgView.setImage(new Image(getClass().getResource("rec/background.jpg").toString()));
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        Thread.sleep(25);
                        progBar.setProgress((i+1)/10.0 + j/100.0);
                    }
                }
                Platform.runLater(() -> {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage()+"\nNo se encuentra el archivo FXMLMain.fxml");
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //stage.setResizable(false);
                    stage.getIcons().add(new Image(getClass().getResource("rec/icon.png").toString()));
                    stage.setTitle("Meso+ - v1.0.0");
                    stage.setMaxWidth(960);
                    stage.setMaxHeight(690);
                    rootPane.getScene().getWindow().hide();
                    try{
                        root.getScene().getWindow().setOnCloseRequest((WindowEvent event)-> {this.closeOperation(event);});
                    }catch (NullPointerException e){
                        System.err.println("root es nulo\n"+e.getMessage());
                    }
                    stage.show();
                });
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage()+"\nProceso splash interrumpido");  
            }          
        }
        public void closeOperation(WindowEvent evt){
            Config.closeScilab();
            System.exit(0);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metodosnumericos.tools.Config;
import metodosnumericos.tools.Tool;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLMainController implements Initializable {

    @FXML
    private AnchorPane displayPane;
    @FXML
    private RadioMenuItem rmSimp;
    @FXML
    private RadioMenuItem rmStat;
    private ToggleGroup tg;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Config.setScilab();
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLRoots.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
        rmStat.setSelected(true);
        tg = new ToggleGroup();
        rmStat.setToggleGroup(tg);
        rmSimp.setToggleGroup(tg);
    }

    @FXML
    private void openRoots(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLRoots.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openEstadistica(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLInterpolEstadistica.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openInterpolSplin(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLInterpolSplin.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openInterpolLagrange(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLInterpolLagrange.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openSimpleInterpol(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLSimpleInterpol.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openBisection(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLBisection.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openNewtonRaphson(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLNewtonRaphson.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openInterpolNewton(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLInterpolNewton.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void openNewtonJacobi(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLJacobi.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openUniformPart(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLUniformPartition.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openIntegInterpol(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLIntegInterpol.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openTrapezium(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLTrapezium.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openSimpson13(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLSimpson13.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openSimpson38(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLSimpson38.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openGaussSeidel(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLGaussSeidel.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openEuler(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLEuler.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void openUpgradeEuler(ActionEvent event) {
        AnchorPane roots = null;
        try {
            roots = FXMLLoader.load(getClass().getResource("FXMLUpgradeEuler.fxml"));
            displayPane.getChildren().add(roots);
        } catch (IOException ex) {
            System.err.println("No se encontró un archivo :/");
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void setFix(ActionEvent event) {
        Stage stage = new Stage();
        Tool t = new Tool();
        OptionPane p = new OptionPane("Ingresa el valor de Fix (0-9)");
        p.getBtn().setOnAction((ActionEvent evt)->{
            String s = p.getTf().getText();
            if(t.isANumber(s)){
                int d = (int)Double.parseDouble(s);
                System.out.println("Fix has been changed");
                Config.setFix(d);
            }
            stage.close();
        });
        p.getBtnC().setOnAction((ActionEvent evt)->{
            stage.close();
        });
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setMaxHeight(188);
        stage.setMaxWidth(346);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Fix");
        stage.getIcons().add(new Image(OptionPane.getIcon()));
        stage.show();
    }

    @FXML
    private void setNum(ActionEvent event) {
        Stage stage = new Stage();
        Tool t = new Tool();
        OptionPane p = new OptionPane("Ingresa el máximo de iteraciones");
        p.getBtn().setOnAction((ActionEvent evt)->{
            String s = p.getTf().getText();
            if(t.isANumber(s)){
                int d = (int)Double.parseDouble(s);
                System.out.println("NumMaxIteration has been changed");
                Config.setMaxIteration(d);
            }
            stage.close();
        });
        p.getBtnC().setOnAction((ActionEvent evt)->{
            stage.close();
        });
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setMaxHeight(188);
        stage.setMaxWidth(346);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Numero Max. de Iteraciones");
        stage.getIcons().add(new Image(OptionPane.getIcon()));
        stage.show();
    }

    @FXML
    private void copyToCB(ActionEvent event) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(Config.getLastResult());
        cb.setContents(ss, ss);
        System.out.println("Result has been copied to clipboard!!");
    }

    @FXML
    private void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void switchMethod(ActionEvent event) {
         //T = Statistical F = Simple
         Config.setInterpolMethod(!rmSimp.isSelected());
    }

    @FXML
    private void openManual(ActionEvent event) {
        try {
            File path = new File (Config.getPDF()+"Manual.pdf");
            Desktop.getDesktop().open(path);
       }catch (IOException ex) {
            System.out.println("No se pudo abrir porque no se encontró o yo que sé");
            ex.printStackTrace();
       }
    }
    
}

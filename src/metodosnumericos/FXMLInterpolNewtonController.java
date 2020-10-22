/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import metodosnumericos.tools.Config;
import metodosnumericos.tools.RootIteration;
import metodosnumericos.tools.TabFunctionPoint;
import metodosnumericos.tools.Tool;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLInterpolNewtonController implements Initializable {

    @FXML
    private TableView<TabFunctionPoint> tvFTab;
    @FXML
    private TextField tfX;
    @FXML
    private TextField tfY;
    @FXML
    private TextField tfValue;
    @FXML
    private Label lbResult;
    @FXML
    private ComboBox<String> grade;
    @FXML
    private Button btnDel;

    /**
     * Initializes the controller class.
     */
    private final Tool t = new Tool();
    private Scilab sci;
    private final File interpolacionNewton = new File(Config.getFolder()+"interpolacionNewton.sce");
    private final List<double[]> funct = new ArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(interpolacionNewton);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/interpolacionNewton.sce no encontrado \n"+ex.getMessage());
        }
        TableColumn col = tvFTab.getColumns().get(0);
        col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>("x"));
        col = tvFTab.getColumns().get(1);
        col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>("y"));
        btnDel.setDisable(true);
    }       

    @FXML
    private void calc(ActionEvent event) {
        if(funct.size()<2){
            OptionPane.showMessageDialog(null, "Valores insuficientes para la interpolación");
        }
        else if(tfValue.getText().equals("")){
            OptionPane.showMessageDialog(null, "El campo del valor a calcular está vacio");
        }else if(!t.isANumber(tfValue.getText())){
            OptionPane.showMessageDialog(null, "El valor ingresado no es un número");
        }else{
            double [][] f = new double[2][funct.size()];
            for (int i = 0; i < funct.size(); i++) {
                f[0][i]=funct.get(i)[0];
                f[1][i]=funct.get(i)[1];
            }
            try {
                //function y = interpolNewton(func, grado, x)
                int g = grade.getItems().indexOf(grade.getValue())+1;
                sci.exec("f = "+t.getScilabMatrix(f));
                sci.exec("res = interpolNewton(f, "+g+","+tfValue.getText()+")");
                ScilabType result = sci.get("res");
                lbResult.setText("f("+tfValue.getText()+") = "+t.getFormNumber(result.toString()));
                Config.setLastResult(lbResult.getText());
            } catch (JavasciException ex) {
                System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
                OptionPane.showMessageDialog(null, "Este método solo funciona con incrementos de X iguales");
            }
        }
    }

    @FXML
    private void clean(ActionEvent event) {
        tfX.setText("");
        tfY.setText("");
        tfValue.setText("");
        lbResult.setText("");
        funct.clear();
        tvFTab.getItems().clear();
        grade.getItems().clear();
    }

    @FXML
    private void add(ActionEvent event) {
        if(tfX.getText().equals("")||tfY.getText().equals("")){
            OptionPane.showMessageDialog(null, "Algún campo está vacio");
        }else if(!t.isANumber(tfX.getText())||!t.isANumber(tfY.getText())){
            OptionPane.showMessageDialog(null, "Los valores agregados no son números");
        }else{
            double x = Double.parseDouble(tfX.getText());
            double y = Double.parseDouble(tfY.getText());
            tvFTab.getItems().add(new TabFunctionPoint(String.format("%."+Config.getFix()+"f",x), 
                    String.format("%."+Config.getFix()+"f",y)));
            double[] point = {x, y};
            funct.add(point);
            tfX.setText("");
            tfY.setText("");
            if(funct.size()>=2){
                String aux = funct.size()-1+"o grado";
                grade.getItems().add(aux);
                grade.setValue(aux);
            }
            btnDel.setDisable(false);
            tfX.requestFocus();
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        int i = funct.size()-1;
        if(i>=0){
            tfX.setText("");
            tfY.setText("");
            tvFTab.getItems().remove(i);
            funct.remove(i);
            if(!grade.getItems().isEmpty()){
                grade.getItems().remove(i-1);
                if(grade.getItems().size()>=2)
                    grade.setValue(grade.getItems().get(i-2));
            }
        }
        if(i<=0) 
            btnDel.setDisable(true);
    }
    
    @FXML
    private void change(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            tfY.requestFocus();
        }
    }

    @FXML
    private void change2(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            if(tfX.getText().equals("")||tfY.getText().equals("")){
                OptionPane.showMessageDialog(null, "Algún campo está vacio");
            }else if(!t.isANumber(tfX.getText())||!t.isANumber(tfY.getText())){
                OptionPane.showMessageDialog(null, "Los valores agregados no son números");
            }else{
                double x = Double.parseDouble(tfX.getText());
                double y = Double.parseDouble(tfY.getText());
                tvFTab.getItems().add(new TabFunctionPoint(String.format("%."+Config.getFix()+"f",x), 
                        String.format("%."+Config.getFix()+"f",y)));
                double[] point = {x, y};
                funct.add(point);
                tfX.setText("");
                tfY.setText("");
                tfX.requestFocus();
                if(funct.size()>=2){
                    String aux = funct.size()-1+"o grado";
                    grade.getItems().add(aux);
                    grade.setValue(aux);
                }
                btnDel.setDisable(false);
            }
        }
    }
}

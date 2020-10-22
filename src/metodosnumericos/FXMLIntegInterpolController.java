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
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
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
public class FXMLIntegInterpolController implements Initializable {

    @FXML
    private TableView<TabFunctionPoint> tvFTab;
    @FXML
    private TextField tfX;
    @FXML
    private TextField tfY;
    @FXML
    private Label lbResult;
    @FXML
    private ComboBox<String> grade;
    @FXML
    private Button btnDel;
    @FXML
    private Label lblFunc;
    @FXML
    private TextField tfLimB;
    @FXML
    private TextField tfLimA;
    @FXML
    private AnchorPane paneStat;

    /**
     * Initializes the controller class.
     */
    private final Tool t = new Tool();
    private Scilab sci;
    private final File interpolacionEstadistica = new File(Config.getFolder()+"interpolacionEstadistica.sce");
    private final File interpolacionSimple = new File(Config.getFolder()+"interpolacionSimple.sce");
    private final List<double[]> funct = new ArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(interpolacionEstadistica);
            sci.exec(interpolacionSimple);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/interpolacionEstadistica.sce no encontrado \n"+ex.getMessage());
            System.err.println("       archivo de scripts/interpolacionSimple.sce   no encontrado \n"+ex.getMessage());
        }
        TableColumn col = tvFTab.getColumns().get(0);
        col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>("x"));
        col = tvFTab.getColumns().get(1);
        col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>("y"));
        btnDel.setDisable(true);
        //T = Statistical F = Simple
        paneStat.setVisible(Config.isInterpolMethod());
    }       

    @FXML
    private void calc(ActionEvent event) {
        if(funct.size()<2){
            JOptionPane.showMessageDialog(null, "Valores insuficientes para la interpolación");
        }
        else if(tfLimA.getText().equals("")||tfLimB.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de los limites no puede estar vacio");
        }else if(!t.isANumber(tfLimA.getText())||!t.isANumber(tfLimB.getText())){
            JOptionPane.showMessageDialog(null, "Los limites tienen que ser números reales");
        }else if(Double.parseDouble(tfLimA.getText())>Double.parseDouble(tfLimB.getText())){
            JOptionPane.showMessageDialog(null, "El limite superior debe ser mayor que el inferior");
        }else{
            double [][] f = new double[2][funct.size()];
            double a = Double.parseDouble(tfLimA.getText()), b = Double.parseDouble(tfLimB.getText());
            for (int i = 0; i < funct.size(); i++) {
                f[0][i]=funct.get(i)[0];
                f[1][i]=funct.get(i)[1];
            }
            try {
                //function y = interpolEstadistica(funcTab, grado, x)
                if(Config.isInterpolMethod()){
                    int g = grade.getItems().indexOf(grade.getValue())+1;
                    sci.exec("f = "+t.getScilabMatrix(f));
                    sci.exec("[res, func] = interpolEstadistica(f, "+g+","+f[1][0]+")");
                }else{
                    sci.exec("f = "+t.getScilabMatrix(f));
                    sci.exec("[res, func] = interpolSimple(f,"+f[1][0]+")");
                }
                ScilabType func = sci.get("func");
                //System.out.println(func);
                String [][] coef = t.getFormMatrix(func.toString(), func.getWidth(), func.getHeight());
                //t.viewMatrix(coef);
                String fx = t.getFunction(coef);
                //System.out.println(fx);
                lblFunc.setText(fx+" dx");
                sci.exec("function [y]=f(x), y="+fx+", endfunction");
                sci.exec("result = intg("+a+","+b+",f)");
                ScilabType result = sci.get("result");
                lbResult.setText(t.getFormNumber(result.toString()));
                Config.setLastResult(lbResult.getText());
            } catch (JavasciException ex) {
                System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
            }
        }
    }

    @FXML
    private void clean(ActionEvent event) {
        tfX.setText("");
        tfY.setText("");
        tfLimB.setText("");
        tfLimA.setText("");
        lbResult.setText("");
        funct.clear();
        tvFTab.getItems().clear();
        grade.getItems().clear();
    }

    @FXML
    private void add(ActionEvent event) {
        if(tfX.getText().equals("")||tfY.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Algún campo está vacio");
        }else if(!t.isANumber(tfX.getText())||!t.isANumber(tfY.getText())){
            JOptionPane.showMessageDialog(null, "Los valores agregados no son números");
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
                JOptionPane.showMessageDialog(null, "Algún campo está vacio");
            }else if(!t.isANumber(tfX.getText())||!t.isANumber(tfY.getText())){
                JOptionPane.showMessageDialog(null, "Los valores agregados no son números");
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

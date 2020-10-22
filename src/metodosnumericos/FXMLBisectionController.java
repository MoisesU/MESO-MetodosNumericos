/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import metodosnumericos.tools.BisectionIteration;
import metodosnumericos.tools.Config;
import metodosnumericos.tools.RootIteration;
import metodosnumericos.tools.Tool;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLBisectionController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private Label lblRoot;
    @FXML
    private TableView<BisectionIteration> tvRoot;
    @FXML
    private TextField tfInter;
    @FXML
    private TextField tfFunc;

    /**
     * Initializes the controller class.
     */
    private final Tool t = new Tool();
    private Scilab sci;
    private final File bisection = new File(Config.getFolder()+"bisection.sce");
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(bisection);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/bisection.sce no encontrado \n"+ex.getMessage());
        }
    }    

    @FXML
    private void calcular(ActionEvent event) {
        //function [table, root, err] = bisection(func, inter1, inter2, fx, maxit)
        if(tfFunc.getText().isEmpty()||tfInter.getText().isEmpty())
            OptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
        else{
            double [] interv = t.getInterv(tfInter.getText());
            if(!Pattern.matches("((-|)\\d+(\\.\\d)*,( |)(-|)\\d+(\\.\\d)*)", tfInter.getText().trim())){
                    OptionPane.showMessageDialog(null, "El intervalo ingresado no es válido");
                    if(interv[0]>=interv[1]){
                        OptionPane.showMessageDialog(null, "El intervalo ingresado no es válido");
                    }
            }
            else{
                try {
                    if(sci.exec("function [y]=f(x), y="+tfFunc.getText()+", endfunction")){
                        sci.exec("[tab, result, err] = bisection(f, "+interv[0]+", "+interv[1]+", "+Config.getFix()+", "+Config.getMaxIteration()+")");
                        ScilabType result = sci.get("result");
                        ScilabType err = sci.get("err");
                        ScilabType tab = sci.get("tab");
                        String [][] table = t.getFormTable(tab.toString(), tab.getWidth(), tab.getHeight());
                        setTableValues(table);
                        lblRoot.setText(t.getFormNumber(result.toString()));
                        lblError.setText(t.getFormNumber(err.toString()));
                        Config.setLastResult(lblRoot.getText());
                    }
                    else{
                        OptionPane.showMessageDialog(null,"Las función no está escrita con el patron correcto\n(ejemplo: 2*x + 3*x - 5*x");
                    }
                } catch (JavasciException ex) {
                    System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
                }
            }
        }
        
    }

    private void setTableValues(String[][] table) {
        ObservableList<BisectionIteration> values = FXCollections.observableArrayList();
        int j = 0;
        for (int i = 0; i < table.length; i++) {
            values.add(new BisectionIteration(table[i]));
        }
        for (String prop: new String[]{"n", "a", "xm", "b", "fa", "fb", "fxm", "error"}) {
            TableColumn col = tvRoot.getColumns().get(j);
            col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>(prop));
            j++;
        }
        tvRoot.setItems(values);
    }
    
    private void clear(ActionEvent event){
        lblRoot.setText("");
        lblError.setText("");
        tfFunc.setText("");
        tfInter.setText("");
        tvRoot.getItems().clear();
    } 

}   
    


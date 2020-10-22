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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class FXMLRootsController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private Label lblRoot;
    @FXML
    private TextField tfNum;
    @FXML
    private TableView<RootIteration> tvRoot;
    
    private final Tool t = new Tool();
    private Scilab sci;
    private final File roots = new File(Config.getFolder()+"roots.sce");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvRoot.setEditable(true);
        try {
            sci = Config.getScilab();
            sci.exec(roots);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/roots.sce no encontrado \n"+ex.getMessage());
        }
        //System.out.println(getClass().getResource("rec/adv.png").getPath());
    }    

    @FXML
    private void calcular(ActionEvent event) {
        //function [xn1, err] = root(number, fix)
        if(tfNum.getText().isEmpty()){
            OptionPane.showMessageDialog(null, "Tiene que ingresar un número");
        }
        else if(!t.isANumber(tfNum.getText())){
            OptionPane.showMessageDialog(null, "El valor ingresado no es un número");
        }
        else{
            try {
                sci.exec("[result, err, tab] = root("+tfNum.getText()+", "+Config.getFix()+")");
                ScilabType result = sci.get("result");
                ScilabType err = sci.get("err");
                ScilabType tab = sci.get("tab");
                String [][] table = t.getFormTable(tab.toString(), tab.getWidth(), tab.getHeight());
                setTableValues(table);
                lblRoot.setText(t.getFormNumber(result.toString()));
                lblError.setText(t.getFormNumber(err.toString()));
                Config.setLastResult(lblRoot.getText());
            } catch (JavasciException ex) {
                System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
            }
        }
    }

    private void setTableValues(String[][] table) {
        ObservableList<RootIteration> values = FXCollections.observableArrayList();
        int j = 0;
        for (int i = 0; i < table.length; i++) {
            values.add(new RootIteration(table[i]));
        }
        for (String prop: new String[]{"n", "xn", "xn1", "error"}) {
            TableColumn col = tvRoot.getColumns().get(j);
            col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>(prop));
            j++;
        }
        tvRoot.setItems(values);
    }
    
    @FXML
    private void clear(ActionEvent event){
        lblRoot.setText("");
        lblError.setText("");
        tfNum.setText("");
        tvRoot.getItems().clear();
    } 
}

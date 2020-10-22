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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metodosnumericos.tools.Config;
import metodosnumericos.tools.RootIteration;
import metodosnumericos.tools.Tool;
import metodosnumericos.tools.UpgradeEulerIteration;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLUpgradeEulerController implements Initializable {

    @FXML
    private TextField tfFunc;
    @FXML
    private TextField tfY;
    @FXML
    private TextField tfX;
    @FXML
    private TextField tfH;
    @FXML
    private TextField tfNumT;
    @FXML
    private TableView<UpgradeEulerIteration> tvTable;
    
    

    private final Tool t = new Tool();
    private Scilab sci;
    private final File euler = new File(Config.getFolder()+"euler.sce");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(euler);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/euler.sce no encontrado \n"+ex.getMessage());
        }
    }   

    @FXML
    private void calc(ActionEvent event) {
        if(tfFunc.getText().isEmpty()||tfY.getText().isEmpty()||tfX.getText().isEmpty()||tfH.getText().isEmpty()||tfNumT.getText().isEmpty()){
            OptionPane.showMessageDialog(null, "Ningún campo puede estar en blanco");
        }else if(!t.isANumber(tfY.getText())||!t.isANumber(tfX.getText())||!t.isANumber(tfH.getText())||!t.isANumber(tfNumT.getText())){
            OptionPane.showMessageDialog(null, "Algún parametro no es un número valido");
        }else{
            try{
                // function datos = euler(fxy, x1, y1, h, n)
                if(sci.exec("function [z]=f(x, y), z="+tfFunc.getText()+", endfunction")){
                    sci.exec("tab = eulerMejorado(f, "+tfX.getText().trim()+","+tfY.getText().trim()+", "+tfH.getText().trim()+", "+(int)Double.parseDouble(tfNumT.getText())+")");
                    ScilabType tab = sci.get("tab");
                    String [][] table = t.getFormTable(tab.toString(), tab.getWidth(), tab.getHeight());
                    setTableValues(table);
                    Config.setLastResult(t.joinMatrix(table));
                }
                else{
                    OptionPane.showMessageDialog(null,"Las función no está escrita con el patron correcto\n(ejemplo: 2*x + 3*x - 5*x");
                }
            }
            catch (JavasciException ex) {
                System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
            }
        }
    }
    
    @FXML
    private void clean(ActionEvent event) {
        tfFunc.setText("");
        tfY.setText("");
        tfX.setText("");
        tfH.setText("");
        tfNumT.setText("");
        tvTable.getItems().clear();
    }
    
    private void setTableValues(String[][] table) {
        ObservableList<UpgradeEulerIteration> values = FXCollections.observableArrayList();
        int j = 0;
        for (int i = 0; i < table.length; i++) {
            values.add(new UpgradeEulerIteration(table[i]));
        }
        for (String prop: new String[]{"n", "xn", "yn", "hfxy", "yp", "fxyp", "prom", "yc"}) {
            TableColumn col = tvTable.getColumns().get(j);
            col.setCellValueFactory(new PropertyValueFactory<RootIteration, String>(prop));
            j++;
        }
        tvTable.setItems(values);
    }
    
}

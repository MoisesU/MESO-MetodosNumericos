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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import metodosnumericos.tools.Config;
import metodosnumericos.tools.Item;
import metodosnumericos.tools.Tool;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

/**
 * FXML Controller class
 *
 * @author MoisésUlises
 */
public class FXMLUniformPartitionController implements Initializable {

    @FXML
    private TextField tfLimB;
    @FXML
    private Label lbResult;
    @FXML
    private TextField tfLimA;
    @FXML
    private TextField tfSquares;
    @FXML
    private TableView<Item> tvRoot;
    @FXML
    private TextField tfFunc;

     /**
     * Initializes the controller class.
     */
    private final Tool t = new Tool();
    private Scilab sci;
    private final File particionUniforme = new File(Config.getFolder()+"particionUniforme.sce");
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(particionUniforme);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/particionUniforme.sce no encontrado \n"+ex.getMessage());
        }
    }    

    @FXML
    private void calc(ActionEvent event) {
        if(tfLimA.getText().equals("")||tfLimB.getText().equals("")||tfFunc.getText().equals("")||tfSquares.getText().equals("")){
            OptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
        }else if(!t.isANumber(tfLimA.getText())||!t.isANumber(tfLimB.getText())||!t.isANumber(tfSquares.getText())){
            OptionPane.showMessageDialog(null, "Los limites deben ser números reales");
        }else if(Double.parseDouble(tfLimA.getText())>Double.parseDouble(tfLimB.getText())){
            OptionPane.showMessageDialog(null, "El limite superior debe ser mayor que el inferior");
        }else{
            try{
                // function [res, tab] = particionUniforme(f, a, b, rect)
                int r = Integer.parseInt(tfSquares.getText());
                if(sci.exec("function [y]=f(x), y="+tfFunc.getText()+", endfunction")){
                    sci.exec("[result, tab] = particionUniforme(f, "+tfLimA.getText()+","+tfLimB.getText()+", "+r+")");
                    ScilabType tab = sci.get("tab");
                    String [][] table = t.getFormMatrix(tab.toString(), tab.getWidth(), tab.getHeight());
                    t.fillTableView(tvRoot, table);
                    ScilabType result = sci.get("result");
                    lbResult.setText(t.getFormNumber(result.toString()));
                    Config.setLastResult(lbResult.getText());
                    
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
        tfLimB.setText("");
        lbResult.setText("");
        tfLimA.setText("");
        tfSquares.setText("");
        tfFunc.setText("");
        tvRoot.getItems().clear();
    }
}

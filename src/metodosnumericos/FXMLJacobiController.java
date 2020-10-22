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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class FXMLJacobiController implements Initializable {

    @FXML
    private TextArea taInput;
    @FXML
    private TableView<Item> tvA;
    @FXML
    private TableView<Item> tvB;
    @FXML
    private TableView<Item> tvX;
    
    

    /**
     * Initializes the controller class.
     */
    
    private final Tool t = new Tool();
    private Scilab sci;
    private final File jacobi = new File(Config.getFolder()+"jacobi.sce");
    private final String pattern = "((\\-| |)(\\d*(\\.\\d+|)(( |)\\*( |)|)|)[a-z]\\d*( |))((| )(\\-|\\+)(| )(\\d*(\\.\\d+|)(( |)\\*( |)|)|)[a-z]\\d*( |))*=(| )(\\- |\\-|)(\\d*\\.\\d+|\\d+)";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sci = Config.getScilab();
            sci.exec(jacobi);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: archivo de scripts/Jacobi.sce no encontrado \n"+ex.getMessage());
        }
    }    

    @FXML
    private void calc(ActionEvent event) {
        boolean check = true;
        String[] ecuations = null;
        if(taInput.getText().equals("")){
            OptionPane.showMessageDialog(null,"El campo de ecuaciones no ha sido llenado");
            check = false;
        }
        else{
            ecuations = t.getEcuations(taInput.getText().trim());
            int i=1;
            for (String ecu: ecuations) {
                if(!ecu.matches(pattern)){
                    //System.err.println("Las ecuación "+i+" no está escrita con el patron correcto\n(eg. 2*a + 3*b - 5*c = 9)");
                    OptionPane.showMessageDialog(null,"Las ecuación "+i+" no está escrita con el patron correcto\n(eg. 2*a + 3*b - 5*c = 9)");
                    check = false;
                }   
                i++;
            }
        }
        if(check){
            String[][] terms = t.getTerms(ecuations);
            System.out.println("Pasé el chequeo");
            for (String[] aux: terms) {
                for (String aux2: aux) {
                    if(aux2.equals(""))
                        check = false;
                }
            }
            if(!check){
                OptionPane.showMessageDialog(null,"La ecuacion no está completa\nSi existe algún valor en 0 deberá agregarlo explicitamente\n"
                        + "(eg. 2*a + 0*b - 5*c = 9");
            }
            else{
                //function x = jacobi(A, B);
                double[][] a = t.getMatrixA(terms);
                double[] b = t.getMatrixB(terms);
                t.fillTableView(tvA, t.matrixDoubleToString(a));
                t.fillTableView(tvB, t.arrayDoubleToString(b));
                try{
                    sci.exec("A = "+t.getScilabMatrix(a));
                    sci.exec("B = "+t.getScilabMatrixColumn(b));
                    sci.exec("X = jacobi(A,B, "+Config.getFix()+")");
                    ScilabType tab = sci.get("X");
                    String [][] table = {{"indef"},{"indef"},{"indef"}};
                    try{
                        table = t.getFormMatrix(tab.toString(), tab.getWidth(), tab.getHeight());
                    }catch(Exception e){
                        System.err.println("El resultado no puede ser mostrado por:" + e.getMessage());
                        OptionPane.showMessageDialog(null, "El resultado no se puede mostrar en tabla, pero scilab arrojó esto:\n"+tab);
                        Config.setLastResult(tab.toString());
                    }
                    t.fillTableView(tvX, table);
                    Config.setLastResult(t.joinMatrix(table));
                } catch (JavasciException ex) {
                    System.err.println("Error: al obtener una variable de scilab \n"+ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void clean(ActionEvent event) {
        taInput.setText("");
        tvA.getItems().clear();
        tvB.getItems().clear();
        tvX.getItems().clear();
    }
    
}

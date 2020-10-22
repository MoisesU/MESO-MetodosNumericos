/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos.tools;

import metodosnumericos.OptionPane;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;

/**
 *
 * @author MoisésUlises
 */
public class Config {
    private static int maxIteration = 8;
    private static String lastResult = "";
    private static int fix = 5;
    private static final String FOLDER = "C:\\Program Files\\MESO+\\srcipts\\";
    private static final String PDF = "C:\\Program Files\\MESO+\\manual\\";
    private static Scilab sci;
    private static boolean InterpolMethod = true;
    
    
    public static void setScilab(){
        try {
            sci = new Scilab();
            sci.open();
        } catch (JavasciException.InitializationException ex) {
            System.err.println("Error: al instanciar Scilab\n"+ex.getMessage());
        } catch (JavasciException ex) {
            System.err.println("Error: al abrir Scilab\n"+ex.getMessage());
            OptionPane.showMessageDialog(null, "Error al intentar abrir scilab \n Es posible que no se haya configurado la variable de entorno \n por favor consulte el manual de usuario");
        } catch(Exception e){
            OptionPane.showMessageDialog(null, "Es posible que no se haya configurado la variable de entorno \n por favor consulte el manual de usuario");
        }
    }
    
    public static Scilab getScilab(){
        return sci;
    }

    public static int getMaxIteration() {
        return maxIteration;
    }

    public static void setMaxIteration(int maxIteration) {
        Config.maxIteration = maxIteration;
    }

    public static int getFix() {
        return fix;
    }

    public static void setFix(int fix) {
        Config.fix = fix;
    }

    public static String getFolder() {
        return FOLDER;
    }
    public static boolean isInterpolMethod() {
        return InterpolMethod;
    }

    public static void setInterpolMethod(boolean InterpolMethod) {
        Config.InterpolMethod = InterpolMethod;
    }

    public static void closeScilab() {
        sci.close();
    }

    public static String getLastResult() {
        return lastResult;
    }

    public static void setLastResult(String lastResult) {
        Config.lastResult = lastResult;
    }


    public static String getPDF() {
        return PDF;
    }
    
    
    
}

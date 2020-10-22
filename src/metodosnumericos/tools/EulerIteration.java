/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos.tools;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MoisésUlises
 */
public class EulerIteration {
    private final SimpleStringProperty n;
    private final SimpleStringProperty xn;
    private final SimpleStringProperty yn;
    private final SimpleStringProperty  fxy;
    private final SimpleStringProperty hfxy;
    private final SimpleStringProperty yn1;

    public EulerIteration(String n, String xn, String yn, String fxy, String hfxy, String yn1) {
        this.n = new SimpleStringProperty(n);
        this.xn = new SimpleStringProperty(xn);
        this.yn = new SimpleStringProperty(yn);
        this.fxy = new SimpleStringProperty(fxy);
        this.hfxy = new SimpleStringProperty(hfxy);
        this.yn1 = new SimpleStringProperty(yn1);
    }
    
    public EulerIteration(String [] values) {
        this.n = new SimpleStringProperty(values[0]);
        this.xn = new SimpleStringProperty(values[1]);
        this.yn = new SimpleStringProperty(values[2]);
        this.fxy = new SimpleStringProperty(values[3]);
        this.hfxy = new SimpleStringProperty(values[4]);
        this.yn1 = new SimpleStringProperty(values[5]);
    }

    public String getN() {
        return n.get();
    }

    public String getXn() {
        return xn.get();
    }

    public String getYn() {
        return yn.get();
    }

    public String getFxy() {
        return fxy.get();
    }

    public String getHfxy() {
        return hfxy.get();
    }

    public String getYn1() {
        return yn1.get();
    }
    
    
}

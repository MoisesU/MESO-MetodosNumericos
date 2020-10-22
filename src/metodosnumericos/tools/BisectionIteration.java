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
public class BisectionIteration {
    
    private final SimpleStringProperty n;
    private final SimpleStringProperty a;
    private final SimpleStringProperty xm;
    private final SimpleStringProperty b;
    private final SimpleStringProperty fa;
    private final SimpleStringProperty fb;
    private final SimpleStringProperty fxm;
    private final SimpleStringProperty error;
    
    public BisectionIteration(String[] row) {
        this.n = new SimpleStringProperty(row[0]);
        this.a = new SimpleStringProperty(row[1]);
        this.xm = new SimpleStringProperty(row[2]);
        this.b = new SimpleStringProperty(row[3]);
        this.fa = new SimpleStringProperty(row[4]);
        this.fb = new SimpleStringProperty(row[5]);
        this.fxm = new SimpleStringProperty(row[6]);
        this.error = new SimpleStringProperty(row[7]);
    }

    public BisectionIteration(String n, String a, String xm, String b, String fa, String fb, String fxm, String error) {
        this.n = new SimpleStringProperty(n);
        this.a = new SimpleStringProperty(a);
        this.xm = new SimpleStringProperty(xm);
        this.b = new SimpleStringProperty(b);
        this.fa = new SimpleStringProperty(fa);
        this.fb = new SimpleStringProperty(fb);
        this.fxm = new SimpleStringProperty(fxm);
        this.error = new SimpleStringProperty(error);
    }

    public String getN() {
        return n.get();
    }

    public String getA() {
        return a.get();
    }

    public String getXm() {
        return xm.get();
    }

    public String getB() {
        return b.get();
    }

    public String getFa() {
        return fa.get();
    }

    public String getFb() {
        return fb.get();
    }

    public String getFxm() {
        return fxm.get();
    }

    public String getError() {
        return error.get();
    }
    
}

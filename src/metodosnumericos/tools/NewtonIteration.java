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
public class NewtonIteration {
    private final SimpleStringProperty n;
    private final SimpleStringProperty xn;
    private final SimpleStringProperty fxn;
    private final SimpleStringProperty dxn;
    private final SimpleStringProperty df;
    private final SimpleStringProperty xn1;
    private final SimpleStringProperty err;

    public NewtonIteration(String n, String xn, String fxn, String dxn, String df, String xn1, String err) {
        this.n = new SimpleStringProperty(n);
        this.xn = new SimpleStringProperty(xn);
        this.fxn = new SimpleStringProperty(fxn);
        this.dxn = new SimpleStringProperty(dxn);
        this.df = new SimpleStringProperty(df);
        this.xn1 = new SimpleStringProperty(xn1);
        this.err = new SimpleStringProperty(err);
    }
    
    public NewtonIteration(String [] row) {
        this.n = new SimpleStringProperty(row[0]);
        this.xn = new SimpleStringProperty(row[1]);
        this.fxn = new SimpleStringProperty(row[2]);
        this.dxn = new SimpleStringProperty(row[3]);
        this.df = new SimpleStringProperty(row[4]);
        this.xn1 = new SimpleStringProperty(row[5]);
        this.err = new SimpleStringProperty(row[6]);
    }

    public String getN() {
        return n.get();
    }

    public String getXn() {
        return xn.get();
    }

    public String getFxn() {
        return fxn.get();
    }

    public String getDxn() {
        return dxn.get();
    }

    public String getDf() {
        return df.get();
    }

    public String getXn1() {
        return xn1.get();
    }

    public String getErr() {
        return err.get();
    }
    
    
}

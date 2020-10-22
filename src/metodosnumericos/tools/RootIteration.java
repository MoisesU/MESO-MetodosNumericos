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
public class RootIteration {
    private final SimpleStringProperty n;
    private final SimpleStringProperty xn;
    private final SimpleStringProperty xn1;
    private final SimpleStringProperty error;

    public RootIteration(String n, String xn, String xn1, String error) {
        this.n = new SimpleStringProperty(n);
        this.xn = new SimpleStringProperty(xn);
        this.xn1 = new SimpleStringProperty(xn1);
        this.error = new SimpleStringProperty(xn1);
    }
    public RootIteration(String [] row) {
        this.n = new SimpleStringProperty(row[0]);
        this.xn = new SimpleStringProperty(row[1]);
        this.xn1 = new SimpleStringProperty(row[2]);
        this.error = new SimpleStringProperty(row[3]);
    }

    public String getN() {
        return n.get();
    }

    public String getXn() {
        return xn.get();
    }

    public String getXn1() {
        return xn1.get();
    }

    public String getError() {
        return error.get();
    }
}

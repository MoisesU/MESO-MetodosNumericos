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
public class TabFunctionPoint {
    SimpleStringProperty x;
    SimpleStringProperty y;

    public TabFunctionPoint(String x, String y) {
        this.x = new SimpleStringProperty(x);
        this.y = new SimpleStringProperty(y);
    }
    
    public TabFunctionPoint(String [] point) {
        this.x = new SimpleStringProperty(point[0]);
        this.y = new SimpleStringProperty(point[1]);;
    }
    
    public String getX() {
        return x.get();
    }

    public void setX(String x) {
        this.x.set(x);
    }

    public String getY() {
        return y.get();
    }

    public void setY(String y) {
        this.y.set(y);
    }
    
}

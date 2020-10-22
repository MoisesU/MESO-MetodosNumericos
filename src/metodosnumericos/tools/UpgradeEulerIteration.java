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
public class UpgradeEulerIteration {
    private final SimpleStringProperty n;
    private final SimpleStringProperty xn;
    private final SimpleStringProperty yn;
    private final SimpleStringProperty  hfxy;
    private final SimpleStringProperty yp;
    private final SimpleStringProperty fxyp;
    private final SimpleStringProperty prom;
    private final SimpleStringProperty yc;

    public UpgradeEulerIteration(String n, String xn, String yn, String hfxy, String yp, String fxyp, String prom, String yc) {
        this.n = new SimpleStringProperty(n);
        this.xn = new SimpleStringProperty(xn);
        this.yn = new SimpleStringProperty(yn);
        this.hfxy = new SimpleStringProperty(hfxy);
        this.yp = new SimpleStringProperty(yp);
        this.fxyp = new SimpleStringProperty(fxyp);
        this.prom = new SimpleStringProperty(prom);
        this.yc = new SimpleStringProperty(yc);
    }
    
    public UpgradeEulerIteration(String [] values) {
        this.n = new SimpleStringProperty(values[0]);
        this.xn = new SimpleStringProperty(values[1]);
        this.yn = new SimpleStringProperty(values[2]);
        this.hfxy = new SimpleStringProperty(values[3]);
        this.yp = new SimpleStringProperty(values[4]);
        this.fxyp = new SimpleStringProperty(values[5]);
        this.prom = new SimpleStringProperty(values[6]);
        this.yc = new SimpleStringProperty(values[7]);
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

    public String getHfxy() {
        return hfxy.get();
    }

    public String getYp() {
        return yp.get();
    }
    
    public String getFxyp() {
        return fxyp.get();
    }
    
    public String getYc() {
        return yc.get();
    }
    
    public String getProm() {
        return prom.get();
    }
    
    
}

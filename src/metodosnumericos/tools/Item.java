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
public class Item{
    private final SimpleStringProperty[] info;

    public Item(String... value) {
        this.info = new SimpleStringProperty[value.length];
        for (int i = 0; i < info.length; i++) {
            this.info[i] = new SimpleStringProperty(value[i]);
        }
    }
    public String getValue(int i) {
        return info[i].get();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < info.length; i++) {
            s+=info[i].get()+" \t";
        }
        return "Item: "+s;
    }

}
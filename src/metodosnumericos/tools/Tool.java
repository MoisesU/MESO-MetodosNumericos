/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos.tools;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author MoisésUlises
 */
public class Tool {
    
    public String[][] getTable(String matrix, int w, int h){
        String[][] result = new String[h][w];
        initMatrix(result, "");
        int x = 0;
        int y = 0;
        for(char c: matrix.toCharArray()){
            if(Character.isDigit(c) || c == '.' || c == '-' || c == 'e')
                result[x][y]+= c;
            else if(c == ',')
                y++;
            else if(c == ';'){
                x++;
                y=0;
            }
        }
        return result;
    }
    public void initMatrix(String[][] matrix, String value){
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = value;
            }
        }
    }
    public void viewMatrix(String[][] matriz){
        for (int x=0; x < matriz.length; x++) {
            System.out.print("|");
            for (int y=0; y < matriz[x].length; y++) {
                System.out.print (matriz[x][y]);
                if (y!=matriz[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }
    public String joinMatrix(String[][] matriz){
        String res = "";
        for (int x=0; x < matriz.length; x++) {
            for (int y=0; y < matriz[x].length; y++) {
                res+=matriz[x][y];
                if (y!=matriz[x].length-1) res+="\t";
            }
            res+="\n";
        }
        return res;
    }
    public boolean isANumber(String s){
        try { 
            double num = Double.parseDouble(s);
        }
        catch (Exception e){ 
            return false;
        } 
        return true; 
    }
    public double[] getInterv(String s){
        String [] interv = {"",""};
        int i = 0;
        for(char x:s.toCharArray()){
            if(Character.isDigit(x)||x=='.'||x=='-')
                interv[i]+=x;
            if(x == ',')
                i++;
        }
        return new double[]{Double.parseDouble(interv[0]), Double.parseDouble(interv[1])};
    }
    public String getFormNumber(String num){
        return String.format("%."+Config.getFix()+"f", Double.parseDouble(num.substring(1,num.length()-1)));
    }
    public String[][] getFormTable(String table, int w, int h){
        String[][] formTable = getTable(table, w, h);
        for (int i = 0; i < formTable.length; i++) {
            formTable[i][0] = Integer.toString((int)Double.parseDouble(formTable[i][0]));
        }
        for (int i = 0; i < formTable.length; i++) {
            for (int j = 1; j < formTable[i].length; j++) {
                formTable[i][j] = String.format("%."+Config.getFix()+"f", Double.parseDouble(changeExp(formTable[i][j])));
            }
        }
        return formTable;
    }
    public String[][] getFormMatrix(String table, int w, int h){
        String[][] formTable = getTable(table, w, h);
        for (int i = 0; i < formTable.length; i++) {
            for (int j = 0; j < formTable[i].length; j++) {
                formTable[i][j] = String.format("%."+Config.getFix()+"f", Double.parseDouble(changeExp(formTable[i][j])));
            }
        }
        return formTable;
    }
    public String changeExp(String num){
        String aux = num.charAt(0)=='-'?"-":""; 
        for (int i = aux.length(); i < num.length(); i++) {
                aux+=num.charAt(i)=='-'?"e"+num.charAt(i):num.charAt(i);
        }
        return aux;
    }
    public String[][] getTerms(String[] ecu) {
        String[][] terms = new String[ecu.length][ecu.length+1];
        initMatrix(terms, "");
        for (int i = 0; i < ecu.length; i++) {
            int x = -1;
            ecu[i] = ecu[i].charAt(0)=='-'?ecu[i]:'+'+ecu[i];
            for (int j = 0; j < ecu[i].length(); j++) {
                if(ecu[i].charAt(j)=='-'||ecu[i].charAt(j)=='+'){
                    x++;
                }
                if(ecu[i].charAt(j)=='='){
                    x++;
                    if(ecu[i].charAt(j+1)!='-'){
                        if(ecu[i].charAt(j+1)==' '){
                            if(ecu[i].charAt(j+2)=='-'){
                                terms[i][x]+='-';
                                j+=2;
                            }
                        }
                    }
                    else{
                        terms[i][x]+='-';
                        j++;
                    }
                }
                else if(ecu[i].charAt(j)!=' '){
                    terms[i][x]+=ecu[i].charAt(j);
                }
            }
        }
        return terms;
    }
    public String[] getEcuations(String m){
        List<String> ecu = new ArrayList<>();
        String [] ecuations = {};
        String aux = "";
        for (char x: m.toCharArray()) {
            if(x == '\n'){
                ecu.add(aux);
                aux = "";
            }
            else
                aux+=x;
        }
        ecu.add(aux);
        return ecu.toArray(ecuations);
    }
    public double[][] getMatrixA(String[][] terms){
        double[][] matrix = new double[terms.length][terms.length];
        for (int i = 0; i < terms.length; i++) {
            for (int j = 0; j < terms[0].length-1; j++) {
                if(isANumber(terms[i][j])){
                    matrix[i][j]=Double.parseDouble(terms[i][j]);
                }
                else{
                    double[] t = {1, 0};
                    String aux = "";
                    if(terms[i][j].equals("")){
                        t[0] = 0;
                        t[1] = j+1;
                    } 
                    else{
                        for(char c: terms[i][j].toCharArray()){
                            if(Character.isDigit(c)||c=='.'||c=='-')
                                aux+=c;
                            if(Character.isAlphabetic(c)){
                                if(!aux.equals(""))
                                    t[0]=aux.equals("-")?-1:Double.parseDouble(aux);
                                aux = "";
                            }
                        }
                        if(!aux.equals(""))
                            t[1] = Double.parseDouble(aux);
                    }
                    matrix[i][j]=t[0];
                }
            }
        }
        return matrix;
    }
    public double[] getMatrixB(String[][] terms){
        double[] matrix = new double[terms.length];
        for (int i = 0; i < terms.length; i++) {
            matrix[i] = Double.parseDouble(terms[i][terms[0].length-1]);
        }
        return matrix;
    }
    public String getScilabMatrix(double[][] nums){
        String matrix = "[";
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                matrix+=nums[i][j]+" ";
            }
            if(i!=nums.length-1)
                matrix+=";";
        }
        return matrix + "]";
    }
    public String getScilabMatrixColumn(double[] nums){
        String matrix = "[";
        for (int i = 0; i < nums.length-1; i++) {
            matrix+=nums[i]+";";
        }
        return matrix + nums[nums.length-1] + "]";
    }
    public String getScilabMatrixRow(double[] nums){
        String matrix = "[";
        for (int i = 0; i < nums.length-1; i++) {
            matrix+=nums[i]+" ";
        }
        return matrix + "]";
    }
    public void fillTableView(TableView tabla, String[][]content){
        double size = tabla.getWidth()/content[0].length;
        ObservableList <Item> values = FXCollections.observableArrayList();
        for (int i = 0; i < content[0].length; i++) {
            final int x = i;
            TableColumn col = new TableColumn("h"+(i+1));
            col.setPrefWidth(size);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                    return new SimpleObjectProperty<String>(param.getValue().getValue(x));
                }
            });
            tabla.getColumns().add(col);
        }
        for (int i = 0; i < content.length; i++) {
            values.add(new Item(content[i]));
        }
        tabla.setItems(values);
    }
    public void fillTableView(TableView tabla, String[]content){
        double size = tabla.getWidth();
        ObservableList <Item> values = FXCollections.observableArrayList();
        final int x = 0;
        TableColumn col = new TableColumn();
        col.setPrefWidth(size);
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new SimpleObjectProperty<String>(param.getValue().getValue(x));
            }
        });
        tabla.getColumns().add(col);
        for (int i = 0; i < content.length; i++) {
            values.add(new Item(content[i]));
        }
        tabla.setItems(values);
    }
    public String [][] matrixDoubleToString(double[][]content){
        String [][] res = new String[content.length][content[0].length];
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                res[i][j] = Double.toString(content[i][j]);
            }
        }
        return res;
    }
    public String [] arrayDoubleToString(double[] content){
        String [] res = new String[content.length];
        for (int i = 0; i < content.length; i++) {
            res[i] = Double.toString(content[i]);
        }
        return res;
    }

    public String getFunction(String[][] coef) {
        int s = coef.length;
        String func = coef[s-1][0] + "*x^"+(s-1);
        for (int i = s-2; i > 0; i--) {
            func+=(coef[i][0].charAt(0)=='-'?coef[i][0]:"+"+coef[i][0]) + "*x"+(i==1?"":"^"+i); 
        }
        return func + (coef[0][0].charAt(0)=='-'?coef[0][0]:"+"+coef[0][0]);
    }
    
    public boolean isEvenNumber(int x){
        return x%2==0;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MoisésUlises
 */
public class OptionPane extends AnchorPane{
    
    private Label msg;
    private Button btn, btnC;
    private TextField tf;
    private ImageView img;
    private final String iconDir;
   
    
    public OptionPane(String image, String txt){
        this.setMaxHeight(USE_PREF_SIZE);
        this.setMaxWidth(USE_PREF_SIZE);
        this.setPrefSize(336, 164);
        this.getStyleClass().add("background");
        this.getStylesheets().add(getClass().getResource("css/style.css").toString());
        msg = new Label(txt);
        msg.setLayoutX(75);
        msg.setLayoutY(30);
        msg.setPrefSize(251, 81);
        msg.getStyleClass().add("label-option");
        btn = new Button("Aceptar");
        btn.setLayoutX(131);
        btn.setLayoutY(111);
        btn.setMnemonicParsing(false);
        img = new ImageView();
        img.setLayoutX(13);
        img.setLayoutY(34);
        img.setFitHeight(81);
        img.setFitWidth(59);
        img.setPreserveRatio(true);
        String name = image!=null?image:"adv.png";
        img.setImage(new Image(getClass().getResource("rec/"+name).toString()));
        this.getChildren().addAll(msg, btn, img);
        msg.setWrapText(true);
        iconDir = "";
    }
    public OptionPane(String txt){
        this.setMaxHeight(USE_PREF_SIZE);
        this.setMaxWidth(USE_PREF_SIZE);
        this.setPrefSize(336, 164);
        this.getStyleClass().add("background");
        this.getStylesheets().add(getClass().getResource("css/style.css").toString());
        msg = new Label(txt);
        msg.setWrapText(true);
        msg.setLayoutX(84);
        msg.setLayoutY(34);
        msg.setPrefSize(233, 31);
        msg.getStyleClass().add("label-option");

        btn = new Button("Aceptar ");
        btn.setLayoutX(206);
        btn.setLayoutY(111);
        btn.setMnemonicParsing(false);
        
        btnC = new Button("Cancelar");
        btnC.setLayoutX(111);
        btnC.setLayoutY(111);
        btnC.setMnemonicParsing(false);
        
        tf = new TextField();
        tf.setLayoutX(84);
        tf.setLayoutY(69);
        tf.setPrefSize(233, 31);
        
        img = new ImageView();
        img.setLayoutX(13);
        img.setLayoutY(34);
        img.setFitHeight(81);
        img.setFitWidth(59);
        img.setPreserveRatio(true);
        img.setImage(new Image(getClass().getResource("rec/qst.png").toString()));
        this.getChildren().addAll(msg, btn, img, tf, btnC);
        
        iconDir = "";
    }

    public TextField getTf() {
        return tf;
    }
    
    public OptionPane(){
         iconDir = getClass().getResource("rec/icon.png").toString();
    }
    
    public static String getIcon(){
        return new OptionPane().iconDir;
    }
    
    public static void showMessageDialog(String img, String text){
        Stage stage = new Stage();
        OptionPane p = new OptionPane(img, text);
        Scene scene = new Scene(p);
        p.getBtn().setOnAction((ActionEvent event)->{
            stage.close();
        });
        stage.setScene(scene);
        stage.setMaxHeight(198);
        stage.setMaxWidth(350);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Error ");
        stage.getIcons().add(new Image(OptionPane.getIcon()));
        stage.show();
    }
    
    public static void showMessageDialog(String img, String text, String title){
        Stage stage = new Stage();
        OptionPane p = new OptionPane(img, text);
        Scene scene = new Scene(p);
        p.getBtn().setOnAction((ActionEvent event)->{
            stage.close();
        });
        stage.setScene(scene);
        stage.setMaxHeight(198);
        stage.setMaxWidth(350);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.getIcons().add(new Image(OptionPane.getIcon()));
        stage.show();
    }
    
     public static void showInputDialog(String text, String title, String value){
        Stage stage = new Stage();
        OptionPane p = new OptionPane(text);
        p.getBtn().setOnAction((ActionEvent event)->{
            var(value, p.getTf().getText());
            stage.close();
        });
        p.getBtnC().setOnAction((ActionEvent event)->{
            stage.close();
        });
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setMaxHeight(188);
        stage.setMaxWidth(346);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.getIcons().add(new Image(OptionPane.getIcon()));
        stage.show();
    }

    private static void var(String s, String s2) {
        s = s2;
    }
    
    public Label getMsg() {
        return msg;
    }

    public Button getBtn() {
        return btn;
    }

    public Button getBtnC() {
        return btnC;
    }

    public ImageView getImg() {
        return img;
    }

    public String getIconDir() {
        return iconDir;
    }
    
    
}

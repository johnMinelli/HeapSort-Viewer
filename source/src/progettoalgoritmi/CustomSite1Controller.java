/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Larix
 */
public class CustomSite1Controller implements Initializable {

    Random rand = new Random();

    @FXML
    private TextField txt_custom_size;

    @FXML
    private AnchorPane internal_pane;

    @FXML
    private Button generate_button;
    
    @FXML
    private AnchorPane customPane;
    
    @FXML
    private Text error_txt;
    
    int size;
    TextField txt[];
    int myarray[];
    
   public boolean validate(){
        if (!txt_custom_size.getText().isEmpty()) {
            if(txt_custom_size.getText().matches("[0-9]+")){
                if (Double.parseDouble(txt_custom_size.getText())%1==0) {
                    if ((Integer.parseInt(txt_custom_size.getText())<=15)&&(Integer.parseInt(txt_custom_size.getText())>=1)) {
                        return true; 
                    }
                }
           
            }
        }
        return false;
    }
    @FXML
    void save_custom_size(ActionEvent event) {
        internal_pane.getChildren().clear();
        if (validate()) {
            generate_button.setDisable(false);
            size = Integer.parseInt(txt_custom_size.getText());
            txt = new TextField[size];
            myarray = new int[size];
            int x = 0;
            int y = 0;
            for (int i = 0; i < myarray.length; i++) {
                txt[i] = new TextField(String.valueOf(myarray[i]));
                txt[i].setPrefWidth(50);
                txt[i].relocate(x, y);
                x = x + 50;
                if (x % 500 == 0) {
                    y = y + 30;
                    x = 0;
                }
            }
            for (int i = 0; i < myarray.length; i++) {
                internal_pane.getChildren().add(txt[i]);
            }
        }else{
            error_txt.setVisible(true);
        }
    }

    @FXML
    void genera_custom_array(ActionEvent event) throws IOException {

        for (int i = 0; i < myarray.length; i++) {
            myarray[i] = Integer.parseInt(txt[i].getText());
        }
        for (int i = 0; i < myarray.length; i++) {
            internal_pane.getChildren().remove(txt[i]);
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < myarray.length; i++) {
            txt[i] = new TextField(String.valueOf(myarray[i]));
            txt[i].setPrefWidth(50);
            txt[i].relocate(x, y);
            txt[i].setDisable(true);
            x = x + 50;
            if (x % 500 == 0) {
                y = y + 30;
                x = 0;
            }
        }
        for (int i = 0; i < myarray.length; i++) {
            internal_pane.getChildren().add(txt[i]);
        }
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("AlgoTypeSelection.fxml"));
        customPane.getScene().setRoot(custom_pane);
        custom_pane.getScene().setUserData(myarray);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

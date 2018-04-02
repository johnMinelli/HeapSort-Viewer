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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Larix
 */
public class RandomSite1Controller implements Initializable {

    Random rand = new Random();

    @FXML
    private TextField txt_input_size;

    //@FXML
    //private TextArea text_area_array;
    @FXML
    private TableView<Integer> tabella;
    
    @FXML
    private Text error_txt;
    
    @FXML
    private AnchorPane anc_lbl;
    
    @FXML
    private AnchorPane randomPane;
    
    @FXML
    private Button btn_confirm_size;
    
    @FXML
    private Button next_btn;
    
    int[] myarray;
    
    @FXML
    void see_array(ActionEvent event) throws IOException {
        anc_lbl.getChildren().clear();
        if (validate()) {
            next_btn.setDisable(false);
            int size = Integer.parseInt(txt_input_size.getText());
            //Label lbs[] = new Label[size];
            TextField txt[] = new TextField[size];
            myarray = new int[size];
            for (int i = 0; i < myarray.length; i++) {
                myarray[i] = rand.nextInt(100);
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
                anc_lbl.getChildren().add(txt[i]);
            }
        }else{
            error_txt.setVisible(true);
        }
        

    }
    
     @FXML
    void goToSelection(ActionEvent event) throws IOException{
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("AlgoTypeSelection.fxml"));
        randomPane.getScene().setRoot(custom_pane);
        custom_pane.getScene().setUserData(myarray);
        //randomPane.getChildren().setAll(custom_pane);
    }
    
    public boolean validate(){
        if (!txt_input_size.getText().isEmpty()) {
            if(txt_input_size.getText().matches("[0-9]+")){
                if (Double.parseDouble(txt_input_size.getText())%1==0) {
                    if ((Integer.parseInt(txt_input_size.getText())<=15)&&(Integer.parseInt(txt_input_size.getText())>=1)) {
                        return true; 
                    }
                }
           
            }
        }
        return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

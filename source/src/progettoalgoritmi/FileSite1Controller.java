/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static java.nio.file.Files.size;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Larix
 */
public class FileSite1Controller implements Initializable {

    @FXML
    private Label is_valid_label;

    @FXML
    private Label is_valid_label_2;

    @FXML
    private AnchorPane file_pane;

    @FXML
    private Label vettore_label;
    
    @FXML
    private AnchorPane filePane;
    
    @FXML
    private Button next_btn;
    
    String[] myarray;
    
    int[] myRealArray;
    
    List<String> list;
    
    @FXML
    void file_chooser(ActionEvent event) throws IOException {
        is_valid_label.setText("");
        is_valid_label_2.setText("");
        file_pane.getChildren().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            //lettura da file funziona solo se i numeri sono organizzati in colonna
            list = Files.readAllLines(Paths.get(selectedFile.toPath().toString()), StandardCharsets.UTF_8);
            myarray = list.toArray(new String[list.size()]);  
            if (validate()) {
                      
                TextField txt[] = new TextField[list.size()];
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
                    file_pane.getChildren().add(txt[i]);
                }
                Files.lines(selectedFile.toPath()).forEach(System.out::println);
                is_valid_label.setText("File selected: " + selectedFile.getName());
                is_valid_label_2.setTextFill(Paint.valueOf("#27ae60"));
                is_valid_label_2.setText("Il file è valido!");
                vettore_label.setText("Ecco il vettore caricato:");
                next_btn.setDisable(false);
            }
            else {
            is_valid_label.setText("File selection cancelled.");
            is_valid_label_2.setTextFill(Paint.valueOf("#c0392b"));
            is_valid_label_2.setText("Il file NON è valido!");
            }
        }
     }
    
    @FXML
    void goToSelection(ActionEvent event) throws IOException {
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("AlgoTypeSelection.fxml"));
        filePane.getScene().setRoot(custom_pane);
        custom_pane.getScene().setUserData(myRealArray);
    }
    
    public boolean validate(){
        boolean flag=true;
        myRealArray = new int[myarray.length];
        if ((myarray.length<=15) && (myarray.length>=1)) {
            int i = 0;
            while ( (i < myarray.length) && (flag)) {
                if (!myarray[i].isEmpty()) {
                    if (myarray[i].matches("[0-9]+")) {
                        myRealArray[i]=Integer.parseInt(myarray[i]);
                    }else{
                        flag=false;
                    }
                }else{
                    flag=false;
                }
                i++;
            }
        }else{
           flag=false; 
        }
        return flag;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

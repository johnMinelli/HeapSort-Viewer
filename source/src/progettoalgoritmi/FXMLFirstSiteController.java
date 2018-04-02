/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Larix
 */
public class FXMLFirstSiteController implements Initializable {

    @FXML
    private AnchorPane random_anchorpane;

    @FXML
    private Button btn_random;

    @FXML
    private Button btn_file;

    @FXML
    private Button btn_custom;

    @FXML
    void go_to_custom(ActionEvent event) throws IOException {
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("CustomSite1.fxml"));
        random_anchorpane.getChildren().setAll(custom_pane);
    }

    @FXML
    void go_to_file(ActionEvent event) throws IOException {
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("FileSite1.fxml"));
        random_anchorpane.getChildren().setAll(custom_pane);
    }

    @FXML
    void go_to_random(ActionEvent event) throws IOException {
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("RandomSite1.fxml"));
        random_anchorpane.getScene().setRoot(custom_pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

}

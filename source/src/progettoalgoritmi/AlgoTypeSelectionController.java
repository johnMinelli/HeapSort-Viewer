/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author laric
 */
public class AlgoTypeSelectionController {

    
    
    
    @FXML
    private Button btn_motion;

    @FXML
    private Button btn_stepByStep;
    
    @FXML
    private AnchorPane chooserPane;
    
  
    
    @FXML
    private void goToStepByStep(ActionEvent event) throws IOException{
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("StepByStepAnimation.fxml"));
        chooserPane.getScene().setRoot(custom_pane);
    }
    @FXML
    private void goToMotion(ActionEvent event) throws IOException{
        AnchorPane custom_pane = FXMLLoader.load(getClass().getResource("MotionAnimation.fxml"));
        chooserPane.getScene().setRoot(custom_pane);
    }
    
    
}

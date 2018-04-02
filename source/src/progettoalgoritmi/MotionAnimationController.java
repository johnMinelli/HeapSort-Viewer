/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author laric
 */
public class MotionAnimationController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    int toSort[] = {30, 15, 16, 20, 14, 18, 80, 1, 0 , 34 , 22 , 99 , 123 , 546, 19 };
//    int toSort2[] = {30, 15, 16, 20, 14, 18, 80, 1, 0 , 34 , 22 , 99 , 123 , 546, 19 };
    int toSort[];
    int toSort2[];
    Cerchio swapTable[];

    boolean anim = false;
    boolean ja;
    String tempText="";
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private VBox vert;
        
    @FXML
    private HBox hor;
    
    @FXML
    private Group a;

    @FXML
    private Button start;
    
    @FXML
    private TextArea textAreaComments;

    @FXML
    private TextField duration;

    @FXML
    private Button restart;
    
    StackPane stkRect;
    StackPane stk;
    
    anima animator;
    
    
    @FXML
    private void restartButton(ActionEvent event) throws InterruptedException{
        toSort = (int[]) anchor.getScene().getUserData();
        animator = new anima(vert.getScene(),toSort,swapTable,this,true);
        double dur = getInputDuration();
        if (dur==1) {
            animator.setDurata(1);
            duration.setText("1");
        }else{
            animator.setDurata(dur);
        }            
        duration.setEditable(false);
        restart.setDisable(true);
        
        vert.getChildren().clear();
        hor.getChildren().clear();
        stk.getChildren().clear();
        stkRect.getChildren().clear();
        
        createThings();
        
        //old_sort old = new old_sort(toSort2);
        animator.run();
        
        
    }
    
    public double getInputDuration(){
        double dur;
        if (!duration.getText().isEmpty()) {
            dur = Double.parseDouble(duration.getText());
            if (Double.isNaN(dur)) {
                dur=1;
            }   
        }else{
            dur=1;
        }
        return dur;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws InterruptedException  {
        start.setDisable(true);
        restart.setDisable(true);
        
        toSort = (int[]) anchor.getScene().getUserData();
        swapTable = new Cerchio[toSort.length];
        createThings();
        animator = new anima(vert.getScene(), toSort, swapTable, this, true);
        if (!duration.getText().isEmpty()) {
            double dur = Double.parseDouble(duration.getText());
            if (!Double.isNaN(dur)) {
                animator.setDurata(dur);
            }
        }else{
            animator.setDurata(1);
            duration.setText("1");
        }            
        duration.setEditable(false);
        //old_sort old = new old_sort(toSort2);
        animator.run();
        
        
    }
    
    public void setCommentText(String text){
        textAreaComments.setText(text);
    }

    public String getCommentText() {
        String txt;
        txt = textAreaComments.getText();
        return txt;
    }
    
    public void disableButtons(){        
            duration.setEditable(true);
            restart.setDisable(false);
    }
    public void focusOnLastLine(){
         textAreaComments.requestFocus();   
         textAreaComments.end();
    }
    
    public void createThings(){
        textAreaComments.setText("");
        textAreaComments.setEditable(false);
        int times = 0;
        int lastRow = 0;
        int idInc = 0;
        //calcolo il numero di righe necessarie e quanti numeri ci sono nell'ultima riga
        while (Math.pow(2, times) <= toSort.length) {
            lastRow = toSort.length - (int) Math.pow(2, times);
            times++;
        }
        //le i sono le righe e le j sono le colonne
        for (int i = 0; i < times; i++) {
            GridPane grid = new GridPane();
            grid.setId("grid" + i);
            vert.getChildren().add(grid);
            grid.setPrefHeight(50);
            grid.setPrefWidth(800); 
            for (int j = 0; j < Math.pow(2, i); j++) {
                if ((i != times - 1) || (i == times - 1 && j <= lastRow)) {
                        //Creo i rettangoli e la struttura hor[stack(rect,testo)]
                    Rectangle rect = new Rectangle(hor.getPrefWidth()/toSort.length, 20,Color.LIGHTBLUE);
                    rect.setHeight(50);
                    Text textRect = new Text(toSort[idInc]+"");
                    textRect.setTextAlignment(TextAlignment.CENTER);
                    stkRect=new StackPane(rect,textRect);
                    stkRect.setMaxSize(0,0);
                    hor.getChildren().add(stkRect);
                        //Creo la struttura grid[stack(cerchio,testo)] che andra` dentro vert
                    Circle circ = new Circle(20, Color.LIGHTBLUE);
                    Text text = new Text(toSort[idInc]+"");
                    stk=new StackPane(circ,text);
                    //Tooltip.install(circ, new Tooltip(toSort2[idInc]+""));
                    stk.setMaxSize(0, 0);
                    grid.add(stk, j, 0);
                        //assegno larghezza alle colonne in base alla riga
                    ColumnConstraints column = new ColumnConstraints();
                    column.setPercentWidth(1 / Math.pow(2, i) * 100);
                    column.setHalignment(HPos.CENTER);
                    grid.getColumnConstraints().add(column);
                        //coordinate per la linea e per la swapTable
                    double endX = (1 / Math.pow(2, i) * grid.getPrefWidth()) * (j) + (1 / Math.pow(2, i) * grid.getPrefWidth() / 2);
                    double endY = grid.getPrefHeight() * i + circ.getRadius(); 
                    swapTable[idInc] = new Cerchio(stk,stkRect,endX,endY,circ.getRadius(),circ.getRadius(),0);
                    idInc++;
                    if (i > 0) {
                            //coordinate per la linea
                        double startX = (1 / Math.pow(2, i - 1) * grid.getPrefWidth()) * (j / 2) + (1 / Math.pow(2, i - 1) * grid.getPrefWidth() / 2);
                        double startY = grid.getPrefHeight() * (i - 1) + circ.getRadius();
                        Line line = new Line(startX,startY+40,endX,endY+40);
                        a.getChildren().add(line);
                        line.toBack();
                    }
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start.setVisible(true);
        start.toFront();
        restart.setDisable(true);

    }
}    
    

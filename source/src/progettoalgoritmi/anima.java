/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

/**
 *
 * @author Gio
 * 
 * 
 * 
 * 
 * 
 */
public class anima {
    Scene myScene;
    sort algorith;
    String nleft,nright;
    int toSort[];
    Cerchio swapTable[];
    Cerchio l;
    Cerchio r;
    Circle tmp;
    Rectangle tmp2;
    double rad;
    int ndxL;
    int ndxR;
    int n1,n2,n3,n4;
    boolean isHeap=false;
    boolean loop;
    Timeline timeline;
    PathTransition transition1;
    PathTransition transition2;
    PathTransition transition3;
    PathTransition transition4;
    
    String input;
    int status=1;
    StepByStepAnimationController sbs = null;
    MotionAnimationController mac = null;
    
    double durata;

    public void setDurata(double durata) {
        this.durata = durata;
    }
    
    
    public anima(Scene vert, int toSort[],Cerchio swapTable[],StepByStepAnimationController sbs, boolean loop ) {
        this.myScene = vert;
        this.toSort = toSort;
        this.swapTable = swapTable;
        this.sbs = sbs;
        this.loop = loop;
    }
    public anima(Scene vert, int toSort[],Cerchio swapTable[],MotionAnimationController mac, boolean loop ) {
        this.myScene = vert;
        this.toSort = toSort;
        this.swapTable = swapTable;
        this.mac = mac;
        this.loop = loop;
    }
        
        
        
        
    
    
    
    public void run(){
        ////////////////////////////////////////////////////////////////////////
                                //     COLORA I CERCHI      //
                                
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.5), event ->{
                    //Prendi object Cerchio dalla swaptable
                l=swapTable[Integer.parseInt(input.substring(0,input.indexOf("-")))];
                r=swapTable[Integer.parseInt(input.substring(input.indexOf("-")+1))];
                n1=toSort[Integer.parseInt(input.substring(0,input.indexOf("-")))];
                n2=toSort[Integer.parseInt(input.substring(input.indexOf("-")+1))];
                if(mac==null){
                    if ("".equals(sbs.getCommentText())) {
                        sbs.setCommentText("Confronto "+n1+" con "+n2);
                    }else{  
                        sbs.setCommentText(sbs.getCommentText()+"\n"+"Confronto "+n1+" con "+n2);
                    }
                    sbs.focusOnLastLine();
                }else{
                    if ("".equals(mac.getCommentText())) {
                        mac.setCommentText("Confronto "+n1+" con "+n2);
                    }else{  
                        mac.setCommentText(mac.getCommentText()+"\n"+"Confronto "+n1+" con "+n2);
                    }
                    mac.focusOnLastLine();
                }
                    //Colora il circle degli elementi
                tmp=(Circle)(l.getStk().getChildren().get(0));
                tmp.setFill(Color.RED);
                tmp=(Circle)(r.getStk().getChildren().get(0));
                tmp.setFill(Color.RED);
                    //Colora il rectangle degli elementi
                tmp2=(Rectangle)(l.getStkRect().getChildren().get(0));
                tmp.setFill(Color.GREEN);
                tmp2=(Rectangle)(r.getStkRect().getChildren().get(0));
                tmp.setFill(Color.GREEN);
            }),
            new KeyFrame(Duration.seconds(durata+0.5), event ->{
                    //Ricolora normale
                tmp=(Circle)(l.getStk().getChildren().get(0));
                tmp.setFill(Color.LIGHTBLUE);
                tmp=(Circle)(r.getStk().getChildren().get(0));
                tmp.setFill(Color.LIGHTBLUE);
                tmp2=(Rectangle)(l.getStkRect().getChildren().get(0));
                tmp.setFill(Color.LIGHTBLUE);
                tmp2=(Rectangle)(r.getStkRect().getChildren().get(0));
                tmp.setFill(Color.LIGHTBLUE);
            })
        );
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(mac==null){
                        sbs.setBtn_next();
                }
                if(loop){
                nextInput();
                }
            }
        });
        ////////////////////////////////////////////////////////////////////////
                                    //     TRANSIZIONE      //
            //Transizione rectangles
        transition1 = new PathTransition();
        transition1.setDuration(Duration.seconds(durata));
        
        transition2 = new PathTransition();
        transition2.setDuration(Duration.seconds(durata));
                                                 
            //Transizione circles
        transition3 = new PathTransition();
        transition3.setDuration(Duration.seconds(durata));

        transition4 = new PathTransition();
        transition4.setDuration(Duration.seconds(durata));
        
        transition4.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(mac==null){
                        sbs.setBtn_next();
                    }
                    if(loop){
                    nextInput();
                    }
                }
            });
        ////////////////////////////////////////////////////////////////////////
                                    //     START      //
                                    
        algorith = new sort(toSort);
        nextInput();
        status++;
        
    }      

    public void getCoordNode(){
            //prendo object Cerchio dalla swapTable
            
        if(Integer.parseInt(input.substring(input.indexOf(" ")+1,input.indexOf("swap")))<Integer.parseInt(input.substring(input.indexOf("swap")+4))){
            ndxL = Integer.parseInt(input.substring(input.indexOf(" ")+1,input.indexOf("swap")));
            ndxR = Integer.parseInt(input.substring(input.indexOf("swap")+4));
        }else{
            ndxR = Integer.parseInt(input.substring(input.indexOf(" ")+1,input.indexOf("swap")));
            ndxL = Integer.parseInt(input.substring(input.indexOf("swap")+4));
        }

        l=swapTable[ndxL];
        r=swapTable[ndxR];
        rad=tmp.getRadius();
        
        n3=toSort[ndxL];
        n4=toSort[ndxR];
        
        if(mac==null){
            if (isHeap) {
                sbs.setCommentText(sbs.getCommentText()+"\n"+"E'stato formato un HEAP!"+" -> "+"scambio "+n3+" con "+n4);
                sbs.focusOnLastLine();
                isHeap=false;
            }else{
                sbs.setCommentText(sbs.getCommentText()+"\n"+"il padre è minore del figlio "+" -> "+"scambio "+n3+" con "+n4);
                sbs.focusOnLastLine();
            }
        }else{
            if (isHeap) {
                mac.setCommentText(mac.getCommentText()+"\n"+"E'stato formato un HEAP!"+" -> "+"scambio "+n3+" con "+n4);
                mac.focusOnLastLine();
                isHeap=false;
            }else{
                mac.setCommentText(mac.getCommentText()+"\n"+"il padre è minore del figlio "+" -> "+"scambio "+n3+" con "+n4);
                mac.focusOnLastLine();
            }
        }
        
        //imposto la transizione per Cerchio l->stackRect
        transition1.setNode(l.getStkRect());
         Arc arc1 = new Arc((ndxR-ndxL+1)*tmp2.getWidth()/2+l.getNativeRectX(), tmp2.getHeight()/2, (ndxR-ndxL)*tmp2.getWidth()/2+2, 30, 180, 180);
        //transition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition1.setPath(arc1);
            //imposto la transizione per Cerchio r->stackRect
        transition2.setNode(r.getStkRect());
        Arc arc2 = new Arc(-(ndxR-ndxL-1)*tmp2.getWidth()/2+r.getNativeRectX(), tmp2.getHeight()/2, (ndxR-ndxL)*tmp2.getWidth()/2+2, 30, 0, 180);
        //        Arc arc2 = new Arc(-(ndxR-ndxL)/2*tmp2.getWidth(),20, (ndxR-ndxL)/2*tmp2.getWidth(), 60, 0, 180+5);
        transition2.setPath(arc2);
            //imposto la transizione per Cerchio l->stack
        transition3.setNode(l.getStk());
        Path path3 = new Path();  
        path3.getElements().add(new MoveTo(l.getNativeX(),l.getNativeY()));//partenza dal pivot nativo
        path3.getElements().add(new LineTo(l.getNativeX()+r.getCenterX()-l.getCenterX(), l.getNativeY()+r.getCenterY()-l.getCenterY()));//destinazione cordNative+(centro dest-centro mio)
        transition3.setPath(path3);
            //imposto la transizione per Cerchio r->stack
        transition4.setNode(r.getStk());
        Path path4 = new Path();
        path4.getElements().add(new MoveTo(r.getNativeX(),r.getNativeY()));//partenza dal pivot nativo
        path4.getElements().add(new LineTo(r.getNativeX()+l.getCenterX()-r.getCenterX(), r.getNativeY()+l.getCenterY()-r.getCenterY()));//destinazione cordNative+(centro dest-centro mio)
        transition4.setPath(path4);
        swapTable[ndxL].setNativeRectX(l.getNativeRectX()+(ndxR-ndxL)*tmp2.getWidth()+1);
        swapTable[ndxR].setNativeRectX(r.getNativeRectX()-((ndxR-ndxL)*tmp2.getWidth()+1));
            //aggiorno le coordinate del pivot native rispetto alla nuova posizione
        swapTable[ndxL].setNativeX(l.getNativeX()+r.getCenterX()-l.getCenterX());
        swapTable[ndxL].setNativeY(l.getNativeY()+r.getCenterY()-l.getCenterY());
        swapTable[ndxR].setNativeX(r.getNativeX()+l.getCenterX()-r.getCenterX());
        swapTable[ndxR].setNativeY(r.getNativeY()+l.getCenterY()-r.getCenterY());
            //aggiorno la swaptable scambiando la posizione nell'array
        StackPane temp = l.getStk();
        swapTable[ndxL].setStk(r.getStk());
        swapTable[ndxR].setStk(temp);
        StackPane temp1 = l.getStkRect();
        swapTable[ndxL].setStkRect(r.getStkRect());
        swapTable[ndxR].setStkRect(temp1);
        double temp2 = l.getNativeX();
        swapTable[ndxL].setNativeX(r.getNativeX());
        swapTable[ndxR].setNativeX(temp2);
        double temp3 = l.getNativeY();
        swapTable[ndxL].setNativeY(r.getNativeY());
        swapTable[ndxR].setNativeY(temp3);
        double temp4 = l.getNativeRectX();
        swapTable[ndxL].setNativeRectX(r.getNativeRectX());
        swapTable[ndxR].setNativeRectX(temp4);
    }
    
    public void nextInput(){
            //chiamo la classe dell'algoritmo che si ferma quando arriva in un
            //punto dove c'è un animazione restituendo una stringa-->"input"
            //al variare di input viene eseguita un'animazione diversa
        input=algorith.start(status);
        if(!input.matches("ERR")){
            if(!input.matches("endSORT")){
                if(status==5){
                    status++;
                }
                if(input.contains("start")){
                    status=5;
                    isHeap=true;
                }
                if(input.contains("-")){
                    timeline.play();            
                }else if(input.contains("swap")){
                    getCoordNode();
                    transition1.play();
                    transition2.play();
                    transition3.play();
                    transition4.play();                  
                }       
            }else{
                if(mac==null){
                    sbs.setCommentText(sbs.getCommentText()+"\n"+"L'array è stato ordinato!");
                    sbs.focusOnLastLine();
                    sbs.disableButtons();
                }else{
                    mac.setCommentText(mac.getCommentText()+"\n"+"L'array è stato ordinato!");
                    mac.focusOnLastLine();
                    mac.disableButtons();
                }
            }
        }
    }
    
}

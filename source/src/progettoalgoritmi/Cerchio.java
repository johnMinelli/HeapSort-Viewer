/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;


import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Gio
 */
public class Cerchio {
    private StackPane stk;
    private StackPane stkRect;
    private double centerX; 
    private double centerY;
    private double nativeX;
    private double nativeY;
    private double nativeRectX;

    public Cerchio(StackPane stk, StackPane stkRect, double centerX, double centerY, double nativeX, double nativeY, double nativeRectX) {
        this.stk = stk;
        this.stkRect = stkRect;
        this.centerX = centerX;
        this.centerY = centerY;
        this.nativeX = nativeX;
        this.nativeY = nativeY;
        this.nativeRectX = nativeRectX;
    }

    public StackPane getStk() {
        return stk;
    }

    public StackPane getStkRect() {
        return stkRect;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getNativeX() {
        return nativeX;
    }

    public double getNativeY() {
        return nativeY;
    }

    public double getNativeRectX() {
        return nativeRectX;
    }

    public void setStk(StackPane stk) {
        this.stk = stk;
    }

    public void setStkRect(StackPane stkRect) {
        this.stkRect = stkRect;
    }
    
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setNativeX(double nativeX) {
        this.nativeX = nativeX;
    }

    public void setNativeY(double nativeY) {
        this.nativeY = nativeY;
    }

    public void setNativeRectX(double nativeRectX) {
        this.nativeRectX = nativeRectX;
    }

}

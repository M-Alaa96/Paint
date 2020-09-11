/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPaint;

import java.awt.Color;

/**
 *
 * @author Kdeep
 */
public abstract class Shape implements Cloneable{
    
    protected Point startPoint;
    protected boolean isFilled;
    protected boolean selected;
    protected boolean rotated;
    protected Color shapeColor; //Java built in class color

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
        
        
        
    }

    public boolean isRotated() {
        return rotated;
    }

    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    
    public Shape(){
        
    }
    
    
    public Shape(Point startPoint, boolean isFilled, Color shapeColor) {
        this.startPoint = startPoint;
        this.isFilled = isFilled;
        this.shapeColor = shapeColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public boolean isIsFilled() {
        return isFilled;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }
    
    public abstract boolean containPoint(Point p);
    public abstract void move(Point a, Point b);    
    public abstract void resize(Point a, Point b);
    
    public abstract boolean containUpperLeft(Point p);
    public abstract boolean containLowerLeft(Point p);
    public abstract boolean containUpperRight(Point p);
    public abstract boolean containLowerRight(Point p);
}

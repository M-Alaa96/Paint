/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPaint;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Kdeep
 */
public class Line extends Shape{
    private Point endPoint;

    public Line() {
        
    }

    
    
    
    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Line(Point startPoint, Point endPoint, boolean isFilled, Color shapeColor) {
        super(startPoint, isFilled, shapeColor);
        this.endPoint = endPoint;
    }
    
    public boolean containPoint(Point p)//boolean method that checks if the point is very near the line or not for selection purposes
    {
        Line2D line = new Line2D.Float(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
        double d = line.ptLineDist(p.getX(), p.getY());//calculates the distance between the point and the line
        
        if(d <= 2)//if the distance is very small
        {
            return true;
        }
        return false;
        
        
        
    }
    
    @Override
    public void move(Point a, Point b)
    {
        startPoint.setX(startPoint.getX()+ b.getX() - a.getX());
        startPoint.setY(startPoint.getY() + b.getY() - a.getY());
        endPoint.setX(endPoint.getX()+ b.getX() - a.getX());
        endPoint.setY(endPoint.getY() + b.getY() - a.getY());
        
        
    }

    
    
    public boolean containUpperLeft(Point p){
    
    
    
    return false;
}
    
    public boolean containUpperRight(Point p){
    
    
    
    return false;
}
    
    public boolean containLowerLeft(Point p){
    
    
    
    return false;
}
    public boolean containLowerRight(Point p){
        
          
    return false;
               
        
    }
    
    
    public boolean containStart(Point p){
        
       Rectangle2D r = new Rectangle2D.Float((startPoint.getX())-5, (startPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    return false;
        
        
    }
    
    
    public boolean containEnd(Point p){
        
        Rectangle2D r = new Rectangle2D.Float((endPoint.getX())-5, (endPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
        
        
    }
    public void resize(Point A, Point B){
     
        //e3ml ba2it el cases la2n el case de hya el upper left bas b if conditions l kol el 7alat
        if(containStart(A)){
        
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
        
        }else if(containEnd(A)){
            
        endPoint.setX(endPoint.getX() + B.getX() - A.getX());
        endPoint.setY(endPoint.getY() + B.getY() - A.getY());
        
            
            
        }
        
    }
}

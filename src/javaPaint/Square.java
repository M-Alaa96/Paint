/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPaint;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Kdeep
 */
public class Square extends Rectangle{
    
    
    
    
        private int length;

    public Square() {
        super.setWidth(length);
        super.setHeight(length);
    }

    public Square(int length,  Point startPoint, boolean isFilled, Color shapeColor) {
        super(length, length, startPoint, isFilled, shapeColor);
        this.length = length;
    }

   

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
         super.setWidth(length);
        super.setHeight(length);
    }

    
    
    public boolean containPoint(Point p)
    {
        Rectangle2D r2d = new Rectangle2D.Float(getStartPoint().getX(), getStartPoint().getY(), getLength(), getLength());
                    
                    if(r2d.contains(p.getX(), p.getY()))
                    {
                        return true;
                    }
                    return false;
    }
    
    public void move(Point a, Point b)
    {
        
        startPoint.setX(startPoint.getX()+ b.getX() - a.getX());
        startPoint.setY(startPoint.getY() + b.getY() - a.getY());
        
    }
    
    public boolean containUpperLeft(Point p){
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())-5, (startPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    return false;
}
    
    public boolean containUpperRight(Point p){
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(length)-5, (startPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    return false;
}
    
    public boolean containLowerLeft(Point p){
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())-5, (startPoint.getY())+(length)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
}
    public boolean containLowerRight(Point p){
        
        Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(length)-5, (startPoint.getY())+(length)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
        
        
        
    }
    
    
    public void resize(Point A, Point B){
     
        //e3ml ba2it el cases la2n el case de hya el upper left bas b if conditions l kol el 7alat
        if(containUpperLeft(A)){
        length = length + A.getX() - B.getX();
//        height = height + A.getY() - B.getY();
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
        
        }else if(containLowerLeft(A)){
            
        length = length + A.getX() - B.getX();
//        height = height + B.getY() - A.getY();
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY());
            
            
        }else if(containUpperRight(A)){
            
        length = length + B.getX() - A.getX();
//        height = height + A.getY() - B.getY();
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
            
            
        }else if(containLowerRight(A)){
            
        length = length + B.getX() - A.getX();
//        height = height + B.getY() - A.getY();
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY());
            
            
        }
        
    }
}

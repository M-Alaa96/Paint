
package javaPaint;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    
    private int height, width;

    
    public Rectangle(int height, int width, Point startPoint, boolean isFilled, Color shapeColor) {//constructor
        super(startPoint, isFilled, shapeColor);
        this.height = height;
        this.width = width;
    }

    public Rectangle() {//empty constructor for the factory D.P
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public boolean containPoint(Point p)
    {
        Rectangle2D r2d = new Rectangle2D.Float(getStartPoint().getX(), getStartPoint().getY(), getWidth(), getHeight());
                    
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
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(width)-5, (startPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    return false;
}
    
    public boolean containLowerLeft(Point p){
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())-5, (startPoint.getY())+(height)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
}
    public boolean containLowerRight(Point p){
        
        Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(width)-5, (startPoint.getY())+(height)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
        
        
        
    }
    
    
    public void resize(Point A, Point B){
     
        //e3ml ba2it el cases la2n el case de hya el upper left bas b if conditions l kol el 7alat
        if(containUpperLeft(A)){
        width = width + A.getX() - B.getX();
        height = height + A.getY() - B.getY();
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
        
        }else if(containLowerLeft(A)){
            
        width = width + A.getX() - B.getX();
        height = height + B.getY() - A.getY();
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY());
            
            
        }else if(containUpperRight(A)){
            
        width = width + B.getX() - A.getX();
        height = height + A.getY() - B.getY();
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
            
            
        }else if(containLowerRight(A)){
            
        width = width + B.getX() - A.getX();
        height = height + B.getY() - A.getY();
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY());
            
            
        }
        
    }
    
}

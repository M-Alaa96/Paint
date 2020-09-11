
package javaPaint;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Shape {

    int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Circle() {
    }

    public Circle(int radius, Point startPoint, boolean isFilled, Color shapeColor) {
        super(startPoint, isFilled, shapeColor);
        this.radius = radius;
    }
    
    public boolean containPoint(Point p)
    {
        Ellipse2D e = new Ellipse2D.Float(getStartPoint().getX(), getStartPoint().getY(), getRadius()*2, getRadius()*2);
                    
                    if(e.contains(p.getX(), p.getY()))
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
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(radius*2)-5, (startPoint.getY())-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    return false;
}
    
    public boolean containLowerLeft(Point p){
    
    Rectangle2D r = new Rectangle2D.Float((startPoint.getX())-5, (startPoint.getY())+(radius*2)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
}
    public boolean containLowerRight(Point p){
        
        Rectangle2D r = new Rectangle2D.Float((startPoint.getX())+(radius*2)-5, (startPoint.getY())+(radius*2)-5, 10, 10);
    
    if(r.contains(p.getX(), p.getY())){
        
        return true;
    }
    
    
    return false;
        
        
        
    }
    
    
    public void resize(Point A, Point B){
     
        //e3ml ba2it el cases la2n el case de hya el upper left bas b if conditions l kol el 7alat
        if(containUpperLeft(A)){
        radius = radius + A.getX() - B.getX();
        
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
        
        }else if(containLowerLeft(A)){
            
        radius = radius + A.getX() - B.getX();
        
        startPoint.setX(startPoint.getX() + B.getX() - A.getX());
        startPoint.setY(startPoint.getY());
            
            
        }else if(containUpperRight(A)){
            
        radius = radius + B.getX() - A.getX();
        
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY() + B.getY() - A.getY());
            
            
        }else if(containLowerRight(A)){
            
        radius = radius + B.getX() - A.getX();
        
        startPoint.setX(startPoint.getX());
        startPoint.setY(startPoint.getY());
            
            
        }
        
    }

}

package javaPaint;

import java.awt.Color;
import java.awt.Polygon;

public class Triangle extends Shape {
    
    private Point secondPoint, thirdPoint;

    public Triangle(Point secondPoint, Point thirdPoint, Point startPoint, boolean isFilled, Color shapeColor) {
        super(startPoint, isFilled, shapeColor);
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    public Triangle() {
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    public void setThirdPoint(Point thirdPoint) {
        this.thirdPoint = thirdPoint;
    }
    
    public boolean containPoint(Point p)
    {
        Polygon t2d = new Polygon();
                    t2d.addPoint(getStartPoint().getX(), getStartPoint().getY());
                    t2d.addPoint(getSecondPoint().getX(), getSecondPoint().getY());
                    t2d.addPoint(getThirdPoint().getX(), getThirdPoint().getY());
                    
                    if(t2d.contains(p.getX(), p.getY()))//if the triangle contains the point selected
                    {
                        return true;
                    }
                    return false;
        
    }
    
    public void move(Point a, Point b)
    {
        
        startPoint.setX(startPoint.getX()+ b.getX() - a.getX());
        startPoint.setY(startPoint.getY() + b.getY() - a.getY());
        
        secondPoint.setX(secondPoint.getX()+ b.getX() - a.getX());
        secondPoint.setY(secondPoint.getY() + b.getY() - a.getY());
        
        thirdPoint.setX(thirdPoint.getX()+ b.getX() - a.getX());
        thirdPoint.setY(thirdPoint.getY() + b.getY() - a.getY());
        
        
    }

    @Override
    public void resize(Point a, Point b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containUpperLeft(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containLowerLeft(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containUpperRight(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containLowerRight(Point p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

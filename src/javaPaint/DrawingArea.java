
package javaPaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Stack;
import javafx.scene.transform.Affine;
import javax.swing.SwingUtilities;

public class DrawingArea extends javax.swing.JPanel implements Container {

    /**
     * Creates new form DrawingArea
     */
    Point A;//start point specified by the first mouse pressed
    Point B;
    ArrayList<Shape> shapes = new ArrayList<Shape>(); //Arraylist of shapes
    int currentShape = 0;//current shape choosed by the user
    Color currentColor = Color.BLACK;//Color of the drawing (Default is black)
    boolean filledShape = false;//if the shape is filled
    int mode = 0;//Drawing or selecting mode
    Shape selectedShape;
    Shape shapeToResize;
    
    
    ArrayList<Observer> observers = new ArrayList<>();
    
    public void attach(Observer o){
        
        observers.add(o);
        
    }
    
    
    public void notifyAllObservers(){
        
        for(Observer r: observers){
            
            r.Update();
        }
        
        
    }
    
    
    public void addHistory(String h){
        
        History.add(h);
        notifyAllObservers();
               
    }
    
    public void deSelect(){
        
        mode = 0;        
        selectedShape.setSelected(false);
        repaint();
        selectedShape = null;
        currentShape = 0;
        
    }
    
    
    ArrayList<String> History = new ArrayList<>();
    
    private ShapeFactory shapeFactory = ShapeFactory.getInstance();

    Stack<Shape> s1 = new Stack<Shape>();
    Stack<Shape> s2 = new Stack<Shape>();
    
    
    public Iterator getIterator(){//Creates shapesiterator instance
        
        
        return new ShapesIterator();
        
    }   
   
    private class ShapesIterator implements Iterator { //ShapesIterator for Iterator D.P

        int index;

        @Override

        public Object next() {

            if (hasNext()) {

                return shapes.get(index++);

            }
            return null;
        }

        @Override
        public boolean hasNext() {

            if (index < shapes.size()) {

                return true;

            }

            return false;
        }

    }



public void undo()
    {
        
        if(s1.isEmpty() == false)
        {
            Shape s = s1.pop();
            s2.push(s);
            shapes.remove(s);
            
            addHistory("Undo applied");
            repaint();
        }
        
        
    }
    

    public void cut() throws CloneNotSupportedException{
        
        mode = 4;
        if(selectedShape != null){
                                   Shape s = (Shape) selectedShape.clone();
            
            s.setSelected(true);
            
            shapes.remove(selectedShape);
            selectedShape = s;
            shapes.add(selectedShape);
            
            
            repaint();
                       
                       
        }
        
    }


    public void copy() throws CloneNotSupportedException{
        
        mode = 3;
        if(selectedShape != null){
            
            selectedShape.setSelected(false);
            
            Shape s = (Shape) selectedShape.clone();
            
            s.setSelected(true);
            
            
            shapes.add(s);
            
            
            repaint();
                       
                       
        }
        
        
        
    }
    
    
    
    public void redo()
    {
        
        if(s2.isEmpty() == false)
        {
            Shape s = s2.pop();
            s1.push(s);
            shapes.add(s);
            addHistory("Redo applied");
            repaint();
        }
        
        
        
    }
    
    public DrawingArea() {
        initComponents();
    }

    @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Iterator I = getIterator();
        
        Graphics2D g2d=(Graphics2D)g;
        AffineTransform old=g2d.getTransform();
        
        while (I.hasNext()) {
            
            Shape shape = (Shape) I.next();
            
            g.setColor(shape.getShapeColor());
            
              if(shape.isRotated())
                g2d.rotate(Math.toRadians(45),shape.getStartPoint().getX(),shape.getStartPoint().getY());
            else 
                g2d.setTransform(old);
            
            if(shape instanceof Line)//checks if the the shape is (new) line
            {
                Line l = (Line) shape; //Make shape refrence to line to access the elements of line (Polymorphism)
                g.drawLine(l.getStartPoint().getX(), l.getStartPoint().getY(),
                        l.getEndPoint().getX(), l.getEndPoint().getY());
            
                if(l.isSelected())
                {
                    
                     
                     
                     g.fillRect((l.getStartPoint().getX())-5, (l.getStartPoint().getY())-5, 10, 10);
                     
                     g.fillRect((l.getEndPoint().getX())-5, (l.getEndPoint().getY())-5, 10, 10);
                 
                    
                    
                    
                    
                }
            }else if(shape instanceof Rectangle) //checks if the the shape is new rectangle
            {
            
                 Rectangle r = (Rectangle) shape; //Make shape refrence to rect to access the elements of rectangle
                 
                 
                 if(r.isIsFilled()) //Checks if the rect is filled or not
                 {
                     
                     g.fillRect(r.getStartPoint().getX(), r.getStartPoint().getY(), r.getWidth(), r.getHeight());
                     
                     
                 }else
                 {
                 
                     g.drawRect(r.getStartPoint().getX(), r.getStartPoint().getY(), r.getWidth(), r.getHeight());
                 
                 }
                 
                 if(r.isSelected())
                 {
                     
                     g.fillRect((r.getStartPoint().getX())-5, (r.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((r.getStartPoint().getX())+(r.getWidth())-5, (r.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((r.getStartPoint().getX())-5, (r.getStartPoint().getY())+(r.getHeight())-5, 10, 10);
                     g.fillRect((r.getStartPoint().getX())+(r.getWidth())-5, (r.getStartPoint().getY())+(r.getHeight())-5, 10, 10);
                 }
                 
                 
                 
            
            
            }else if(shape instanceof Square) //checks if the the shape is new square
            {
            
                 Square s = (Square) shape; //Make shape refrence to rect to access the elements of rectangle
                 
                 
                 if(s.isIsFilled()) //Checks if the rect is filled or not
                 {
                     
                     g.fillRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getLength(), s.getLength());
                     
                     
                 }else
                 {
                 
                     g.drawRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getLength(), s.getLength());
                 
                 }
                 
                 if(s.isSelected())
                 {
                     
                     g.fillRect((s.getStartPoint().getX())-5, (s.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((s.getStartPoint().getX())+(s.getLength())-5, (s.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((s.getStartPoint().getX())-5, (s.getStartPoint().getY())+(s.getLength())-5, 10, 10);
                     g.fillRect((s.getStartPoint().getX())+(s.getLength())-5, (s.getStartPoint().getY())+(s.getLength())-5, 10, 10);
                 }
                 
                 
                 
            
            
            }else if(shape instanceof Triangle) //checks if the the shape is new triangle
            {
            Triangle t = (Triangle) shape;
            Polygon p = new Polygon();
            p.addPoint(t.getStartPoint().getX(), t.getStartPoint().getY());
            p.addPoint(t.getSecondPoint().getX(), t.getSecondPoint().getY());
            p.addPoint(t.getThirdPoint().getX(), t.getThirdPoint().getY());
            
            if(t.isIsFilled())
            {
                g.fillPolygon(p);
            }else
            {
                g.drawPolygon(p);
            }
            
            if(t.isSelected())
                 {
                     
                     g.fillRect((t.getStartPoint().getX())-5, (t.getStartPoint().getY())-5, 10, 10);//first up point
                     g.fillRect((t.getThirdPoint().getX())-5, (t.getSecondPoint().getY())-5, 10, 10);//right angle point
                     g.fillRect((t.getSecondPoint().getX())-5, +(t.getSecondPoint().getY())-5, 10, 10);//far point"is the second point"
                     //g.fillRect((t.getThirdPoint().getX())-5, (t.getStartPoint().getY())-5, 10, 10);
                 }
            
            }else if(shape instanceof Circle) //checks if the the shape is new circle
            {
            Circle c = (Circle) shape;
            
            
            
            if(c.isIsFilled())
            {
                g.fillOval(c.getStartPoint().getX(), c.getStartPoint().getY(), c.getRadius()*2, c.getRadius()*2);
            }else
            {
               g.drawOval(c.getStartPoint().getX(), c.getStartPoint().getY(), c.getRadius()*2, c.getRadius()*2);
            }
                    
            
                    if(c.isSelected())
                 {
                     g.fillRect((c.getStartPoint().getX())-5, (c.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((c.getStartPoint().getX())+(c.getRadius()*2)-5, (c.getStartPoint().getY())-5, 10, 10);
                     g.fillRect((c.getStartPoint().getX())-5, (c.getStartPoint().getY())+(c.getRadius()*2)-5, 10, 10);
                     g.fillRect((c.getStartPoint().getX())+(c.getRadius()*2)-5, (c.getStartPoint().getY())+(c.getRadius()*2)-5, 10, 10);
            
                 }
            
            }
            
            
            
            
        }
        
       
        
        
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
            //evt is an object event when mouse pressed , this point to configure the start point
            A = new Point(evt.getX(), evt.getY());//Initialize the start point with the coordinates of the location where the mouse pressed 
            if(mode == 0){ //if in drawing mode
            if(currentShape == 1)
            {
            
            //Line l = new Line(A, A, false, currentColor);//creates a new line starts and ends at the point where the mouse clicked
            Line l = (Line)shapeFactory.getShape("Line");
            l.setStartPoint(A);
            l.setEndPoint(A);
            l.setSelected(false);
            l.setShapeColor(currentColor);
            
            
            
            shapes.add(l);//adds the shape to the arraylist of shapes
            s1.push(l);//push in the undo stack    
            s2.clear();//clears the redo stack
            addHistory("A line drawed");
            repaint();//refreshes the view of  the edited (created) shape
            
            } else if(currentShape == 4)
            {
             
            //Rectangle R = new Rectangle(0, 0, A, filledShape, currentColor);//creates a new rectangle starts and ends at the point where the mouse clicked
            Rectangle R = (Rectangle) shapeFactory.getShape("rectangle");
            R.setHeight(0);
            R.setWidth(0);
            R.setStartPoint(A);
            R.setIsFilled(filledShape);
            R.setShapeColor(currentColor);
            
            
            
            shapes.add(R);//adds the shape to the arraylist of shapes
            s1.push(R);//push in the undo stack            
            s2.clear();//clears the redo stack
            
            addHistory("A rectangle drawed");
            repaint();//refreshes the view of  the edited (created) shape
            
            } else if(currentShape == 44)
            {
             
            /*Creating a new square from the shape factory D.P*/
                
            Square s = (Square) shapeFactory.getShape("square");
            s.getLength();
            s.setStartPoint(A);
            s.setIsFilled(filledShape);
            s.setShapeColor(currentColor);
            
            
            
            shapes.add(s);//adds the shape to the arraylist of shapes
            s1.push(s);//push in the undo stack            
            s2.clear();//clears the redo stack
            
            addHistory("A square drawed");
            repaint();//refreshes the view of  the edited (created) shape
            
            } else if(currentShape == 3)//if the desired shape is a triangle
            {
                //Triangle t = new Triangle(A, A, A, filledShape, currentColor);//creates a new triangle with all the points is the start point
                
                Triangle t = (Triangle) shapeFactory.getShape("triangle");
                t.setStartPoint(A);
                t.setSecondPoint(A);
                t.setThirdPoint(A);
                t.setIsFilled(filledShape);
                t.setShapeColor(currentColor);
                
                
                
                shapes.add(t);//adds the triangle to the arraylist
                s1.push(t);//push in the undo stack    
                s2.clear();//clears the redo stack
                
                addHistory("A triangle drawed");
                repaint();
            }else if(currentShape == 5)//if the desired shape is a circle
            {
                //Circle c = new Circle(0, A, filledShape, currentColor);//creates a new circle in the start point
                
                Circle c = (Circle) shapeFactory.getShape("circle");
                c.setRadius(0);
                c.setStartPoint(A);
                c.setIsFilled(filledShape);
                c.setShapeColor(currentColor);
                
                shapes.add(c);//adds the circle to the arraylist
                s1.push(c);//push in the undo stack
                s2.clear();//clears the redo stack
                
                addHistory("A circle drawed");
                repaint();
            }
        /*MainFrame f = (MainFrame)SwingUtilities.getWindowAncestor(this);
        f.UpdateHistory(History);*/
        
        
            }else if(mode == 1) //Selection mode
            {
                selectedShape = null;
                for(int i = 0;i<shapes.size();i++)//no selection when pressed anywhere
                {
                    shapes.get(i).setSelected(false);//set all shapes to be not selected
                }
                  for(int i = shapes.size()-1;i>=0;i--)//looping over the arraylist
            {
                Shape s = shapes.get(i);            
                if(s.containPoint(A)){
                 
                    s.setSelected(true);
                    selectedShape = s;
                    if(s instanceof Line){
                        addHistory("A line is selected");
                    }else if(s instanceof Rectangle){
                                               
                        addHistory("A rectangle is selected");
                        
                    }else if(s instanceof Square){
                                               
                        addHistory("A square is selected");
                        
                    }else if(s instanceof Circle){
                                               
                        addHistory("A circle is selected");
                        
                    }else if(s instanceof Triangle){
                                               
                        addHistory("A triangle is selected");
                        
                    }
                    break;
                   
                }
    
    
    }
                  repaint();
            }else if(mode == 2) {
                
                shapeToResize = null;
                if(selectedShape != null){
                    if(selectedShape instanceof Line){
                        
                        Line l = (Line) selectedShape;
                        
                        if(l.containStart(A) || l.containEnd(A)){
                            
                            shapeToResize = l;
                        }
                        
                        
                    }else if(selectedShape.containUpperLeft(A) || selectedShape.containLowerLeft(A)
                            || selectedShape.containLowerRight(A) || selectedShape.containUpperRight(A)){
                        
                        shapeToResize = selectedShape;
                        
                    }
                    
                                       
                    
                }
                
                
                
            }else if (mode == 3)
            {
                mode = 0;
                
                if(selectedShape != null){
                    
                    
                    selectedShape.setStartPoint(A);
                    selectedShape.setSelected(true);
                    addHistory("Copy");
                }
                repaint();             
                
                
                
            }else if (mode == 4)
            {
                mode = 0;
                
                if(selectedShape != null){
                    
                    selectedShape.setStartPoint(A);
                    selectedShape.setSelected(true);
                    addHistory("Cut");
                }
                repaint();             
                
                
                
            }
        
        
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        
        B = new Point(evt.getX(), evt.getY());//initializes the point that dragged the shape to
        if(mode == 0)//if in drawing mode
        {
        if(currentShape == 1)
        {
        
            Line l = (Line) shapes.get(shapes.size()- 1); //creates a new line and get it from the arraylist of index size-1
            l.setEndPoint(B);
            repaint();//refreshes the view so a line is showed between the start point and the last point the mouse dragged to
            
                       
            
        } else if(currentShape == 4)
        {
        
            Rectangle b = (Rectangle) shapes.get(shapes.size()- 1); //creates a new rectangle and get it from the arraylist of index size-1
//            
//            The rectangle has 4 cases:
//            *x of ep larger than x sp and y of ep larger than y of sp
//            *x of ep larger than x sp and y of ep smaller than y of sp
//            *x of ep is smaller than x sp and y of ep larger than y of sp        
//            *x of ep is smaller than x sp and y of ep smaller than y of sp 
//                    

            if(B.getX()<A.getX() && B.getY() < A.getY())
            {
                b.setStartPoint(B);
            }else if(A.getX()<B.getX() && A.getY() > B.getY())
            {
            
                Point start = new Point(A.getX(), B.getY());
                b.setStartPoint(start);
            
            
            }else if(A.getX() > B.getX() && A.getY() < B.getY())
            {
            Point start = new Point(B.getX(), A.getY());
                b.setStartPoint(start);
            
            }
            
            
            /*Height and width of the rectangle*/
            
            b.setHeight(Math.abs(((B.getY())-(A.getY()))));
            b.setWidth(Math.abs((B.getX())-(A.getX())));
            repaint();
            
            
            
             
        }else if(currentShape == 44)
        {
        
            Square s = (Square) shapes.get(shapes.size()- 1); //creates a new rectangle and get it from the arraylist of index size-1
//            
//            The square has 4 cases:
//            *x of ep larger than x sp and y of ep larger than y of sp
//            *x of ep larger than x sp and y of ep smaller than y of sp
//            *x of ep is smaller than x sp and y of ep larger than y of sp        
//            *x of ep is smaller than x sp and y of ep smaller than y of sp 
//                    

            if(B.getX()<A.getX() && B.getY() < A.getY())
            {
                s.setStartPoint(B);
            }else if(A.getX()<B.getX() && A.getY() > B.getY())
            {
            
                Point start = new Point(A.getX(), B.getY());
                s.setStartPoint(start);
            
            
            }else if(A.getX() > B.getX() && A.getY() < B.getY())
            {
            Point start = new Point(B.getX(), A.getY());
                s.setStartPoint(start);
            
            }
            
            
            /*Height and width of the rectangle*/
            

            s.setWidth(Math.abs(((B.getX())-(A.getX()))));
                     s.setHeight(Math.abs(((B.getY())-(A.getY()))));
                                 s.setLength(Math.min(s.getHeight(),s.getWidth()));
            
            repaint();
            
            
            
             
        }else if(currentShape == 3)
        {
            Triangle t = (Triangle) shapes.get(shapes.size()-1);
            t.setSecondPoint(B);
            t.setThirdPoint(new Point(A.getX(), B.getY()));
            repaint();
        
        }else if(currentShape == 5)
        {
            Circle c = (Circle) shapes.get(shapes.size()-1);
            c.setRadius(Math.abs(B.getX()- A.getX())/2);
            if(B.getX()<A.getX() && B.getY() < A.getY())
            {
                c.setStartPoint(B);
            }else if(A.getX()<B.getX() && A.getY() > B.getY())
            {
            
                Point start = new Point(B.getX(), A.getY());
                c.setStartPoint(start);
            
            
            }else if(A.getX() > B.getX() && A.getY() < B.getY())
            {
            Point start = new Point(B.getX(), A.getY());
                c.setStartPoint(start);
            
            }
            repaint();
        
        }
        }else if(mode == 1)//if in selection mode
        {
            if(selectedShape != null)
                
            { 
                
                selectedShape.move(A, B);
                A = new Point(evt.getX(), evt.getY());
                
                repaint();
            }
        
        }else if (mode == 2){
            
            if(shapeToResize != null){
                selectedShape.resize(A, B);
                A = new Point(evt.getX(), evt.getY());
                repaint();
                
            }
            
            
        }
        
        
        
        
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:-
        
        
    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        
        Point p = new Point(evt.getX(), evt.getY());
        if(mode == 1)//if the selection mode is enabled
        {
            boolean insideShape = false;
            for(int i = shapes.size()-1;i>=0;i--)//loops the arraylist of shapes to find the shape in which the cursor inside
            {
                          
                if(shapes.get(i).containPoint(p)){
                    
                    
                    insideShape = true;
                }
                 
                
            }        
            if(insideShape)//checks if the cursor is inside the shape and if it is then changes the shape of the cursor
                    {
                        this.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
                    }else {
                        
                        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        
                        
                    }
        
        }else if(mode == 2){
            
            boolean insideUpperLeftCorner = false;
            boolean insideLowerLeftCorner = false;
            boolean insideUpperRightCorner = false;
            boolean insidelLowerRightCorner = false;
            
            
            for(int i = shapes.size()-1;i>=0;i--)//loops the arraylist of shapes to find the shape in which the cursor inside
            {
                if(shapes.get(i) instanceof Line){
                    
                    
                }else if(shapes.get(i).containUpperLeft(p)){
                    
                    
                    insideUpperLeftCorner = true;
                    break;
                    
                }else if(shapes.get(i).containLowerLeft(p)){
                    
                    
                    insideLowerLeftCorner = true;
                    break;
                    
                }else if(shapes.get(i).containUpperRight(p)){
                    
                    
                    insideUpperRightCorner = true;
                    break;
                    
                }else if(shapes.get(i).containLowerRight(p)){
                    
                    
                    insidelLowerRightCorner = true;
                    break;
                    
                }
            
            
            
            
        
                
                if(insideUpperLeftCorner){//edit for all directionsw of the shape of the cursor
                    
                    this.setCursor(new java.awt.Cursor(java.awt.Cursor.NW_RESIZE_CURSOR));
                    
                }else if(insideLowerLeftCorner){
                    
                    
                    this.setCursor(new java.awt.Cursor(java.awt.Cursor.NW_RESIZE_CURSOR));
                    
                }else if(insideUpperRightCorner){
                    
                    
                    this.setCursor(new java.awt.Cursor(java.awt.Cursor.NW_RESIZE_CURSOR));
                }else if(insidelLowerRightCorner){
                    
                    
                    this.setCursor(new java.awt.Cursor(java.awt.Cursor.NW_RESIZE_CURSOR));
                }else {
                        
                        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        
                        
                    }
    
    
    
            }
    
    }
    }   
    
    public void Delete(){// delete selected shape
        
        if(selectedShape != null)
        {
            shapes.remove(selectedShape); //remove the shape from the arraylist
            selectedShape = null;
            addHistory("Delete applied");
            repaint();//refresh the view
            
        }
        
        
    }
    
    public void Rotate(){
        
        if(selectedShape != null){
            
            selectedShape.rotated = true;
        }
        
        repaint();
    }
    
    
    public void fillShape(){ // function to fill a shape that is already drawn
        
        if(selectedShape != null)
        {
            if(filledShape){//if the checkbox is selected
            selectedShape.setIsFilled(true);//set the filling option to true
            selectedShape.setShapeColor(currentColor);
            repaint();
            }else{
                selectedShape.setIsFilled(false);//set the filling option to false
                repaint();
            }
            
        }      
        
    }
    
    
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables



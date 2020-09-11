
package javaPaint;

public class ShapeFactory {
    
    private static ShapeFactory sf; //instance of singelton D.P
    private ShapeFactory(){ //private constructor
    
    
    
    
}
    
    public static ShapeFactory getInstance(){ // returning the one instance for the singelton D.P
        
        if(sf == null){
            sf = new ShapeFactory();
        }
        
        return sf;
    }
    
    public Shape getShape(String shapeType){//method responsible for creating the shapes
        
        if(shapeType == null){
            return null;
        }
        
        if(shapeType.equalsIgnoreCase("line")){
            
            
            return new Line();
            
        }else if(shapeType.equalsIgnoreCase("square")){
            
            
            return new Square();
            
        }else if(shapeType.equalsIgnoreCase("rectangle")){
            
            
            return new Rectangle();
            
        }else if(shapeType.equalsIgnoreCase("circle")){
            
            
            return new Circle();
            
        }else if(shapeType.equalsIgnoreCase("triangle")){
            
            
            return new Triangle();
            
        }
        
        return null;
        
        
        
    }
    
    
    
    
    
}

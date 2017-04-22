package utilities;

public class MathTools {

    public static double getSlope(Vector2i p1, Vector2i p2) {
        try {
            return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        } catch (ArithmeticException ae) {
            //still broken
            return 0;
        }
    }
    
    public static double distance(Vector2i p1, Vector2i p2) {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY() - p1.getY(),2));
    }
    
    public static double polarToX(double r, double theta) {
        return r*Math.cos(theta);
    }
    
    public static double polarToY(double r, double theta) {
        return r*Math.sin(theta);
    }
}

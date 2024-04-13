//import java.lang.Math;
public class Walec {
    private double radius;
    private double height;

    public Walec(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public Walec() {
    }

    public double getVolume() {
        return getBaseArea() * height;
    }

    public double getBaseArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getLateralSurfaceArea() {
        return 2 * Math.PI * radius * height;
    }

    public double getSurfaceArea() {
        return 2 * getBaseArea() + getLateralSurfaceArea();
    }
    public double getRadius() {
        return radius;
    }
    public double getHeight() {
        return height;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}

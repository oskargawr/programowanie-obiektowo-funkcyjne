public class Animal {
    private String name;
    private double height;
    private double weight;
    private double length;

    public Animal(String name, double height, double weight, double length) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return "Name: " + name + ", Height: " + height + ", Weight: " + weight + ", Length: " + length;
    }
}

import java.util.ArrayList;
import java.util.function.Predicate;

public class MasaiMara {
    private ArrayList<Animal> natureReserve;
    public MasaiMara() {
        natureReserve = new ArrayList<>();
    }
    public void populateReserve() {
        natureReserve.add(new Animal("Lion", 1.2, 190, 2.1));
        natureReserve.add(new Animal("Elephant", 3.2, 5000, 6.8));
        natureReserve.add(new Animal("Giraffe", 5.5, 800, 5.9));
        natureReserve.add(new Animal("Zebra", 1.5, 300, 2.6));
        natureReserve.add(new Animal("Rhino", 1.8, 2300, 4.2));
    }
    public ArrayList<Animal> getAnimalsHigher(double height) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : natureReserve) {
            if (animal.getHeight() > height) animals.add(animal);
        }
        return animals;
    }
    public ArrayList<Animal> getAnimalsHigherAndLonger(double height, double length) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : natureReserve) {
            if (animal.getHeight() > height && animal.getLength() > length) animals.add(animal);
        }
        return animals;
    }

    public ArrayList<Animal> getAnimals(Predicate<Animal> check) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (Animal animal : natureReserve) {
            if (check.test(animal)) animals.add(animal);
        }
        return animals;
    }
}

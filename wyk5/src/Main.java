import java.util.Scanner;

public class Main {
    public static void showOptions() {
        System.out.println("1. Animals taller than 120 cm");
        System.out.println("2. Animals taller than 120 cm and longer than 2.5 m");
        System.out.println("3. Animals longer than 120 cm and heavier than 100 kg");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

//    public static void printAnimals(Animal[] animals) {
//        for (Animal animal : animals) {
//            System.out.println(animal);
//        }
//    }




    public static void main(String[] args) {
        int option;
        Scanner reader = new Scanner(System.in);
        MasaiMara reserve = new MasaiMara();
        reserve.populateReserve();

        do {
            showOptions();
            option = reader.nextInt();
            switch (option) {
                case 1 -> printAnimals(reserve.getAnimals(a -> a.getHeight() > 120));
                case 2 -> printAnimals(reserve.getAnimals(a -> a.getHeight() > 120 && a.getLength() > 2.5));
                case 3 -> {
                    final double length = 120, weight = 100;
                    printAnimals(reserve.getAnimals(a -> a.getLength() > length && a.getWeight() > weight));
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }
}
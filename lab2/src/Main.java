import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Walec walec = new Walec();

    public static void Introduction() {
        System.out.println("a) Wyswietl wartosci zmiennych instancji obiektu klasy Walec \n" +
                "b) Ustaw wartosc wysokosci walca \n" +
                "c) Ustaw wartosc promienia walca \n" +
                "d) Oblicz  pole powierzchni walca \n" +
                "e) Oblicz objetosc walca \n" +
                "f) Zakoncz program");
    }

    public static void displayValues() {
        System.out.println("Promien: " + walec.getRadius());
        System.out.println("Wysokosc: " + walec.getHeight());
    }

    public static void setHeightValue() {
        System.out.println("Podaj wysokosc: ");
        double height = scanner.nextDouble();
        scanner.nextLine();
        walec.setHeight(height);
    }

    public static void setRadiusValue() {
        System.out.println("Podaj promien: ");
        double radius = scanner.nextDouble();
        scanner.nextLine();
        walec.setRadius(radius);
    }

    public static void calculateSurfaces() {
        System.out.println("Pole powierzchni podstawy: " + walec.getBaseArea());
        System.out.println("Pole powierzchni bocznej walca: " + walec.getLateralSurfaceArea());
        System.out.println("Pole powierzchni calkowitej walca: " + walec.getSurfaceArea());
    }

    public static void calculateVolume() {
        System.out.println("Objetosc walca: " + walec.getVolume());
    }

    public static void main(String[] args) {
        boolean control = true;
        introduction();
        while (control) {
            System.out.println("Wybierz opcje (a/b/c/d/e/f): ");
            String option = scanner.nextLine();
            switch (option) {
                case "a" -> displayValues();
                case "b" -> setHeightValue();
                case "c" -> setRadiusValue();
                case "d" -> calculateSurfaces();
                case "e" -> calculateVolume();
                case "f" -> control = false;
                default -> System.out.println("Niepoprawna opcja");
            }
        }

    }
}
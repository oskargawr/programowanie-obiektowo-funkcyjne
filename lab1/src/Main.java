import java.util.Scanner;

public class Main {
    public static void calculateFactorial(Scanner scanner) {
        System.out.println("wprowadz liczbe n");
        int number = Integer.parseInt(scanner.nextLine());
        if (number < 0) {
            System.out.println("Wprowadz liczbe naturalna");
            return;
        }
        System.out.println("Silnia z " + number + " wynosi: " + Calculations.factorial(number));
    }

    public static void calculateSum(Scanner scanner) {
        System.out.println("wprowadz liczbe a");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.println("wprowadz liczbe b");
        int b = Integer.parseInt(scanner.nextLine());
        if (a > b) {
            System.out.println("a musi byc mniejsze od b");
            return;
        }
        System.out.println("Suma z zakresu (" + a + ", " + b + ") wynosi: " + Calculations.sum(a, b));
    }

    public static void main(String[] args) {
        boolean control = true;
        Scanner myObj = new Scanner(System.in);

        while(control) {
            System.out.println("1 - silnia (n), 2 - suma z zakresu (a, b), 3 - wyjscie");
            String input = myObj.nextLine();
            switch (input) {
                case "1" -> calculateFactorial(myObj);
                case "2" -> calculateSum(myObj);
                case "3" -> control = false;
                default -> System.out.println("Niepoprawny wybor");
            }
        }
    }
}

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GradeList gradeList = new GradeList();

    public static void addGrade() {
        System.out.println("Podaj ocenę: ");
        double grade = scanner.nextDouble();
        gradeList.addGrade(grade);
        scanner.nextLine();
        System.out.println("Dodano ocenę: " + grade);
    }

    public static void getAverage() {
        if (gradeList.isEmpty()) {
            System.out.println("Nie ma żadnych ocen");
            return;
        }
        System.out.println("Średnia ocen: " + gradeList.getAverage());
    }

    public static void getLowestGrade() {
        if (gradeList.isEmpty()) {
            System.out.println("Nie ma żadnych ocen");
            return;
        }
        System.out.println("Najniższa ocena: " + gradeList.getLowestGrade());
    }

    public static void getHighestGrade() {
        if (gradeList.isEmpty()) {
            System.out.println("Nie ma żadnych ocen");
            return;
        }
        System.out.println("Najwyższa ocena: " + gradeList.getHighestGrade());
    }

    public static void introduction() {
        System.out.println("Jestem programem to zarządzania ocenami studenta");
        System.out.println("a) Dodaj ocenę");
        System.out.println("b) Oblicz średnią");
        System.out.println("c) Znajdź najniższą ocenę");
        System.out.println("d) Znajdź najwyższą ocenę");
        System.out.println("e) Zakończ program");
    }


    public static void main(String[] args) {
        boolean control = true;
        introduction();
        while (control) {

            System.out.println("Wybierz opcję (a / b / c / d / e): ");
            String option = scanner.nextLine();
            switch (option) {
                case "a" -> addGrade();
                case "b" -> getAverage();
                case "c" -> getLowestGrade();
                case "d" -> getHighestGrade();
                case "e" -> control = false;
                default -> System.out.println("Niepoprawna opcja");
            }
        }
    }
}
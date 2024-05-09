import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Kalendarz kalendarz = new Kalendarz();

    public static void listWeekdays() {
        System.out.println("1. Poniedzialek");
        System.out.println("2. Wtorek");
        System.out.println("3. Sroda");
        System.out.println("4. Czwartek");
        System.out.println("5. Piatek");
        System.out.println("6. Sobota");
        System.out.println("7. Niedziela");
    }

    public static String getValidatedString(String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String getValidatedPriorytet(String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (!input.equals("normalny") && !input.equals("wysoki") && !input.equals("najwyższy")) {
                System.out.println("Zly priorytet, wprowadz: 'normalny', 'wysoki', lub 'najwyższy'.");
                input = null;
            }
        } while (input == null);
        return input;
    }

    public static LocalTime getValidatedLocalTime(String prompt, LocalTime startTime) {
        LocalTime time;
        do {
            System.out.println(prompt);
            try {
                time = LocalTime.parse(scanner.nextLine());
                if (startTime != null && time.isBefore(startTime)) {
                    System.out.println("Czas zakonczenia spotkania nie moze byc przed czasem rozpoczecia spotkania.");
                    time = null;
                } else if (startTime == null && time.isBefore(Spotkanie.getConstantStartingHour())) {
                    System.out.println("Spotkanie nie moze rozpoczynac sie przed " + Spotkanie.getConstantStartingHour() + ". Sprobuj ponownie.");
                    time = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Zly format czasu. Wprowadz dane w formacie hh:mm.");
                time = null;
            }
        } while (time == null);
        return time;
    }

    public static void addSpotkania() {
        System.out.println("Na który dzień chcesz dodać spotkanie?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();
        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            String opis = getValidatedString("Podaj opis spotkania:");
            LocalTime czasPoczatku = getValidatedLocalTime("Podaj czas rozpoczęcia spotkania (hh:mm):", null);
            LocalTime czasZakonczenia = getValidatedLocalTime("Podaj czas zakończenia spotkania (hh:mm):", czasPoczatku);
            String priorytet = getValidatedPriorytet("Podaj priorytet spotkania (normalny, wysoki, najwyższy):");
            Spotkanie spotkanie = new Spotkanie(opis, czasPoczatku, czasZakonczenia, priorytet);
            kalendarz.addSpotkanie(dzien, spotkanie);
        }
    }

    public static void getDzien(int dzien) {
        switch (dzien) {
            case 1 -> System.out.println("-- Poniedzialek --");
            case 2 -> System.out.println("-- Wtorek --");
            case 3 -> System.out.println("-- Sroda --");
            case 4 -> System.out.println("-- Czwartek --");
            case 5 -> System.out.println("-- Piatek --");
            case 6 -> System.out.println("-- Sobota --");
            case 7 -> System.out.println("-- Niedziela --");
        }
    }

    public static void getSpotkaniaNaDzien() {
        System.out.println("Wyswietl spotkania na wybrany dzien");
        System.out.println("Na który dzień chcesz wyświetlić spotkania?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();

        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            System.out.println("Spotkania na wybrany dzień:");
            ArrayList<Spotkanie> spotkania = kalendarz.getFilteredSpotkaniaNaDzien(dzien, spotkanie -> true);
            getDzien(dzien);
            if (spotkania.isEmpty()) {
                System.out.println("Brak spotkań na ten dzień");
            } else {
                for (Spotkanie spotkanie : spotkania) {
                    System.out.println(spotkanie.toString());
                }
            }
        }
    }
    public static void deleteMeeting() {
        System.out.println("Usun spotkanie z wybranego dnia");
        System.out.println("Na który dzień chcesz usunąć spotkanie?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();
        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            ArrayList<Spotkanie> spotkania = kalendarz.getFilteredSpotkaniaNaDzien(dzien, spotkanie -> true);
            if (spotkania.isEmpty()) {
                System.out.println("Brak spotkań na ten dzień");
            } else {
                System.out.println("Które spotkanie chcesz usunąć?");
                for (int i = 0; i < spotkania.size(); i++) {
                    System.out.println(i + 1 + ". " + spotkania.get(i).toString());
                }
                int index = scanner.nextInt();
                scanner.nextLine();
                if (index < 1 || index > spotkania.size()) {
                    System.out.println("Nie ma takiego spotkania");
                } else {
                    kalendarz.usunSpotkanie(dzien, spotkania.get(index - 1));
                }
            }
        }
    }

    public static void getMeetingsByPriorityUsingLambda() {
        System.out.println("Wyswietl wszystkie spotnania w danym dniu o danym priorytecie");
        System.out.println("Na który dzień chcesz wyświetlić spotkania?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();
        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            String priorytet = getValidatedPriorytet("Podaj priorytet spotkania (normalny, wysoki, najwyższy):");
            Predicate<Spotkanie> predicate = spotkanie -> spotkanie.getPriorytet().equals(priorytet);
            getFilteredSpotkania(dzien, predicate);
        }
    }

    public static void addFixedMeetingsToMonday() {
        Spotkanie spotkanie1 = new Spotkanie("Spotkanie 1", LocalTime.of(8, 0), LocalTime.of(9, 0), "normalny");
        kalendarz.addSpotkanie(1, spotkanie1);

        Spotkanie spotkanie2 = new Spotkanie("Spotkanie 2", LocalTime.of(10, 0), LocalTime.of(11, 0), "wysoki");
        kalendarz.addSpotkanie(1, spotkanie2);

        Spotkanie spotkanie3 = new Spotkanie("Spotkanie 3", LocalTime.of(12, 0), LocalTime.of(13, 0), "najwyższy");
        kalendarz.addSpotkanie(1, spotkanie3);

        Spotkanie spotkanie4 = new Spotkanie("Spotkanie 4", LocalTime.of(14, 0), LocalTime.of(15, 0), "normalny");
        kalendarz.addSpotkanie(1, spotkanie4);

        Spotkanie spotkanie5 = new Spotkanie("Spotkanie 5", LocalTime.of(16, 0), LocalTime.of(17, 0), "wysoki");
        kalendarz.addSpotkanie(1, spotkanie5);

        Spotkanie spotkanie6 = new Spotkanie("Spotkanie 6", LocalTime.of(18, 0), LocalTime.of(19, 0), "najwyższy");
        kalendarz.addSpotkanie(1, spotkanie6);

        Spotkanie spotkanie7 = new Spotkanie("Spotkanie 7", LocalTime.of(20, 0), LocalTime.of(21, 0), "normalny");
        kalendarz.addSpotkanie(1, spotkanie7);
    }

    public static void getFilteredSpotkania(int dzien, Predicate<Spotkanie> predicate) {
        ArrayList<Spotkanie> filteredSpotkania = kalendarz.getFilteredSpotkaniaNaDzien(dzien, predicate);
        getDzien(dzien);
        if (filteredSpotkania.isEmpty()) {
            System.out.println("Brak spotkań na ten dzień");
        } else {
            for (Spotkanie spotkanie : filteredSpotkania) {
                System.out.println(spotkanie.toString());
            }
        }
    }

    public static void getFilteredSpotkaniaNaDzienStartingBeforeTime() {
        System.out.println("Wyswietl wszystkie spotkania w wybranym dniu, zaczynajace sie nie wczesniej niz podany czas");
        System.out.println("Na który dzień chcesz wyświetlić spotkania?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();

        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            LocalTime time = getStartTime();

            Predicate<Spotkanie> predicate = spotkanie ->
                    spotkanie.getCzasPoczatku().isAfter(time) || spotkanie.getCzasPoczatku().equals(time);

            getFilteredSpotkania(dzien, predicate);
        }
    }
    public static void getFilteredSpotkaniaNaDzien() {
        System.out.println("Na który dzień chcesz wyświetlić spotkania?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();

        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            LocalTime poczatekPrzedzialu = getStartTime();
            LocalTime koniecPrzedzialu = getEndTime();

            Predicate<Spotkanie> predicate = spotkanie ->
                    (spotkanie.getCzasPoczatku().compareTo(poczatekPrzedzialu) >= 0) &&
                            (spotkanie.getCzasZakonczenia().compareTo(koniecPrzedzialu) <= 0);

            getFilteredSpotkania(dzien, predicate);
        }
    }

    public static void getMeetingsByPriorityAndStartingAfterTime() {
        System.out.println("Wyswietlenie spotkan w wybranym dniu, o wybranym priorytecie, zaczynajacych sie nie wczesniej niz podany czas");
        System.out.println("Na który dzień chcesz wyświetlić spotkania?");
        listWeekdays();
        int dzien = scanner.nextInt();
        scanner.nextLine();

        if (dzien < 1 || dzien > 7) {
            System.out.println("Nie ma takiego dnia tygodnia");
        } else {
            String priorytet = getValidatedPriorytet("Podaj priorytet spotkania (normalny, wysoki, najwyższy):");
            LocalTime time = getStartTime();

            Predicate<Spotkanie> predicate = spotkanie ->
                    spotkanie.getPriorytet().equals(priorytet) &&
                            spotkanie.getCzasPoczatku().isAfter(time) || spotkanie.getCzasPoczatku().equals(time);

            getFilteredSpotkania(dzien, predicate);
        }
    }

    public static LocalTime getStartTime() {
        System.out.println("Podaj godzinę początku przedziału czasowego:");
        int godzinaPoczatku = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj minutę początku przedziału czasowego:");
        int minutaPoczatku = scanner.nextInt();
        scanner.nextLine();
        return LocalTime.of(godzinaPoczatku, minutaPoczatku);
    }

    public static LocalTime getEndTime() {
        System.out.println("Podaj godzinę końca przedziału czasowego:");
        int godzinaKonca = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj minutę końca przedziału czasowego:");
        int minutaKonca = scanner.nextInt();
        scanner.nextLine();
        return LocalTime.of(godzinaKonca, minutaKonca);
    }

    public static void introduction() {
        System.out.println("Jestem programem obsługującym kalendarz tygodniowy spotkań");
        System.out.println("1. Dodaj spotkanie na wybrany dzien");
        System.out.println("2. Usun spotkanie z wybranego dnia");
        System.out.println("3. Wyswietl spotkania na wybrany dzien");
        System.out.println("4. Wyswietl wszystkie spotnania w danym dniu o danym priorytecie");
        System.out.println("5. Wyswietl wszystkie spotkania w wybranym dniu, zaczynajace sie nie wczesniej niz podany czas");
        System.out.println("6. Wyswietl spotkania w wybranym dniu, ktore mieszcza sie w podanym przedziale czasowym");
        System.out.println("7. Wyswietlenie spotkan w wybranym dniu, o wybranym priorytecie, zaczynajacych sie nie wczesniej niz podany czas");
        System.out.println("8. Zakoncz program");

    }

    public static void main(String[] args) {
        boolean control = true;
        addFixedMeetingsToMonday();

        while (control) {
            introduction();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addSpotkania();
                case 2 -> deleteMeeting();
                case 3 -> getSpotkaniaNaDzien();
                case 4 -> getMeetingsByPriorityUsingLambda();
                case 5 -> getFilteredSpotkaniaNaDzienStartingBeforeTime();
                case 6 -> getFilteredSpotkaniaNaDzien();
                case 7 -> getMeetingsByPriorityAndStartingAfterTime();
                case 8 -> control = false;
                default -> System.out.println("Nie ma takiej opcji");
            }
        }
    }
}
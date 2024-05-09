import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Kalendarz {
    private ArrayList<Spotkanie>[] spotkaniaNaDzien;
    public Kalendarz() {
        spotkaniaNaDzien = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            spotkaniaNaDzien[i] = new ArrayList<>();
        }
    }

    public void addSpotkanie(int dzien, Spotkanie spotkanie) {
        spotkaniaNaDzien[dzien - 1].add(spotkanie);
    }

    public void usunSpotkanie(int dzien, Spotkanie spotkanie) {
        spotkaniaNaDzien[dzien - 1].remove(spotkanie);
    }

    public ArrayList<Spotkanie> getFilteredSpotkaniaNaDzien(int dzien, Predicate<Spotkanie> predicate) {
        ArrayList<Spotkanie> filteredSpotkania = new ArrayList<>();
        for (Spotkanie spotkanie : spotkaniaNaDzien[dzien - 1]) {
            if (predicate.test(spotkanie)) {
                filteredSpotkania.add(spotkanie);
            }
        }
        return filteredSpotkania;
    }
}
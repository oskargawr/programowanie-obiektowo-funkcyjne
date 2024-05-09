import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Spotkanie> getSpotkaniaNaDzien(int dzien) {
        return spotkaniaNaDzien[dzien - 1];
    }
}

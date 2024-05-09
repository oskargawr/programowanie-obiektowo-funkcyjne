import java.time.LocalTime;

public class Spotkanie {
    private static LocalTime STARTING_HOUR = LocalTime.of(7, 45);
    private String opis;
    private LocalTime czasPoczatku;
    private LocalTime czasZakonczenia;
    private String priorytet;

    public Spotkanie(String opis, LocalTime czasPoczatku, LocalTime czasZakonczenia, String priorytet) {
        this.opis = opis;
        this.czasPoczatku = czasPoczatku;
        this.czasZakonczenia = czasZakonczenia;
        this.priorytet = priorytet;
    }

    public String getOpis() {
        return opis;
    }

    public LocalTime getCzasPoczatku() {
        return czasPoczatku;
    }

    public LocalTime getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public String getPriorytet() {
        return priorytet;
    }

    public static LocalTime getConstantStartingHour() {
        return STARTING_HOUR;
    }

    @Override
    public String toString() {
        return "Spotkanie: " + opis + ", czas początku: " + czasPoczatku + ", czas zakończenia: " + czasZakonczenia + ", priorytet: " + priorytet;
    }
}
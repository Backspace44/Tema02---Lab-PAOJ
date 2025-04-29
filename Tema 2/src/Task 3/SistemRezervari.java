import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clasa de baza pentru exceptiile de rezervare (checked)
class RezervareException extends Exception {
    public RezervareException(String mesaj) {
        super(mesaj);
    }
}

// Exceptie derivata pentru locuri indisponibile
class LocIndisponibilException extends RezervareException {
    private int locSolicitat;

    public LocIndisponibilException(int locSolicitat) {
        super("Locul " + locSolicitat + " nu este disponibil pentru rezervare.");
        this.locSolicitat = locSolicitat;
    }

    public int getLocSolicitat() {
        return locSolicitat;
    }
}

// Exceptie derivata pentru date invalide
class DateInvalideException extends RezervareException {
    private String dataInvalida;

    public DateInvalideException(String dataInvalida, String motiv) {
        super("Data " + dataInvalida + " este invalida: " + motiv);
        this.dataInvalida = dataInvalida;
    }

    public String getDataInvalida() {
        return dataInvalida;
    }
}

// Clasa principala pentru sistemul de rezervari
public class SistemRezervari {
    private Map<LocalDate, List<Integer>> rezervari;
    private int numarTotalLocuri;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public SistemRezervari(int numarTotalLocuri) {
        this.numarTotalLocuri = numarTotalLocuri;
        this.rezervari = new HashMap<>();
    }

    // Metoda pentru verificarea disponibilitatii unui loc
    private boolean esteLocDisponibil(LocalDate data, int loc) {
        if (loc < 1 || loc > numarTotalLocuri) {
            return false; // Locul nu exista in sistem
        }

        List<Integer> locuriRezervate = rezervari.getOrDefault(data, new ArrayList<>());
        return !locuriRezervate.contains(loc);
    }

    // Metoda pentru rezervarea unui loc la o anumita data
    public void rezervaLoc(String dataStr, int loc) throws RezervareException {
        LocalDate data;

        // Verifica validitatea datei
        try {
            data = LocalDate.parse(dataStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateInvalideException(dataStr, "Format incorect. Folositi formatul dd-MM-yyyy.");
        }

        // Verifica daca data este in trecut
        LocalDate astazi = LocalDate.now();
        if (data.isBefore(astazi)) {
            throw new DateInvalideException(dataStr, "Nu puteti face rezervari pentru date din trecut.");
        }

        // Verifica daca data este prea indepartata in viitor (max 1 an)
        if (data.isAfter(astazi.plusYears(1))) {
            throw new DateInvalideException(dataStr, "Rezervarile sunt permise doar pentru urmatoarele 12 luni.");
        }

        // Verifica disponibilitatea locului
        if (!esteLocDisponibil(data, loc)) {
            throw new LocIndisponibilException(loc);
        }

        // Daca totul este in regula, creeaza rezervarea
        rezervari.computeIfAbsent(data, k -> new ArrayList<>()).add(loc);
        System.out.println("Rezervare efectuata cu succes pentru data " + dataStr + ", locul " + loc);
    }

    // Metoda pentru afisarea rezervarilor existente
    public void afiseazaRezervari() {
        if (rezervari.isEmpty()) {
            System.out.println("Nu exista rezervari in sistem.");
            return;
        }

        System.out.println("\nRezervari existente:");
        rezervari.forEach((data, locuri) -> {
            System.out.println(data.format(FORMATTER) + ": locurile " + locuri);
        });
    }

    // Metoda main pentru testarea sistemului
    public static void main(String[] args) {
        SistemRezervari sistem = new SistemRezervari(10);
        String[] dateTest = {"20-05-2025", "15-06-2025", "01-01-2024", "invalid-date", "15-06-2025"};
        int[] locuriTest = {3, 5, 2, 7, 5};

        // Contor pentru rezervari
        int rezervariReusite = 0;

        try {
            // incercam sa facem cateva rezervari de test
            for (int i = 0; i < dateTest.length; i++) {
                try {
                    sistem.rezervaLoc(dateTest[i], locuriTest[i]);
                    rezervariReusite++;
                } catch (LocIndisponibilException e) {
                    System.out.println("Eroare: " + e.getMessage());
                    System.out.println("Sugestie: incercati un alt loc sau o alta data.");
                } catch (DateInvalideException e) {
                    System.out.println("Eroare: " + e.getMessage());
                    if (e.getDataInvalida().equals("invalid-date")) {
                        System.out.println("Exemplu de format corect: 25-12-2025");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("A aparut o eroare generala neasteptata: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Raportul final - se va afisa indiferent daca rezervarile au reusit sau nu
            System.out.println("\n=== RAPORT FINAL ===");
            System.out.println("Total incercari de rezervare: " + dateTest.length);
            System.out.println("Rezervari reusite: " + rezervariReusite);
            System.out.println("Rezervari esuate: " + (dateTest.length - rezervariReusite));
            sistem.afiseazaRezervari();
            System.out.println("===================");
        }
    }
}
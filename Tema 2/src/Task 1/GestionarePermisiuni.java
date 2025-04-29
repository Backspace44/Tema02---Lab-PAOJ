interface PoateEdita {}
interface PoateSterge {}
interface PoateVizualiza {}

class Utilizator {
    private String nume;
    private String email;

    public Utilizator(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + nume;
    }
}

class Administrator extends Utilizator implements PoateEdita, PoateSterge, PoateVizualiza {
    public Administrator(String nume, String email) {
        super(nume, email);
    }
}

class Editor extends Utilizator implements PoateEdita, PoateVizualiza {
    public Editor(String nume, String email) {
        super(nume, email);
    }
}

class Vizitator extends Utilizator implements PoateVizualiza {
    public Vizitator(String nume, String email) {
        super(nume, email);
    }
}

class ActiuneService {
    public boolean poateEdita(Utilizator utilizator) {
        return utilizator instanceof PoateEdita;
    }

    public boolean poateSterge(Utilizator utilizator) {
        return utilizator instanceof PoateSterge;
    }

    public boolean poateVizualiza(Utilizator utilizator) {
        return utilizator instanceof PoateVizualiza;
    }

    public void executaEditare(Utilizator utilizator, String continut) {
        if ((poateEdita(utilizator))) {
            System.out.println(utilizator.getNume() + " a editat: " + continut);
        } else  {
            System.out.println("Eroare: " + utilizator.getNume() + " nu are permisiune de editare!");
        }
    }

    public void executaStergere(Utilizator utilizator, String element) {
        if (poateSterge(utilizator)) {
            System.out.println(utilizator.getNume() + " a sters: " + element);
        } else {
            System.out.println("Eroare: " + utilizator.getNume() + " nu are permisiune de stergere!");
        }
    }

    public void executaVizualizare(Utilizator utilizator, String document) {
        if (poateVizualiza(utilizator)) {
            System.out.println(utilizator.getNume() + " a vizualizat: " + document);
        } else {
            System.out.println("Eroare: " + utilizator.getNume() + " nu are permisiune de vizualizare!");
        }
    }

    public void afiseazaPermisiuni(Utilizator utilizator) {
        System.out.println("\nPermisiuni pentru " + utilizator + ":");
        System.out.println("- Editare: " + (poateEdita(utilizator) ? "DA" : "NU"));
        System.out.println("- Stergere: " + (poateSterge(utilizator) ? "DA" : "NU"));
        System.out.println("- Vizualizare: " + (poateVizualiza(utilizator) ? "DA" : "NU"));
    }
}

public class GestionarePermisiuni {
    public static void main(String[] args) {
        Administrator admin = new Administrator("Ana", "ana@example.com");
        Editor editor = new Editor("Mihai", "mihai@example.com");
        Vizitator vizitator = new Vizitator("Elena", "elena@example.com");

        ActiuneService serviciu = new ActiuneService();

        serviciu.afiseazaPermisiuni(admin);
        serviciu.afiseazaPermisiuni(editor);
        serviciu.afiseazaPermisiuni(vizitator);

        System.out.println("\nTestarea actiunilor:");

        serviciu.executaEditare(admin, "raport anual");
        serviciu.executaStergere(admin, "fisier temporar");
        serviciu.executaVizualizare(admin, "statistici");

        serviciu.executaEditare(editor, "articol stiri");
        serviciu.executaStergere(editor, "comentariu");
        serviciu.executaVizualizare(editor, "articol");

        serviciu.executaEditare(vizitator, "pagina");
        serviciu.executaStergere(vizitator, "imagine");
        serviciu.executaVizualizare(vizitator, "continut public");
    }
}

// Clasa abstracta de baza pentru toate organismele vii
abstract class OrganismViu {
    private String nume;
    private int varsta;

    // Constructor
    public OrganismViu(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    // Getters & Setters
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    // Metode abstracte care trebuie implementate de clasele derivate
    public abstract void respira();
    public abstract void seHraneste();

    // Metoda comuna pentru toate organismele vii
    public void afiseazaInfo() {
        System.out.println("Organism: " + this.getClass().getSimpleName() +
                ", Nume: " + nume +
                ", Varsta: " + varsta);
    }
}

// Clasa Animal extinde OrganismViu si implementeaza unele metode ca finale
abstract class Animal extends OrganismViu {
    private String habitat;
    private boolean domestic;

    // Constructor
    public Animal(String nume, int varsta, String habitat, boolean domestic) {
        super(nume, varsta);
        this.habitat = habitat;
        this.domestic = domestic;
    }

    // Getters & Setters
    public String getHabitat() {
        return habitat;
    }

    public boolean isDomestic() {
        return domestic;
    }

    // Metoda respira() este marcata ca finala - nu poate fi suprascrisa in clasele derivate
    @Override
    public final void respira() {
        System.out.println(getNume() + " respira prin plamani sau branhii.");
    }

    // Metoda abstracta seHraneste() inca trebuie implementata
    @Override
    public abstract void seHraneste();

    // Metoda specifica animalelor
    public void seDeplaseaza() {
        System.out.println(getNume() + " se deplaseaza.");
    }
}

// Clasa abstracta Mamifer deriva din Animal si adauga comportamente specifice
abstract class Mamifer extends Animal {
    private int perioadaGestatie;
    private boolean blana;

    // Constructor
    public Mamifer(String nume, int varsta, String habitat, boolean domestic,
                   int perioadaGestatie, boolean blana) {
        super(nume, varsta, habitat, domestic);
        this.perioadaGestatie = perioadaGestatie;
        this.blana = blana;
    }

    // Getter pentru perioadaGestatie
    public int getPerioadaGestatie() {
        return perioadaGestatie;
    }

    // Metoda specifica mamiferelor
    public void alapteaza() {
        System.out.println(getNume() + " alapteaza puii.");
    }

    // Metoda specifica mamiferelor
    public void arePar() {
        if (blana) {
            System.out.println(getNume() + " are blana deasa.");
        } else {
            System.out.println(getNume() + " are putin par.");
        }
    }
}

// Clasa concreta Urs care extinde Mamifer
class Urs extends Mamifer {
    private boolean hiberneaza;

    // Constructor
    public Urs(String nume, int varsta, String habitat, boolean domestic,
               int perioadaGestatie, boolean hiberneaza) {
        super(nume, varsta, habitat, domestic, perioadaGestatie, true); // Ursii au intotdeauna blana
        this.hiberneaza = hiberneaza;
    }

    // Implementarea metodei abstracte seHraneste()
    @Override
    public void seHraneste() {
        System.out.println(getNume() + " este omnivor si mananca fructe, miere si peste.");
    }

    // Metoda specifica ursilor
    public void hiberneaza() {
        if (hiberneaza) {
            System.out.println(getNume() + " hiberneaza in timpul iernii.");
        } else {
            System.out.println(getNume() + " nu hiberneaza.");
        }
    }
}

// Clasa concreta Delfin care extinde Mamifer
class Delfin extends Mamifer {
    private int adancimeMaxima;

    // Constructor
    public Delfin(String nume, int varsta, String habitat, boolean domestic,
                  int perioadaGestatie, int adancimeMaxima) {
        super(nume, varsta, habitat, domestic, perioadaGestatie, false); // Delfinii nu au blana
        this.adancimeMaxima = adancimeMaxima;
    }

    // Implementarea metodei abstracte seHraneste()
    @Override
    public void seHraneste() {
        System.out.println(getNume() + " este carnivor si se hraneste cu pesti mici si calamari.");
    }

    // Metoda specifica delfinilor
    public void folosireSonar() {
        System.out.println(getNume() + " foloseste sonar pentru a detecta prada la o adancime de pana la " +
                adancimeMaxima + " metri.");
    }

    // Metoda specifica delfinilor - suprascrierea unei metode nefinale din Animal
    @Override
    public void seDeplaseaza() {
        System.out.println(getNume() + " inoata cu o viteza impresionanta.");
    }
}

// Clasa principala pentru testarea ierarhiei
public class SistemOrganismeVii {
    public static void main(String[] args) {
        // Crearea unei liste de organisme pentru demonstrarea polimorfismului
        OrganismViu[] organisme = new OrganismViu[4];

        // Instantierea obiectelor
        organisme[0] = new Urs("Balu", 5, "Padure", false, 7, true);
        organisme[1] = new Delfin("Flipper", 10, "Ocean", false, 12, 200);
        organisme[2] = new Urs("Teddy", 3, "Zoo", true, 7, false);
        organisme[3] = new Delfin("Ecco", 8, "Delfinariu", true, 12, 150);

        // Demonstrarea polimorfismului prin iterarea listei
        System.out.println("===== Demonstrarea polimorfismului =====");
        for (OrganismViu organism : organisme) {
            // Afiseaza informatii despre organism (metoda comuna)
            organism.afiseazaInfo();

            // Apelarea metodelor abstracte implementate
            organism.respira();
            organism.seHraneste();

            // Utilizarea instanceof pentru a apela metode specifice
            if (organism instanceof Mamifer) {
                Mamifer mamifer = (Mamifer) organism;
                mamifer.arePar();
                mamifer.alapteaza();

                // Apelarea metodelor specifice pentru fiecare tip de mamifer
                if (mamifer instanceof Urs) {
                    Urs urs = (Urs) mamifer;
                    urs.hiberneaza();
                } else if (mamifer instanceof Delfin) {
                    Delfin delfin = (Delfin) mamifer;
                    delfin.folosireSonar();
                    delfin.seDeplaseaza(); // Metoda suprascrisa
                }
            }

            System.out.println("-------------------------------");
        }
    }
}
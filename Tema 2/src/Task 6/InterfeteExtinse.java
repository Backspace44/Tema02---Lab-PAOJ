// Interfata de baza Dispozitiv
interface Dispozitiv {
    // Metode abstracte care trebuie implementate
    void porneste();
    void opreste();

    // Metoda default
    default String status() {
        // Apeleaza metoda privata interna
        return getStatusPrefix() + " in starea: " + getStareInterna();
    }

    // Metoda statica
    static void descriereGenerala() {
        System.out.println("Acesta este un dispozitiv electronic general.");
    }

    // Metoda privata folosita intern de metodele interfetei
    private String getStareInterna() {
        return "necunoscuta";
    }

    // O alta metoda privata pentru demonstratie
    private String getStatusPrefix() {
        return "Dispozitivul este";
    }
}

// Interfata extinsa Smart
interface Smart extends Dispozitiv {
    void conectareInternet();
    void descarcaActualizari();

    @Override
    default String status() {
        return "Smart: " + Dispozitiv.super.status() + ", conectat la internet";
    }
}

// Interfata extinsa Conectabil
interface Conectabil extends Dispozitiv {
    void conectareDispozitiv(Dispozitiv altDispozitiv);

    @Override
    default String status() {
        return "Conectabil: " + Dispozitiv.super.status();
    }
}

// Clasa Telefon care implementeaza interfata Smart
class Telefon implements Smart {
    private boolean pornit = false;
    private boolean conectatInternet = false;

    @Override
    public void porneste() {
        pornit = true;
        System.out.println("Telefonul a fost pornit.");
    }

    @Override
    public void opreste() {
        pornit = false;
        conectatInternet = false;
        System.out.println("Telefonul a fost oprit.");
    }

    @Override
    public void conectareInternet() {
        if (pornit) {
            conectatInternet = true;
            System.out.println("Telefonul s-a conectat la internet.");
        } else {
            System.out.println("Nu se poate conecta la internet - telefonul este oprit!");
        }
    }

    @Override
    public void descarcaActualizari() {
        if (conectatInternet) {
            System.out.println("Se descarca actualizari pentru telefon...");
        } else {
            System.out.println("Nu se pot descarca actualizari - telefonul nu este conectat la internet!");
        }
    }
}

// Clasa Smartwatch care implementeaza interfata Smart
class Smartwatch implements Smart {
    private boolean pornit = false;
    private boolean conectatInternet = false;

    @Override
    public void porneste() {
        pornit = true;
        System.out.println("Smartwatch-ul a fost pornit.");
    }

    @Override
    public void opreste() {
        pornit = false;
        conectatInternet = false;
        System.out.println("Smartwatch-ul a fost oprit.");
    }

    @Override
    public void conectareInternet() {
        if (pornit) {
            conectatInternet = true;
            System.out.println("Smartwatch-ul s-a conectat la internet.");
        } else {
            System.out.println("Nu se poate conecta la internet - smartwatch-ul este oprit!");
        }
    }

    @Override
    public void descarcaActualizari() {
        if (conectatInternet) {
            System.out.println("Se descarca actualizari pentru smartwatch...");
        } else {
            System.out.println("Nu se pot descarca actualizari - smartwatch-ul nu este conectat la internet!");
        }
    }
}

// Clasa Televizor care implementeaza interfata Conectabil
class Televizor implements Conectabil {
    private boolean pornit = false;
    private Dispozitiv dispozitivConectat = null;

    @Override
    public void porneste() {
        pornit = true;
        System.out.println("Televizorul a fost pornit.");
    }

    @Override
    public void opreste() {
        pornit = false;
        dispozitivConectat = null;
        System.out.println("Televizorul a fost oprit.");
    }

    @Override
    public void conectareDispozitiv(Dispozitiv altDispozitiv) {
        if (pornit) {
            dispozitivConectat = altDispozitiv;
            System.out.println("Dispozitiv conectat la televizor.");
        } else {
            System.out.println("Nu se poate conecta dispozitivul - televizorul este oprit!");
        }
    }

    // Metoda specifica televizorului
    public void schimbaCanal(int numarCanal) {
        if (pornit) {
            System.out.println("S-a schimbat canalul la: " + numarCanal);
        } else {
            System.out.println("Nu se poate schimba canalul - televizorul este oprit!");
        }
    }
}

// Clasa Main pentru demonstrarea tuturor tipurilor de apeluri posibile
public class InterfeteExtinse {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAtIE APELURI INTERFEtE sI METODE ===\n");

        // Demonstrare metoda statica din interfata
        System.out.println("1. Apel metoda statica din interfata:");
        Dispozitiv.descriereGenerala();
        System.out.println();

        // Crearea instantelor
        System.out.println("2. Crearea si utilizarea instantelor:");
        Telefon telefon = new Telefon();
        Smartwatch smartwatch = new Smartwatch();
        Televizor televizor = new Televizor();

        // Demonstrare pentru Telefon (implementeaza Smart)
        System.out.println("\n=== TELEFON (implementeaza Smart) ===");
        // Metode abstracte din Dispozitiv
        telefon.porneste();
        System.out.println("Status telefon: " + telefon.status()); // Metoda default suprascrisa in Smart
        telefon.conectareInternet(); // Metoda specifica interfetei Smart
        telefon.descarcaActualizari(); // Metoda specifica interfetei Smart
        telefon.opreste();

        // Demonstrare pentru Smartwatch (implementeaza Smart)
        System.out.println("\n=== SMARTWATCH (implementeaza Smart) ===");
        smartwatch.porneste();
        System.out.println("Status smartwatch: " + smartwatch.status()); // Metoda default suprascrisa in Smart
        smartwatch.conectareInternet();
        smartwatch.descarcaActualizari();
        smartwatch.opreste();

        // Demonstrare pentru Televizor (implementeaza Conectabil)
        System.out.println("\n=== TELEVIZOR (implementeaza Conectabil) ===");
        televizor.porneste();
        System.out.println("Status televizor: " + televizor.status()); // Metoda default suprascrisa in Conectabil
        televizor.conectareDispozitiv(telefon); // Metoda specifica interfetei Conectabil
        televizor.schimbaCanal(5); // Metoda specifica clasei Televizor
        televizor.opreste();

        // Demonstrare polimorfism
        System.out.println("\n=== DEMONSTRARE POLIMORFISM ===");
        // Array de dispozitive
        Dispozitiv[] dispozitive = new Dispozitiv[3];
        dispozitive[0] = telefon;
        dispozitive[1] = smartwatch;
        dispozitive[2] = televizor;

        // Iteram prin dispozitive si apelam metodele comune
        for (Dispozitiv d : dispozitive) {
            d.porneste();
            System.out.println(d.status()); // Observam comportamentul polimorfic al metodei default
            d.opreste();
            System.out.println();
        }

        // Demonstrare cast pentru a accesa metode specifice
        System.out.println("=== DEMONSTRARE CAST sI METODE SPECIFICE ===");
        for (Dispozitiv d : dispozitive) {
            d.porneste();
            if (d instanceof Smart) {
                System.out.println("Dispozitiv Smart detectat!");
                Smart smartDevice = (Smart) d;
                smartDevice.conectareInternet();
                smartDevice.descarcaActualizari();
            } else if (d instanceof Conectabil) {
                System.out.println("Dispozitiv Conectabil detectat!");
                Conectabil conectabilDevice = (Conectabil) d;
                // Conectam cu primul dispozitiv din lista
                conectabilDevice.conectareDispozitiv(dispozitive[0]);
            }
            d.opreste();
            System.out.println();
        }
    }
}
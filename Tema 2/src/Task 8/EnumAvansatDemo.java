/**
 * Implementarea cerintelor pentru enum-uri avansate cu metode si constructori personalizati
 */
public class EnumAvansatDemo {

    /**
     * Enum-ul NivelAcces cu valori, constructori si metode personalizate
     */
    enum NivelAcces {
        // Definirea valorilor enum-ului cu parametri pentru constructor
        ADMIN(1, "Acces complet"),
        EDITOR(2, "Acces de editare"),
        USER(3, "Acces de baza"),
        GUEST(4, "Acces limitat");

        // Atributele enum-ului
        private final int cod;
        private final String descriere;

        /**
         * Constructor personalizat pentru enum
         * @param cod codul numeric al nivelului de acces
         * @param descriere descrierea nivelului de acces
         */
        NivelAcces(int cod, String descriere) {
            this.cod = cod;
            this.descriere = descriere;
        }

        /**
         * Metoda pentru obtinerea descrierii
         * @return descrierea nivelului de acces
         */
        public String getDescriere() {
            return descriere;
        }

        /**
         * Metoda pentru obtinerea codului
         * @return codul nivelului de acces
         */
        public int getCod() {
            return cod;
        }

        /**
         * Metoda statica pentru obtinerea unui NivelAcces bazat pe cod
         * @param cod codul cautat
         * @return NivelAcces corespunzator sau null daca nu exista
         */
        public static NivelAcces dinCod(int cod) {
            // Parcurgem toate valorile enum-ului pentru a gasi cea cu codul potrivit
            for (NivelAcces nivel : values()) {
                if (nivel.getCod() == cod) {
                    return nivel;
                }
            }
            return null; // Returnam null daca nu gasim un nivel potrivit
        }

        /**
         * Suprascrierea metodei toString
         */
        @Override
        public String toString() {
            return name() + " (cod: " + cod + ", descriere: " + descriere + ")";
        }
    }

    /**
     * Clasa ContUtilizator care are ca atribut un NivelAcces
     */
    static class ContUtilizator {
        private String numeUtilizator;
        private String email;
        private NivelAcces nivelAcces;

        /**
         * Constructor pentru ContUtilizator
         * @param numeUtilizator numele utilizatorului
         * @param email adresa de email
         * @param nivelAcces nivelul de acces al utilizatorului
         */
        public ContUtilizator(String numeUtilizator, String email, NivelAcces nivelAcces) {
            this.numeUtilizator = numeUtilizator;
            this.email = email;
            this.nivelAcces = nivelAcces;
        }

        /**
         * Metoda pentru a verifica daca utilizatorul are un nivel de acces egal sau superior
         * @param nivelNecesar nivelul minim necesar
         * @return true daca utilizatorul are nivelul necesar, false in caz contrar
         */
        public boolean areAccesSuperior(NivelAcces nivelNecesar) {
            // Comparam enum-urile dupa ordinea lor naturala (ordinea definita in enum)
            // Cu cat ordinea e mai mica, cu atat nivelul de acces este mai mare
            return this.nivelAcces.ordinal() <= nivelNecesar.ordinal();
        }

        /**
         * Metoda pentru a verifica daca utilizatorul are un nivel de acces pe baza codului
         * @param codNivel codul nivelului minim necesar
         * @return true daca utilizatorul are nivelul necesar, false in caz contrar
         */
        public boolean areAccesSuperiorCod(int codNivel) {
            NivelAcces nivelNecesar = NivelAcces.dinCod(codNivel);
            if (nivelNecesar == null) {
                return false; // Codul nu este valid
            }
            return areAccesSuperior(nivelNecesar);
        }

        /**
         * Getter pentru nivelul de acces
         * @return nivelul de acces al utilizatorului
         */
        public NivelAcces getNivelAcces() {
            return nivelAcces;
        }

        /**
         * Getter pentru numele utilizatorului
         * @return numele utilizatorului
         */
        public String getNumeUtilizator() {
            return numeUtilizator;
        }

        /**
         * Metoda toString pentru afisarea informatiilor despre cont
         */
        @Override
        public String toString() {
            return "ContUtilizator{" +
                    "numeUtilizator='" + numeUtilizator + '\'' +
                    ", email='" + email + '\'' +
                    ", nivelAcces=" + nivelAcces +
                    '}';
        }
    }

    /**
     * Metoda main pentru demonstrarea functionalitatii
     */
    public static void main(String[] args) {
        System.out.println("==== DEMONSTRAtIE ENUM-URI AVANSATE ====\n");

        // Afisarea valorilor enum-ului si a atributelor lor
        System.out.println("1. Valorile enum-ului NivelAcces:");
        for (NivelAcces nivel : NivelAcces.values()) {
            System.out.println(" - " + nivel.name() + ": cod=" + nivel.getCod() + ", descriere='" + nivel.getDescriere() + "'");
        }
        System.out.println();

        // Demonstrarea metodei dinCod()
        System.out.println("2. Demonstrarea metodei dinCod():");
        int codCautat = 3;
        NivelAcces nivelGasit = NivelAcces.dinCod(codCautat);
        if (nivelGasit != null) {
            System.out.println(" - Pentru codul " + codCautat + " s-a gasit nivelul: " + nivelGasit);
        } else {
            System.out.println(" - Nu s-a gasit niciun nivel pentru codul " + codCautat);
        }

        int codInexistent = 10;
        NivelAcces nivelInexistent = NivelAcces.dinCod(codInexistent);
        if (nivelInexistent != null) {
            System.out.println(" - Pentru codul " + codInexistent + " s-a gasit nivelul: " + nivelInexistent);
        } else {
            System.out.println(" - Nu s-a gasit niciun nivel pentru codul " + codInexistent);
        }
        System.out.println();

        // Crearea unor conturi cu diferite niveluri de acces
        System.out.println("3. Crearea conturilor de utilizator:");
        ContUtilizator[] conturi = {
                new ContUtilizator("admin", "admin@example.com", NivelAcces.ADMIN),
                new ContUtilizator("editor", "editor@example.com", NivelAcces.EDITOR),
                new ContUtilizator("user1", "user1@example.com", NivelAcces.USER),
                new ContUtilizator("guest", "guest@example.com", NivelAcces.GUEST)
        };

        for (ContUtilizator cont : conturi) {
            System.out.println(" - " + cont);
        }
        System.out.println();

        // Filtrarea conturilor cu nivel de acces superior unui anumit nivel
        System.out.println("4. Filtrarea conturilor cu nivel de acces superior sau egal cu USER:");
        NivelAcces nivelMinim = NivelAcces.USER;
        System.out.println(" - Conturi cu acces minim " + nivelMinim + ":");

        for (ContUtilizator cont : conturi) {
            if (cont.areAccesSuperior(nivelMinim)) {
                System.out.println("    * " + cont.getNumeUtilizator() + " (" + cont.getNivelAcces() + ")");
            }
        }
        System.out.println();

        // Filtrarea conturilor dupa codul nivelului de acces
        System.out.println("5. Filtrarea conturilor cu nivel de acces superior sau egal cu codul 2 (EDITOR):");
        int codMinim = 2;
        System.out.println(" - Conturi cu acces minim cod " + codMinim + ":");

        for (ContUtilizator cont : conturi) {
            if (cont.areAccesSuperiorCod(codMinim)) {
                System.out.println("    * " + cont.getNumeUtilizator() + " (" + cont.getNivelAcces() + ")");
            }
        }
        System.out.println();

        // Demonstrarea ordinii naturale a enum-urilor
        System.out.println("6. Ordinea naturala a enum-urilor NivelAcces:");
        for (NivelAcces nivel : NivelAcces.values()) {
            System.out.println(" - " + nivel.name() + " are ordinea: " + nivel.ordinal());
        }
        System.out.println();

        // Compararea directa a doua enum-uri
        System.out.println("7. Compararea directa a enum-urilor:");
        NivelAcces nivel1 = NivelAcces.ADMIN;
        NivelAcces nivel2 = NivelAcces.USER;

        System.out.println(" - " + nivel1 + " comparativ cu " + nivel2 + ":");
        System.out.println("   * nivel1 == nivel2: " + (nivel1 == nivel2));
        System.out.println("   * nivel1.compareTo(nivel2): " + nivel1.compareTo(nivel2));
        System.out.println("   * nivel1.ordinal() < nivel2.ordinal(): " + (nivel1.ordinal() < nivel2.ordinal()));

        // in exemplul nostru, ordinal mic inseamna nivel de acces mare, deci ADMIN (0) > USER (2)
        if (nivel1.ordinal() < nivel2.ordinal()) {
            System.out.println("   * " + nivel1 + " are un nivel de acces mai inalt decat " + nivel2);
        } else {
            System.out.println("   * " + nivel2 + " are un nivel de acces mai inalt decat " + nivel1);
        }
    }
}
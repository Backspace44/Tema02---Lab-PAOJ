import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class GestionareProduse {

    /**
     * Clasa Produs cu atributele cod, nume si pret
     */
    static class Produs {
        private String cod;
        private String nume;
        private double pret;

        /**
         * Constructor pentru clasa Produs
         */
        public Produs(String cod, String nume, double pret) {
            this.cod = cod;
            this.nume = nume;
            this.pret = pret;
        }

        /**
         * Suprascrierea metodei equals() pentru a compara produsele dupa cod
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Produs produs = (Produs) o;
            return Objects.equals(cod, produs.cod);
        }

        /**
         * Suprascrierea metodei hashCode() pentru a genera un hash bazat pe cod
         */
        @Override
        public int hashCode() {
            return Objects.hash(cod);
        }

        /**
         * Metoda toString() pentru afisarea informatiilor despre produs
         */
        @Override
        public String toString() {
            return "Produs{" +
                    "cod='" + cod + '\'' +
                    ", nume='" + nume + '\'' +
                    ", pret=" + pret +
                    '}';
        }

        // Getteri pentru atributele clasei Produs
        public String getCod() {
            return cod;
        }

        public String getNume() {
            return nume;
        }

        public double getPret() {
            return pret;
        }
    }

    public static void main(String[] args) {
        System.out.println("==== DEMONSTRAtIE GESTIONARE PRODUSE ====\n");

        // Crearea unor produse pentru test
        Produs p1 = new Produs("A001", "Laptop", 3500.0);
        Produs p2 = new Produs("A002", "Monitor", 1200.0);
        Produs p3 = new Produs("A003", "Telefon", 2000.0);
        Produs p4 = new Produs("A001", "Laptop Gaming", 4000.0); // Acelasi cod ca p1

        // Crearea unui HashSet pentru produse
        System.out.println("1. Adaugarea produselor in HashSet si demonstrarea ca duplicatele nu se adauga:");
        HashSet<Produs> produse = new HashSet<>();

        System.out.println("Adaugare p1: " + produse.add(p1));
        System.out.println("Adaugare p2: " + produse.add(p2));
        System.out.println("Adaugare p3: " + produse.add(p3));
        System.out.println("Adaugare p4 (acelasi cod ca p1): " + produse.add(p4));

        System.out.println("\nProduse in HashSet:");
        for (Produs p : produse) {
            System.out.println(" - " + p);
        }
        System.out.println("Numar total produse in HashSet: " + produse.size());

        // Crearea unui HashMap pentru produse si stocuri
        System.out.println("\n2. Crearea unui HashMap care mapeaza un produs la stocul disponibil:");
        HashMap<Produs, Integer> stocuri = new HashMap<>();

        stocuri.put(p1, 10);
        stocuri.put(p2, 15);
        stocuri.put(p3, 20);
        stocuri.put(p4, 5); // Va suprascrie stocul pentru p1 deoarece au acelasi cod

        System.out.println("\nProduse si stocuri in HashMap:");
        for (Map.Entry<Produs, Integer> entry : stocuri.entrySet()) {
            System.out.println(" - " + entry.getKey() + " => Stoc: " + entry.getValue());
        }

        // Demonstrarea utilizarii metodei entrySet()
        System.out.println("\n3. Utilizarea metodei entrySet() pentru a itera prin HashMap:");
        for (Map.Entry<Produs, Integer> entry : stocuri.entrySet()) {
            System.out.println(" - Produsul " + entry.getKey().getNume() +
                    " (cod: " + entry.getKey().getCod() +
                    ") are un stoc de " + entry.getValue() + " bucati.");
        }

        // Demonstrarea utilizarii metodei forEach()
        System.out.println("\n4. Utilizarea metodei forEach() pentru a itera prin HashMap:");
        stocuri.forEach((produs, stoc) -> {
            System.out.println(" - Produsul " + produs.getNume() +
                    " (cod: " + produs.getCod() +
                    ") are un stoc de " + stoc + " bucati.");
        });

        // Demonstrarea metodei forEach() pentru HashSet
        System.out.println("\n5. Utilizarea metodei forEach() pentru a itera prin HashSet:");
        produse.forEach(produs -> {
            System.out.println(" - " + produs.getNume() +
                    " (cod: " + produs.getCod() +
                    ", pret: " + produs.getPret() + ")");
        });

        // Verificarea existentei unui produs specific in HashSet
        System.out.println("\n6. Verificarea existentei unui produs in HashSet:");
        Produs produsCautat = new Produs("A001", "Nu conteaza numele", 0.0);
        System.out.println(" - Produsul cu codul " + produsCautat.getCod() +
                (produse.contains(produsCautat) ? " exista" : " nu exista") + " in HashSet.");

        // Obtinerea stocului pentru un produs specific
        System.out.println("\n7. Obtinerea stocului pentru un produs specific:");
        Integer stocGasit = stocuri.get(produsCautat);
        System.out.println(" - Stocul pentru produsul cu codul " + produsCautat.getCod() +
                (stocGasit != null ? " este: " + stocGasit : " nu a fost gasit."));
    }
}
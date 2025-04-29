public final class Cash extends MetodaPlata {
    private String locatie;

    public Cash(double suma, String beneficiar, String locatie) {
        super(suma, beneficiar);
        this.locatie = locatie;
    }

    @Override
    public boolean valideaza() {
        return true;
    }

    public String getConfirmare() {
        return "Tranzactia cash este instanta la locatia: " + locatie;
    }
}

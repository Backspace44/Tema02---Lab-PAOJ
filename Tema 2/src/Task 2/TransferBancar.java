public final class TransferBancar extends MetodaPlata {
    private String iban;
    private String bancaDestinatar;

    public TransferBancar(double suma, String beneficiar, String iban, String bancaDestinatar) {
        super(suma, beneficiar);
        this.iban = iban;
        this.bancaDestinatar = bancaDestinatar;
    }

    @Override
    public boolean valideaza() {
        return verificaIBAN();
    }

    private boolean verificaIBAN() {
        return iban != null && iban.matches("RO\\d{22}");
    }
}

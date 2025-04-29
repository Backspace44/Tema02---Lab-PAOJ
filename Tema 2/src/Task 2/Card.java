public final class Card extends MetodaPlata {
    private String numarCard;
    private String cvv;
    private String dataExpirare;

    public Card(double suma, String beneficiar, String numarCard, String cvv, String dataExpirare) {
        super(suma, beneficiar);
        this.numarCard = numarCard;
        this.cvv = cvv;
        this.dataExpirare = dataExpirare;
    }

    @Override
    public boolean valideaza() {
        return validareCVV() && validareDataExpirare();
    }

    private boolean validareCVV() {
        return cvv != null && cvv.matches("\\d{3}");
    }

    private boolean validareDataExpirare() {
        return dataExpirare != null && dataExpirare.matches("\\d{2}/\\d{2}");
    }
}

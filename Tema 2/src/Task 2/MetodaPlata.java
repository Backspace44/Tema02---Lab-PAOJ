public sealed class MetodaPlata permits Card, Cash, TransferBancar {
    private double suma;
    private String beneficiar;

    public MetodaPlata(double suma, String beneficiar) {
        this.suma = suma;
        this.beneficiar = beneficiar;
    }

    public double getSuma() {
        return suma;
    }

    public String getBeneficiar() {
        return beneficiar;
    }

    public boolean valideaza() {
        return true;
    }
}

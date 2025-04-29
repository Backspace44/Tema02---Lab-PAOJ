public class TestSistemPlati {
    public static void main(String[] args) {
        ServiciuPlati serviciu = new ServiciuPlati();

        Card card1 = new Card(150.75, "Magazin Online", "1234567890123456", "123", "12/25");
        serviciu.proceseazaPlata(card1);

        Card card2 = new Card(299.99, "Servicii Web", "9876543210987654", "12", "06/24");
        serviciu.proceseazaPlata(card2);

        Cash cash = new Cash(75.50, "Librarie", "Bucuresti, Sector 1");
        serviciu.proceseazaPlata(cash);

        TransferBancar transfer1 = new TransferBancar(1000.00, "Furnizor Energie", "RO12345678901234567890", "BancaX");
        serviciu.proceseazaPlata(transfer1);

        TransferBancar transfer2 = new TransferBancar(500.00, "Chirie", "RO123456", "BancaY");
        serviciu.proceseazaPlata(transfer2);
    }
}
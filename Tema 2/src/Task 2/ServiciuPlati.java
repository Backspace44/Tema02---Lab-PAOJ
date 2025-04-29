public class ServiciuPlati {
    public void proceseazaPlata(MetodaPlata metodaPlata) {
        System.out.println("Procesare plata de " + metodaPlata.getSuma() + "RON catre " + metodaPlata.getBeneficiar());

        if (metodaPlata instanceof Card card) {
            if (card.valideaza()) {
                System.out.println("Plata cu cardul validata si procesata cu succes!");
            } else {
                System.out.println("Eroare: Card invalid (verificati CVV si data de expirare)");
            }
        } else if (metodaPlata instanceof Cash cash) {
            System.out.println(cash.getConfirmare());
            System.out.println("Plata cash procesata cu succes!");
        } else if (metodaPlata instanceof TransferBancar transfer) {
            if (transfer.valideaza()) {
                System.out.println("Transfer bancar validat si in curs de procesare!");
                System.out.println("Transferul va fi procesat in 1-2 zile lucratoare.");
            } else {
                System.out.println("Eroare: IBAN invalid!");
            }
        }

        System.out.println("-------------------------------------");
    }
}

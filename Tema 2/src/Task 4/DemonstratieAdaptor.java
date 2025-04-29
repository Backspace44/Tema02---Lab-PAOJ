// Clasa externa care reprezinta un sistem existent care proceseaza date in format XML
// Presupunem ca aceasta clasa nu poate fi modificata deoarece face parte dintr-o biblioteca terta
class SistemExistent {
    // Metoda care afiseaza date in format XML
    public void afiseazaXML(String xml) {
        System.out.println("Sistem existent proceseaza XML:");
        System.out.println(xml);
        System.out.println("---------------------------------");
    }
}

// Sistemul nostru propriu care genereaza date in format JSON
class SistemPropriu {
    // Metoda care genereaza date in format JSON
    public String genereazaJSON() {
        // in practica, aceasta metoda ar putea construi un JSON mai complex
        // din date din baza de date, calcule, etc.
        return "{\n" +
                "  \"utilizator\": {\n" +
                "    \"id\": 123,\n" +
                "    \"nume\": \"Ion Popescu\",\n" +
                "    \"email\": \"ion.popescu@example.com\",\n" +
                "    \"adresa\": {\n" +
                "      \"strada\": \"Strada Principala\",\n" +
                "      \"numar\": 42,\n" +
                "      \"oras\": \"Bucuresti\",\n" +
                "      \"cod_postal\": \"012345\"\n" +
                "    },\n" +
                "    \"activ\": true,\n" +
                "    \"data_inregistrare\": \"2023-03-15\"\n" +
                "  }\n" +
                "}";
    }
}

// Clasa adaptor care converteste JSON in XML si trimite la sistemul existent
class AdaptorJsonToXml {
    private SistemExistent sistemExistent;

    // Constructor care primeste o instanta a sistemului existent
    public AdaptorJsonToXml(SistemExistent sistemExistent) {
        this.sistemExistent = sistemExistent;
    }

    // Metoda care transforma JSON in XML si trimite la sistemul existent
    public void trimiteDate(String jsonString) {
        // Convertim JSON in XML (o implementare simplificata)
        String xmlString = convertesteJsonLaXml(jsonString);

        // Trimitem XML-ul catre sistemul existent
        sistemExistent.afiseazaXML(xmlString);
    }

    // Metoda care converteste un string JSON intr-un string XML
    // Aceasta este o implementare simplificata si demonstrativa
    private String convertesteJsonLaXml(String jsonString) {
        // in realitate, ar trebui sa folosim o biblioteca precum Jackson sau GSON
        // pentru parsing JSON si apoi sa construim XML-ul corespunzator

        // Aceasta implementare este doar demonstrativa
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

        // Simplificare extrema - inlocuim caracterele JSON cu taguri XML
        // Atentie: Aceasta abordare este foarte naiva si nu va functiona pentru JSON-uri complexe!
        xml += jsonString
                .replace("{", "<")
                .replace("}", ">")
                .replace("\"", "")
                .replace(": ", ">")
                .replace(",\n", "\n")
                .replace("{\n  ", "<")
                .replace("\n  ", "\n    <")
                .replace("\n    }", "\n    </utilizator>")
                .replace("\n}", "\n</utilizator>");

        // Corectam manual unele probleme specifice exemplului nostru
        xml = xml.replace("<utilizator>", "<utilizator>");
        xml = xml.replace("<adresa>", "<adresa>");
        xml = xml.replace("</adresa>", "</adresa>");

        return xml;
    }
}

// Clasa principala care demonstreaza utilizarea adaptorului
public class DemonstratieAdaptor {
    public static void main(String[] args) {
        // Cream o instanta a sistemului existent
        SistemExistent sistemExistent = new SistemExistent();

        // Cream o instanta a sistemului nostru propriu
        SistemPropriu sistemPropriu = new SistemPropriu();

        // Cream adaptorul care va face legatura dintre cele doua sisteme
        AdaptorJsonToXml adaptor = new AdaptorJsonToXml(sistemExistent);

        // Generam date JSON din sistemul nostru
        String jsonDate = sistemPropriu.genereazaJSON();
        System.out.println("Date generate in format JSON:");
        System.out.println(jsonDate);
        System.out.println("---------------------------------");

        // Folosim adaptorul pentru a converti si trimite datele catre sistemul existent
        System.out.println("Trimitere date prin adaptor...");
        adaptor.trimiteDate(jsonDate);

        // Demonstram ca sistemul existent poate fi folosit si direct, fara adaptor
        System.out.println("Folosirea directa a sistemului existent:");
        String xmlExemplu = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<mesaj>Test direct</mesaj>";
        sistemExistent.afiseazaXML(xmlExemplu);
    }
}

/**
 * EXPLICAtIE DE CE PATTERN-UL ADAPTOR ESTE UTIL iN APLICAtII REALE:
 *
 * Pattern-ul Adaptor este extrem de util in multe scenarii din lumea reala:
 *
 * 1. Integrarea cu sisteme legacy - Atunci cand trebuie sa integram un sistem nou
 *    cu unul vechi care foloseste formate sau protocoale diferite, adaptorul permite
 *    comunicarea fara a modifica niciun sistem.
 *
 * 2. Utilizarea bibliotecilor terte - Cand folosim biblioteci externe care au interfete
 *    incompatibile cu sistemul nostru, adaptorul poate "traduce" intre ele.
 *
 * 3. Migrarea graduala a sistemelor - in timpul unei migrari de la un sistem vechi la unul nou,
 *    adaptoarele permit functionarea in paralel si tranzitia treptata.
 *
 * 4. Interoperabilitatea intre sisteme - Adaptorul faciliteaza comunicarea intre sisteme
 *    care folosesc formate diferite (JSON, XML, CSV, etc.) sau protocoale diferite.
 *
 * 5. Testare - Adaptoarele pot fi folosite pentru a izola componentele in timpul testarii,
 *    inlocuind dependentele reale cu mock-uri.
 *
 * 6. Conformitate cu principiul Open/Closed - Pattern-ul Adaptor permite extinderea
 *    functionalitatii fara a modifica codul existent.
 *
 * in exemplul nostru, adaptorul permite integrarea unui sistem care foloseste JSON
 * cu unul care foloseste XML, fara a modifica implementarea niciunui sistem.
 * Acest lucru este foarte comun in practica, mai ales cand se integreaza
 * sisteme dezvoltate de organizatii diferite sau in perioade diferite.
 */
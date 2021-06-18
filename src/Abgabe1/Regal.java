package Abgabe1;

import java.util.ArrayList;

public class Regal {


    final ArrayList<Produkt> produkte;
    String bezeichnung;

    public Regal(String bezeichnung) {
        this.produkte = new ArrayList<Produkt>();
    }

    public void produktHinzufuegenOderAnlegen(Produkt produktAusEingabe) {
        Produkt currentProduktimBestand = null;
        Produkt currentProduktPreis = null;


        for (int i = 0; i < produkte.size(); i++) {
            if (produkte.get(i).dername.equals(produktAusEingabe.dername)) {
                currentProduktimBestand = produkte.get(i);

            }
        }
        for (int a = 0; a < produkte.size(); a++) {
            if (produkte.get(a).preis != produktAusEingabe.preis) {
                currentProduktPreis = produkte.get(a);
                break;
            }
        }
        if (currentProduktimBestand != null) {
            currentProduktimBestand.bestand = currentProduktimBestand.bestand + produktAusEingabe.bestand;
        }
        if (currentProduktPreis != null) {
            currentProduktPreis.preis = produktAusEingabe.preis;
            System.out.println("Der Preis wurde aktualisiert!");
        } else produkte.add(produktAusEingabe);
    }

    public Produkt produktEntnehmen(String dername, int anzahl) {

        Produkt currentProduktimBestand = null;

        for (int i = 0; i < produkte.size(); i++) {
            if (produkte.get(i).dername.equals(dername) && produkte.get(i).bestand >= anzahl) {
                currentProduktimBestand = produkte.get(i);
                System.out.println("Das Produkt wurde entfernt...");
                break;
            } else System.out.println("Das funktioniert leider nicht...");
        }
        if (currentProduktimBestand != null && currentProduktimBestand.bestand >= anzahl) {
            currentProduktimBestand.bestand = currentProduktimBestand.bestand - anzahl;
        }

        return null;
    }

    Produkt getLagerbestand(String dername) {

        for (int a = 0; a < produkte.size(); a++) {
            if (produkte.get(a).dername.equals(dername)) {
                return produkte.get(a);
            }

        }
        return null;
    }

    Produkt getPreisProdukt(String dername) {

        for (int a = 0; a < produkte.size(); a++) {
            if (produkte.get(a).dername.equals(dername)) {
                return produkte.get(a);
            }

        }
        return null;
    }

    Produkt getInfo() {

        for (int i = 0; i < produkte.size(); i++) {

            return produkte.get(i);

        }
        return null;

    }


    Produkt getProdukt(String dername) {

        for (int b = 0; b < produkte.size(); b++) {
            if (produkte.get(b).dername.equals(dername)) {
                return produkte.get(b);
            }
        }
        return null;
    }


}

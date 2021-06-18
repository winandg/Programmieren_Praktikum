package Abgabe1;

import java.util.ArrayList;
import java.util.Scanner;

public class Kiosk {

    final String HELP = "/help";
    final String EXIT = "/exit";
    final String ADD = "/add";
    final String REMOVE = "/remove";
    final String VERSCHIEBEN = "/move";
    final String INFO = "/info";
    final String PRODUKTSTATUS = "/produktStatus";
    final String LAGERBESTAND = "/lagerBestand";
    final String PREIS = "/preis";
    final String STANDARTEINKAUF = "/wochenEinkauf";


    static Scanner s = new Scanner(System.in);
    String gelesenerBefehl;
    static int welchesRegal;

    static Regal erstesRegal;
    static Regal zweitesRegal;

    static ArrayList<Regal> regale = new ArrayList();


    public Kiosk() {
        this.erstesRegal = new Regal("Getraenkestand");
        this.zweitesRegal = new Regal("Tabak");

        regale.add(erstesRegal);
        regale.add(zweitesRegal);
    }


    void update() {

        System.out.println("Willkommen im Kiosk!!!");
        System.out.println("Falls sie uns zum ersten mal besuchen nutzen sie bitte den Befehl: /help");


        do {
            gelesenerBefehl = s.next();
            if (gelesenerBefehl.equals(HELP)) {
                System.out.println("Hier sind alle Befehle aufgelistet: ");
                System.out.println("/help" + "/exit" + "/info");
                System.out.println("/add " + "/remove " + "/selectRegal " + "/produktVerschieben " + "/produktStatus " + "/lagerBestand " + "/preis ");
                gelesenerBefehl = "";

            } else if (gelesenerBefehl.equals(EXIT)) {
                System.out.println("Das Programm wird beendet.");
                System.exit(0);

            } else if (gelesenerBefehl.equals(ADD)) {

                Produkt produktAusEingabe = new Produkt();
                int i = 0;
                do {
                    if (i == 0) {
                        System.out.println("Bitte tragen sie ihren Produkt Namen als String hier ein: ");
                        produktAusEingabe.dername = s.next();
                    } else if (i == 1) {
                        System.out.println("Bitte tragen sie hier die Menge des Produkts ein: ");
                        System.out.println("Bitte einen Integer eingeben!!!");
                        produktAusEingabe.bestand = s.nextInt();
                    } else if (i == 2) {
                        System.out.println("Bitte aktualisieren sie den Verkaufspreis: ");
                        System.out.println("Achtung das System akzeptiert nur Float (komma nicht Punkt)");
                        produktAusEingabe.preis = s.nextFloat();

                    }

                    i++;
                }
                while (i < 4);

                System.out.println("In welches Regal wollen sie das Produkt abspeichern: ");
                System.out.println("Getraenestand = 0");
                System.out.println("Tabak = 1");
                welchesRegal = s.nextInt();
                if (welchesRegal == 0) {
                    erstesRegal.produktHinzufuegenOderAnlegen(produktAusEingabe);

                } else if (welchesRegal == 1) {
                    zweitesRegal.produktHinzufuegenOderAnlegen(produktAusEingabe);
                }
                System.out.println("Dieses Produkt wird in Regal: " + welchesRegal + " gespeichert: ");
                System.out.println(produktAusEingabe.dername);
                System.out.println(produktAusEingabe.bestand);
                System.out.println(produktAusEingabe.preis);


            } else if (gelesenerBefehl.equals(REMOVE)) {

                System.out.println("Bitte geben sie hier das Produkt an das sie entfernen möchten: ");
                String produktName = s.next();
                System.out.println("Wie viel wollen sie entnehmen? ");
                int produktAnzahl = s.nextInt();
                s.next().equals(erstesRegal.produktEntnehmen(produktName, produktAnzahl));

            } else if (gelesenerBefehl.equals(VERSCHIEBEN)) {
                System.out.println("Welches Produkt möchten sie verschieben? ");
                String produktNameZumVerschieben = s.next();
                selectRegal(produktNameZumVerschieben);
                System.out.println("Das Produkt wurde erfolgreich verschoben! ");


            } else if (gelesenerBefehl.equals(INFO)) {

                for (int i = 0; i < regale.size(); i++) {
                    Regal currentRegal = regale.get(i);
                    for (int n = 0; n < currentRegal.produkte.size(); n++) {
                        Produkt currentProdukt = currentRegal.produkte.get(n);
                        System.out.println("Name: " + currentProdukt.dername);
                        System.out.println("Bestand: " + currentProdukt.bestand);
                        System.out.println("Preis: " + currentProdukt.preis);
                    }
                }


            } else if (gelesenerBefehl.equals(PRODUKTSTATUS)) {
                Produkt rueckgabeProdukt = erstesRegal.getProdukt(s.next());
                if (rueckgabeProdukt == null) {
                    System.out.println("Produkt befindet sich noch nicht im Sortiment!");

                }
                System.out.println("Das Produkt wird in: " + welchesRegal + " gelagert: ");
                System.out.println(rueckgabeProdukt.dername);
                System.out.println(rueckgabeProdukt.bestand);
                System.out.println(rueckgabeProdukt.preis);


            } else if (gelesenerBefehl.equals(LAGERBESTAND)) {
                System.out.println("Bitte geben sie hier das Produkt an, dessen Bestand sie überprüfen möchten: ");
                Produkt rueckgabeProdukt3 = erstesRegal.getLagerbestand(s.next());
                System.out.println(rueckgabeProdukt3.bestand);
                if (rueckgabeProdukt3 == null) {
                    System.out.println("Produkt befindet sich noch nicht im Sortiment!");
                }

            } else if (gelesenerBefehl.equals(PREIS)) {
                System.out.println("Bitte geben sie hier das gewünschte Produkt ein: ");
                Produkt rueckgabeProdukt2 = erstesRegal.getPreisProdukt(s.next());
                System.out.println("Die " + rueckgabeProdukt2.dername + " kostet: " + rueckgabeProdukt2.preis + " €");
                if (rueckgabeProdukt2 == null) {
                    System.out.println("Produkt befindet sich noch nicht im Sortiment!");
                }
            }
        }
        while (!gelesenerBefehl.equals(EXIT));

    }

    public static void selectRegal(String name) {
        Produkt produktZuVerschieben = new Produkt();

        for(int i = 0; i < erstesRegal.produkte.size(); i++){
            if(erstesRegal.produkte.get(i).dername.equals(name)){
                produktZuVerschieben.dername = erstesRegal.produkte.get(i).dername;
                produktZuVerschieben.bestand = erstesRegal.produkte.get(i).bestand;
                produktZuVerschieben.preis = erstesRegal.produkte.get(i).preis;
                erstesRegal.produkte.remove(i);
            }
        }
        for(int i = 0; i < zweitesRegal.produkte.size(); i++){
            if(zweitesRegal.produkte.get(i).dername.equals(name)){
                produktZuVerschieben.dername = zweitesRegal.produkte.get(i).dername;
                produktZuVerschieben.bestand = zweitesRegal.produkte.get(i).bestand;
                produktZuVerschieben.preis = zweitesRegal.produkte.get(i).preis;
                zweitesRegal.produkte.remove(i);
            }
        }
        System.out.println("In welches Regal möchtest du das Produkt " + name + " verschieben?");
        System.out.println("Gebe (1) für Regal Nr. 1 ein");
        System.out.println("Gebe (2) für Regal Nr. 2 ein");
        int regalAuswahl = s.nextInt();

        if(regalAuswahl == 1 ){
            erstesRegal.produktHinzufuegenOderAnlegen(produktZuVerschieben);
        }
        else if(regalAuswahl == 2){
            zweitesRegal.produktHinzufuegenOderAnlegen(produktZuVerschieben);
        }
        else{
            System.out.println("Eingabe ungültig");
        }
        System.out.println("Produkt: " + name + " wurde erfogreich in Regal " + regalAuswahl + " verschoben!");
    }
    }



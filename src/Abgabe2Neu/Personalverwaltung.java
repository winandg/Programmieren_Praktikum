package Abgabe2Neu;

import java.util.ArrayList;
import java.util.Scanner;

public class Personalverwaltung {

    //zugriff auf die GetMonatslohn,
    //MintoStunde, gearbeiteteZeit, sollZeitÄndern, istzeitÄndern Methoden
    //Legt eine Klasse Personalverwaltung an, die je eine ArrayList mit Personen und Zimmer enthält.


    ArrayList<Person> personen;
    ArrayList<Zimmer> zimmer = new ArrayList<Zimmer>();

    final String HELP = "/help";
    final String EXIT = "/exit";
    final String INFO = "/info";
    final String ANGESTELLTENANLEGEN = "/angestelltenAnlegen";
    final String PATIENTAENDERN = "/patientAnlegen";
    final String ZIMMERLOESCHEN = "/zimmerLoeschen";
    final String PATIENTENTLASSEN = "/patientEntlassen";
    final String PATIENTVERLEGEN = "/patientVerlegen";
    final String ZIMMERANLEGEN = "/zimmerAnlegen";
    final String ISTZEIT = "/istZeitAendern";
    final String SOLLZEITAENDERN = "/sollZeitAendern";
    final String GETPATIENT = "/getPatient";
    final String GETANGESTELLTEN = "/getAngestellte";


    String gelesenerBefehl;


    void update() {

        Scanner eingabe = new Scanner(System.in);
        System.out.println("Verwaltung-Krankenhaus");
        System.out.println("/help");


        do {
            gelesenerBefehl = eingabe.next();
            switch (gelesenerBefehl) {
                case HELP:
                    System.out.println("Hier sind alle Befehle aufgelistet: ");
                    System.out.println("/help" + "/exit" + "/info");
                    System.out.println("/angestelltenAnlegen " + "/patientAnlegen" + "/zimmerLoeschen" + "/patientEntlassen" + "/patientVerlegen" + "/zimmerAnlegen" + "/zimmerAnlegen" + "/istZeitAendern" + "/sollZeitAendern" + "/getPatient" + "/getAngestellte");
                    gelesenerBefehl = "";
                    break;
                case ZIMMERANLEGEN:
                    zimmerAnlegen();
                    gelesenerBefehl = "";
                    break;
                case EXIT:
                    System.out.println("Das Programm wurde beendet.");
                    return;
                case PATIENTAENDERN: {
                    System.out.println("Name: ");
                    String name = eingabe.next();
                    System.out.println("Alter: ");
                    int alter = eingabe.nextInt();
                    System.out.println("GesetzlichVersichert? True/False");
                    boolean gesetzlichVersichert = eingabe.nextBoolean();
                    System.out.println("Bitte geben sie noch eine gütlige patientenID als ganze Zahl: ");
                    int patientenID = 0;
                    boolean patientenIDvorhanden = true;
                    while (patientenIDvorhanden) {
                        patientenID = eingabe.nextInt();
                        for (int i = 0; i < personen.size(); i++) {
                            if (personen.get(i) instanceof Patient) {                         //Instanz vom Patienten.
                                if (((Patient) personen.get(i)).patientenID != patientenID) {
                                    patientenIDvorhanden = false;
                                } else System.out.println("Diese PatientenID ist bereits vorhanden...");
                            }
                        }
                    }
                    Patient neuerPatient = new Patient(name, alter, gesetzlichVersichert, patientenID);

                    int zaehler = 0;
                    for (int i = 0; i < zimmer.size(); i++) {
                        if (zimmer.get(i).anzahlFreieBetten > zimmer.get(i).patient.size()) {
                            personen.add(neuerPatient);
                            zimmer.get(i).patient.add(neuerPatient);

                        } else ++zaehler;
                    }
                    if (zaehler == zimmer.size()) {
                        System.out.println("Alle Zimmer sind belegt! Bitte eine neues Zimmer anlegen!");
                    }
                    break;
                }
                case ANGESTELLTENANLEGEN:

                /*
                String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto
                 */
                    System.out.println("Arzt = True/Krankenpfleger = False: ");
                    boolean artDerAnstellung = eingabe.nextBoolean();
                    if (artDerAnstellung == true) {
                        //String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto, Enum rang
                        /////////////////////////////////////ARZT/////////////////////////////////////////////////
                        System.out.println("Name: ");
                        String name = eingabe.next();
                        System.out.println("Alter: ");
                        int alter = eingabe.nextInt();
                        System.out.println("Stundenlohn: ");
                        float stundenlohn = eingabe.nextFloat();
                        System.out.println("Steuerklasse: ");
                        int eingabeSteuerKlasse = eingabe.nextInt();
                        System.out.println("sollZeit: ");
                        int sollZeit = eingabe.nextInt();
                        int istZeit = 0;

                        Zeitkonto kontoArzt = new Zeitkonto(istZeit, sollZeit);
                        System.out.println("Rang: ");
                        int eingabeRang = eingabe.nextInt();
                        Arzt.Rang rang = Arzt.Rang.AIA;
                        switch (eingabeRang) {
                            case (0):
                                rang = Arzt.Rang.AIA;
                            case (1):
                                rang = Arzt.Rang.ASSISTENZARZT;
                            case (2):
                                rang = Arzt.Rang.FACHARZT;
                            case (3):
                                rang = Arzt.Rang.OBERARZT;
                            case (4):
                                rang = Arzt.Rang.CHEFARZT;
                        }

                        Arzt neuerArzt = new Arzt(name, alter, stundenlohn, eingabeSteuerKlasse, kontoArzt, rang);

                        int zaehler = 0;
                        for (int i = 0; i < zimmer.size(); i++) {
                            if (zimmer.get(i).anzahlFreieBetten > zimmer.get(i).patient.size()) {
                                personen.add(neuerArzt);
                                zimmer.get(i).angestellter.add(neuerArzt);

                            } else ++zaehler;
                        }
                        if (zaehler == zimmer.size()) {
                            System.out.println("Alle Zimmer sind belegt! Bitte eine neues Zimmer anlegen!");
                        }

                    } else if (artDerAnstellung == false) {
                        /////////////////////////////////////////////////KRANKENPFLEGER//////////////////////////////////////////////////
                        //String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto
                        System.out.println("Name: ");
                        String name = eingabe.next();
                        System.out.println("Alter: ");
                        int alter = eingabe.nextInt();
                        System.out.println("Stundenlohn: ");
                        float stundenlohn = eingabe.nextFloat();
                        System.out.println("Steuerklasse: ");
                        int steuerklasse = eingabe.nextInt();
                        System.out.println("sollZeit: ");
                        int sollZeit = eingabe.nextInt();
                        int istZeit = 0;

                        Zeitkonto konto = new Zeitkonto(sollZeit, istZeit);
                        Krankenpfleger pfleger = new Krankenpfleger(name, alter, stundenlohn, konto);

                        int zaehler = 0;
                        for (int i = 0; i < zimmer.size(); i++) {
                            if (zimmer.get(i).anzahlFreieBetten > zimmer.get(i).patient.size()) {
                                personen.add(pfleger);
                                zimmer.get(i).angestellter.add(pfleger);

                            } else ++zaehler;
                        }
                        if (zaehler == zimmer.size()) {
                            System.out.println("Alle Zimmer sind belegt! Bitte eine neues Zimmer anlegen!");
                        }
                        // wohnheim.angestelltenAnlegen(neuerAngestellter);
                        // Überarbeiten


                    }


                    break;
                case SOLLZEITAENDERN: {

                    System.out.println("Wie heißt die Person?");
                    String name = eingabe.next();
                    System.out.println("Wie viel muss die Person arbeiten?");
                    int geforderteArbeitsZeit = eingabe.nextInt();
                    System.out.println("True = addieren / False = subtrahieren");
                    boolean addSub = eingabe.nextBoolean();


                    for (int i = 0; i < personen.size(); i++) {
                        if (personen.get(i).equals(name)) {
                            if (personen.get(i) instanceof Angestellter) {
                                ((Angestellter) personen.get(i)).konto.sollZeitAendern(geforderteArbeitsZeit, addSub);
                            }
                        }
                    }


                    break;
                }
                case ISTZEIT: {

                    System.out.println("Wie heißt die Person?");
                    String name = eingabe.next();
                    System.out.println("Wie viel hat die Person gearbeitet?");
                    int gearbeiteteZeit = eingabe.nextInt();
                    System.out.println("True = addieren / False = subtrahieren");
                    boolean addSub = eingabe.nextBoolean();

                    for (int i = 0; i < personen.size(); i++) {
                        if (personen.get(i).equals(name)) {
                            if (personen.get(i) instanceof Angestellter) {
                                ((Angestellter) personen.get(i)).konto.istZeitAendern(gearbeiteteZeit, addSub);
                            }
                        }
                    }
                    break;
                }
                case ZIMMERLOESCHEN:

                    System.out.println("Welches Zimmer soll geloescht werden?(ZimmerNummer)");
                    int zimmerNummer = eingabe.nextInt();
                    zimmerLoeschen(zimmerNummer);
                    System.out.println("Das Zimmer befindet sich nicht mehr im System.");


                    break;
                case PATIENTENTLASSEN: {

                    System.out.println("Welcher Patient soll entlassen werden?");
                    int patientenID = eingabe.nextInt();
                    patientEntlassen(patientenID);
                    System.out.println("Das Zimmer befindet sich nicht mehr im System.");

                    break;
                }
                case PATIENTVERLEGEN:


//zwei for Schleifen// In jedem Zimmer die Patientenlisten durchsuchen//PatientenID
//for (int i = 0; i < zimmerListe.size(); i++) {
//                        for (int j = 0; j < zimmerListe.get(i).patientenListe.size(); j++) {
                    break;
                case INFO:

                    //Methode die alle Informationen in der Konsole ausgibt

                    break;
                case GETPATIENT:


                    break;
                case GETANGESTELLTEN:


                    break;
            }

        } while (!gelesenerBefehl.equals(EXIT));
    }

    void patientVerlegen() {

        //Legt eine Methode an um Patienten zu verlegen (von einem Zimmer in ein anderes verschieben). Falls der Patient gesetzlich versichert ist soll dies grundsätzlich nicht klappen (Try/Catch + eigene Exception verwenden!)
    }

    public void patientEntlassen(int patientenID) {


        //Legt eine Methode an um Patienten zu entlassen.

    }

    public void zimmerAnlegen() {
        //Erstellt Methoden um Zimmer anzulegen.
        Scanner eingabe = new Scanner(System.in);


        System.out.println("Zimmernummer: ");
        int zimmernummer = eingabe.nextInt();
        System.out.println("Anzahl der Freien Betten im jeweilgen Zimmer: ");
        int anzahlBetten = eingabe.nextInt();


        Zimmer neuesZimmer = new Zimmer(anzahlBetten, zimmernummer);
        zimmer.add(neuesZimmer);
        System.out.println("Folgendes Zimmer wurde nun angelegt: ");
        System.out.println("Anzahl Betten: " + neuesZimmer.anzahlFreieBetten);
        System.out.println("Zimmernummer: " + neuesZimmer.zimmerNummer);
    }

    void zimmerLoeschen(int zimmerNummer) {


        for (int i = 0; i < zimmer.size(); i++) {
            if (zimmer.get(i).zimmerNummer == zimmerNummer) {
                zimmer.remove(i);
                System.out.println("Das Zimmer mit der Nr :" + zimmerNummer + " wurde geloescht");
            }
            break;
        }
    }

    public void patientAnlegen(Patient neuerPatient) {

        Scanner eingabe = new Scanner(System.in);
        int zimmerNummer = eingabe.nextInt();
        Zimmer currentZimmer = new Zimmer(zimmerNummer);

        //currentZimmer.patientAnlegen(neuerPatient);

        System.out.println(neuerPatient.istGesetzlichVersichert);
        System.out.println(neuerPatient.name);
        System.out.println(neuerPatient.alter);


    }




    /*
    Erstellt Methoden um Zimmer anzulegen und wieder zu löschen
    Erstellt eine Methode die eine neue Person (Patienten oder Angestellten) anlegt und direkt einem freien Bett in einem Zimmer zuweist (Überlagerung/Polymorphie!)
    TIPP: Prüft ob auch Platz im Zimmer ist, bzw. noch Zimmer frei sind
     */


}

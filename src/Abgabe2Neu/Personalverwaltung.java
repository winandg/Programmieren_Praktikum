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
    final String PATIENTANLEGEN = "/patientAnlegen";
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
                    break;
                case ZIMMERANLEGEN:
                    zimmerAnlegen();
                    break;
                case EXIT:
                    System.out.println("Das Programm wurde beendet.");
                    return;
                case PATIENTANLEGEN: {
                    System.out.println("Name: ");
                    String name = eingabe.next();
                    System.out.println("Alter: ");
                    int alter = eingabe.nextInt();
                    System.out.println("GesetzlichVersichert? True/False");
                    boolean gesetzlichVersichert = eingabe.nextBoolean();

                    System.out.println("Bitte geben sie noch eine gültige patientenID als ganze Zahl: ");
                    int patientenID = -1;
                    boolean patientenIDvorhanden = false;
                    while (!patientenIDvorhanden) {
                        patientenID = eingabe.nextInt();
                        for (int i = 0; i < personen.size(); i++) {
                            if (personen.get(i) instanceof Patient) {                         //Instanz vom Patienten.
                                if (((Patient) personen.get(i)).patientenID == patientenID) {
                                    patientenIDvorhanden = true;
                                }
                            }
                        }
                        if (patientenIDvorhanden) {
                            System.out.println("Diese PatientenID ist bereits vorhanden...");
                        }
                    }
                    Patient neuerPatient = new Patient(name, alter, gesetzlichVersichert, patientenID);


                    boolean zimmergefunden = false;
                    for (int i = 0; i < zimmer.size(); i++) {
                        if (zimmer.get(i).anzahlBetten > zimmer.get(i).patient.size()) {
                            personen.add(neuerPatient);
                            zimmer.get(i).patient.add(neuerPatient);
                            zimmergefunden = true;
                            break;
                        }
                    }
                    if (!zimmergefunden) {
                        System.out.println("Alle Zimmer sind belegt! Bitte eine neues Zimmer anlegen!");
                    }
                    break;
                }
                case ANGESTELLTENANLEGEN: {

                /*
                String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto
                 */
                    System.out.println("Arzt = True/Krankenpfleger = False: ");
                    boolean istArzt = eingabe.nextBoolean();
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

                    Zeitkonto zeitkonto = new Zeitkonto(istZeit, sollZeit);

                    if (istArzt == true) {
                        //String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto, Enum rang
                        /////////////////////////////////////ARZT/////////////////////////////////////////////////

                        System.out.println("Rang: ");
                        int eingabeRang = eingabe.nextInt();
                        Arzt.Rang rang;
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
                                break;
                            default:
                                throw new IllegalStateException("Unerlaubter Rang: " + eingabeRang);
                        }

                        Arzt neuerArzt = new Arzt(name, alter, stundenlohn, steuerklasse, zeitkonto, rang);

                        System.out.println("Im welchem Zimmer soll der Arzt arbeiten?");
                        int zimmernummer = eingabe.nextInt();

                        boolean zimmergefunden = false;
                        for (int i = 0; i < zimmer.size(); i++) {
                            if (zimmer.get(i).zimmerNummer == zimmernummer) {
                                personen.add(neuerArzt);
                                zimmer.get(i).angestellter.add(neuerArzt);
                                zimmergefunden = true;
                                break;
                            }
                        }
                        if (!zimmergefunden) {
                            System.out.println("Zimmernummer " + zimmernummer + " konnte nicht gefunden werden!");
                        }


                    } else if (istArzt == false) {
                        /////////////////////////////////////////////////KRANKENPFLEGER//////////////////////////////////////////////////
                        //String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto

                        Krankenpfleger pfleger = new Krankenpfleger(name, alter, stundenlohn, zeitkonto);

                        System.out.println("Im welchem Zimmer soll der Arzt arbeiten?");
                        int zimmernummer = eingabe.nextInt();

                        boolean zimmergefunden = false;
                        for (int i = 0; i < zimmer.size(); i++) {
                            if (zimmer.get(i).zimmerNummer == zimmernummer) {
                                personen.add(pfleger);
                                zimmer.get(i).angestellter.add(pfleger);
                                zimmergefunden = true;
                                break;
                            }
                        }
                        if (!zimmergefunden) {
                            System.out.println("Zimmernummer " + zimmernummer + " konnte nicht gefunden werden!");
                        }
                        // wohnheim.angestelltenAnlegen(neuerAngestellter);
                        // Überarbeiten


                    }
                    break;
                }
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

                    System.out.println("Welches Zimmer soll gelöscht werden?(ZimmerNummer)");
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
                case PATIENTVERLEGEN: {
                    System.out.println("Welcher Patient soll verlegt werden? (patientenID)");
                    int patientenID = eingabe.nextInt();
                    boolean patientIDgefunden = false;
                    Patient patient = null;
                    try {
                        for (int i = 0; i < personen.size(); i++) {
                            if (personen.get(i) instanceof Patient) {
                                if (((Patient) personen.get(i)).patientenID == patientenID) {
                                    patient = (Patient) personen.get(i);
                                    patientIDgefunden = true;
                                    if (((Patient) personen.get(i)).istGesetzlichVersichert) {
                                        throw new IstGesetzlichVersichertException();
                                    }
                                    break;
                                }
                            }
                        }
                    } catch (IstGesetzlichVersichertException e) {
                        e.printStackTrace();
                        break;
                    }
                    if (!patientIDgefunden) {
                        System.out.println("Patient mit der ID " + patientenID + " konnte nicht gefunden werden!");
                        break;
                    }

                    System.out.println("In welches Zimmer soll der Patient verlegt werden?");
                    int zimmernummer = eingabe.nextInt();
                    boolean zimmergefunden = false;
                    int neuesZimmer = 0;
                    for (; neuesZimmer < zimmer.size(); neuesZimmer++) {
                        if (zimmer.get(neuesZimmer).zimmerNummer == zimmernummer) {
                            zimmer.get(neuesZimmer).patient.add(patient);
                            zimmergefunden = true;
                            break;
                        }
                    }
                    if (!zimmergefunden) {
                        System.out.println("Zimmernummer " + zimmernummer + " konnte nicht gefunden werden");
                        break;
                    }

                    if (zimmer.get(neuesZimmer).anzahlBetten < zimmer.get(neuesZimmer).patient.size()) {
                        System.out.println("Nicht genügend freie Betten verfügbar!");
                        break;
                    }
                    int aktuellesZimmer = 0;
                    boolean aktuellesZimmergefunden = false;
                    for (; aktuellesZimmer < zimmer.size(); aktuellesZimmer++) {
                        for (int k = 0; k < zimmer.get(aktuellesZimmer).patient.size(); k++) {
                            if (zimmer.get(aktuellesZimmer).patient.get(k).patientenID == patientenID) {
                                zimmer.get(aktuellesZimmer).patient.remove(patient);
                                aktuellesZimmergefunden = true;
                                break;
                            }
                        }
                        if (aktuellesZimmergefunden) {
                            break;
                        }
                    }


//zwei for Schleifen// In jedem Zimmer die Patientenlisten durchsuchen//PatientenID
//for (int i = 0; i < zimmerListe.size(); i++) {
//                        for (int j = 0; j < zimmerListe.get(i).patientenListe.size(); j++) {
                    break;
                }
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

        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i) instanceof Patient) {
                if (((Patient) personen.get(i)).patientenID == patientenID) {
                    personen.remove(i);
                    break;
                }
            }
        }
    }

    public void zimmerAnlegen() {
        //Erstellt Methoden um Zimmer anzulegen.
        Scanner eingabe = new Scanner(System.in);


        System.out.println("Zimmernummer: ");
        int zimmernummer = eingabe.nextInt();
        System.out.println("Anzahl der Freien Betten im jeweiligen Zimmer: ");
        int anzahlBetten = eingabe.nextInt();


        Zimmer neuesZimmer = new Zimmer(anzahlBetten, zimmernummer);
        zimmer.add(neuesZimmer);
        System.out.println("Folgendes Zimmer wurde nun angelegt: ");
        System.out.println("Anzahl Betten: " + neuesZimmer.anzahlBetten);
        System.out.println("Zimmernummer: " + neuesZimmer.zimmerNummer);
    }

    void zimmerLoeschen(int zimmerNummer) {


        for (int i = 0; i < zimmer.size(); i++) {
            if (zimmer.get(i).zimmerNummer == zimmerNummer) {
                zimmer.remove(i);
                System.out.println("Das Zimmer mit der Nr :" + zimmerNummer + " wurde geloescht");
                break;
            }
        }
    }






    /*
    Erstellt Methoden um Zimmer anzulegen und wieder zu löschen
    Erstellt eine Methode die eine neue Person (Patienten oder Angestellten) anlegt und direkt einem freien Bett in einem Zimmer zuweist (Überlagerung/Polymorphie!)
    TIPP: Prüft ob auch Platz im Zimmer ist, bzw. noch Zimmer frei sind
     */


}

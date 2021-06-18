package Abgabe2Neu;

import java.util.ArrayList;

public class Zimmer {

    ArrayList<Patient> patient;
    ArrayList<Angestellter>angestellter;

    int anzahlFreieBetten;
    int zimmerNummer;

    public Zimmer(int anzahlBetten, int nummer) {
        anzahlFreieBetten = anzahlBetten;
        this.zimmerNummer = nummer;
    }
}

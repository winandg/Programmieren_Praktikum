package Abgabe2Neu;

import java.util.ArrayList;

public class Zimmer {

    ArrayList<Patient> patient;
    ArrayList<Angestellter> angestellter;

    final int anzahlBetten;
    int zimmerNummer;

    public Zimmer(int anzahlBetten, int nummer) {
        this.anzahlBetten = anzahlBetten;
        this.zimmerNummer = nummer;
    }
}

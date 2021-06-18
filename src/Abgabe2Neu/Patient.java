package Abgabe2Neu;

public class Patient extends Person{
    public boolean istGesetzlichVersichert;
    public int patientenID;


    public Patient(String name, int alter, boolean istGesetzlichVersichert, int patientenID) {
        super(name, alter);
        this.istGesetzlichVersichert = istGesetzlichVersichert;
        this.patientenID = patientenID;
    }
/*
    public boolean isIstGesetzlichVersichert() {
        return istGesetzlichVersichert;
    }

    public int getPatientenID() {
        return patientenID;
    }
*/
}

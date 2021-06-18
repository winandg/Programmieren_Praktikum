package Abgabe2Neu;

public abstract class Angestellter extends Person{
    float stundenlohn;
    int steuerklasse;
    Zeitkonto konto;


    public Angestellter(String name, int alter, float stundenlohn,  double steuerklasse, Zeitkonto konto) {
        super(name, alter);
        this.stundenlohn = stundenlohn;
        this.konto = konto;
    }




    abstract float GetMonatslohn(boolean brutto);
    Angestellter(){
    /*
    Erstellt die Klasse Angestellter mit folgenden Attributen:
    float stundenlohn
    int steuerklasse (könnt ihr auch zur Übung als ENUM bauen)
    Zeitkonto konto
    Schreibt einen Konstruktor für die Klasse Angestellter der alle Attribute sinnvoll berücksichtigt
    Diese Klasse ist die Vaterklasse von Arzt und Krankenpfleger
    Erweitert die Klasse Angestellter um eine abstrakte Methode getMonatslohn(boolean brutto)
    TIPP: Wenn der Boolean true ist soll der Bruttolohn & ansonsten der Nettolohn ausgerechnet werden
     */
    }

    public float getStundenlohn() {
        return stundenlohn;
    }
/*
    public int getSteuerklasse() {
        return steuerklasse;
    }
*/
    public Zeitkonto getKonto() {
        return konto;
    }
}

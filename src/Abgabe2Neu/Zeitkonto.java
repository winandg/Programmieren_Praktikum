package Abgabe2Neu;

public class Zeitkonto {

    int sollzeit;
    int istzeit;

    public Zeitkonto(int sollzeit, int istzeit) {
        this.sollzeit = sollzeit;
        this.istzeit = istzeit;
    }

    static String minToStunde(int minuten) {

        int AnzahlderMinuten;
        double volleStunde;
        int verbleibendeMinuten;

        AnzahlderMinuten = minuten;
        volleStunde = Math.floor(AnzahlderMinuten / 60);
        verbleibendeMinuten = (AnzahlderMinuten % 60);
        if (verbleibendeMinuten < 1)
            return "Stunden: " + volleStunde;
        else return "Stunden: " + volleStunde + "Minuten: " + verbleibendeMinuten;
    }


    int offeneZeit() {
        int verbleibendeZeit = sollzeit - istzeit;
        if (verbleibendeZeit > 0) {
            System.out.println("Sie müssen noch " + minToStunde(verbleibendeZeit) + "arbeiten!");
            return verbleibendeZeit;
        } else if (verbleibendeZeit < 0) {
            System.out.println("Sie haben bereits " + minToStunde(Math.abs(verbleibendeZeit)) + " zu viel gearbeitet");
            return verbleibendeZeit;
        } else if (verbleibendeZeit == 0) {
            System.out.println("Sie haben ihre Sollzeit exakt erfüllt.");
            return verbleibendeZeit;
        }
        return verbleibendeZeit;
    }


    void sollZeitAendern(int zeit, boolean addSub) {
        if (addSub == false) {
            sollzeit -= zeit;
            System.out.println("Die Sollzeit beträgt nun: " + minToStunde(sollzeit));
        }
        if (addSub == true) {
            sollzeit += zeit;
            System.out.println("Die Sollzeit beträgt nun: " + minToStunde(sollzeit));
        }
    }

    void istZeitAendern(int zeit, boolean addSub) {
        if (addSub == false) {
            istzeit -= zeit;
            System.out.println("Die Istzeit beträgt nun: " + minToStunde(istzeit));
        }
        if (addSub == true) {
            istzeit += zeit;
            System.out.println("Die Istzeit beträgt nun: " + minToStunde(istzeit));
        }

    }
}

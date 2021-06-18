package Abgabe2Neu;

public class Krankenpfleger extends Angestellter {
    float monatslohn;
    KrankenpflegerSchicht krankenpflegerSchicht;


    public Krankenpfleger(String name, int alter, float stundenlohn, Zeitkonto konto, int steuerklasse) {
        super(name, alter, stundenlohn, steuerklasse, konto);
    }

    public Krankenpfleger(String name, int alter, float stundenlohn, Zeitkonto konto) {
    }

    public enum KrankenpflegerSchicht {
        FRUEHSCHICHT(0.05f), SPAETSCHICHT(0.10f), NACHTSCHICHT(0.25f);

        private float individuellerBonus;

        KrankenpflegerSchicht(float indivduellerBonus) {
            this.individuellerBonus = indivduellerBonus;
        }

        public float getSchicht() {
            return individuellerBonus;
        }
    }

    @Override
    float GetMonatslohn(boolean brutto) {

        float monatslohn = 0.0f;
        float ueberstunde = konto.istzeit - konto.sollzeit;
        if (ueberstunde <= 0) {
            ueberstunde = 0;
        }
        if (ueberstunde > 60) {
            ueberstunde = ueberstunde;
        }

        //Uberstunden im Vorfeld bestimmen. Falls möglich


        if (brutto == true) {
            if (konto.istzeit > konto.sollzeit) {

                if (ueberstunde > 0) {
                    monatslohn = ((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht();
                } else monatslohn = ((stundenlohn / 60) * konto.sollzeit) * krankenpflegerSchicht.getSchicht();


            }
        }
        if (brutto == false) {
            if (steuerklasse == 0) {

                //monatslohn = ((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * rang.getBonusArzt();
                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 1.0f;
                return monatslohn;

            }
            if (steuerklasse == 1) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.88f;
                return monatslohn;
            }
            if (steuerklasse == 2) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.82f;
                return monatslohn;
            }
            if (steuerklasse == 3) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.77f;
                return monatslohn;
            }
            if (steuerklasse == 4) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.71f;
                return monatslohn;
            }
            if (steuerklasse == 5) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.62f;
                return monatslohn;
            }
            if (steuerklasse == 6) {

                monatslohn = (((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * krankenpflegerSchicht.getSchicht()) * 0.55f;
                return monatslohn;
            }
        }
        /*                 monatslohn = ((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * rang.getBonusArzt();

        if (brutto == true) {
            if (konto.istzeit > konto.sollzeit) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + () *
        } else if (brutto == false) {



            SteuerBerechnung[] steuerKlasse = SteuerBerechnung.values();

            for(SteuerBerechnung s : steuerKlasse){

                if (konto.istzeit > konto.sollzeit) {
                    monatslohn = ((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * rang.getBonusArzt();
                } else monatslohn = (stundenlohn / 60) * konto.istzeit * rang.getBonusArzt();

            }
        monatslohn = 3500;
        return 0;
    }
    */
        return -1.0f;
    }
}

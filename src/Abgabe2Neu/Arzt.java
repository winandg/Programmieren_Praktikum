package Abgabe2Neu;

import AltklausurWochentagAusgabe.WochentagAusgabe;
import org.w3c.dom.ls.LSOutput;

public class Arzt extends Angestellter {

    Rang rang;

    public Arzt(String name, int alter, float stundenlohn, int steuerklasse, Zeitkonto konto, Rang rang) {
        super(name, alter, stundenlohn, steuerklasse, konto);

        this.rang = rang;
    }

    @Override
    float GetMonatslohn(boolean brutto) {

        float monatslohn = 0.0f;
        float ueberstunde = Math.max(konto.istzeit - konto.sollzeit, 0);
        //Uberstunden im Vorfeld bestimmen. Falls möglich

        if (brutto == true) {
            monatslohn = ((stundenlohn / 60) * konto.sollzeit + (ueberstunde) * 1.20f) * rang.getBonusArzt();
        } else if (brutto == false) {
            if (steuerklasse == 0) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 1;
            }
            if (steuerklasse == 1) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.88f;
            }
            if (steuerklasse == 2) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.82f;
            }
            if (steuerklasse == 3) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.77f;
            }
            if (steuerklasse == 4) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.71f;
            }
            if (steuerklasse == 5) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.62f;
            }
            if (steuerklasse == 6) {
                monatslohn = ((stundenlohn / 60) * konto.sollzeit + ueberstunde * 1.20f) * rang.getBonusArzt() * 0.55f;
            }

        }
        return monatslohn;
    }

    enum Rang {
        AIA(1.00f),
        ASSISTENZARZT(1.10f),
        FACHARZT(1.25f),
        OBERARZT(1.40f),
        CHEFARZT(1.80f);

        Rang(float individuellerBonusArzt) {
            this.individuellerBonusArzt = individuellerBonusArzt;
        }

        private final float individuellerBonusArzt;

        public float getBonusArzt() {
            return individuellerBonusArzt;
        }
    }
}

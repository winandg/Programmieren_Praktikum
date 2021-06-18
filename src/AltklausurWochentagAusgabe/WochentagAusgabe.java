package AltklausurWochentagAusgabe;

public class WochentagAusgabe {

    public enum Wochentag{
        Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag, Sonntag
    }

    public static void main(String[] args) {
        Wochentag [] wt = Wochentag.values();
        for( Wochentag w : wt){
            System.out.println(w + " ist der " + w.ordinal() + ". Tag der Woche");
        }
    }

}

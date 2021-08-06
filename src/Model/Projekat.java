package Model;

public class Projekat {
    public String naziv;
    public String odgovornaOsoba;
    public String klijent;
    public String gotov;

    public Projekat(String klijent, String naziv, String odgovornaOsoba, String gotov) {
        this.klijent = klijent;
        this.naziv = naziv;
        this.odgovornaOsoba = odgovornaOsoba;
        this.gotov = gotov;
    }
}

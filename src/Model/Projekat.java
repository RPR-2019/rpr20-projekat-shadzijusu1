package Model;

public class Projekat {
    public int klijent;
    public String naziv;
    public int odgovornaOsoba;
    public Boolean gotov;

    public Projekat(int klijent, String naziv, int odgovornaOsoba, Boolean gotov) {
        this.klijent = klijent;
        this.naziv = naziv;
        this.odgovornaOsoba = odgovornaOsoba;
        this.gotov = gotov;
    }
}

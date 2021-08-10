package ba.unsa.etf.rpr.projekat.model;

public class Project {
    private String naziv;
    private String odgovornaOsoba;
    private String klijent;
    private String gotov;

    public Project(String klijent, String naziv, String odgovornaOsoba, String gotov) {
        this.klijent = klijent;
        this.naziv = naziv;
        this.odgovornaOsoba = odgovornaOsoba;
        this.gotov = gotov;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOdgovornaOsoba() {
        return odgovornaOsoba;
    }

    public void setOdgovornaOsoba(String odgovornaOsoba) {
        this.odgovornaOsoba = odgovornaOsoba;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public String getGotov() {
        return gotov;
    }

    public void setGotov(String gotov) {
        this.gotov = gotov;
    }
}

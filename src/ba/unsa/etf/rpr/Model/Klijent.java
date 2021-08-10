package ba.unsa.etf.rpr.Model;

import ba.unsa.etf.rpr.DAO.CRMDao;

import java.util.Date;

public class Klijent extends Korisnik {
    public Date datumRodjenja;
    public String grad;
    public String telefon;
    public String odgovornaOsoba;
    public Date datumAktivacije;
    public String status;
    public Date datumKontaktiranja;
    public Klijent(String ime, String prezime, String email, String password, POZICIJA pozicija) {
        super(ime, prezime, email, password, pozicija);
    }

    public Klijent() {
        super();
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getOdgovornaOsoba() {
        return odgovornaOsoba;
    }

    public void setOdgovornaOsoba(String odgovornaOsoba) {
        this.odgovornaOsoba = odgovornaOsoba;
    }

    public Date getDatumAktivacije() {
        return datumAktivacije;
    }

    public void setDatumAktivacije(Date datumAktivacije) {
        this.datumAktivacije = datumAktivacije;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatumKontaktiranja() {
        return datumKontaktiranja;
    }

    public void setDatumKontaktiranja(Date datumKontaktiranja) {
        this.datumKontaktiranja = datumKontaktiranja;
    }
}

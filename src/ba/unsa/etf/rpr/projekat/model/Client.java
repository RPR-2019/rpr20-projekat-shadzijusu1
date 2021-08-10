package ba.unsa.etf.rpr.projekat.model;

import java.util.Date;

public class Client extends User {
    private Date datumRodjenja;
    private String grad;
    private String telefon;
    private String odgovornaOsoba;
    private Date datumAktivacije;
    private STATUS status;
    private Date datumKontaktiranja;
    public Client(String ime, String prezime, String email, String password, POSITION pozicija) {
        super(ime, prezime, email, password, pozicija);
    }

    public Client() {
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

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public Date getDatumKontaktiranja() {
        return datumKontaktiranja;
    }

    public void setDatumKontaktiranja(Date datumKontaktiranja) {
        this.datumKontaktiranja = datumKontaktiranja;
    }
}

package Model;

import DAO.CRMDao;

public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private POZICIJA pozicija;
    private String slika;

    public Korisnik() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Korisnik(int id, String ime, String prezime, String email, String password, POZICIJA pozicija, String slika) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
        this.slika = slika;
    }

    public Korisnik(String ime, String prezime, String email, String password, POZICIJA pozicija) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
        this.slika = ("/img/blank-profile-picture.png");
    }

    public Korisnik(String ime, String prezime, String email, String password, POZICIJA pozicija, String slika) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
        this.slika = (slika);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
        CRMDao.postaviIme(ime, email);
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
        CRMDao.postaviPrezime(prezime, email);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        CRMDao.postaviMail(email, ime, prezime, password);

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        CRMDao.postaviPass(password, email);
    }

    public POZICIJA getPozicija() {
        return pozicija;
    }

    public void setPozicija(POZICIJA pozicija) {
        this.pozicija = pozicija;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String pic) {
        this.slika = slika;
        CRMDao.postaviSliku(pic, email);
    }
}



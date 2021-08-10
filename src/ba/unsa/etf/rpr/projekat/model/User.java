package ba.unsa.etf.rpr.projekat.model;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;

public class User {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private POSITION pozicija;
    private String slika;

    public User(int id, String ime, String prezime, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String ime, String prezime, String email, String password, POSITION pozicija, String slika) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
        this.slika = slika;
    }

    public User(String ime, String prezime, String email, String password, POSITION pozicija) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
        this.slika = ("/img/blank-profile-picture.png");
    }

    public User(String ime, String prezime, String email, String password, POSITION pozicija, String slika) {
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
        CRMDao.postaviIme(ime, id);
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
        CRMDao.postaviPrezime(prezime, id);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        CRMDao.postaviMail(email, id);

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        CRMDao.postaviPass(password, id);
    }

    public POSITION getPozicija() {
        return pozicija;
    }

    public void setPozicija(POSITION pozicija) {
        this.pozicija = pozicija;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String pic) {
        this.slika = slika;
        CRMDao.postaviSliku(pic, id);
    }
}



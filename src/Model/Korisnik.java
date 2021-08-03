package Model;

public class Korisnik {
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private POZICIJA pozicija;

    public Korisnik(String ime, String prezime, String email, String password, POZICIJA pozicija) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.pozicija = pozicija;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public POZICIJA getPozicija() {
        return pozicija;
    }

    public void setPozicija(POZICIJA pozicija) {
        this.pozicija = pozicija;
    }
}



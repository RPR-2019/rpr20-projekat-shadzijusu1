package Controller;

import DAO.CRMDao;
import Model.Klijent;
import Model.Korisnik;
import Model.POZICIJA;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KlijentController {
    public Klijent klijent = new Klijent("","","","", POZICIJA.Klijent);
    public CRMDao model;
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label fldPozicija;
    public Label nameFld;
    public KlijentController(Korisnik korisnik) {
        this.klijent.setIme(korisnik.getIme());
        this.klijent.setPrezime(korisnik.getPrezime());
        this.klijent.setEmail(korisnik.getEmail());
        this.klijent.setPassword(korisnik.getPassword());
        this.klijent.setPozicija(korisnik.getPozicija());
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        nameFld.setText(klijent.getIme() + " " + klijent.getPrezime());
        fldIme.setText(klijent.getIme());
        fldPrezime.setText(klijent.getPrezime());
        fldEmail.setText(klijent.getEmail());
        fldPass.setText(klijent.getPassword());
        fldPozicija.setText(klijent.getPozicija().toString());
    }

}

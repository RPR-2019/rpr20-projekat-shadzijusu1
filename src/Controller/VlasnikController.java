package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.Projekat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VlasnikController {
    public Korisnik vlasnik;
    public CRMDao model;
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label fldPozicija;
    public Label nameFld;
    public VlasnikController(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        nameFld.setText(vlasnik.getIme() + " " + vlasnik.getPrezime());
        fldIme.setText(vlasnik.getIme());
        fldPrezime.setText(vlasnik.getPrezime());
        fldEmail.setText(vlasnik.getEmail());
        fldPass.setText(vlasnik.getPassword());
        fldPozicija.setText(vlasnik.getPozicija().toString());
    }
}

package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.POZICIJA;
import Model.Projekat;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FotografController {
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label fldPozicija;
    public ListView<Projekat> mojiProjekti;
    public Label nameFld;
    public Korisnik fotograf;
    public CRMDao model;
    public FotografController(Korisnik fotograf) {
        this.fotograf = fotograf;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        nameFld.setText(fotograf.getIme() + " " + fotograf.getPrezime());
        fldIme.setText(fotograf.getIme());
        fldPrezime.setText(fotograf.getPrezime());
        fldEmail.setText(fotograf.getEmail());
        fldPass.setText(fotograf.getPassword());
        fldPozicija.setText(fotograf.getPozicija().toString());
    }
}

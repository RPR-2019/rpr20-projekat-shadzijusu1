package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.POZICIJA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegLogController {
    public TextField imeFld;
    public TextField prezimeFld;
    public TextField emailFld;
    public PasswordField passFld;
    public ChoiceBox<POZICIJA> pozicijaBox;
    public ObservableList<POZICIJA> pozicije = FXCollections.observableArrayList();
    public CRMDao model;

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();

        if (pozicijaBox != null) {
            pozicije.add(POZICIJA.Vlasnik);
            pozicije.add(POZICIJA.Fotograf);
            pozicije.add(POZICIJA.Klijent);
            pozicijaBox.setItems(pozicije);
        }
    }

    public void registrujSe(ActionEvent actionEvent) throws IOException {
        String ime = imeFld.getText();
        String prezime = prezimeFld.getText();
        String email = emailFld.getText();
        String pass = passFld.getText();
        POZICIJA pozicija = pozicijaBox.getValue();

        Korisnik k = new Korisnik(ime, prezime, email, pass, pozicija);
        model.dodajKorisnika(ime, prezime, email, pass, pozicija);
        if (pozicija == POZICIJA.Fotograf) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/fotograf_front_page.fxml"));
            FotografController ctrl = new FotografController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        } else if (pozicija == POZICIJA.Klijent) {

            otvoriDodatne(k);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/vlasnik_front_page.fxml"));
            VlasnikController ctrl = new VlasnikController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
    }

    public void logujSe(ActionEvent actionEvent) throws IOException {
        Korisnik k = model.getKorisnik(emailFld.getText(), passFld.getText());
        if (k.getPozicija() == POZICIJA.Vlasnik) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/vlasnik_front_page.fxml"));
                VlasnikController ctrl = new VlasnikController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else if (k.getPozicija() == POZICIJA.Fotograf) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/fotograf_front_page.fxml"));
                FotografController ctrl = new FotografController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/klijent_front_page.fxml"));
            KlijentController ctrl = new KlijentController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
            }
    }

    private void otvoriDodatne(Korisnik k) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/klijent_info_register.fxml"));
        KlijentInfoController ctrl = new KlijentInfoController(k);
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

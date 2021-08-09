package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Korisnik;
import ba.unsa.etf.rpr.Model.POZICIJA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

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
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/fotograf_front_page.fxml" ), bundle);
            FotografController ctrl = new FotografController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        } else if (pozicija == POZICIJA.Klijent) {

            otvoriDodatne(k);
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/vlasnik_front_page.fxml" ), bundle);
            VlasnikController ctrl = new VlasnikController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
    }

    public void logujSe(ActionEvent actionEvent) throws IOException {
        Korisnik k = model.getKorisnik(emailFld.getText(), passFld.getText());
        if (k.getPozicija() == POZICIJA.Vlasnik) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/vlasnik_front_page.fxml" ), bundle);
                VlasnikController ctrl = new VlasnikController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else if (k.getPozicija() == POZICIJA.Fotograf) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/fotograf_front_page.fxml" ), bundle);
                FotografController ctrl = new FotografController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/klijent_front_page.fxml" ), bundle);

            KlijentController ctrl = new KlijentController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
            }
    }

    private void otvoriDodatne(Korisnik k) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/klijent_info_register.fxml" ), bundle);
        KlijentInfoController ctrl = new KlijentInfoController(k);
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

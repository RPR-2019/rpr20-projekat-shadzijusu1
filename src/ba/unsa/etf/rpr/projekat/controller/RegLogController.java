package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.POSITION;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public ChoiceBox<POSITION> pozicijaBox;
    public ObservableList<POSITION> pozicije = FXCollections.observableArrayList();
    public CRMDao model;

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();

        if (pozicijaBox != null) {
            pozicije.add(POSITION.Vlasnik);
            pozicije.add(POSITION.Fotograf);
            pozicije.add(POSITION.Klijent);
            pozicijaBox.setItems(pozicije);
        }
        if(imeFld != null) {
            imeFld.textProperty().addListener((obs, oldIme, newIme) -> {
                if (!newIme.isEmpty()) {
                    imeFld.getStyleClass().removeAll("poljeNijeIspravno");
                    imeFld.getStyleClass().add("poljeIspravno");
                } else {
                    imeFld.getStyleClass().removeAll("poljeIspravno");
                    imeFld.getStyleClass().add("poljeNijeIspravno");
                }
            });
        }
        if(prezimeFld != null) {
            prezimeFld.textProperty().addListener((obs, oldIme, newIme) -> {
                if (!newIme.isEmpty()) {
                    prezimeFld.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeFld.getStyleClass().add("poljeIspravno");
                } else {
                    prezimeFld.getStyleClass().removeAll("poljeIspravno");
                    prezimeFld.getStyleClass().add("poljeNijeIspravno");
                }
            });
        }
        emailFld.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                emailFld.getStyleClass().removeAll("poljeNijeIspravno");
                emailFld.getStyleClass().add("poljeIspravno");
            } else {
                emailFld.getStyleClass().removeAll("poljeIspravno");
                emailFld.getStyleClass().add("poljeNijeIspravno");
            }
        });

        passFld.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                passFld.getStyleClass().removeAll("poljeNijeIspravno");
                passFld.getStyleClass().add("poljeIspravno");
            } else {
                passFld.getStyleClass().removeAll("poljeIspravno");
                passFld.getStyleClass().add("poljeNijeIspravno");
            }
        });

    }

    public void registrujSe(ActionEvent actionEvent) throws IOException {
        String ime = imeFld.getText();
        String prezime = prezimeFld.getText();
        String email = emailFld.getText();
        String pass = passFld.getText();
        POSITION pozicija = pozicijaBox.getValue();

        User k = new User(ime, prezime, email, pass, pozicija);
        model.dodajKorisnika(ime, prezime, email, pass, pozicija);
        if (pozicija == POSITION.Fotograf) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/photographer_front_page.fxml"), bundle);
            PhotographerController ctrl = new PhotographerController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        } else if (pozicija == POSITION.Klijent) {

            otvoriDodatne(k);
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader( getClass().getResource(
                    "/fxml/owner_front_page.fxml"), bundle);
            OwnerController ctrl = new OwnerController(k);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void logujSe(ActionEvent actionEvent) throws IOException {
        User k = model.getKorisnik(emailFld.getText(), passFld.getText());
        if(k == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Neispravan email ili password.");
            alert.setContentText("Pokušajte ponovo!");
            alert.showAndWait();
        }
        else {
            if (k.getPozicija() == POSITION.Vlasnik) {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/owner_front_page.fxml"), bundle);
                OwnerController ctrl = new OwnerController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
                myStage.setTitle(bundle.getString("my_page"));
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else if (k.getPozicija() == POSITION.Fotograf) {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/photographer_front_page.fxml"), bundle);
                PhotographerController ctrl = new PhotographerController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
                myStage.setTitle(bundle.getString("my_page"));
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            } else {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/client_front_page.fxml"), bundle);
                ClientController ctrl = new ClientController(k);
                loader.setController(ctrl);
                Parent root = loader.load();
                Stage myStage = new Stage();
                myStage.setTitle(bundle.getString("my_page"));
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.setResizable(false);
                myStage.show();
            }
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }
    }

    private void otvoriDodatne(User k) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/klijent_info_register.fxml" ), bundle);
        ClientInfoController ctrl = new ClientInfoController(k);
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

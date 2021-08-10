package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EditProfileController {
    public Korisnik korisnik;
    public TextField imeFld;
    public TextField prezimeFld;
    public TextField emailFld;
    public TextField passFld;
    public TextField telefonFld;
    public int klijentId;
    public ImageView profileImg;
    private GifController gifController;
    public String tipKorisnika;
    public CRMDao model;
    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public void setProfileImg(ImageView profileImg) {
        this.profileImg = profileImg;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        imeFld.setText(korisnik.getIme());
        prezimeFld.setText(korisnik.getPrezime());
        emailFld.setText(korisnik.getEmail());
        passFld.setText(korisnik.getPassword());

        Image image;
        if (korisnik.getSlika().equals("/img/blank-profile-picture"))
            image = new Image(getClass().getResourceAsStream(korisnik.getSlika()));
        else
            image = new Image(korisnik.getSlika());
        profileImg.setImage(image);


        if(tipKorisnika.equals("Klijent")) {
            telefonFld.setText(model.getTelefon(klijentId));
        }
            imeFld.textProperty().addListener((obs, oldIme, newIme) -> {
                if (!newIme.isEmpty()) {
                    imeFld.getStyleClass().removeAll("poljeNijeIspravno");
                    imeFld.getStyleClass().add("poljeIspravno");
                } else {
                    imeFld.getStyleClass().removeAll("poljeIspravno");
                    imeFld.getStyleClass().add("poljeNijeIspravno");
                }
            });
            prezimeFld.textProperty().addListener((obs, oldIme, newIme) -> {
                if (!newIme.isEmpty()) {
                    prezimeFld.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeFld.getStyleClass().add("poljeIspravno");
                } else {
                    prezimeFld.getStyleClass().removeAll("poljeIspravno");
                    prezimeFld.getStyleClass().add("poljeNijeIspravno");
                }
            });
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
        if(telefonFld != null) {
            telefonFld.textProperty().addListener((obs, oldIme, newIme) -> {
                if (!newIme.isEmpty()) {
                    telefonFld.getStyleClass().removeAll("poljeNijeIspravno");
                    telefonFld.getStyleClass().add("poljeIspravno");
                } else {
                    telefonFld.getStyleClass().removeAll("poljeIspravno");
                    telefonFld.getStyleClass().add("poljeNijeIspravno");
                }
            });
        }
    }

    public void dajSlike(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/pretragaslike.fxml"), bundle);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("picture_search"));
        gifController = loader.getController();
        gifController.setImageView(profileImg);
        gifController.setTrenutni(korisnik);
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.show();
    }

    public void sacuvajPromjene(ActionEvent actionEvent) throws IOException {
        korisnik.setIme(imeFld.getText());
        korisnik.setPrezime(prezimeFld.getText());
        korisnik.setEmail(emailFld.getText());
        korisnik.setPassword(passFld.getText());
        korisnik.setSlika(profileImg.getImage().getUrl());
        model.postaviTelefon(telefonFld.getText(), korisnik.getId());
        imeFld.getScene().getWindow().hide();
        if (tipKorisnika.equals("Klijent")) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/klijent_front_page.fxml"), bundle);
            KlijentController ctrl = new KlijentController(korisnik);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
        else if(tipKorisnika.equals("Vlasnik")) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/vlasnik_front_page.fxml"), bundle);
            VlasnikController ctrl = new VlasnikController(korisnik);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
        else {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/fotograf_front_page.fxml"), bundle);
            FotografController ctrl = new FotografController(korisnik);
            loader.setController(ctrl);
            Parent root = loader.load();
            Stage myStage = new Stage();
            myStage.setTitle(bundle.getString("my_page"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
    }
}

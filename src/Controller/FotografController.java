package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.Projekat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    public ImageView profileImg;


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
        Image image;
        if(fotograf.getSlika().equals("/img/blank-profile-picture.png"))
             image = new Image(getClass().getResourceAsStream(fotograf.getSlika()));
        else
         image = new Image(fotograf.getSlika());

        profileImg.setImage(image);
    }
    public void odjaviSe(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/editProfile.fxml"));
        EditProfileController ctrl = new EditProfileController(fotograf);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

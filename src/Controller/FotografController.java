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
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

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

    public TextField imeFld;
    public TextField prezimeFld;
    public TextField emailFld;
    public TextField passFld;

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
    public void odjaviSe(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/editProfile.fxml"));
        FotografController ctrl = this;
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        imeFld.setText(fotograf.getIme());
        prezimeFld.setText(fotograf.getPrezime());
        emailFld.setText(fotograf.getEmail());
        passFld.setText(fotograf.getPassword());
    }
    public void dajSlike(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/pretragaslike.fxml"));
        GifController ctrl = new GifController();
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();

    }
}

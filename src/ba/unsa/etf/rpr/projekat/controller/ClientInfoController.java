package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ClientInfoController {
    public DatePicker datumRodjenja;
    public ChoiceBox<String> grad;
    public TextField telefon;
    public CRMDao model;
    public User klijent;

    public ClientInfoController(User klijent) {
        this.klijent = klijent;
    }

    @FXML
    public void initialize() {
        ObservableList<String> gradovi = FXCollections.observableArrayList();
        gradovi.add("Sarajevo");
        grad.setItems(gradovi);
        telefon.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                telefon.getStyleClass().removeAll("poljeNijeIspravno");
                telefon.getStyleClass().add("poljeIspravno");
            } else {
                telefon.getStyleClass().removeAll("poljeIspravno");
                telefon.getStyleClass().add("poljeNijeIspravno");
            }
        });

    }
    public void okBtn(ActionEvent actionEvent) throws IOException {
        model = CRMDao.getInstance();
        model.addKlijentInfo(datumRodjenja.getValue(), telefon.getText(), 1081);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/client_front_page.fxml"), bundle);
        ClientController ctrl = new ClientController(klijent);
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setTitle(bundle.getString("my_page"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

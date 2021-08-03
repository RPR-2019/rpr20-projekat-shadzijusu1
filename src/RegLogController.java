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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegLogController {
    public TextField imeFld;
    public TextField prezimeFld;
    public TextField emailFld;
    public PasswordField passFld;
    public ChoiceBox<POZICIJA> pozicijaBox;
    public ObservableList<POZICIJA> pozicije = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if(pozicijaBox != null) {
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

        if(pozicija == POZICIJA.Fotograf) {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/fotografFrontPage.fxml"));
            Parent root = loader.load();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
        else if(pozicija == POZICIJA.Klijent) {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/klijentFrontPage.fxml"));
            Parent root = loader.load();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
        else {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/vlasnikFrontPage.fxml"));
            Parent root = loader.load();
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
        }
    }
    public void logujSe() {
        //open window za tog korisnika
        System.out.println("Stari korisnik");

    }
}

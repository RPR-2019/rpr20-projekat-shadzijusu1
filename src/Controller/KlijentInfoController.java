package Controller;

import DAO.CRMDao;
import Model.Klijent;
import Model.Korisnik;
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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KlijentInfoController {
    public DatePicker datumRodjenja;
    public ChoiceBox<String> grad;
    public TextField telefon;
    public CRMDao model;
    public Korisnik klijent;

    public KlijentInfoController(Korisnik klijent) {
        this.klijent = klijent;
    }

    @FXML
    public void initialize() {
        ObservableList<String> gradovi = FXCollections.observableArrayList();
        gradovi.add("Sarajevo");
        grad.setItems(gradovi);
    }
    public void okBtn(ActionEvent actionEvent) throws IOException {
        model = CRMDao.getInstance();
        model.addKlijentInfo(datumRodjenja.getValue(), telefon.getText(), 1081);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/klijent_front_page.fxml"));
        KlijentController ctrl = new KlijentController(klijent);
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

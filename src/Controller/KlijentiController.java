package Controller;

import DAO.CRMDao;
import Model.Klijent;
import Model.Korisnik;
import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;

public class KlijentiController {
    public String status;
    public CRMDao model;
    public TableView<Klijent> tableViewKlijenti;
    private ObservableList<Klijent> klijenti = FXCollections.observableArrayList();

    public TableColumn<Klijent, String> colIme;
    public TableColumn<Klijent, String> colPrezime;
    public TableColumn<Klijent, Date> colDatumRodj;
    public TableColumn<Klijent, String> colOdgovornaOsoba;
    public TableColumn<Klijent, Date> colKontaktiranje;
    public TableColumn<Klijent, String> colTelefon;
    public TableColumn<Klijent, Date> colDatumAktivacije;

    public KlijentiController(String status) {
        this.status = status;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        colIme.setCellValueFactory(new PropertyValueFactory<Klijent, String>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<Klijent, String>("prezime"));
        colDatumRodj.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumRodjenja"));
        colDatumAktivacije.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumAktivacije"));
        colOdgovornaOsoba.setCellValueFactory(new PropertyValueFactory<Klijent, String>("odgovornaOsoba"));
        colKontaktiranje.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumKontaktiranja"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<Klijent, String>("telefon"));

        ArrayList<Klijent> clients = model.getClients();
        if(status == "Aktivan") {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).status.equals("Aktivan")) {
                    klijenti.add(clients.get(i));

                }

            }

            tableViewKlijenti.setItems(klijenti);

        }
        else {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).status.equals("Neaktivan"))
                    klijenti.add(clients.get(i));
            }
            tableViewKlijenti.setItems(klijenti);

        }
    }
}

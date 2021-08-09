package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Klijent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KlijentiController {
    public String status;
    public CRMDao model;
    public TableView<Klijent> tableViewKlijenti;
    private ObservableList<Klijent> klijenti = FXCollections.observableArrayList();
    public Button addClientBtn;
    public Button closeBtn;

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
        if(status == "Neaktivan")
            addClientBtn.setVisible(false);
        model = CRMDao.getInstance();
        colIme.setCellValueFactory(new PropertyValueFactory<Klijent, String>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<Klijent, String>("prezime"));
        colDatumRodj.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumRodjenja"));
        colDatumAktivacije.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumAktivacije"));
        colOdgovornaOsoba.setCellValueFactory(new PropertyValueFactory<Klijent, String>("odgovornaOsoba"));
        colKontaktiranje.setCellValueFactory(new PropertyValueFactory<Klijent, Date>("datumKontaktiranja"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<Klijent, String>("telefon"));

        ArrayList<Klijent> clients = model.getClients();
        if (status == "Aktivan") {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).status.equals("Aktivan")) {
                    klijenti.add(clients.get(i));

                }

            }

            tableViewKlijenti.setItems(klijenti);

        } else {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).status.equals("Neaktivan"))
                    klijenti.add(clients.get(i));
            }
            tableViewKlijenti.setItems(klijenti);

        }
        addClientBtn.setOnAction(actionEvent -> {
            try {
                dodajKlijenta();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent)
                );
    }
    private void closeAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
    public void dodajKlijenta() throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/dodaj_klijenta.fxml" ), bundle);
        AddClientController ctrl = new AddClientController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_client"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

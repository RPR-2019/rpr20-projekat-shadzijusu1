package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ClientTableController {
    public String status;
    public CRMDao model;
    public TableView<Client> tableViewKlijenti;
    private final ObservableList<Client> klijenti = FXCollections.observableArrayList();
    public Button addClientBtn;
    public Button closeBtn;

    public TableColumn<Client, String> colIme;
    public TableColumn<Client, String> colPrezime;
    public TableColumn<Client, Date> colDatumRodj;
    public TableColumn<Client, String> colOdgovornaOsoba;
    public TableColumn<Client, Date> colKontaktiranje;
    public TableColumn<Client, String> colTelefon;
    public TableColumn<Client, Date> colDatumAktivacije;

    public ClientTableController(String status) {
        this.status = status;
    }
    @FXML
    public void initialize() {
        if(status == "Neaktivan")
            addClientBtn.setVisible(false);
        model = CRMDao.getInstance();
        colIme.setCellValueFactory(new PropertyValueFactory<Client, String>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<Client, String>("prezime"));
        colDatumRodj.setCellFactory(column -> {
            TableCell<Client, Date> cell = new TableCell<Client, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        colDatumRodj.setCellValueFactory(new PropertyValueFactory<Client, Date>("datumRodjenja"));
        colDatumAktivacije.setCellFactory(column -> {
            TableCell<Client, Date> cell = new TableCell<Client, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        colDatumAktivacije.setCellValueFactory(new PropertyValueFactory<Client, Date>("datumAktivacije"));

        colOdgovornaOsoba.setCellValueFactory(new PropertyValueFactory<Client, String>("odgovornaOsoba"));
        colKontaktiranje.setCellFactory(column -> {
            TableCell<Client, Date> cell = new TableCell<Client, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        colKontaktiranje.setCellValueFactory(new PropertyValueFactory<Client, Date>("datumKontaktiranja"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<Client, String>("telefon"));

        ArrayList<Client> clients = model.getClients();
        if (status == "Aktivan") {
            for (Client client : clients) {
                if (client.getStatus().equals("Aktivan")) {
                    klijenti.add(client);

                }

            }

            tableViewKlijenti.setItems(klijenti);

        } else {
            for (Client client : clients) {
                if (client.getStatus().equals("Neaktivan"))
                    klijenti.add(client);
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
                "/fxml/add_client.fxml"), bundle);
        AddClientController ctrl = new AddClientController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_client"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}

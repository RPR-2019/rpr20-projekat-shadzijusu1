package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class VlasnikController {
    public Korisnik vlasnik;
    public CRMDao model;
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label fldPozicija;
    public Label nameFld;
    public ImageView profileImg;
    public VlasnikController(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        nameFld.setText(vlasnik.getIme() + " " + vlasnik.getPrezime());
        fldIme.setText(vlasnik.getIme());
        fldPrezime.setText(vlasnik.getPrezime());
        fldEmail.setText(vlasnik.getEmail());
        fldPass.setText(vlasnik.getPassword());
        fldPozicija.setText(vlasnik.getPozicija().toString());
        Image image;
        if(vlasnik.getSlika().equals("/img/blank-profile-picture.png"))
        image = new Image(getClass().getResourceAsStream(vlasnik.getSlika()));
        else
            image = new Image(vlasnik.getSlika());
        profileImg.setImage(image);
    }
    public void odjaviSe(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/edit_profile.fxml"));
        EditProfileController ctrl = new EditProfileController(vlasnik);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Uređivanje profila");

        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void openMail(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/send_email.fxml"));
        Parent root = loader.load();
        myStage.setTitle("Pošta");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void activeClients(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/klijenti.fxml"));
        KlijentiController ctrl = new KlijentiController("Aktivan");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Aktivni klijenti");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void archivedClients(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/klijenti.fxml"));
        KlijentiController ctrl = new KlijentiController("Neaktivan");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Arhivirani klijenti");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void employeesTable(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/uposlenici.fxml"));
        UposleniciController ctrl = new UposleniciController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Zaposleni");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void projectsTable(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/projekti.fxml"));
        ProjektiController ctrl = new ProjektiController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Projekti");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void addEmployee(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/dodaj_zaposlenog.fxml"));
        AddEmployeeController ctrl = new AddEmployeeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Dodavanje zaposlenog");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void addProject(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/add_project.fxml"));
        AddProjectController ctrl = new AddProjectController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Dodavanje projekta");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void addTask(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/add_task.fxml"));
        AddTaskController ctrl = new AddTaskController("Vlasnik");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Dodavanje taska");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }


}

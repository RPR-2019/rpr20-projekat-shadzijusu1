package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Klijent;
import ba.unsa.etf.rpr.Model.Korisnik;
import ba.unsa.etf.rpr.Model.POZICIJA;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KlijentController {
    public Klijent klijent = new Klijent("","","","", POZICIJA.Klijent);
    public CRMDao model;
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label nameFld;
    public ImageView profileImg;
    public ListView<String> projektiView;
    public KlijentController(Korisnik korisnik) {
        this.klijent.setIme(korisnik.getIme());
        this.klijent.setPrezime(korisnik.getPrezime());
        this.klijent.setEmail(korisnik.getEmail());
        this.klijent.setPassword(korisnik.getPassword());
        this.klijent.setPozicija(korisnik.getPozicija());
        this.klijent.setId(korisnik.getId());
        this.klijent.setSlika(korisnik.getSlika());
    }



    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        nameFld.setText(klijent.getIme() + " " + klijent.getPrezime());
        fldIme.setText(klijent.getIme());
        fldPrezime.setText(klijent.getPrezime());
        fldEmail.setText(klijent.getEmail());
        fldPass.setText(klijent.getPassword());
        Image image;
        if(klijent.getSlika().equals("/img/blank-profile-picture.png"))
            image = new Image(getClass().getResourceAsStream(klijent.getSlika()));
        else
            image = new Image(klijent.getSlika());

        profileImg.setImage(image);
        ObservableList<String> projekti = model.dajProjekte(klijent.getId());
        projektiView.setItems(projekti);
        projektiView.getSelectionModel().selectedItemProperty().addListener((obs, oldProjekat, newProjekat) -> {
            try {
                openProjectDetails(newProjekat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openProjectDetails(String newProjekat) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/project_detail.fxml" ), bundle);

        ProjectDataController ctrl = new ProjectDataController(newProjekat);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void odjaviSe(ActionEvent actionEvent) {
       System.exit(0);
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/edit_client_info.fxml" ), bundle);
        EditProfileController ctrl = new EditProfileController(klijent);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("edit_profile"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void openMail(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/send_email.fxml" ), bundle);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("posta"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void kontakt(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/contact_info.fxml" ), bundle);

        Parent root = loader.load();
        myStage.setTitle(bundle.getString("kontakt"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

}

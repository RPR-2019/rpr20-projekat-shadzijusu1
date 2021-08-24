package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ClientController {
    public User klijent;
    public CRMDao model;
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public Label nameFld;
    public ImageView profileImg;
    public ListView<String> projektiView;

    public ClientController(User user) {
        this.klijent = user;
    }


    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        klijent = model.getUser(klijent.getId());
        nameFld.setText(klijent.getIme() + " " + klijent.getPrezime());
        fldIme.setText(klijent.getIme());
        fldPrezime.setText(klijent.getPrezime());
        fldEmail.setText(klijent.getEmail());
        fldPass.setText(klijent.getPassword());
        Image image;
        if (klijent.getSlika().equals("/img/blank-profile-picture.png"))
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/project_detail.fxml"), bundle);

        ProjectDataController ctrl = new ProjectDataController(newProjekat, "Klijent");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void odjaviSe(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        LanguageController ctrl = new LanguageController();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choose_your_language.fxml"), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        myStage.setScene(scene);
        myStage.show();
        myStage.toFront();
        fldIme.getScene().getWindow().hide();

    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/edit_client_info.fxml"), bundle);
        EditProfileController ctrl = new EditProfileController();
        ctrl.setProfileImg(profileImg);
        ctrl.setKorisnik(klijent);
        ctrl.setTipKorisnika("Klijent");
        ctrl.setKlijentId(klijent.getId());
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("edit_profile"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        fldIme.getScene().getWindow().hide();
    }

    public void openMail(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/send_email.fxml"), bundle);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("posta"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void kontakt(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/contact_info.fxml"), bundle);

        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        myStage.setScene(scene);
        myStage.setTitle(bundle.getString("kontakt"));
        myStage.setResizable(false);
        myStage.show();
    }

}

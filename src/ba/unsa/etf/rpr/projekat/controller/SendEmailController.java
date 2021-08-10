package ba.unsa.etf.rpr.projekat.controller;


import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.GMailAuthenticator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class SendEmailController {
    public TextField sender;
    public PasswordField pass;
    public TextField primalac;
    public TextField subject;
    public TextArea text;
    public static CRMDao model;
    @FXML
    public void initialize() {
        sender.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                sender.getStyleClass().removeAll("poljeNijeIspravno");
                sender.getStyleClass().add("poljeIspravno");
            } else {
                sender.getStyleClass().removeAll("poljeIspravno");
                sender.getStyleClass().add("poljeNijeIspravno");
            }
        });
        pass.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                pass.getStyleClass().removeAll("poljeNijeIspravno");
                pass.getStyleClass().add("poljeIspravno");
            } else {
                pass.getStyleClass().removeAll("poljeIspravno");
                pass.getStyleClass().add("poljeNijeIspravno");
            }
        });
        primalac.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                primalac.getStyleClass().removeAll("poljeNijeIspravno");
                primalac.getStyleClass().add("poljeIspravno");
            } else {
                primalac.getStyleClass().removeAll("poljeIspravno");
                primalac.getStyleClass().add("poljeNijeIspravno");
            }
        });
        subject.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                subject.getStyleClass().removeAll("poljeNijeIspravno");
                subject.getStyleClass().add("poljeIspravno");
            } else {
                subject.getStyleClass().removeAll("poljeIspravno");
                subject.getStyleClass().add("poljeNijeIspravno");
            }
        });
        text.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                text.getStyleClass().removeAll("poljeNijeIspravno");
                text.getStyleClass().add("poljeIspravno");
            } else {
                text.getStyleClass().removeAll("poljeIspravno");
                text.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }
    public static boolean sendEmail(String sender, String password, String recipent, String subject, String text) throws MessagingException {
       model = CRMDao.getInstance();
        ArrayList<String> mejlovi = model.getClientMails();
        for (String s : mejlovi) {
            if (s.equals(recipent)) {
                model.setDatumKontaktiranja(LocalDateTime.now().toString(), recipent);
                break;
            }
        }


        //  Dialog dialog = AlertMaker.loadingMail();
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new GMailAuthenticator(sender, password));

        Message message = prepareMessage(session, sender, recipent, subject, text);
        Transport.send(message);
        //  dialog.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mail");
        alert.setHeaderText(null);
        alert.setContentText("Vaš mail je poslan!");

        alert.showAndWait();
        return true;
    }


    private static Message prepareMessage(Session session, String myAcc, String recipent, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcc));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public void close(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void send(ActionEvent actionEvent) {
        Runnable task = () -> {
            Platform.runLater(() -> {
                // do something here
                boolean poslano = false;
                try {
                    poslano = sendEmail(sender.getText(), pass.getText(), primalac.getText(), subject.getText(), text.getText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            });
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
package ba.unsa.etf.rpr.Controller;


import ba.unsa.etf.rpr.DAO.CRMDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
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
    public TextField text;
    public static CRMDao model;

    public static boolean sendEmail(String sender, String password, String recipent, String subject, String text) throws MessagingException {
       model = CRMDao.getInstance();
        ArrayList<String> mejlovi = model.getClientMails();
        for(int i = 0; i < mejlovi.size(); i++) {
            if(mejlovi.get(i).equals(recipent)) {
                model.setDatumKontaktiranja(LocalDateTime.now().toString(), recipent);
                break;
            }
        }


        System.out.println("PREPARING MESSAGE");
        //  Dialog dialog = AlertMaker.loadingMail();
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new GMailAuthenticator(sender, password));

        Message message = prepareMessage(session, sender, recipent, subject, text);
        Transport.send(message);
        System.out.println("MESSAGE SENT");
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
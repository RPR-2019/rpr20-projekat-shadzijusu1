package DAO;

import Model.Klijent;
import Model.Korisnik;
import Model.POZICIJA;
import Model.Projekat;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CRMDao {
    private static CRMDao instance = null;
    private Connection conn;
    private static PreparedStatement dodajKorisnika, dajKorisnika, postaviSliku, postaviIme, postaviPrezime,
    postaviMail, postaviPass, dajMojeProjekte, dajProjekat, dajKorisnikaFromId, dodajKlijenta;
    private SimpleObjectProperty<Klijent> klijent = new SimpleObjectProperty<>();

    private CRMDao() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dodajKorisnika = conn.prepareStatement("INSERT INTO Korisnik VALUES(?,?,?,?,?,?,?)");
            dajKorisnika = conn.prepareStatement("SELECT ime, prezime, email, password, pozicija, slika, id FROM Korisnik where" +
                    " email=? and password=?");
            postaviSliku = conn.prepareStatement("UPDATE Korisnik SET slika=? where email=?");
            postaviIme = conn.prepareStatement("UPDATE Korisnik SET ime=? where email=?");
            postaviPrezime = conn.prepareStatement("UPDATE Korisnik SET prezime=? where email=?");
            postaviMail = conn.prepareStatement("UPDATE Korisnik SET email=? where ime=? and prezime=? and password=?");
            postaviPass = conn.prepareStatement("UPDATE Korisnik SET password=? where email=?");
            dajMojeProjekte = conn.prepareStatement("SELECT naziv FROM Projekat where klijent=? or odgovornaOsoba=?");
            dajProjekat = conn.prepareStatement("SELECT klijent, odgovornaOsoba, gotov from Projekat where naziv=?");
            dajKorisnikaFromId = conn.prepareStatement("SELECT ime, prezime from Korisnik where id=?");
            dodajKlijenta = conn.prepareStatement("INSERT INTO Klijent VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        } catch (
                SQLException e) {

        }

    }

    public static CRMDao getInstance() {
        if (instance == null) instance = new CRMDao();
        return instance;
    }


    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }


    public void dodajKorisnika(String ime, String prezime, String email, String password, POZICIJA pozicija) {

        try {
            dodajKorisnika.setString(1, ime);
            dodajKorisnika.setString(2, prezime);
            dodajKorisnika.setString(3, email);
            dodajKorisnika.setString(4, password);
            dodajKorisnika.setString(5, pozicija.toString());
            dodajKorisnika.setString(7, "/img/blank-profile-picture.png");
            Random random = new Random();
            int id = random.nextInt(25555);
            dodajKorisnika.setInt(6, id);
            dodajKorisnika.executeUpdate();

            if(pozicija.toString().equals("Klijent")) {
              klijent = new SimpleObjectProperty<>(new Klijent(ime, prezime, email, password, pozicija));
              klijent.get().setSlika("/img/blank-profile-picture.png");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addKlijentInfo(LocalDate datumRodjenja, String telefon) {
        try {
            dodajKlijenta.setString(1, klijent.get().getIme());
            dodajKlijenta.setString(2, klijent.get().getPrezime());
            dodajKlijenta.setString(3, klijent.get().getEmail());
            dodajKlijenta.setString(4, klijent.get().getPassword());
            dodajKlijenta.setString(5, klijent.get().getPozicija().toString());
            dodajKlijenta.setInt(6, klijent.get().getId());
            dodajKlijenta.setString(7, datumRodjenja.toString());
            dodajKlijenta.setString(8, "Sarajevo");
            dodajKlijenta.setString(9, telefon);
            dodajKlijenta.setString(10, null);
            dodajKlijenta.setString(11, LocalDateTime.now().toString());
            dodajKlijenta.setString(12, "Aktivan");
            dodajKlijenta.setString(13, null);
            dodajKlijenta.setString(14, klijent.get().getSlika());
            dodajKlijenta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Korisnik getKorisnik(String email, String pass) {
        Korisnik k = null;
        try {
            dajKorisnika.setString(1, email);
            dajKorisnika.setString(2, pass);

            ResultSet result = dajKorisnika.executeQuery();
//            if(result.next() == false) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Greška");
//                alert.setHeaderText("Neispravan email ili password.");
//                alert.setContentText("Pokušajte ponovo!");
//
//                alert.showAndWait();
//                return k;
//            }
            while (result.next()) {
                String ime = result.getString(1);
                String prezime = result.getString(2);
                String poz = result.getString(5);
                POZICIJA pozicija = POZICIJA.Klijent;
                String slika = result.getString(6);
                if(poz.equals("Vlasnik"))
                    pozicija = POZICIJA.Vlasnik;
                else if(poz.equals("Fotograf"))
                    pozicija = POZICIJA.Fotograf;
                int id = result.getInt(7);
                k = new Korisnik(ime, prezime, email, pass, pozicija, slika);
                k.setId(id);
                return k;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }
    public static void postaviSliku(String slika, String email) {
        try {
            postaviSliku.setString(1, slika);
            postaviSliku.setString(2, email);
            postaviSliku.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviIme(String ime, String email) {
        try {
            postaviIme.setString(1, ime);
            postaviIme.setString(2, email);
            postaviIme.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviPrezime(String prezime, String email) {
        try {
            postaviPrezime.setString(1, prezime);
            postaviPrezime.setString(2, email);
            postaviPrezime.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviMail(String email, String ime, String prezime, String password) {
        try {
            postaviMail.setString(1, email);
            postaviMail.setString(2, ime);
            postaviMail.setString(3, ime);
            postaviMail.setString(4, ime);

            postaviMail.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviPass(String password, String email) {
        try {
            postaviPass.setString(1, password);
            postaviPass.setString(2, email);
            postaviPass.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ObservableList<String> dajProjekte(int id) {
        ObservableList<String> naziviProjekata = FXCollections.observableArrayList();
        try {
            dajMojeProjekte.setInt(1, id);
            dajMojeProjekte.setInt(2, id);
            ResultSet rs = dajMojeProjekte.executeQuery();
            while (rs.next()) {
                String naziv = rs.getString(1);
                naziviProjekata.add(naziv);
            }
            return naziviProjekata;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return naziviProjekata;
    }
    public Projekat dajProjekat(String naziv) {
            Projekat projekat = null;
            try {
            dajProjekat.setString(1, naziv);
            ResultSet rs = dajProjekat.executeQuery();
            int klijent = rs.getInt(1);
            int odgovornaOsoba = rs.getInt(2);
            int status = rs.getInt(3);
            boolean gotov = false;
            if(status == 1)
                gotov = true;
               projekat = new Projekat(klijent, naziv, odgovornaOsoba, gotov);
            return projekat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projekat;
    }
    public String userNameFromId(int id) {
        Korisnik k = null;
        try {
            dajKorisnikaFromId.setInt(1, id);
            ResultSet rs = dajKorisnikaFromId.executeQuery();
            String ime = rs.getString(1);
            String prezime = rs.getString(2);
            return ime + " " + prezime;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
}

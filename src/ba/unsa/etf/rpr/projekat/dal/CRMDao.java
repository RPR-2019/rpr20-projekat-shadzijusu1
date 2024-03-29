package ba.unsa.etf.rpr.projekat.dal;

import ba.unsa.etf.rpr.projekat.model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CRMDao {
    private static CRMDao instance = null;
    private static PreparedStatement dodajKorisnika, dajKorisnika, postaviSliku, postaviIme, postaviPrezime,
            postaviMail, postaviPass, dajMojeProjekte, dajProjekat, dajKorisnikaFromId, dodajKlijenta, dajKlijentaZaOdgOsobu,
            dajTaskoveZa, dajKlijentaPoImenu, dodajTask, cekirajTask, dajKlijente, dajZaposlene, dajProjekte,
            postaviOdgovornuOsobu, dajKorisnikaPoImenu, dodajProjekat, dajStatus, postaviStatus,
            apdejtProjekat, postaviDatumKontaktiranja, dajMailove, getUserFromId, dajTelefon, postaviTelefon;
    private SimpleObjectProperty<Client> klijent = new SimpleObjectProperty<>();
    private static Integer id = 0;

    private CRMDao() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            dodajKorisnika = conn.prepareStatement("INSERT INTO Korisnik VALUES(?,?,?,?,?,?,?)");
            dajKorisnika = conn.prepareStatement("SELECT ime, prezime, email, password, pozicija, slika, id FROM Korisnik where" +
                    " email=? and password=?");
            postaviSliku = conn.prepareStatement("UPDATE Korisnik SET slika=? where id=?");
            postaviIme = conn.prepareStatement("UPDATE Korisnik SET ime=? where id=?");
            postaviPrezime = conn.prepareStatement("UPDATE Korisnik SET prezime=? where id=?");
            postaviMail = conn.prepareStatement("UPDATE Korisnik SET email=? where id=?");
            postaviPass = conn.prepareStatement("UPDATE Korisnik SET password=? where id=?");
            dajMojeProjekte = conn.prepareStatement("SELECT naziv FROM Projekat where klijent=? or odgovornaOsoba=?");
            dajProjekat = conn.prepareStatement("SELECT klijent, odgovornaOsoba, gotov from Projekat where naziv=?");
            dajKorisnikaFromId = conn.prepareStatement("SELECT ime, prezime from Korisnik where id=?");
            dodajKlijenta = conn.prepareStatement("INSERT INTO Klijent VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            dajKlijentaZaOdgOsobu = conn.prepareStatement("SELECT ime, prezime from Klijent where odgovornaOsoba=?");
            dajTaskoveZa = conn.prepareStatement("SELECT naziv, opis, deadline, chekiran from Task where odgovornaOsoba=? AND klijentId=?");
            dajKlijentaPoImenu = conn.prepareStatement("SELECT id from Klijent where ime=? and prezime=?");
            dajKorisnikaPoImenu = conn.prepareStatement("SELECT id from Korisnik where ime=? and prezime=?");

            dodajTask = conn.prepareStatement("INSERT INTO Task VALUES(?,?,?,?,?,?,?)");
            cekirajTask = conn.prepareStatement("UPDATE Task set chekiran=1 where naziv=?");
            dajKlijente = conn.prepareStatement("SELECT ime, prezime, datumRodjenja, odgovornaOsoba, datumKontaktiranja, telefon, status, datumAktivacije from Klijent");
            dajZaposlene = conn.prepareStatement("SELECT id, ime, prezime, email from Korisnik where pozicija='Fotograf'");
            dajProjekte = conn.prepareStatement("SELECT klijent, naziv, odgovornaOsoba, gotov from Projekat");
            postaviOdgovornuOsobu = conn.prepareStatement("UPDATE Klijent set odgovornaOsoba=? where id=?");

            dodajProjekat = conn.prepareStatement("INSERT INTO PROJEKAT VALUES(?,?,?,?,?)");
            dajStatus = conn.prepareStatement("SELECT status from Klijent where id=?");
            postaviStatus = conn.prepareStatement("UPDATE Klijent set status=? where id=?");
            apdejtProjekat = conn.prepareStatement("UPDATE Projekat set gotov=? and odgovornaOsoba=? where naziv=?");

            postaviDatumKontaktiranja = conn.prepareStatement("UPDATE Klijent set datumKontaktiranja=? where email=?");
            dajMailove = conn.prepareStatement("SELECT email from Klijent");
            getUserFromId = conn.prepareStatement("SELECT ime, prezime, email, password, pozicija, slika from Korisnik where id=?");

            dajTelefon = conn.prepareStatement("SELECT telefon from Klijent where id=?");
            postaviTelefon = conn.prepareStatement("UPDATE Klijent set telefon=? where id=?");
        } catch (
                SQLException e) {
                e.printStackTrace();
            }

    }

    public static CRMDao getInstance() {
        if (instance == null) instance = new CRMDao();
        return instance;
    }



    public void dodajKorisnika(String ime, String prezime, String email, String password, POSITION pozicija) {

        try {
            dodajKorisnika.setString(1, ime);
            dodajKorisnika.setString(2, prezime);
            dodajKorisnika.setString(3, email);
            dodajKorisnika.setString(4, password);
            dodajKorisnika.setString(5, pozicija.toString());
            dodajKorisnika.setString(7, "/img/blank-profile-picture.png");
            Random random = new Random();
            id = random.nextInt(25555);
            dodajKorisnika.setInt(6, id);
            dodajKorisnika.executeUpdate();

            if (pozicija.toString().equals("Klijent")) {
                klijent = new SimpleObjectProperty<>(new Client(ime, prezime, email, password, pozicija));
                klijent.get().setSlika("/img/blank-profile-picture.png");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addKlijentInfo(LocalDate datumRodjenja, String telefon, int odgovornaOsobaId) {
        try {
            dodajKlijenta.setString(1, klijent.get().getIme());
            dodajKlijenta.setString(2, klijent.get().getPrezime());
            dodajKlijenta.setString(3, klijent.get().getEmail());
            dodajKlijenta.setString(4, klijent.get().getPassword());
            dodajKlijenta.setString(5, klijent.get().getPozicija().toString());
            dodajKlijenta.setInt(6, id);
            dodajKlijenta.setString(7, datumRodjenja.toString());
            dodajKlijenta.setString(8, "Sarajevo");
            dodajKlijenta.setString(9, telefon);
            dodajKlijenta.setInt(10, odgovornaOsobaId);
            dodajKlijenta.setString(11, LocalDateTime.now().toString());
            dodajKlijenta.setString(12, "Aktivan");
            dodajKlijenta.setString(13, LocalDateTime.now().toString());
            dodajKlijenta.setString(14, klijent.get().getSlika());
            dodajKlijenta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getKorisnik(String email, String pass) {
        User k = null;
        try {
            dajKorisnika.setString(1, email);
            dajKorisnika.setString(2, pass);

            ResultSet result = dajKorisnika.executeQuery();
            while(result.next()) {
                String ime = result.getString(1);
                String prezime = result.getString(2);
                String poz = result.getString(5);
                POSITION pozicija = POSITION.Klijent;
                String slika = result.getString(6);
                if (poz.equals("Vlasnik"))
                    pozicija = POSITION.Vlasnik;
                else if (poz.equals("Fotograf"))
                    pozicija = POSITION.Fotograf;
                int id = result.getInt(7);
                k = new User(ime, prezime, email, pass, pozicija, slika);
                k.setId(id);
                return k;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }

    public static void postaviSliku(String slika, int id) {
        try {
            postaviSliku.setString(1, slika);
            postaviSliku.setInt(2, id);
            postaviSliku.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void postaviIme(String ime, int id) {
        try {
            postaviIme.setString(1, ime);
            postaviIme.setInt(2, id);
            postaviIme.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void postaviPrezime(String prezime, int id) {
        try {
            postaviPrezime.setString(1, prezime);
            postaviPrezime.setInt(2, id);
            postaviPrezime.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void postaviMail(String email, int id) {
        try {
            postaviMail.setString(1, email);
            postaviMail.setInt(2, id);
            postaviMail.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void postaviPass(String password, int id) {
        try {
            postaviPass.setString(1, password);
            postaviPass.setInt(2, id);
            postaviPass.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void postaviTelefon(String telefon, int id) {
        try {
            postaviTelefon.setString(1, telefon);
            postaviTelefon.setInt(2, id);
            postaviTelefon.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getTelefon(int id) {
        try {
            dajTelefon.setInt(1, id);
            ResultSet rs = dajTelefon.executeQuery();
            return rs.getString(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
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

    public Project dajProjekat(String naziv) {
        Project projekat = null;
        try {
            dajProjekat.setString(1, naziv);
            ResultSet rs = dajProjekat.executeQuery();
            int klijentId = rs.getInt(1);
            String klijent = userNameFromId(klijentId);
            int odgovornaOsobaId = rs.getInt(2);
            String odgovornaOsoba = userNameFromId(odgovornaOsobaId);
            boolean status = rs.getBoolean(3);
            String gotov = "Završen";
            if (!status)
                gotov = "Aktivan";
            projekat = new Project(klijent, naziv, odgovornaOsoba, gotov);
            return projekat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projekat;
    }

    public String userNameFromId(int id) {
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

    public ObservableList<String> getKlijentZaOdgOsobu(int id) {
        ObservableList<String> klijenti = FXCollections.observableArrayList();
        try {
            dajKlijentaZaOdgOsobu.setInt(1, id);
            ResultSet rs = dajKlijentaZaOdgOsobu.executeQuery();
            while (rs.next()) {
                String ime = rs.getString(1);
                String prezime = rs.getString(2);
                String naziv = ime + " " + prezime;
                klijenti.add(naziv);
            }
            return klijenti;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return klijenti;
    }

    public ArrayList<Task> taskovi(int odgovornaOsoba, int klijent) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            dajTaskoveZa.setInt(1, odgovornaOsoba);
            dajTaskoveZa.setInt(2, klijent);
            ResultSet rs = dajTaskoveZa.executeQuery();
            while (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(rs.getString(3));
                Task task = new Task(rs.getString(2), rs.getString(1), date, rs.getBoolean(4));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public int getKlijentId(String naziv) {
        try {
            String[] nazivTrimed = naziv.split(" ");
            String ime = nazivTrimed[0];
            String prezime = nazivTrimed[1];
            dajKlijentaPoImenu.setString(1, ime);
            dajKlijentaPoImenu.setString(2, prezime);
            ResultSet rs = dajKlijentaPoImenu.executeQuery();
            int id = -1;
            while (rs.next())
                id = rs.getInt(1);
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public int getKorisnikFromName(String naziv) {
        try {
            String[] nazivTrimed = naziv.split(" ");
            String ime = nazivTrimed[0];
            String prezime = nazivTrimed[1];
            dajKorisnikaPoImenu.setString(1, ime);
            dajKorisnikaPoImenu.setString(2, prezime);
            ResultSet rs = dajKorisnikaPoImenu.executeQuery();
            int id = -1;
            while (rs.next())
                id = rs.getInt(1);
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void addTask(String naziv, String opis, LocalDate deadline, int klijent, int odgovornaOsoba) {
        try {
            dodajTask.setString(1, opis);
            dodajTask.setString(2, naziv);
            dodajTask.setString(3, deadline.toString());
            dodajTask.setInt(4, 0);
            int id = new Random().nextInt(789434);
            dodajTask.setInt(5, id);
            dodajTask.setInt(6, odgovornaOsoba);
            dodajTask.setInt(7, klijent);
            dodajTask.executeUpdate();

            postaviOdgovornuOsobu.setInt(1, odgovornaOsoba);
            postaviOdgovornuOsobu.setInt(2, klijent);
            postaviOdgovornuOsobu.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void finishTask(String naziv) {
        try {
            cekirajTask.setString(1, naziv);
            cekirajTask.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Client> getClients() {
        ArrayList<Client> klijenti = new ArrayList<>();
        try {
            ResultSet rs = dajKlijente.executeQuery();
            while (rs.next()) {
                Client k = new Client();
                k.setIme(rs.getString(1));
                k.setPrezime(rs.getString(2));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(rs.getString(3));
                k.setDatumRodjenja(date);
                int odgovornaOsobaId = rs.getInt(4);
                String odgovornaOsoba = userNameFromId(odgovornaOsobaId);
                k.setOdgovornaOsoba(odgovornaOsoba);
                date = formatter.parse(rs.getString(5));
                k.setDatumKontaktiranja(date);
                k.setTelefon(rs.getString(6));
                if (rs.getString(7).equals("Aktivan"))
                    k.setStatus(STATUS.Aktivan);
                else
                    k.setStatus(STATUS.Neaktivan);

                date = formatter.parse(rs.getString(8));
                k.setDatumAktivacije(date);
                klijenti.add(k);
            }
            return klijenti;
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return klijenti;
    }

    public ArrayList<User> getEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        try {
            ResultSet rs = dajZaposlene.executeQuery();
            while (rs.next()) {
                User employee = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> projekti = new ArrayList<>();
        try {
            ResultSet rs = dajProjekte.executeQuery();
            while (rs.next()) {
                int klijentId = rs.getInt(1);
                String klijent = userNameFromId(klijentId);
                int odgovornaOsobaId = rs.getInt(3);
                String odgovornaOsoba = userNameFromId(odgovornaOsobaId);
                boolean status = rs.getBoolean(4);
                String gotov = "Završen";
                if (!status)
                    gotov = "Aktivan";
                Project projekat = new Project(klijent, rs.getString(2), odgovornaOsoba, gotov);
                projekti.add(projekat);
            }
            return projekti;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projekti;
    }

    public void addProject(int klijent, String naziv, int odgovornaOsoba) {
        try {
            int id = new Random().nextInt(64546);
            dodajProjekat.setInt(1, id);
            dodajProjekat.setInt(2, klijent);
            dodajProjekat.setString(3, naziv);
            dodajProjekat.setInt(4, odgovornaOsoba);
            dodajProjekat.setInt(5, 0);
            dodajProjekat.executeUpdate();

            postaviOdgovornuOsobu.setInt(1, odgovornaOsoba);
            postaviOdgovornuOsobu.setInt(2, klijent);
            postaviOdgovornuOsobu.executeUpdate();

            dajStatus.setInt(1, klijent);
            ResultSet rs = dajStatus.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals("Neaktivan")) {
                    postaviStatus.setString(1, "Aktivan");
                    postaviStatus.setInt(2, klijent);
                    postaviStatus.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProject(int odgovornaOsoba, int gotov, String naziv) {
        try {
            apdejtProjekat.setInt(1, gotov);
            apdejtProjekat.setInt(2, odgovornaOsoba);
            apdejtProjekat.setString(3, naziv);
            apdejtProjekat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void arhivirajKlijenta(int klijent) {
        try {
            postaviStatus.setString(1, "Neaktivan");
            postaviStatus.setInt(2, klijent);
            postaviStatus.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getClientMails() {
        ArrayList<String> mejlovi = new ArrayList<>(0);
        try {
            ResultSet rs = dajMailove.executeQuery();
            while (rs.next()) {
                mejlovi.add(rs.getString(1));
            }
            return mejlovi;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mejlovi;
    }

    public void setDatumKontaktiranja(String kontakt, String email) {
        try {
            postaviDatumKontaktiranja.setString(1, (kontakt));
            postaviDatumKontaktiranja.setString(2, email);
            postaviDatumKontaktiranja.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        User k = null;
        try {
            getUserFromId.setInt(1, id);

            ResultSet result = getUserFromId.executeQuery();

                String ime = result.getString(1);
                String prezime = result.getString(2);
                String email = result.getString(3);
                String pass = result.getString(4);
                String poz = result.getString(5);
                POSITION pozicija = POSITION.Klijent;
                String slika = result.getString(6);
                if (poz.equals("Vlasnik"))
                    pozicija = POSITION.Vlasnik;
                else if (poz.equals("Fotograf"))
                    pozicija = POSITION.Fotograf;
                k = new User(ime, prezime, email, pass, pozicija, slika);
                k.setId(id);
                return k;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }

}
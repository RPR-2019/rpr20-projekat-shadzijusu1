package Model;

import java.util.Date;

public class Task {
    public String opis;
    public String naziv;
    public Date deadline;
    public Boolean chekiran;

    public Task(String opis, String naziv, Date deadline, Boolean chekiran) {
        this.opis = opis;
        this.naziv = naziv;
        this.deadline = deadline;
        this.chekiran = chekiran;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean getChekiran() {
        return chekiran;
    }

    public void setChekiran(Boolean chekiran) {
        this.chekiran = chekiran;
    }
}


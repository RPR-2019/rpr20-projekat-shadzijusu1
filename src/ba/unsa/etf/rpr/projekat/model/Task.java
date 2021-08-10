package ba.unsa.etf.rpr.projekat.model;

import java.util.Date;

public class Task {
    private String opis;
    private String naziv;
    private Date deadline;
    private Boolean chekiran;
    private Integer odgovornaOsoba;
    private Integer klijentId;


    public Task(String opis, String naziv, Date deadline, Boolean chekiran, Integer odgovornaOsoba, Integer klijentId) {
        this.opis = opis;
        this.naziv = naziv;
        this.deadline = deadline;
        this.chekiran = chekiran;
        this.odgovornaOsoba = odgovornaOsoba;
        this.klijentId = klijentId;
    }

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

    public Integer getOdgovornaOsoba() {
        return odgovornaOsoba;
    }

    public void setOdgovornaOsoba(Integer odgovornaOsoba) {
        this.odgovornaOsoba = odgovornaOsoba;
    }

    public Integer getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Integer klijentId) {
        this.klijentId = klijentId;
    }
}


package hu.nje.gyakbead.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pilota")
public class Pilota {
    @Id
    private Integer az;
    private String nev;
    private String nem;
    private LocalDate szuldat;
    private String nemzet;

    public Pilota() {
    }

    public Pilota(Integer az, String nev, String nem, LocalDate szuldat, String nemzet) {
        this.az = az;
        this.nev = nev;
        this.nem = nem;
        this.szuldat = szuldat;
        this.nemzet = nemzet;
    }

    public Integer getAz() {
        return az;
    }

    public void setAz(Integer az) {
        this.az = az;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getNem() {
        return nem;
    }

    public void setNem(String nem) {
        this.nem = nem;
    }

    public LocalDate getSzuldat() {
        return szuldat;
    }

    public void setSzuldat(LocalDate szuldat) {
        this.szuldat = szuldat;
    }

    public String getNemzet() {
        return nemzet;
    }

    public void setNemzet(String nemzet) {
        this.nemzet = nemzet;
    }
}

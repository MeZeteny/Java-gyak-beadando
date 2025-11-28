package hu.nje.gyakbead.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gp")
public class Gp {
    @Id
    private LocalDate datum;
    private String nev;
    private String helyszin;

    public Gp() {
    }

    public Gp(LocalDate datum, String nev, String helyszin) {
        this.datum = datum;
        this.nev = nev;
        this.helyszin = helyszin;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }
}
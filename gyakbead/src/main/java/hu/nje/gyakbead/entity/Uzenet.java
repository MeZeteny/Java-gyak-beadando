package hu.nje.gyakbead.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzenet")
public class Uzenet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név megadása kötelező!")
    @Size(max = 100, message = "A név túl hosszú!")
    private String nev;

    @NotBlank(message = "Az email megadása kötelező!")
    @Email(message = "Érvénytelen email cím!")
    private String email;

    @NotBlank(message = "A tárgy megadása kötelező!")
    @Size(max = 200, message = "A tárgy túl hosszú!")
    private String targy;

    @NotBlank(message = "Az üzenet megadása kötelező!")
    @Size(min = 10, max = 2000, message = "Az üzenet 10–2000 karakter között lehet!")
    private String szoveg;

    private LocalDateTime kuldesIdeje = LocalDateTime.now();

    public Uzenet() {
    }

    public Uzenet(Long id, String nev, String email, String targy, String szoveg, LocalDateTime kuldesIdeje) {
        this.id = id;
        this.nev = nev;
        this.email = email;
        this.targy = targy;
        this.szoveg = szoveg;
        this.kuldesIdeje = kuldesIdeje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    public String getSzoveg() {
        return szoveg;
    }

    public void setSzoveg(String szoveg) {
        this.szoveg = szoveg;
    }

    public LocalDateTime getKuldesIdeje() {
        return kuldesIdeje;
    }

    public void setKuldesIdeje(LocalDateTime kuldesIdeje) {
        this.kuldesIdeje = kuldesIdeje;
    }
}

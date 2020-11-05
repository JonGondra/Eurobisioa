package ehu.isad.model;

import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Lag {
    private Herrialdea herrialdea;
    private String artista;
    private String abestia;
    private int puntuazioa;
    private int puntuak;
    private Image bandera;

    public Lag(Herrialdea herrialdea, String artista, String abestia, Integer puntuak) throws FileNotFoundException {
        this.herrialdea = herrialdea;
        this.artista = artista;
        this.abestia = abestia;
        this.puntuazioa = 0;
        this.puntuak = puntuak;

        String path = Utils.lortuEzarpenak().getProperty("pathtoimages")+herrialdea.getBandera()+".png";
        this.bandera = new Image(new FileInputStream(path));
    }

    public String getIzena() {
        return herrialdea.getIzena();
    }

    public Image getBandera() {
        return bandera;
    }

    public String getArtista() {
        return artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public int getPuntuazioa() {
        return puntuazioa;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setBandera(Image bandera) {
        this.bandera = bandera;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAbestia(String abestia) {
        this.abestia = abestia;
    }

    public void setPuntuazioa(int puntuazioa) {
        this.puntuazioa = puntuazioa;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }

    public void puntuazioaEguneratu(String bozkatzaile, Integer puntuak) {
        EurobisioaKud eurobisioaKud = EurobisioaKud.getInstance();
        eurobisioaKud.puntuakEguneratu(bozkatzaile, this, puntuak);
    }

}

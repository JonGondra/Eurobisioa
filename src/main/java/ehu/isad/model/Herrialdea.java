package ehu.isad.model;

import javafx.scene.image.Image;

public class Herrialdea {
    private String izena;
    private String bandera;


    public Herrialdea(String izena, String bandera) {
        this.izena = izena;
        this.bandera = bandera;
    }

    public String getIzena() {
        return izena;
    }

    public String getBandera() {
        return bandera;
    }

    @Override
    public String toString() {
        return izena;
    }
}

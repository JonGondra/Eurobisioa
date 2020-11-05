package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;
import ehu.isad.model.Lag;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EurobisioaKud {

    private static final EurobisioaKud instance = new EurobisioaKud();
    public static EurobisioaKud getInstance() {
        return instance;
    }

    private EurobisioaKud() { }

    public List<Herrialdea> lortuHerrialdeak(){
        String query = "SELECT izena, bandera FROM Herrialde";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Herrialdea> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                System.out.println(izena + ":" + bandera);
                emaitza.add(new Herrialdea(izena,bandera));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }

    public String bozkatuDu(String herrialdea){
        String query = "SELECT h.bandera bandera FROM Herrialde h, Bozkaketa b WHERE izena='"+herrialdea+"' AND b.bozkatuDu=h.izena AND b.urtea=(SELECT strftime('%Y','now'))";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            if(rs.next()){
                return rs.getString("bandera");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Lag> lortuOrdezkariak(){
        String query = "SELECT izena, bandera, artista, abestia, puntuak FROM Ordezkaritza, Herrialde  WHERE izena = herrialdea AND urtea=(SELECT strftime('%Y','now')-1) ORDER BY izena ASC";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Lag> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                String artista = rs.getString("artista");
                String abestia = rs.getString("abestia");
                Integer puntuak = rs.getInt("puntuak");
                Herrialdea herrialdea=new Herrialdea(izena,bandera);
                emaitza.add(new Lag(herrialdea,artista,abestia,puntuak));
            }
        } catch(SQLException | FileNotFoundException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }

    public List<Lag> lortuTop3(){
        String query = "SELECT izena, bandera, artista, abestia, puntuak FROM Ordezkaritza, Herrialde  WHERE izena = herrialdea AND urtea=(SELECT strftime('%Y','now')-1) ORDER BY puntuak DESC LIMIT 3";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Lag> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                String artista = rs.getString("artista");
                String abestia = rs.getString("abestia");
                Integer puntuak = rs.getInt("puntuak");
                Herrialdea herrialdea=new Herrialdea(izena,bandera);
                emaitza.add(new Lag(herrialdea,artista,abestia,puntuak));
            }
        } catch(SQLException | FileNotFoundException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }

    public void puntuakEguneratu(String bozkatzaile, Lag bozkatua, Integer puntuak){
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        String query1 = "INSERT INTO Bozkaketa VALUES ('"+bozkatua.getIzena()+"', '"+bozkatzaile+"',(SELECT strftime('%Y','now')),"+puntuak+")";
        String query2 = "UPDATE Ordezkaritza SET puntuak=puntuak+"+puntuak+" WHERE herrialdea='"+bozkatua.getIzena()+"' and urtea = (SELECT strftime('%Y', 'now')-1)";
        dbKudeatzaile.execSQL(query1);
        dbKudeatzaile.execSQL(query2);
    }

}

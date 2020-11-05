package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ErroreaKud {

    private Eurobisioa main;

    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }
    @FXML
    private ImageView bandera_imgvw;

    @FXML
    private Text txt_banatu;

    @FXML
    private Button btn_ok;

    @FXML
    private ImageView logo;

    @FXML
    void onClick(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();

    }

    public void info(Herrialdea herrialdea){
        this.txt_banatu.setText(herrialdea.getIzena()+ "k jada banatu ditu bere puntuak");
        this.banderaJarri(herrialdea.getBandera());
        this.logoJarri();
    }

    public void banderaJarri(String herrialdea) {
        String imagePath = Utils.lortuEzarpenak().getProperty("pathtoimages")+herrialdea+"bandera.png";
        try {
            bandera_imgvw.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void logoJarri(){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathtoimages")+"logo.png";
        try {
            logo.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

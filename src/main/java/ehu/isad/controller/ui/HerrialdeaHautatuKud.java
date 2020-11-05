package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Lag;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements Initializable {

    private Eurobisioa main;

    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }

    @FXML
    private ComboBox<Herrialdea> cmbBx_herrialdeak;

    @FXML
    private Button btn_ok;

    @FXML
    private ImageView logo;

    @FXML
    void onClick(ActionEvent event) {
        if (cmbBx_herrialdeak.getValue() != null) {
            Herrialdea herrialdea = (Herrialdea) cmbBx_herrialdeak.getValue();
            String bandera = EurobisioaKud.getInstance().bozkatuDu(herrialdea.getIzena());
            if (bandera != null) { //bozkatu du
                main.erroreaErakutsi(herrialdea);

            } else { //ez du bozkatu
                main.bozkaketaEginErakutsi(herrialdea);
            }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Herrialdea> herrialdeList = EurobisioaKud.getInstance().lortuHerrialdeak();
        ObservableList<Herrialdea> herrialdeak = FXCollections.observableArrayList(herrialdeList);
        cmbBx_herrialdeak.setItems(herrialdeak);
        this.logoJarri();

    }
}



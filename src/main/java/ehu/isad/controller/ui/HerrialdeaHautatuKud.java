package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
    void onClick(ActionEvent event) {
        if (cmbBx_herrialdeak.getValue() != null) {
            Herrialdea herrialdea = (Herrialdea) cmbBx_herrialdeak.getValue();
            String bandera = EurobisioaKud.getInstance().bozkatuDu(herrialdea.getIzena());
            if (bandera != null) { //Jada bozkatu du
                main.erroreaErakutsi(herrialdea);

            } else { //Oraindik ez du bozkatu

            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Herrialdea> herrialdeList = EurobisioaKud.getInstance().lortuHerrialdeak();
        ObservableList<Herrialdea> herrialdeak = FXCollections.observableArrayList(herrialdeList);
        cmbBx_herrialdeak.setItems(herrialdeak);

    }
}



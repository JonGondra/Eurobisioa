package ehu.isad.controller.ui;
import ehu.isad.Eurobisioa;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Top3Kud implements Initializable {

    private Eurobisioa main;
    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }

    @FXML
    private ImageView logo;

    @FXML
    private Button ok_btn;

    @FXML
    private TableView<?> tb_data;

    @FXML
    private TableColumn<?, ?> bandera_img;

    @FXML
    private TableColumn<?, ?> izena;

    @FXML
    private TableColumn<?, ?> puntuak;

    @FXML
    void onClick(ActionEvent event) {

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
        this.logoJarri();
    }
}

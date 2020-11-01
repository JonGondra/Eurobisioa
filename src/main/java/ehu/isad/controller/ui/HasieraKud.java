package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HasieraKud {

    private Eurobisioa main;

    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }

    @FXML
    private Button bozkatu_btn;

    @FXML
    private ImageView imgvw_logo;

    @FXML
    void onClick(ActionEvent event) {this.main.herrialdeaHautatuErakutsi();}

}

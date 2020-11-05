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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Top3Kud implements Initializable {

    private Eurobisioa main;
    private List<Lag> lagList;
    private ObservableList<Lag> lagak;

    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }

    @FXML
    private ImageView logo;

    @FXML
    private Button ok_btn;

    @FXML
    private TableView<Lag> tb_data;

    @FXML
    private TableColumn<Lag, Image> bandera_img;

    @FXML
    private TableColumn<Lag, Herrialdea> izena;

    @FXML
    private TableColumn<Lag, Integer> puntuak;

    @FXML
    void onClick(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();
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
        lagList = EurobisioaKud.getInstance().lortuTop3();
        lagak = FXCollections.observableArrayList(lagList);
        tb_data.setEditable(true);

        izena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        puntuak.setCellValueFactory(new PropertyValueFactory<>("puntuak"));

        bandera_img.setCellValueFactory(new PropertyValueFactory<Lag, Image>("bandera"));

        bandera_img.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(15);
                    imageview.setFitWidth(20);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                    // tbData.refresh();
                }else{
                    setGraphic(null);
                    setText(null);
                }
            }
        });


        //add your data to the table here.
        tb_data.setItems(lagak);
    }
}

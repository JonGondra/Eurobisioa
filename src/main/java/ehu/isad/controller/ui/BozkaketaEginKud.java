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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BozkaketaEginKud implements Initializable {

    private Eurobisioa main;
    private String bozkatzaile;
    private Integer puntuak=0;
    private List<Lag> lagList;
    private ObservableList<Lag> lagak;


    public void setMainApp(Eurobisioa main) {
        this.main = main;
    }

    @FXML
    private TableView<Lag> tb_data;

    @FXML
    private TableColumn<Lag, Image> col_bandera;

    @FXML
    private TableColumn<Lag, Herrialdea> col_herrialdea;

    @FXML
    private TableColumn<Lag, String> col_artista;

    @FXML
    private TableColumn<Lag, String> col_abestia;

    @FXML
    private TableColumn<Lag, Integer> col_puntuak;

    @FXML
    private Text txt;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView imgvq_herrialdea;


    @FXML
    void onClick(ActionEvent event) {
        /*
        Lag[] herrialdeak = tb_data.getItems().toArray(new Lag[0]);
        int i = 0;
        while (i<herrialdeak.length){
            if(!herrialdeak[i].getIzena().equals(bozkatzaile)){
                herrialdeak[i].puntuazioaEguneratu(bozkatzaile, this.puntuak);
                i++;
            }
        }
        main.top3Erakutsi();

         */
        List<Lag>bozkatuak = this.getBozkatuak();
        if(this.puntuak <= 5 && this.puntuak > 0){
            for(int i=0; i<bozkatuak.size(); i++){
                Lag lag = bozkatuak.get(i);
                lag.puntuazioaEguneratu(bozkatzaile,lag.getPuntuazioa());
            }
            main.top3Erakutsi();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lagList = EurobisioaKud.getInstance().lortuOrdezkariak();
        lagak = FXCollections.observableArrayList(lagList);
        tb_data.setEditable(true);

        col_herrialdea.setCellValueFactory(new PropertyValueFactory<>("izena"));
        col_abestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        col_artista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        col_puntuak.setCellValueFactory(new PropertyValueFactory<>("puntuazioa"));

        col_puntuak.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_puntuak.setOnEditCommit(
                t -> {

                    if(t.getNewValue()>t.getOldValue() && t.getNewValue()>=0){ //Puntuak eman
                        this.puntuak = this.puntuak+(t.getNewValue()-t.getOldValue());
                    }
                    else if(t.getNewValue()<t.getOldValue() && t.getNewValue()>=0){ //Puntuak kendu
                        this.puntuak = this.puntuak-(t.getOldValue()-t.getNewValue());
                    }
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setPuntuazioa(t.getNewValue());
                }
        );

        col_bandera.setCellValueFactory(new PropertyValueFactory<Lag, Image>("bandera"));

        col_bandera.setCellFactory(p -> new TableCell<>() {
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

        Callback<TableColumn<Lag, Integer>, TableCell<Lag, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.<Lag, Integer>forTableColumn(new IntegerStringConverter());

        col_puntuak.setCellFactory(col -> {
            TableCell<Lag, Integer> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    if (cell.getTableView().getSelectionModel().getSelectedItem().getIzena().equals(this.bozkatzaile)) {
                        cell.setEditable(false);
                    }else {
                        cell.setEditable(true);
                    }
                }
            });

            return cell ;
        });


    }


    public void info(Herrialdea herrialdea){
        this.txt.setText(herrialdea.getIzena()+ "k horrela banatu nahi ditu bere puntuak:");
        this.bozkatzaile = herrialdea.getIzena();
        this.banderaJarri(herrialdea.getBandera());
        this.logoJarri();
    }

    private void banderaJarri(String bandera){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathtoimages")+bandera+"bandera.png";
        try {
            imgvq_herrialdea.setImage(new Image(new FileInputStream(imagePath)));
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
    private List<Lag> getBozkatuak(){
        List<Lag> emaitza = new ArrayList<>();
        for(int i=0; i < this.lagak.size(); i++){
            if(this.lagak.get(i).getPuntuazioa() != 0){
                emaitza.add(this.lagak.get(i));
            }
        }
        return emaitza;
    }

}

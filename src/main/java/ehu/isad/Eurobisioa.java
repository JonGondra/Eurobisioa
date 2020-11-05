package ehu.isad;

import ehu.isad.controller.ui.*;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Eurobisioa extends Application {

  private Parent HasieraUI;
  private Parent HerrialdeaHautatuUI;
  private Parent ErroreaUI;
  private Parent BozkaketaEginUI;
  private Parent Top3UI;

  private Stage stage;

  private Scene sceneHas;
  private Scene sceneHerrialdeHautatu;
  private Scene sceneErrorea;
  private Scene sceneBozkaketaEgin;
  private Scene sceneTop3;

  private HasieraKud hasieraKud;
  private HerrialdeaHautatuKud herrialdeaHautatuKud;
  private ErroreaKud erroreaKud;
  private BozkaketaEginKud bozkaketaEginKud;
  private Top3Kud top3Kud;

  @Override
  public void start(Stage primaryStage) throws Exception{
    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("EUROVISION");
    stage.setScene(sceneHas);
    stage.show();
  }

  private void pantailakKargatu() throws IOException {
    FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/hasiera.fxml"));
    HasieraUI = (Parent) loaderHasiera.load();
    hasieraKud = loaderHasiera.getController();
    hasieraKud.setMainApp(this);
    sceneHas = new Scene(HasieraUI);

    FXMLLoader loaderHarrialdeaHautatu = new FXMLLoader(getClass().getResource("/herrialdeaHautatu.fxml"));
    HerrialdeaHautatuUI = (Parent) loaderHarrialdeaHautatu.load();
    herrialdeaHautatuKud = loaderHarrialdeaHautatu.getController();
    herrialdeaHautatuKud.setMainApp(this);
    sceneHerrialdeHautatu = new Scene(HerrialdeaHautatuUI);

    FXMLLoader loaderErrorea = new FXMLLoader(getClass().getResource("/errorea.fxml"));
    ErroreaUI = (Parent) loaderErrorea.load();
    erroreaKud = loaderErrorea.getController();
    erroreaKud.setMainApp(this);
    sceneErrorea = new Scene(ErroreaUI);

    FXMLLoader loaderBozkaketaEgin = new FXMLLoader(getClass().getResource("/bozkaketaegin.fxml"));
    BozkaketaEginUI = (Parent) loaderBozkaketaEgin.load();
    bozkaketaEginKud = loaderBozkaketaEgin.getController();
    bozkaketaEginKud.setMainApp(this);
    sceneBozkaketaEgin = new Scene(BozkaketaEginUI);

    FXMLLoader loaderTop3 = new FXMLLoader(getClass().getResource("/top3.fxml"));
    Top3UI = (Parent) loaderTop3.load();
    top3Kud = loaderTop3.getController();
    top3Kud.setMainApp(this);
    sceneTop3 = new Scene(Top3UI);


  }

  public void herrialdeaHautatuErakutsi(){
    stage.setTitle("Informazioaren Eguneraketa");
    this.ikonoaJarri("");
    stage.setScene(sceneHerrialdeHautatu);
    stage.show();
  }

  public void erroreaErakutsi(Herrialdea herrialdea){
    stage.setTitle(herrialdea.getIzena()+"ren inguruko informazioa");
    erroreaKud.banderaJarri(herrialdea.getIzena());
    erroreaKud.info(herrialdea);
    this.ikonoaJarri(herrialdea.getIzena());
    stage.setScene(sceneErrorea);
    stage.show();
  }

  public void bozkaketaEginErakutsi(Herrialdea herrialdea){
    stage.setTitle("BozkaketaPanela");
    bozkaketaEginKud.info(herrialdea);
    this.ikonoaJarri(herrialdea.getIzena());
    stage.setScene(sceneBozkaketaEgin);
    stage.show();
  }

  public void top3Erakutsi(){
    stage.setTitle("TOP 3");
    this.ikonoaJarri("");
    stage.setScene(sceneTop3);
    stage.show();
  }

  private void ikonoaJarri(String izena){
    String path = Utils.lortuEzarpenak().getProperty("pathtoimages")+izena+"bandera.png";
    try {
      if(stage.getIcons().size()>0){
        stage.getIcons().remove(0);
      }
      stage.getIcons().add(new Image(new FileInputStream(path)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }



  public static void main(String[] args) {
    launch(args);
  }
}

package ehu.isad;

import ehu.isad.controller.ui.ErroreaKud;
import ehu.isad.controller.ui.HasieraKud;
import ehu.isad.controller.ui.HerrialdeaHautatuKud;
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

  private Stage stage;

  private Scene sceneHas;
  private Scene sceneHerrialdeHautatu;
  private Scene sceneErrorea;

  private HasieraKud hasieraKud;
  private HerrialdeaHautatuKud herrialdeaHautatuKud;
  private ErroreaKud erroreaKud;

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
  }

  public void herrialdeaHautatuErakutsi(){
    stage.setTitle("Informazioaren Eguneraketa");
    stage.setScene(sceneHerrialdeHautatu);
    stage.show();
  }

  public void erroreaErakutsi(Herrialdea herrialdea){
    stage.setTitle(herrialdea.getIzena()+"ren inguruko informazioa");
    erroreaKud.banderaJarri(herrialdea.getIzena());
    erroreaKud.info(herrialdea);
    stage.setScene(sceneErrorea);
    stage.show();
  }



  public static void main(String[] args) {
    launch(args);
  }
}

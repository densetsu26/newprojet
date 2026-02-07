/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.ucao.uut.tp.poo.applications;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Projet_java extends Application {
    
     public static void main(String[] args) {
        launch(args);}
     
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
            getClass().getClassLoader().getResource("app/acceuil.fxml")
        );

        primaryStage.setTitle("Mon application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        
    }

   
    
}


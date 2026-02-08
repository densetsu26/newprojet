/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.ucao.uut.tp.poo.applications;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Projet_java extends Application {
    
     @Override
    public void start(Stage primaryStage) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/acceuil.fxml"));
            Parent root = loader.load();

           
            Scene scene = new Scene(root);
            
            
            primaryStage.setTitle("Gestion de Transport Maritime");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); 
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Erreur fatale : Impossible de charger l'interface principale.");
            System.err.println("Vérifiez que 'src/main/resources/app/accueil.fxml' existe.");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Erreur : Le chemin du fichier FXML est incorrect.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
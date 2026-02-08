/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principale de l'application
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Chargement de la vue principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tg/ucao/uut/tp/poo/views/acceuil.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("AIR MER - Gestion de Réservations");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERREUR FATALE : Impossible de charger 'acceuil.fxml'. Vérifiez le chemin.");
        }
    }

    public static void main(String[] args) {
    launch(args);
}
}

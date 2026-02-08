/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.applications;

/**
 *
 * @author DELL
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        handleShowAcceuil();
    }

    /**
     * Méthode générique pour charger une vue FXML dans le centre du BorderPane
     */
    private void loadView(String fxmlFile) {
        try {
            // Chargement du fichier FXML
            // Note : vérifie bien le chemin (ex: "/views/Acceuil.fxml")
            Parent root = FXMLLoader.load(getClass().getResource("/app/" + fxmlFile + ".fxml"));
            
            // On l'injecte au centre
            mainBorderPane.setCenter(root);
            
        } catch (IOException e) {
            System.err.println("Erreur : Impossible de charger la vue " + fxmlFile);
        } catch (NullPointerException e) {
            System.err.println("Erreur : Fichier " + fxmlFile + ".fxml non trouvé. Vérifiez le chemin.");
        }
    }

    // --- Gestion des clics sur les boutons ---

    @FXML
    private void handleShowAcceuil() {
        loadView("MotBienvenue"); // Remplace par le nom exact de ton fichier FXML
    }

    @FXML
    private void handleShowBateaux() {
        loadView("BateauView");
    }

    @FXML
    private void handleShowVoyages() {
        loadView("VoyageView");
    }

    @FXML
    private void handleShowTickets() {
        loadView("ViewTicket");
    }
}
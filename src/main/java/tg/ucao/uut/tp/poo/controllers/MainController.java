/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur principal pour le layout avec menu et contenu central.
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleShowAcceuil();
    }

    /**
     * Charge un fichier FXML dans le centre du BorderPane
     */
    private void loadView(String fxmlFile) {
        try {
            URL resource = getClass().getResource("/tg/ucao/uut/tp/poo/views/" + fxmlFile + ".fxml");
            if (resource == null) {
                showError("Fichier introuvable", "Impossible de charger : " + fxmlFile + ".fxml");
                return;
            }

            Parent root = FXMLLoader.load(resource);
            mainBorderPane.setCenter(root);

        } catch (IOException e) {
            showError("Erreur FXML", "Impossible de charger : " + fxmlFile + ".fxml\n" + e.getMessage());
        }
    }

    private void showError(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // --- Actions menu ---

    @FXML
    private void handleShowAcceuil() {
        loadView("MotBienvenue"); // Doit correspondre au FXML exact
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

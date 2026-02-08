/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tg.ucao.uut.tp.poo.models.Voyage;
import tg.ucao.uut.tp.poo.modelsDAO.VoyageDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;

public class VoyageController {

    @FXML private TableView<Voyage> tableVoyages;

    @FXML private TableColumn<Voyage, Long> col_ID;
    @FXML private TableColumn<Voyage, String> col_Depart;
    @FXML private TableColumn<Voyage, String> col_Arrive;
    @FXML private TableColumn<Voyage, LocalDateTime> col_HeureDepart;
    @FXML private TableColumn<Voyage, String> col_Bateau;

    private final VoyageDAO voyageDAO = new VoyageDAO();
    private final ObservableList<Voyage> voyageList = FXCollections.observableArrayList();

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @FXML
    public void initialize() {

        col_ID.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));
        col_Depart.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDepart()));
        col_Arrive.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDestination()));

        // Formatage propre du LocalDateTime
        col_HeureDepart.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });

        col_HeureDepart.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDateDebut())
        );

        // Affichage du NOM du bateau
        col_Bateau.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getBateau() != null
                                ? c.getValue().getBateau().getNom()
                                : "-"
                )
        );

        chargerDonnees();
    }

    public void chargerDonnees() {
        voyageList.setAll(voyageDAO.selectAll());
        tableVoyages.setItems(voyageList);
    }

    @FXML
    private void handleAjouter() {
        ouvrirFormulaire(null);
    }

    @FXML
    private void handleModifier() {
        Voyage selected = tableVoyages.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Sélection requise", "Veuillez sélectionner un voyage.");
            return;
        }
        ouvrirFormulaire(selected);
    }

   @FXML
    private void handleSupprimer(ActionEvent event) {
    Voyage selected = tableVoyages.getSelectionModel().getSelectedItem();
    
    if (selected != null) {
        try {
            // On tente la suppression
            voyageDAO.delete(selected.getId());
            
            // Si ça marche, on rafraîchit le tableau
            chargerVoyages(); 
            
        } catch (java.sql.SQLException e) {
            // Si la base de données refuse (ex: voyage lié à un ticket)
            e.printStackTrace();
            
            // On affiche une petite boîte de message à l'utilisateur
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("Impossible de supprimer ce voyage");
            alert.setContentText("Détail : " + e.getMessage());
            alert.showAndWait();
        }
    }
}


    private void ouvrirFormulaire(Voyage voyage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/tg/ucao/uut/tp/poo/views/AjouterVoyage.fxml")
            );

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(voyage == null ? "Ajouter un voyage" : "Modifier un voyage");
            stage.setScene(new Scene(loader.load()));

            VoyageFormController controller = loader.getController();
            controller.initData(voyage, this);

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void chargerVoyages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

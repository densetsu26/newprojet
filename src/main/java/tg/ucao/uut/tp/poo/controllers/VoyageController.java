/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

/**
 *
 * @author DELL
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.format.DateTimeFormatter;
import tg.ucao.uut.tp.poo.models.Voyage;

public class VoyageController {

    @FXML private TableView<Voyage> tableVoyages;
    @FXML private TableColumn<Voyage, Number> colId;
    @FXML private TableColumn<Voyage, String> colBateau;
    @FXML private TableColumn<Voyage, String> colDepart;
    @FXML private TableColumn<Voyage, String> colArrivee;
    @FXML private TableColumn<Voyage, Object> colDateHeureDepart;
    @FXML private TableColumn<Voyage, Object> colDateHeureArrivee;
    @FXML private TableColumn<Voyage, String> colDuree;

    // Liste observable (si on ajoute un voyage, le tableau se met à jour tout seul)
    private ObservableList<Voyage> voyageData = FXCollections.observableArrayList();
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @FXML
    public void initialize() {
        // 1. Liaison des colonnes avec les Properties du modèle
        //
        //colId.setCellValueFactory(cellData -> cellData.getValue().idVoyageProperty());
        //colDepart.setCellValueFactory(cellData -> cellData.getValue().departProperty());
        //colArrivee.setCellValueFactory(cellData -> cellData.getValue().arriveeProperty());
        
        // 2. Formatage personnalisé pour le Bateau (on affiche son nom)
        //colBateau.setCellValueFactory(cellData -> cellData.getValue().getBateau().nomProperty());

        // 3. Formatage des dates pour l'affichage
        colDateHeureDepart.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    setText(getTableRow().getItem().getDateDebut().format(formatter));
                }
            }
        });

        /*/ 4. Calcul de la durée à la volée
        colDuree.setCellValueFactory(cellData -> {
            long minutes = cellData.getValue().getDuree().toMinutes();
            return new javafx.beans.property.SimpleStringProperty(minutes / 60 + "h " + minutes % 60 + "m");
        });
*/
        // Charger les données (simulées pour l'instant)
        chargerDonnees();
    }

    private void chargerDonnees() {
        // Ici, tu appelleras ton DAO : voyageData.addAll(voyageDAO.findAll());
        tableVoyages.setItems(voyageData);
    }

    @FXML
    private void handleAjouter() {
        System.out.println("Ouverture formulaire ajout...");
        // Logique pour charger ajoutervoyage.fxml
    }

    @FXML
    private void handleModifier() {
        Voyage selected = tableVoyages.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Modification de : " + selected.getId());
        } else {
            afficherAlerte("Selection", "Veuillez sélectionner un voyage à modifier.");
        }
    }

    @FXML
    private void handleSupprimer() {
        Voyage selected = tableVoyages.getSelectionModel().getSelectedItem();
        if (selected != null) {
            voyageData.remove(selected);
            // voyageDAO.delete(selected);
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

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
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.modelsDAO.BateauDAO;

import java.util.Optional;

public class BateauController {

    @FXML private TableView<Bateau> tableBateaux;
    @FXML private TableColumn<Bateau, Long> col_ID;
    @FXML private TableColumn<Bateau, String> col_Nom;
    @FXML private TableColumn<Bateau, String> col_Description;
    @FXML private TableColumn<Bateau, Integer> col_Cap;

    private final BateauDAO bateauDAO = new BateauDAO();
    private final ObservableList<Bateau> bateauList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        col_ID.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getId()));
        col_Nom.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getNom()));
        col_Description.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDescription()));
        col_Cap.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getCapacite()));

        chargerDonnees();
    }

    public void chargerDonnees() {
        bateauList.setAll(bateauDAO.selectAll());
        tableBateaux.setItems(bateauList);
    }

    @FXML
    private void handleAjouter() {
        ouvrirFormulaire(null);
    }

    @FXML
    private void handleModifier() {
        Bateau selected = tableBateaux.getSelectionModel().getSelectedItem();
        if (selected != null) ouvrirFormulaire(selected);
        else alerteSelectionRequise();
    }

    @FXML
    private void handleSupprimer() {
        Bateau selected = tableBateaux.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer ce bateau ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {
                    bateauDAO.delete(selected.getId());
                    chargerDonnees();
                } catch (Exception e) {
                    afficherErreur("Erreur de suppression", e.getMessage());
                }
            }
        } else alerteSelectionRequise();
    }

    private void ouvrirFormulaire(Bateau b) {
        try {
            String fxmlFile = (b == null) ? "/tg/ucao/uut/tp/poo/views/AjouterBateau.fxml" 
                                           : "/tg/ucao/uut/tp/poo/views/ModifierBateau.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));

            BateauFormController controller = loader.getController();
            controller.initData(b, this);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            afficherErreur("Erreur d'ouverture", "Impossible de charger le formulaire.");
        }
    }

    private void alerteSelectionRequise() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un bateau dans le tableau.");
        alert.show();
    }

    private void afficherErreur(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

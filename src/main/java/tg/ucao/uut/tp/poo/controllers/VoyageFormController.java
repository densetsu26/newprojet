/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.models.Voyage;
import tg.ucao.uut.tp.poo.modelsDAO.BateauDAO;
import tg.ucao.uut.tp.poo.modelsDAO.VoyageDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VoyageFormController {

    @FXML private TextField tf_Depart;
    @FXML private TextField tf_Destination;
    @FXML private TextField tf_HeureDebut;
    @FXML private TextField tf_HeureFin;
    @FXML private DatePicker dp_DateDebut;
    @FXML private DatePicker dp_DateFin;
    @FXML private ComboBox<Bateau> cb_Bateau;

    private Voyage voyageActuel;
    private VoyageController parentController;

    private final VoyageDAO voyageDAO = new VoyageDAO();
    private final BateauDAO bateauDAO = new BateauDAO();


    public void initData(Voyage v, VoyageController parent) {
        this.voyageActuel = v;
        this.parentController = parent;

        cb_Bateau.getItems().setAll(bateauDAO.selectAll());

        if (v != null) {
            tf_Depart.setText(v.getDepart());
            tf_Destination.setText(v.getDestination());

            if (v.getDateDebut() != null) {
                dp_DateDebut.setValue(v.getDateDebut().toLocalDate());
                tf_HeureDebut.setText(v.getDateDebut().toLocalTime().toString());
            }

            if (v.getDateFin() != null) {
                dp_DateFin.setValue(v.getDateFin().toLocalDate());
                tf_HeureFin.setText(v.getDateFin().toLocalTime().toString());
            }

            cb_Bateau.setValue(v.getBateau());
        }
    }

    @FXML
    private void handleEnregistrer() {

        try {
            if (!validerFormulaire()) return;

            if (voyageActuel == null) {
                voyageActuel = new Voyage();
            }

            voyageActuel.setDepart(tf_Depart.getText().trim());
            voyageActuel.setDestination(tf_Destination.getText().trim());

            LocalDate dDebut = dp_DateDebut.getValue();
            LocalDate dFin = dp_DateFin.getValue();
            LocalTime hDebut = LocalTime.parse(tf_HeureDebut.getText());
            LocalTime hFin = LocalTime.parse(tf_HeureFin.getText());

            voyageActuel.setDateDebut(LocalDateTime.of(dDebut, hDebut));
            voyageActuel.setDateFin(LocalDateTime.of(dFin, hFin));

            voyageActuel.setBateau(cb_Bateau.getValue());

            if (voyageActuel.isNew()) {
                voyageDAO.create(voyageActuel);
            } else {
                voyageDAO.update(voyageActuel);
            }

            parentController.chargerDonnees();
            fermerFenetre();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    private boolean validerFormulaire() {

        if (tf_Depart.getText().isBlank()
                || tf_Destination.getText().isBlank()
                || dp_DateDebut.getValue() == null
                || dp_DateFin.getValue() == null
                || tf_HeureDebut.getText().isBlank()
                || tf_HeureFin.getText().isBlank()
                || cb_Bateau.getValue() == null) {

            showAlert(Alert.AlertType.WARNING, "Champs manquants",
                    "Tous les champs sont obligatoires.");
            return false;
        }

        LocalDateTime debut = LocalDateTime.of(
                dp_DateDebut.getValue(),
                LocalTime.parse(tf_HeureDebut.getText())
        );

        LocalDateTime fin = LocalDateTime.of(
                dp_DateFin.getValue(),
                LocalTime.parse(tf_HeureFin.getText())
        );

        if (fin.isBefore(debut)) {
            showAlert(Alert.AlertType.WARNING, "Dates invalides",
                    "La date de fin doit être postérieure à la date de début.");
            return false;
        }

        return true;
    }

    private void fermerFenetre() {
        ((Stage) tf_Depart.getScene().getWindow()).close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

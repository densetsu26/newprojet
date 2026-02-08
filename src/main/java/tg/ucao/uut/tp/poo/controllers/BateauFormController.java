/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.modelsDAO.BateauDAO;

public class BateauFormController {

    @FXML private TextField tf_Nom;
    @FXML private TextField tf_cap;
    @FXML private TextArea ta_Description;
    @FXML private TextField tf_NumSiege;

    private Bateau bateauActuel;
    private BateauController parentController;
    private final BateauDAO bateauDAO = new BateauDAO();

    public void initData(Bateau b, BateauController parent) {
        this.bateauActuel = b;
        this.parentController = parent;

        if (b != null) {
            tf_Nom.setText(b.getNom());
            tf_cap.setText(String.valueOf(b.getCapacite()));
            ta_Description.setText(b.getDescription());
            tf_NumSiege.setText(b.getNumerotationSiege());
        }
    }

    @FXML
    private void handleEnregistrer() {
        if (!validerSaisie()) return;

        try {
            if (bateauActuel == null) bateauActuel = new Bateau();

            bateauActuel.setNom(tf_Nom.getText());
            bateauActuel.setCapacite(Integer.parseInt(tf_cap.getText()));
            bateauActuel.setDescription(ta_Description.getText());
            bateauActuel.setNumerotationSiege(tf_NumSiege.getText());

            if (bateauActuel.isNew()) bateauDAO.create(bateauActuel);
            else bateauDAO.update(bateauActuel);

            parentController.chargerDonnees();
            fermer();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur : " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAnnuler() {
        fermer();
    }

    private boolean validerSaisie() {
        if (tf_Nom.getText().isBlank() || tf_cap.getText().isBlank()) return false;
        try {
            int val = Integer.parseInt(tf_cap.getText());
            if (val < 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void fermer() {
        Stage stage = (Stage) tf_Nom.getScene().getWindow();
        stage.close();
    }
}

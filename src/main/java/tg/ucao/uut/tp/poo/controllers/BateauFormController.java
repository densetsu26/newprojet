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

    @FXML private Label lblTitre; // Pense à ajouter un fx:id="lblTitre" dans ton FXML
    @FXML private TextField tfNom;
    @FXML private TextField tfCapacite;
    @FXML private TextArea taDescription;
    @FXML private Button btnEnregistrer;

    private BateauDAO bateauDao = new BateauDAO();
    
    // Variables pour gérer le mode modification
    private Bateau bateauToEdit;
    private boolean isUpdateMode = false;

    /**
     * Méthode appelée depuis la liste pour passer un bateau à modifier
     */
    public void setBateau(Bateau bateau) {
        if (bateau != null) {
            this.bateauToEdit = bateau;
            this.isUpdateMode = true;

            // Remplir les champs avec les données existantes
            tfNom.setText(bateau.getNom());
            tfCapacite.setText(String.valueOf(bateau.getCapacite()));
            taDescription.setText(bateau.getDescription());

            // Changer le texte de l'interface
            if (lblTitre != null) lblTitre.setText("Modifier le bateau");
            btnEnregistrer.setText("Mettre à jour");
        }
    }

    @FXML
    private void handleEnregistrer() {
        try {
            // 1. Validation
            if (tfNom.getText().isEmpty() || tfCapacite.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champs vides", "Le nom et la capacité sont obligatoires.");
                return;
            }

            Integer capacite = Integer.parseInt(tfCapacite.getText());

            if (isUpdateMode) {
                // --- MODE MODIFICATION ---
                bateauToEdit.setNom(tfNom.getText());
                bateauToEdit.setCapacite(capacite);
                bateauToEdit.setDescription(taDescription.getText());

                // Appel au DAO (vérifie si c'est dbUpdate ou update dans ton BaseDBDAO)
               // bateauDao.dbUpdate(bateauToEdit); 
                
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Le bateau a été mis à jour !");
            } else {
                // --- MODE AJOUT ---
                Bateau nouveauBateau = new Bateau(
                    tfNom.getText(),
                    taDescription.getText(),
                    "Standard", 
                    capacite
                );

                // Appel au DAO (dbInsert)
                //bateauDao.dbInsert(nouveauBateau);

                showAlert(Alert.AlertType.INFORMATION, "Succès", "Le bateau a été enregistré !");
            }
            
            fermerFenetre();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de format", "La capacité doit être un nombre entier.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur BDD", "Erreur lors de l'opération : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void fermerFenetre() {
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
    }
}
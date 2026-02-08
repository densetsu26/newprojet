/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.controllers;

/**
 *
 * @author DELL
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.models.Voyage;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class VoyageFormController {

    @FXML private Label lblTitre; // On change le texte dynamiquement
    @FXML private Button btnEnregistrer;
    @FXML private TextField tfDepart, tfArrivee, tfHeureDebut, tfHeureFin;
    @FXML private DatePicker dpDateDebut, dpDateFin;
    @FXML private ComboBox<Bateau> cbBateau;

    private Voyage voyageToEdit; 
    private boolean isUpdateMode = false;

    /**
     
     * @param voyage
     */
    public void setVoyage(Voyage voyage) {
        if (voyage != null) {
            this.voyageToEdit = voyage;
            this.isUpdateMode = true;
            
            // Mise à jour de l'UI
            lblTitre.setText("Modifier le Voyage n°" + voyage.getId());
            btnEnregistrer.setText("Mettre à jour");

            // Pré-remplissage des champs
            tfDepart.setText(voyage.getDepart());
            tfArrivee.setText(voyage.getArriver());
            dpDateDebut.setValue(voyage.getDateDebut().toLocalDate());
            dpDateFin.setValue(voyage.getDatefin().toLocalDate());
            cbBateau.setValue(voyage.getBateau());
        }
    }

    @FXML
    private void handleEnregistrer() {
        // ... (Logique de validation identique) ...

        if (isUpdateMode) {
            // Logique UPDATE : voyageToEdit.setDepart(tfDepart.getText()); ...
            System.out.println("Mise à jour en BDD...");
        } else {
            // Logique INSERT : Voyage n = new Voyage(...);
            System.out.println("Création en BDD...");
        }
    }
}

package tg.ucao.uut.tp.poo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.models.Voyage;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import tg.ucao.uut.tp.poo.modelsDAO.VoyageDAO;

public class VoyageFormController implements Initializable {

    @FXML private Label lblTitre;
    @FXML private Button btnEnregistrer;
    @FXML private TextField tfDepart, tfArrivee, tfHeureDebut, tfHeureFin;
    @FXML private DatePicker dpDateDebut, dpDateFin;
    @FXML private ComboBox<Bateau> cbBateau;
    
   // private final VoyageDAO voyageDAO = new VoyageDAO();
    private Voyage voyageToEdit; 
    private boolean isUpdateMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Optionnel : Configurer ici le format des DatePickers ou charger la liste des bateaux
    }

    public void setVoyage(Voyage voyage) {
        if (voyage != null) {
            this.voyageToEdit = voyage;
            this.isUpdateMode = true;
            
            lblTitre.setText("Modifier le Voyage n°" + voyage.getId());
            btnEnregistrer.setText("Mettre à jour");

            tfDepart.setText(voyage.getDepart());
            tfArrivee.setText(voyage.getArriver());
            
            // Extraction de la date et de l'heure depuis le LocalDateTime du modèle
            dpDateDebut.setValue(voyage.getDateDebut().toLocalDate());
            tfHeureDebut.setText(voyage.getDateDebut().toLocalTime().toString());
            
            dpDateFin.setValue(voyage.getDatefin().toLocalDate());
            tfHeureFin.setText(voyage.getDatefin().toLocalTime().toString());
            
            cbBateau.setValue(voyage.getBateau());
        }
    }

    @FXML
    private void handleEnregistrer() {
        try {
            // 1. Vérification que les dates sont sélectionnées
            if (dpDateDebut.getValue() == null || dpDateFin.getValue() == null) {
                showError("Champs manquants", "Veuillez sélectionner les dates de début et de fin.");
                return;
            }

            
            LocalTime hDebut = LocalTime.parse(tfHeureDebut.getText());
            LocalTime hFin = LocalTime.parse(tfHeureFin.getText());

           
            LocalDateTime debutComplet = LocalDateTime.of(dpDateDebut.getValue(), hDebut);
            LocalDateTime finComplete = LocalDateTime.of(dpDateFin.getValue(), hFin);

            if (isUpdateMode) {
               
                voyageToEdit.setDepart(tfDepart.getText());
                voyageToEdit.setArriver(tfArrivee.getText());
                voyageToEdit.setDateDebut(debutComplet);
                voyageToEdit.setDatefin(finComplete);
                voyageToEdit.setBateau(cbBateau.getValue());
                
                showInfo("Succès", "Le voyage a été mis à jour avec succès.");
            } else {
                
                Voyage newVoyage = new Voyage(0, tfDepart.getText(), tfArrivee.getText(), debutComplet, finComplete, cbBateau.getValue());
                // Ici, tu appellerais ton service DAO : voyageDao.save(nouveau);
                showInfo("Succès", "Le nouveau voyage a été enregistré.");
            }

        } catch (DateTimeParseException e) {
            showError("Format d'heure incorrect", "L'heure doit être au format HH:MM (ex: 08:30 ou 14:00).");
        } catch (Exception e) {
            showError("Erreur inattendue", "Une erreur est survenue : " + e.getMessage());
        }
    }

    

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
       private void fermerFenetre() {
        Stage stage = (Stage) btnEnregistrer.getScene().getWindow();
        stage.close();
    }
}
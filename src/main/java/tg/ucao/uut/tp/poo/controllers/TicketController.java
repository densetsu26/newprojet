package tg.ucao.uut.tp.poo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tg.ucao.uut.tp.poo.models.Bateau;
import tg.ucao.uut.tp.poo.models.Ticket;
import tg.ucao.uut.tp.poo.models.Voyage;
import tg.ucao.uut.tp.poo.modelsDAO.TicketDAO;
import tg.ucao.uut.tp.poo.modelsDAO.VoyageDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketController {

    @FXML private TextField tfNom;
    @FXML private TextField tfPrenom;
    @FXML private ComboBox<Voyage> cbVoyage;
    @FXML private ComboBox<String> cbSiege;
    @FXML private TableView<Ticket> tableTickets;

    @FXML private TableColumn<Ticket, String> colNom;
    @FXML private TableColumn<Ticket, String> colPrenom;
    @FXML private TableColumn<Ticket, String> colVoyage;
    @FXML private TableColumn<Ticket, String> colSiege;

    private final TicketDAO ticketDAO = new TicketDAO();
    private final VoyageDAO voyageDAO = new VoyageDAO();

    private final ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Initialisation des colonnes
        colNom.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNomClient()));
        colPrenom.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getPrenomClient()));
        colVoyage.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getVoyage() != null
                        ? c.getValue().getVoyage().getDepart() + " → " + c.getValue().getVoyage().getDestination()
                        : "-"
        ));
        colSiege.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(c.getValue().getNumeroSiege())
        ));

        // Charger les voyages dans la ComboBox
        cbVoyage.getItems().setAll(voyageDAO.selectAll());

        // Écoute du choix d’un voyage pour mettre à jour les sièges disponibles
        cbVoyage.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                mettreAJourSieges(newV);
            }
        });

        // Charger les tickets existants
        chargerTickets();
    }

    private void chargerTickets() {
        List<Ticket> tickets = ticketDAO.selectAll();
        List<Voyage> voyages = voyageDAO.selectAll();

    // On réassocie manuellement les objets voyages aux tickets pour l'affichage
        for (Ticket t : tickets) {
        voyages.stream()
               .filter(v -> v.getId().equals(t.getVoyageId()))
               .findFirst()
               .ifPresent(t::setVoyage);
    }

    ticketList.setAll(tickets);
    tableTickets.setItems(ticketList);
}

    private void mettreAJourSieges(Voyage voyage) {
        cbSiege.getItems().clear();

        int capacite = voyage.getBateau() != null ? voyage.getBateau().getCapacite() : 0;

        // Liste des sièges déjà réservés
        List<Integer> siegeOccupe = new ArrayList<>();
        for (Ticket t : ticketDAO.selectAll()) {
            if (t.getVoyageId().equals(voyage.getId())) {
                siegeOccupe.add(t.getNumeroSiege());
            }
        }

        // Génération des sièges disponibles
        for (int i = 1; i <= capacite; i++) {
            if (!siegeOccupe.contains(i)) {
                cbSiege.getItems().add(String.valueOf(i));
            }
        }
    }

    @FXML
    private void handleAjouterTicket() {
        try {
            if (!validerFormulaire()) return;

            Voyage selectedVoyage = cbVoyage.getValue();
            int siege = Integer.parseInt(cbSiege.getValue());

            Ticket nouveauTicket = new Ticket(
                    tfNom.getText().trim(),
                    tfPrenom.getText().trim(),
                    selectedVoyage,
                    siege
            );

            ticketDAO.create(nouveauTicket);

            chargerTickets();
            mettreAJourSieges(selectedVoyage);

            // Nettoyer le formulaire
            tfNom.clear();
            tfPrenom.clear();
            cbSiege.getItems().clear();
            cbVoyage.getSelectionModel().clearSelection();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Ticket ajouté avec succès !");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ajouter le ticket : " + e.getMessage());
            alert.showAndWait();
        }
    }

    private boolean validerFormulaire() {
        if (tfNom.getText().isBlank()
                || tfPrenom.getText().isBlank()
                || cbVoyage.getValue() == null
                || cbSiege.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}

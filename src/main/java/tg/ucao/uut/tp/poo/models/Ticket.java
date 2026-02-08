/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;

import tg.ucao.uut.tp.poo.dao.Column;
import tg.ucao.uut.tp.poo.dao.Transient;

/**
 * Modèle métier Ticket
 * Représente un ticket associé à un voyage
 */
public class Ticket extends BaseModel {

    @Column("nom_client")
    private String nomClient;

    @Column("prenom_client")
    private String prenomClient;

    @Column("num_siege")
    private int numeroSiege;

    @Column("voyage_id")
    private Long voyageId; // Persisté en base

    @Transient
    private Voyage voyage; // Utilisé uniquement pour l'affichage

    public Ticket() {
        super();
    }

    public Ticket(String nomClient, String prenomClient, Voyage voyage, int numeroSiege) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.numeroSiege = numeroSiege;
        this.setVoyage(voyage); // Utilise le setter pour bien remplir voyageId
}


    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public int getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(int numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public Long getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    /**
     * Synchronise automatiquement voyageId avec l'objet Voyage
     */
    public final void setVoyage(Voyage voyage) {
        this.voyage = voyage;
        if (voyage != null) {
            this.voyageId = voyage.getId();
        }
    }

    /**
     * Utilisé pour les TableView JavaFX (optionnel)
     */
    @Override
    public Object[] toTableRow() {
        return new Object[]{
            getId(),
            nomClient,
            prenomClient,
            numeroSiege,
            voyage != null
                ? voyage.getDepart() + " → " + voyage.getDestination()
                : "—"
        };
    }
}

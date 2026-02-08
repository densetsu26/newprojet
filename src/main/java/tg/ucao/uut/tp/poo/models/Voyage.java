/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;

import tg.ucao.uut.tp.poo.dao.Column;
import tg.ucao.uut.tp.poo.dao.Transient;

import java.time.LocalDateTime;
import java.util.Objects;

public class Voyage extends BaseModel {

    @Column("depart")
    private String depart;

    @Column("arrivee")
    private String destination;

    @Column("DateDebut")
    private LocalDateTime dateDebut;

    @Column("dateFin")
    private LocalDateTime dateFin;

    // Clé étrangère stockée en base
    @Column("id_bateau")
    private Long bateauId;

    // Objet métier utilisé côté UI uniquement
    @Transient
    private Bateau bateau;

  
    public Voyage() {
        super();
    }

    public Voyage(
            String depart,
            String destination,
            LocalDateTime dateDebut,
            LocalDateTime dateFin,
            Bateau bateau
    ) {
        this.depart = depart;
        this.destination = destination;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        setBateau(bateau);
    }

  
    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Long getBateauId() {
        return bateauId;
    }

    public void setBateauId(Long bateauId) {
        this.bateauId = bateauId;
    }

    public Bateau getBateau() {
        return bateau;
    }

    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
        if (bateau != null) {
            this.bateauId = bateau.getId();
        }
    }

    public boolean estValide() {
        return depart != null
                && destination != null
                && dateDebut != null
                && dateFin != null
                && dateDebut.isBefore(dateFin)
                && bateauId != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voyage)) return false;
        Voyage other = (Voyage) o;
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
        return depart + " → " + destination + " (" + dateDebut.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")) + ")";
}

    @Override
    public Object[] toTableRow() {
        return new Object[]{
                getId(),
                depart,
                destination,
                dateDebut,
                dateFin,
                bateau != null ? bateau.getNom() : null
        };
    }
    
}



  
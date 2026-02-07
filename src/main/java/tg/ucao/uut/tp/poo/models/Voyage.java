/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;

import java.time.Duration;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;




/**
 *
 * @author user
 */
public class Voyage extends BaseModel{
    private long id_Voyage;
    private String depart;
    private String arriver;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Bateau bateau;
    public Duration calculerDurée(){
        Duration diff;
        diff = Duration.between(heureDebut,heureFin);
        
        return diff;
    }
    public Voyage(String depart,String arriver,LocalDate dateDebut, LocalDate dateFin,LocalDateTime HeureDebut, LocalDateTime HeureFin,Bateau bateau){
        this.id_Voyage=+id;
        this.depart=depart;
        this.arriver=arriver;
        this.dateDebut= dateDebut;
        this.dateFin= dateFin;
        this.bateau=bateau;
        this.heureFin= heureFin;
        this.heureDebut= heureDebut;
        
        
    }
    @Override
    public Object[] toTableRow() {
        return new Object[]{getId(), getDepart(), getArriver(), getDateDebut(), getDatefin(), getBateau()};
    }

    /**
     * @return the id
     */
    public Long getId_Voyage() {
        return id_Voyage;
    }

    /**
     * @param id the id to set
     */
    public void setId_Voyage(int id) {
        this.id_Voyage = id;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * @return the depart
     */
    public String getDepart() {
        return depart;
    }

    /**
     * @param depart the depart to set
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * @return the arriver
     */
    public String getArriver() {
        return arriver;
    }

    /**
     * @param arriver the arriver to set
     */
    public void setArriver(String arriver) {
        this.arriver = arriver;
    }

    /**
     * @return the dateDebut
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * @return the datefin
     */
    public LocalDate getDatefin() {
        return dateFin;
    }

    /**
     * @param datefin the datefin to set
     */
    public void setDatefin(LocalDate datefin) {
        this.dateFin = datefin;
    }

    /**
     * @return the bateau
     */
    public Bateau getBateau() {
        return bateau;
    }

    /**
     * @param bateau the bateau to set
     */
    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.arriver);
        return hash;
    }
      @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voyage other = (Voyage) obj;
        return Objects.equals(this.arriver, other.arriver);
    }
    /*public Ticket editerTicket(){
        return new Ticket();
    }*/
    
}

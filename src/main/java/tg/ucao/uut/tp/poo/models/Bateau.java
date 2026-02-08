/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;

import tg.ucao.uut.tp.poo.dao.Column;
import java.util.Objects;

public class Bateau extends BaseModel {

    @Column("nom_bateau")
    private String nom;

    @Column("description")
    private String description;

    @Column("numero_siege")
    private String numerotationSiege;

    @Column("capacite")
    private Integer capacite;

    public Bateau() {
        super();
        this.capacite = 0;
    }

    public Bateau(String nom, String description, String numerotationSiege, Integer capacite) {
        this.nom = nom;
        this.description = description;
        this.numerotationSiege = numerotationSiege;
        this.capacite = capacite;
    }

    // Getters / Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getNumerotationSiege() { return numerotationSiege; }
    public void setNumerotationSiege(String numerotationSiege) { this.numerotationSiege = numerotationSiege; }

    public Integer getCapacite() { return capacite; }
    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    // Debug / Logs
    @Override
    public String toString() {
        return "Bateau{id=" + id + ", nom=" + nom + ", capacite=" + capacite + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bateau)) return false;
        Bateau bateau = (Bateau) o;
        return Objects.equals(id, bateau.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public Object[] toTableRow() {
        return new Object[]{
        getId(), 
        nom, 
        description, 
        numerotationSiege, 
        capacite
    };
}
}

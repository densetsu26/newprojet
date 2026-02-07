/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;




import tg.ucao.uut.tp.poo.dao.Column;

/**
 *
 * @author user
 */
public class Ticket extends BaseModel {
    @Column("id")
    private int numero;
    @Column("numSiege")
    private int numSiege;
    private String nomClient; 
    private String prenomClient;
    private Voyage voyage ;

     public Ticket(String nom,String prenom,Bateau bateau,int s){
        
        this.id++;
        this.nomClient=nom;
        this.prenomClient=prenom;
        this.numSiege=s;
    }

    public int getNumero() {
        return numero;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

  

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }
    
    
    public void setNumero(int id) {
        this.numero = id;
    }

    /**
     * @return the numSiege
     */
    public int getNumSiege() {
        return numSiege;
    }

    /**
     * @param numSiege the numSiege to set
     */
    public void setNumSiege(int numSiege) {
        this.numSiege = numSiege;
    }

    

 


   
  

    public void generePDF() {
    }

    @Override
    public Object[] toTableRow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the voyage
     */
    public Voyage getVoyage() {
        return voyage;
    }

    /**
     * @param voyage the voyage to set
     */
    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

}
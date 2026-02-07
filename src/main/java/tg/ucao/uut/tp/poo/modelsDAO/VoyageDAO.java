/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.modelsDAO;

import tg.ucao.uut.tp.poo.dao.BaseDBDAO;

import tg.ucao.uut.tp.poo.models.Voyage;

/**
 *
 * @author user
 */
public class VoyageDAO extends BaseDBDAO<Voyage>{
    
    public VoyageDAO(String pTableName) {
        super("VoyageBD");
    }
    
}

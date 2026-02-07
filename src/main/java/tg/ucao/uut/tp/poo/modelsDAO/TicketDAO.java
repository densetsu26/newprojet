/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.modelsDAO;

import tg.ucao.uut.tp.poo.models.Ticket;
import tg.ucao.uut.tp.poo.dao.BaseDBDAO;

/**
 *
 * @author user
 */
public class TicketDAO extends BaseDBDAO<Ticket> {
    
    public TicketDAO(String pTableName) {
        super("TicketBD");
    }
    
}

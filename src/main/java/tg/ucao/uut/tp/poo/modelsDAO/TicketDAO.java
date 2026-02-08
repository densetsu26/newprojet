/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.modelsDAO;

import tg.ucao.uut.tp.poo.dao.BaseDBDAO;
import tg.ucao.uut.tp.poo.jdbc.JdbcHelper;
import tg.ucao.uut.tp.poo.models.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TicketDAO extends BaseDBDAO<Ticket> {

    public TicketDAO() {
        super("ticket");
    }

    /**
     * Compte le nombre de tickets associés à un voyage
     *
     * @param voyageId ID du voyage
     * @return nombre de tickets vendus
     * @throws IllegalArgumentException si voyageId est null
     * @throws RuntimeException si erreur SQL
     */
     
    public int countTicketsByVoyage(Long voyageId) {
        if (voyageId == null) {
            throw new IllegalArgumentException("voyageId ne peut pas être null");
        }

        final String sql = "SELECT COUNT(*) FROM ticket WHERE voyage_id = ?";

        try {
            Connection conn = JdbcHelper.getInstance().getConn();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, voyageId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                "Erreur lors du comptage des tickets pour le voyage ID=" + voyageId,
                e
            );
        }

        return 0;
    }
}


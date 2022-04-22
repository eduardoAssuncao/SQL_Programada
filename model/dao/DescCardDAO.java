/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.DescCard;

/**
 *
 * @author Kenny
 */
public class DescCardDAO {
    public void create(DescCard cD) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO desccard (DescID, CardID, Descricao, Qtd, Rarity) VALUES (?,?,?,?,?)");
            stmt.setInt(1, cD.getDescID());
            stmt.setInt(2, cD.getCardID());
            stmt.setString(3, cD.getDescricao());
            stmt.setInt(4, cD.getQtd());
            stmt.setString(5, cD.getRarity());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }

    public List<DescCard> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<DescCard> cardsDesc = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM desccard");
            rs = stmt.executeQuery();

            while (rs.next()) {

                DescCard cardDesc = new DescCard();

                cardDesc.setCardID(rs.getInt("CardID"));
                cardDesc.setDescID(rs.getInt("DescID"));
                cardDesc.setDescricao(rs.getString("Descricao"));
                cardDesc.setQtd(rs.getInt("Qtd"));
                cardDesc.setRarity(rs.getString("Rarity"));
                cardsDesc.add(cardDesc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cardsDesc;

    }
    
    public List<DescCard> readForDesc(String CdR) { //CdR -> CardDescRarity

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<DescCard> cardsDesc = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM desccard WHERE Rarity LIKE ?");
            stmt.setString(1, "%"+CdR+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                DescCard cardDesc = new DescCard();

                cardDesc.setCardID(rs.getInt("CardID"));
                cardDesc.setDescID(rs.getInt("DescID"));
                cardDesc.setDescricao(rs.getString("Descricao"));
                cardDesc.setQtd(rs.getInt("Qtd"));
                cardDesc.setRarity(rs.getString("Rarity"));
                cardsDesc.add(cardDesc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cardsDesc;

    }
    
    public void update(DescCard cD) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE desccard SET CardID = ?, Descricao = ?, Qtd = ?, Rarity = ? WHERE DescID = ?");
            stmt.setInt(1, cD.getCardID());
            stmt.setString(2, cD.getDescricao());
            stmt.setInt(3, cD.getQtd());
            stmt.setString(4, cD.getRarity());
            stmt.setInt(5, cD.getDescID());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(DescCard cD) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM desccard WHERE DescID = ?");
            stmt.setInt(1, cD.getDescID());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}

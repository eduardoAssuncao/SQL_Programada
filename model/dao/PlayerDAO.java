/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Player;
import sun.security.rsa.RSACore;

/**
 *
 * @author Samuelson
 */
//Responsavel por realizarmos nossas funções do CRUD
public class PlayerDAO {
    /*public static void main(String[] args) {
        //PlayerDAO pl = new PlayerDAO();
        //System.out.println(pl.readForDescCallableStatement(4));
    }*/
    
    
    public void create(Player p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO player (NickName,CardID,Ivl)VALUES(?,?,?)");
            stmt.setString(1, p.getNickName());
            stmt.setInt(2, p.getCardID());
            //stmt.setInt(3, p.getPlayerID());
            stmt.setDouble(3, p.getIvl());

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        System.out.println("NickName: "+ procNickName(4));
    }

    public List<Player> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Player> players = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM player");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Player player = new Player();

                player.setCardID(rs.getInt("CardID"));
                player.setNickName(rs.getString("NickName"));
                player.setPlayerID(rs.getInt("PlayerID"));
                player.setIvl(rs.getDouble("Ivl"));
                players.add(player);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return players;

    }
    public List<Player> readForDesc(String Nkn) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Player> players = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM player WHERE NickName LIKE ?");
            stmt.setString(1, "%"+Nkn+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Player player = new Player();

                player.setCardID(rs.getInt("CardID"));
                player.setNickName(rs.getString("NickName"));
                player.setPlayerID(rs.getInt("PlayerID"));
                player.setIvl(rs.getDouble("Ivl"));
                players.add(player);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return players;

    }

    public void update(Player p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE player SET CardID = ?, Ivl = ? ,NickName = ? WHERE PlayerID = ?");
            stmt.setInt(1, p.getCardID());
            stmt.setDouble(2, p.getIvl());
            stmt.setString(3, p.getNickName());
            stmt.setInt(4, p.getPlayerID());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Player p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM player WHERE PlayerID = ?");
            stmt.setInt(1, p.getPlayerID());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    /*public String procCliente(int p) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        
  
        String sql = "{CALL `dbcard`.`Nome_Player`( ? )}";
        String saida;
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, p);
            cs.execute();
            saida = cs.getString(3);
        }
        
        return saida;
    }*/
    
    public String procNickName(int p){
        String sql = "CALL `dbcard`.`Nome_Player`( ? )";
        Connection conn = ConnectionFactory.getConnection();
        String saida = "";
        //List<Player> players = new ArrayList<>();
        try {
           CallableStatement cs = conn.prepareCall(sql);
           cs.setInt(1, p);
           ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Player player = new Player();

                saida = rs.getString("NickName");
                //players.add(new Player(rs.getString("")));
            }
            ConnectionFactory.closeConnection(conn, cs, rs);
            //return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("");
        return saida;
    }
}

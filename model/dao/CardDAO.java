package model.dao;

import com.mysql.cj.jdbc.CallableStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Card;

/**
 *
 * @author Samuelson
 */
public class CardDAO {
    
    public void create(Card c) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO card (PlayerID, CardID, CardName)VALUES(?,?,?)");
            stmt.setInt(2, c.getCardID());
            stmt.setInt(1, c.getPlayerID());
            stmt.setString(3, c.getCardName());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        System.out.println("Procedure DescriptionCard "+ searchByNameCallableStatement("Helgaia"));

    }

    public List<Card> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Card> cards = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM card");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Card card = new Card();

                card.setCardID(rs.getInt("CardID"));
                card.setCardName(rs.getString("CardName"));
                card.setPlayerID(rs.getInt("PlayerID"));
                cards.add(card);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cards;

    }
    
    public List<Card> readForDesc(String Cn) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Card> cards = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM card WHERE CardName LIKE ?");
            stmt.setString(1, "%"+Cn+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Card card = new Card();

                card.setCardID(rs.getInt("CardID"));
                card.setCardName(rs.getString("CardName"));
                card.setPlayerID(rs.getInt("PlayerID"));
                cards.add(card);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cards;

    }
    
    public void update(Card c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE card SET PlayerID = ?, CardName = ? WHERE CardID = ?");
            stmt.setInt(1, c.getPlayerID());
            stmt.setString(2, c.getCardName());
            stmt.setInt(3, c.getCardID());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Card c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM card WHERE CardID = ?");
            stmt.setInt(1, c.getCardID());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public String searchByNameCallableStatement(String name){
        String sql = "CALL `dbcard`.`Card_Name`( ? )";
        Connection conn = ConnectionFactory.getConnection();
        String saida = "";
        //List<Player> players = new ArrayList<>();
        try {
           CallableStatement cs = (CallableStatement) conn.prepareCall(sql);
           cs.setString(1, "%"+ name +"%");
           ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                //Player player = new Player();

                saida = rs.getString("Descricao");
                //players.add(new Player(rs.getString("")));
            }
            ConnectionFactory.closeConnection(conn, cs, rs);
            //return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(saida);
        return saida;
    }
}

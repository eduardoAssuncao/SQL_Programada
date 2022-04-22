/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Samuelson
 */
public class Player {
    
    private int PlayerID;
    private int CardID;
    private String NickName;
    private double Ivl;

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }

    public int getCardID() {
        return CardID;
    }

    public void setCardID(int CardID) {
        this.CardID = CardID;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public double getIvl() {
        return Ivl;
    }

    public void setIvl(double Ivl) {
        this.Ivl = Ivl;
    }

    

    @Override
    public String toString() {
        return getNickName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

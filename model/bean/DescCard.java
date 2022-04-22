/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author Kenny
 */
public class DescCard {
    private int DescID;
    private int CardID;
    private int Qtd;
    private String Descricao;
    private String Rarity;

    public int getDescID() {
        return DescID;
    }

    public void setDescID(int DescID) {
        this.DescID = DescID;
    }

    public int getCardID() {
        return CardID;
    }

    public void setCardID(int CardID) {
        this.CardID = CardID;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getRarity() {
        return Rarity;
    }

    public void setRarity(String Rarity) {
        this.Rarity = Rarity;
    }
    
    
}

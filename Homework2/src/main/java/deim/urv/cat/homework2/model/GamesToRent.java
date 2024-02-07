/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.io.Serializable;

/**
 *
 * @author Ruben
 */
public class GamesToRent implements Serializable {

    String name;
    String console;
    Float price;
    Boolean available;
    String description;
    String gameType;
    String adress;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GamesToRent() {

    }

    public String getName() {
        return name;
    }

    public String getConsole() {
        return console;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public String getGameType() {
        return gameType;
    }

    public String getAdress() {
        return adress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}

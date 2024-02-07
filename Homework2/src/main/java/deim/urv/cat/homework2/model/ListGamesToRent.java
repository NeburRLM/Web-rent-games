/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ruben
 */
@SessionScoped
@Named("listGamesToRent")
public class ListGamesToRent implements Serializable {

    private List<GamesToRent> g;

    public ListGamesToRent() {
        g = new ArrayList<>();
    }

    public void addGameToRent(GamesToRent ga) {
        g.add(ga);
    }

    public GamesToRent returnGame(int i) {
        return (g.get(i));
    }

    public int returnSize() {
        return (g.size());
    }

    public String returnGameIds() {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{");
        jsonString.append("\"list\": [");

        boolean first = true;

        for (GamesToRent game : g) {
            if (!first) {
                jsonString.append(",");
            } else {
                first = false;
            }

            jsonString.append("\"").append(game.getId()).append("\"");
        }

        jsonString.append("]");
        jsonString.append("}");

        return jsonString.toString();
    }

    public float precioTotal() {
        float p = 0;
        for (int i = 0; i < g.size(); i++) {
            p = p + g.get(i).getPrice();
        }
        return p;
    }

    public String returnInfoGames() {
        String s = "";
        for (int i = 0; i < g.size(); i++) {
            s = s + g.get(i).getName() + "|";
        }
        return s;
    }

    public String comprovaId() {
        String s = "";
        for (int i = 0; i < g.size(); i++) {
            s = s + g.get(i).getId() + "|";
        }
        return s;
    }

    public void clearList() {
        g.clear();
    }

    public boolean isListEmpty() {
        return g.isEmpty();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;

/**
 *
 * @author Ruben
 */
@Controller
@Path("ListGames")
public class ListGamesController {

    @Inject
    private Models model;

    @GET
    public String showForm() {
        return "listGamesView.jsp"; // Injects CRSF token
    }

    @GET
    @Path("infoUser")
    public String showInfoUser() {
        return "infoUser.jsp"; // Injects CRSF token
    }

    @GET
    @View("gameView.jsp")
    @Path("gameDetails")
    public void showGameDetails(@QueryParam("id") int id,
            @QueryParam("gameName") String name,
            @QueryParam("price") Float price,
            @QueryParam("availability") Boolean availability,
            @QueryParam("description") String description,
            @QueryParam("gameType") String gameType,
            @QueryParam("pickupLocationAddress") String pickupLocationAddress,
            @QueryParam("console") String console,
            @Context HttpServletRequest httpRequest) {

        model.put("id", id);
        model.put("name", name);
        model.put("console", console);
        model.put("price", price);
        model.put("availability", availability);
        model.put("description", description);
        model.put("gameType", gameType);
        model.put("pickupLocationAddress", pickupLocationAddress);
    }

}

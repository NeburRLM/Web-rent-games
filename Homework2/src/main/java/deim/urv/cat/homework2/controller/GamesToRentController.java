/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.GamesToRent;
import deim.urv.cat.homework2.model.ListGamesToRent;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 *
 * @author Ruben
 */
@Controller
@Path("/saveToRent")
public class GamesToRentController {

    @Inject
    ListGamesToRent listGamesToRent;

    @POST
    @Path("/saveToRent")
    public String saveToRent(@FormParam("id") String id,
            @FormParam("name") String name,
            @FormParam("price") Float price) {

        GamesToRent gamesToRent = new GamesToRent();
        gamesToRent.setName(name);
        gamesToRent.setId(id);
        gamesToRent.setPrice(price);

        listGamesToRent.addGameToRent(gamesToRent);

        return "redirect:/ListGames";
    }
}

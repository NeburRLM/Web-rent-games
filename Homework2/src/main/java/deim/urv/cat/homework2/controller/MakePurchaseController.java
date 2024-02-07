/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.InfoPurchase;
import deim.urv.cat.homework2.model.ListGamesToRent;
import deim.urv.cat.homework2.model.SaveUser;

import jakarta.inject.Inject;

import jakarta.mvc.Controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
/**
 *
 * @author Ruben
 */
@Controller
@Path("/makePurchase")
public class MakePurchaseController {

    @Inject
    SaveUser user;

    @Inject
    ListGamesToRent listGamesToRent;

    @Inject
    InfoPurchase infoPurchase;

    @POST
    @Path("purchase")
    public String showMakePurchase() throws MalformedURLException, IOException {

        int idUser = user.getId();
        String ls = listGamesToRent.returnGameIds();

     
        // Construir la URL con los parámetros
        URL url = new URL("http://localhost:8080/Homework1/webresources/rental/?id_customer=" + idUser);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String credentials = "sob" + ":" + "sob";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
        // Configurar la conexión para enviar datos
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = ls.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            // Crea un lector JSON a partir de la cadena
        try (JsonReader reader = Json.createReader(new StringReader(response.toString()))) {
            // Convierte la cadena JSON en un objeto JsonObject
            JsonObject jsonObject = reader.readObject();

            // Accede a los valores por clave
            int id = jsonObject.getInt("id");
            String rentalDate = jsonObject.getString("rentalDate");
            float totalAmount = (float) jsonObject.getJsonNumber("totalAmount").doubleValue();
            infoPurchase.setId(id);
            infoPurchase.setRentalDate(rentalDate);
            infoPurchase.setTotalAmount(totalAmount);
            // Imprime los valores
            System.out.println("ID: " + id);
            System.out.println("Rental Date: " + rentalDate);
            System.out.println("Total Amount: " + totalAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        }
  
        // Muestra las partes resultantes
        connection.disconnect();
     

        return "makePurchase.jsp"; // Injects CRSF token
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.SaveUser;
import deim.urv.cat.homework2.model.PathUrl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Ruben
 */
@Controller
@Path("/login")
public class LoginController {

    @Inject
    SaveUser user;

    @Inject
    PathUrl pathUrl;

    @GET
    public String showLogin(@QueryParam("url") String url) {
        pathUrl.setUrl(url);
        return "login.jsp"; // Injects CRSF token
    }

    @POST
    @Path("loginn")
    public String log(
            @FormParam("usuario") String usuario,
            @FormParam("contraseña") String contraseña) throws MalformedURLException, IOException {

        URL url = new URL("http://localhost:8080/Homework1/webresources/customer/findByName?name=" + usuario);
        System.out.println("SDGFSG: " + usuario);
        // Abrir una conexión HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurar autenticación básica
        String credentials = usuario + ":" + contraseña;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

        // Configurar método de la solicitud (GET, POST, etc.)
        connection.setRequestMethod("GET");

        // Leer la respuesta
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            String json = response.toString();
            JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
            user.setName(usuario);
            user.setEmail(jsonObject.getString("email"));
            user.setPhone(jsonObject.getString("phone"));
            int id = jsonObject.getInt("id");
            user.setId(id);

            connection.disconnect();

            return "redirect:/" + pathUrl.getUrl();
        }
        String encodedUrlComponent = URLEncoder.encode(pathUrl.getUrl(), StandardCharsets.UTF_8.toString());
        encodedUrlComponent = encodedUrlComponent.replaceAll("\\+", "%20");
        return "redirect:/login?url="+ encodedUrlComponent;

    }
}
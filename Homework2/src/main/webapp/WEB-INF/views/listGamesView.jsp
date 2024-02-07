<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listado de Videojuegos</title>

        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                min-height: 100vh;
                overflow-x: hidden; 
            }

            h1 {
                text-align: center;
                padding: 20px;
                background-color: #343a40;
                color: #fff;
                margin: 0;
                width: 100%; 
            }

            #header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
                padding: 10px;
                background-color: #343a40;
            }

            .button-container {
                display: flex;
                align-items: center;
            }

            button.cesta,
            button.inicio-sesion {
                background: none;
                border: none;
                cursor: pointer;
                padding: 10px;
                transition: transform 0.3s ease;
                margin-right: 20px; 
            }

            button.cesta img {
                width: 30px;
                height: auto;
                transition: transform 0.3s ease;
            }

            button.cesta:hover,
            button.alquilar:hover,
            button.inicio-sesion:hover {
                transform: scale(1.2);
            }

            button.inicio-sesion {
                color: #fff;
                background-color: #007bff;
                border-radius: 5px;
                margin-left: auto; 
            }

            #resultado {
                display: flex;
                flex-wrap: wrap;
                max-width: 1200px;
                margin: 20px auto;
                overflow-y: auto;
                max-height: calc(100vh - 150px);
                justify-content: flex-start; 
                align-items: flex-start; 
            }

            .game-card {
                background-color: #fff;
                border: 1px solid #dee2e6;
                border-radius: 8px;
                overflow: hidden;
                margin: 10px;
                width: 170px;
                height: 320px; 
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease;
            }

            .game-card:hover {
                transform: scale(1.05);
            }

            .game-image {
                width: 100%;
                height: 190px; 
                object-fit: cover;
            }

            .game-info {
                padding: 15px;
                text-align: left;
            }

            h2 {
                margin: 10px 0;
                color: #343a40;
                font-size: 1.2em;
                white-space: normal; 
                display: -webkit-box;
                -webkit-line-clamp: 2; 
                -webkit-box-orient: vertical;
                overflow: hidden;
                text-overflow: ellipsis; 
            }

            p {
                color: #555;
                margin: 0;
                font-size: 1em;
            }

            .availability {
                font-weight: bold;
                margin-top: 10px;
                font-size: 1em;
            }

            .availability.available {
                color: #28a745;
            }

            .availability.not-available {
                color: #dc3545;
            }
        </style>
    </head>
    <body>
        <div id="header">
            <button class="inicio-sesion" id="iniciarSesion" onclick="mostrarInicioSesion()">Iniciar Sesión</button>
            <button class="cesta" onclick="mostrarCesta()">
                <img src="../resources/img/Cesta.png" alt="Icono de la cesta">
            </button>
        </div>
        <h1>Game</h1>
        <div id="resultado"></div>
        <script>
            function llamarServlet() {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var juegos = JSON.parse(xhr.responseText);
                        if (juegos.length > 0) {
                            mostrarEnLista(juegos);
                        }
                    }
                };
                xhr.open("GET", "http://localhost:8080/Homework1/webresources/game", true);
                xhr.send();
            }

            function mostrarEnLista(juegos) {
                var container = document.getElementById("resultado");

                juegos.forEach(function (juego) {
                    var gameCard = crearGameCard(juego);
                    container.appendChild(gameCard);
                });
            }

            function crearGameCard(juego) {
                var gameCard = document.createElement("div");
                gameCard.className = "game-card";



                var imagen = document.createElement("img");
                imagen.src = "../resources/img/" + juego.name + ".png";
                imagen.alt = juego.name + " carátula";
                imagen.className = "game-image";

                var info = document.createElement("div");
                info.className = "game-info";

                var nombre = document.createElement("h2");
                var console = document.createElement("p");
                nombre.textContent = juego.name + " (" + juego.console + ")";

                var availability = document.createElement("p");
                availability.className = "availability " + (juego.availability ? "available" : "not-available");
                availability.textContent = juego.availability ? "Disponible" : "No Disponible";

                var precio = document.createElement("p");
                precio.textContent = juego.weeklyRentalPrice.toFixed(2) + "€";

                var descripcion = document.createElement("p");
                descripcion.textContent = "Descripción: " + juego.description;

                var tipoJuego = document.createElement("p");
                tipoJuego.textContent = "Tipo de juego: " + juego.gameType;

                var direccionRecogida = document.createElement("p");
                direccionRecogida.textContent = "Dirección de recogida: " + juego.pickupLocationAddress;

                info.appendChild(nombre);
                info.appendChild(console);
                info.appendChild(availability);
                info.appendChild(precio);

                // Agregar evento de clic a la tarjeta
                gameCard.addEventListener("click", function () {
                    // Obtiene la ruta actual
                    var rutaActual = window.location.pathname;
                  
                    var nuevaPagina = "/gameDetails?\n\id=" + encodeURIComponent(juego.id) +
                            "&gameName=" + encodeURIComponent(juego.name) +
                            "&console=" + encodeURIComponent(juego.console) +
                            "&price=" + encodeURIComponent(juego.weeklyRentalPrice) +
                            "&availability=" + encodeURIComponent(juego.availability) +
                            "&description=" + encodeURIComponent(juego.description) +
                            "&gameType=" + encodeURIComponent(juego.gameType) +
                            "&pickupLocationAddress=" + encodeURIComponent(juego.pickupLocationAddress);

                    // Redirige 
                    window.location.href = rutaActual + nuevaPagina;
                });



                gameCard.appendChild(imagen);
                gameCard.appendChild(info);

                return gameCard;
            }

            llamarServlet();


            function mostrarInicioSesion() {
                switch ("${user.getName()}") {
                    case "":
                        window.location.href = "<%= request.getContextPath() %>/Web/login?url=" + "ListGames";
                        break;
                    default :
                        window.location.href = "<%= request.getContextPath() %>/Web/ListGames/infoUser";
                        break;
                }

            }

            function verificarSesion() {
                var button = document.getElementById("iniciarSesion");
                switch ("${user.getName()}") {
                    case "":
                        button.textContent = "IniciarSesion";
                        break;
                    default :
                        button.textContent = "Bienvenido ${user.getName()}";
                        break;
                }
            }
            verificarSesion();

            function mostrarCesta() {
                // Redirige 
                window.location.href = "<%= request.getContextPath() %>/Web/shopingCart";
            }
        </script>
    </body>
</html>

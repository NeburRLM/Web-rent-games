<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${name}</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                min-height: 100vh;
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #007bff;
            }

            div {
                max-width: 600px;
                background-color: #fff;
                border: 1px solid #dee2e6;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
                margin-bottom: 20px;
            }

            button.cesta {
                background: none;
                border: none;
                cursor: pointer;
                position: fixed;
                top: 20px;
                right: 20px;
                padding: 5px;
                transition: transform 0.3s ease;
            }

            button.cesta img {
                width: 30px;
                height: auto;
                transition: transform 0.3s ease;
            }

            button.inicio-sesion {
                color: #fff;
                background-color: #007bff;
                border-radius: 5px;

                padding: 10px;
                cursor: pointer;
                transition: transform 0.3s ease;
            }

            button.cesta:hover,
            button.list-games:hover,
            button.inicio-sesion:hover,
            button.alquilar:hover {
                transform: scale(1.2);
            }

            button.alquilar {
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button.alquilar:disabled {
                background-color: #ccc;
                cursor: not-allowed;
            }
            button.list-games {
                padding: 10px;
                background-color: #28a745;
                color: #fff;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
        </style>
    </head>
    <body>
        <!-- Botón de iniciar sesión -->
        <button class="inicio-sesion" id="iniciarSesionGame" onclick="mostrarInicioSesion()">Iniciar Sesión</button>
        <button class="list-games" onclick="goListGames()">Go to ListGames</button>
        <!-- Botón de la cesta -->
        <button class="cesta" onclick="mostrarCesta()">
            <img src="../../resources/img/Cesta.png" alt="Icono de la cesta">
        </button>

        <h1>${name} (${console})</h1>

        <% 
            // Obtén la ruta del contexto de la aplicación
            String contextPath = request.getContextPath();
            // Escapa el nombre del juego para asegurar que sea seguro en una URL
            String escapedName = java.net.URLEncoder.encode((String) request.getAttribute("name"), "UTF-8");
            // Construye la URL completa de la imagen utilizando el nombre del juego
            String imageUrl = contextPath + "/resources/img/" + escapedName + ".png";
        %>

        <img src="<%= imageUrl %>" alt="${escapedName} Carátula">

        <div>
            <p><strong>Preu del lloguer setmanal:</strong> ${price}€</p>
            <p><strong>Disponibilidad:</strong> ${availability ? '<span style="color: #28a745;">Disponible</span>' : '<span style="color: #dc3545;">No Disponible</span>'}</p>
            <p><strong>Descripció:</strong> ${description}</p>
            <p><strong>Tipus de videojoc:</strong> ${gameType}</p>
            <p><strong>Adreça de recollida:</strong> ${pickupLocationAddress}</p>
        </div>

        <!-- Botón para añadir el videojuego a la cistella de lloguer -->
        <button class="alquilar" onclick="alquilarVideojuego()" ${availability ? '' : 'disabled'}>
            Add to chart
        </button>

        <script>
            function alquilarVideojuego() {
                var existingIds = "${listGamesToRent.comprovaId()}";
                var currentId = "${id}";

                // Verifica si el ID actual ya está en la lista
                if (existingIds.includes(currentId)) {
                    alert("El juego ya está en la lista.");

                } else
                {
                    // Construye el formulario dinámicamente
                    var form = document.createElement('form');
                    form.action = '<%= request.getContextPath() %>/Web/saveToRent/saveToRent';
                    form.method = 'post';
                    form.innerHTML = `
                
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="name" value="${name}">        
                <input type="hidden" name="price" value="${price}">
               
            `;
                    // Agrega el formulario al cuerpo del documento y envíalo
                    document.body.appendChild(form);
                    form.submit();
                }
            }

            function mostrarInicioSesion() {
                var rutaActual = window.location.href;

                switch ("${user.getName()}") {
                    case "":

                        var inicio = rutaActual.indexOf("ListGames"); // Encuentra la posición del inicio de "/ListGames"

                        var urlDeseada = rutaActual.substring(inicio);
                        var urlEncoded = encodeURIComponent(urlDeseada);
                        window.location.href = "<%= request.getContextPath() %>/Web/login?url=" + urlEncoded;
                        break;
                    default :
                        window.location.href = "<%= request.getContextPath() %>/Web/ListGames/infoUser";
                        break;
                }

            }

            function verificarSesion() {
                var button = document.getElementById("iniciarSesionGame");
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
                // Redirige a la página de la cesta
                window.location.href = "<%= request.getContextPath() %>/Web/shopingCart";
            }

            function goListGames() {
                // Redirige a la página ListGames
                window.location.href = "<%= request.getContextPath() %>/Web/ListGames";
            }


        </script>
    </body>
</html>

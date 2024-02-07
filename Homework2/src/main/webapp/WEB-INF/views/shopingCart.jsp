<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lista de Juegos</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                overflow: hidden;
            }

            .game-container {
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-height: 90vh;
                overflow-y: auto;
                width: 100%;
                box-sizing: border-box;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            .game-list {
                list-style-type: none;
                padding: 0;
                text-align: center;
            }

            .game-item {
                margin: 10px 0;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .game-image {
                max-width: 100px;
                height: auto;
                margin: 10px 0;
            }

            .rental-info {
                text-align: center;
                margin-top: 30px;
            }

            .rental-info p {
                margin: 10px 0;
                font-size: 18px;
                color: #28a745;
            }

            .rental-info strong {
                color: #333;
            }

            .rent-button {
                text-align: center;
                margin-top: 20px;
            }

            .rent-button button {
                background-color: #dc3545;
                color: #fff;
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: transform 0.3s ease-in-out;
            }

            .rent-button button:hover {
                transform: translateY(-5px);
            }

            .rent-button button[disabled] {
                background-color: #ccc;
                color: #999;
                cursor: not-allowed;
            }
            .redirect-button {
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }
            .redirect-button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="game-container">
            <h1>Lista de Juegos</h1>
            <ul class="game-list">
                <script>
                    var juegos = "${listGamesToRent.returnInfoGames()}".split("|");

                    for (var i = 0; i < juegos.length - 1; i++) {
                        var imageUrl = "../resources/img/" + juegos[i] + ".png";
                        document.write('<li class="game-item"><strong>Juego ' + (i + 1) + ':</strong> ' + juegos[i] + '<br><img class="game-image" src="' + imageUrl + '" alt="' + juegos[i] + '"></li>');
                    }
                </script>
            </ul>
            <div class="rental-info">
                <p><strong>Tiempo de alquiler:</strong> <script>var currentDate = new Date(); currentDate.setDate(currentDate.getDate() + 7); document.write(currentDate.toDateString());</script></p>
                <p><strong>Precio Total:</strong> <script>document.write(${listGamesToRent.precioTotal()});</script> euros</p>
            </div>
            <div class="rent-button">

                <form id="redirectForm" action="<%= request.getContextPath() %>/Web/makePurchase/purchase" method="POST" style="display: none;">

                </form>

                <!-- Botón que inicia la redirección POST -->
                <button id="redirectButton" onclick="alquilarVideojuego()" ${listGamesToRent.isListEmpty() ? 'disabled' : ''}>
                    Make rent
                </button>
                <a href="<%= request.getContextPath() %>/Web/ListGames" class="redirect-button">Go to Main Page</a>
            </div>

            <!-- Script para la redirección POST -->
            <script>
                // Obtén referencia al botón y al formulario
                var redirectButton = document.getElementById('redirectButton');
                var redirectForm = document.getElementById('redirectForm');

                function alquilarVideojuego() {
                    switch ("${user.getName()}") {
                        case "":
                            window.location.href = "<%= request.getContextPath() %>/Web/login?url=shopingCart";
                            break;
                        default:
                            document.getElementById('redirectForm').submit();
                            break;
                    }
                }
            </script>
        </div>

    </body>
</html> 
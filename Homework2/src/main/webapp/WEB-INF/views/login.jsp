<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID" %>

<%
    // Generar un token CSRF único y guardarlo en la sesión
    String csrfToken = UUID.randomUUID().toString();
    session.setAttribute("csrfToken", csrfToken);
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
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

            h2 {
                color: #343a40;
                margin-bottom: 20px;
            }

            form {
                width: 300px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"],
            button[type="button"] {
                background-color: #007bff;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-right: 5px;
            }

            input[type="submit"]:hover,
            button[type="button"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <h2>Iniciar Sesión</h2>

        <form action="<%= request.getContextPath() %>/Web/login/loginn" method="post">
            <input type="hidden" name="csrfToken" value="<%= csrfToken %>">
            Usuario: <input type="text" name="usuario" required><br>
            Contraseña: <input type="password" name="contraseña" required><br>
            <input type="submit" value="Iniciar Sesión">
            <button type="button" onclick="cancelarInicioSesion()">Cancelar</button>
        </form>
        <script>
            function cancelarInicioSesion() {
                // Redirige al controlador Login
                history.back();
            }
        </script>
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt</title>
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

            .receipt-container {
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                width: 100%;
                box-sizing: border-box;
            }

            h1, p {
                text-align: center;
                color: #333;
            }

            p {
                margin: 10px 0;
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
        <div class="receipt-container">
            <h1>Purchase Details</h1>

            <p><strong>ID:</strong> ${infoPurchase.getId()}</p>
            <p><strong>Rental Date:</strong> ${infoPurchase.getRentalDate()}</p>
            <p><strong>Total Amount:</strong> ${infoPurchase.getTotalAmount()}</p>
            ${listGamesToRent.clearList()}
            <a href="<%= request.getContextPath() %>/Web/ListGames" class="redirect-button">Go to Main Page</a>
        </div>
    </body>
</html>

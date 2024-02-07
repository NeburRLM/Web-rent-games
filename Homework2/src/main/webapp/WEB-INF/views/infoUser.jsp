<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Information</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                min-height: 100vh;
            }

            h1 {
                text-align: center;
                padding: 20px;
                background-color: #343a40;
                color: #fff;
                margin: 0;
                width: 100%;
            }

            .user-info-container {
                max-width: 600px;
                margin: 20px;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            p {
                margin: 10px 0;
                color: #555;
                font-size: 1.2em;
            }
        </style>
    </head>
    <body>
        <h1>User Information</h1>
        <div class="user-info-container">
            <p><strong>Name:</strong> ${user.getName()}</p>
            <p><strong>Email:</strong> ${user.getEmail()}</p>
            <p><strong>Phone:</strong> ${user.getPhone()}</p>
        </div>
    </body>
</html>
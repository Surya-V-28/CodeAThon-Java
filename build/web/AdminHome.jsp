<%-- 
    Document   : AdminHome
    Created on : 10-Nov-2024, 12:45:30 pm
    Author     : SURYA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Action Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .container {
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .buttons {
            margin-top: 20px;
        }
        .buttons button {
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .buttons button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Choose an Action</h1>
        <div class="buttons">
            <!-- View Profile Button -->
            <form action="admin.jsp" method="post" style="display:inline;">
                <button type="submit">View Students</button>
            </form>
            
            <!-- Edit Profile Button -->
            <form action="MaxRegistered.jsp" method="post" style="display:inline;">
                <button type="submit">Maximun Registered</button>
            </form>
            
          
        </div>
    </div>
</body>
</html>

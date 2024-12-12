<%-- 
    Document   : errors
    Created on : 10-Nov-2024, 3:55:54 am
    Author     : SURYA
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        /* General body styling */
        body {
            
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #a2d9ff, #ffc3a0);
            color: #333;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            text-align: center;
        }

        /* Heading styling */
        h2 {
            font-size: 32px;
            color: rgb(255, 0, 0);
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* Paragraph styling */
        p {
            font-size: 18px;
            color: rgb(0, 0, 0);
            margin-bottom: 20px;
            max-width: 600px;
            line-height: 1.6;
        }

        /* Link styling */
        a {
            font-size: 18px;
            color: #007BFF;
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #007BFF;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            background-color: #007BFF;
            color: white;
        }

        /* Responsive design */
        @media (max-width: 768px) {
            h2 {
                font-size: 28px;
            }

            p {
                font-size: 16px;
            }

            a {
                font-size: 16px;
            }
        }

        @media (max-width: 480px) {
            h2 {
                font-size: 24px;
            }

            p {
                font-size: 14px;
            }

            a {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h2>Error</h2>
    <p>There was an issue retrieving your information. Please try again later.</p>
    <a href="login.jsp">Return to Login</a>

</body>
</html>
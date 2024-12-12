<%-- 
    Document   : timeMismatch.jsp
    Created on : 10-Nov-2024, 3:56:22 am
    Author     : SURYA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule Mismatch</title>
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
        }
        

        /* Heading styling */
        h2 {
            font-size: 30px;
            color: rgb(252, 0, 0);
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

        /* Go back link styling */
        a {
            font-size: 16px;
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
                font-size: 26px;
            }

            p {
                font-size: 16px;
            }

            a {
                font-size: 14px;
            }
        }

        @media (max-width: 480px) {
            h2 {
                font-size: 22px;
            }

            p {
                font-size: 14px;
            }

            a {
                font-size: 12px;
            }
        }
    </style>
</head>
<body>

    <h2>Schedule Mismatch </h2>
    <p>Your access time does not match the scheduled time for your course.</p>
    <p>Please try again at the correct time or contact support for assistance.</p>
    <a href="home.jsp">Go back to Home</a>

</body>
</html>
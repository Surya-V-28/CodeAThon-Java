<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="vitlogo.jpeg" type="image/icon type">
    <title>Student Login</title>
    <style>
        /* Main styling for the page */
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #a2d9ff, #ffc3a0);
        }

        /* Container styling */
        .login-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 300px;
            text-align: center;
        }

        /* Title styling */
        h2 {
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }

        /* Label styling */
        label {
            display: block;
            text-align: left;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555;
        }

        /* Input field styling */
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        /* Button styling */
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        /* Hover and reflexive effect for submit button */
        .login-container:hover {
            box-shadow: 0 8px 12px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
    <!-- Login form container -->
    <div class="login-container">
        <h2>Student Login</h2>
        <form action="StudentScheduleServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
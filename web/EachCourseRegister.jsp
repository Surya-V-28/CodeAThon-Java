<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%
    // Get the session and check if the username attribute is set
    String username = (String) session.getAttribute("StudentUsername");
    String email = (String) session.getAttribute("email");
    if (username == null) {
        // If no session, redirect to login page
        response.sendRedirect("login.jsp?noSessionFoundForUser");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Registration</title>
    <style>
        /* General body styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #a2d9ff, #ffc3a0);
            color: #333;
            height: 100vh;
        }

        /* Header and profile area styling */
        header {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            background-color: #007BFF;
            color: white;
            align-items: center;
        }

        header h1 {
            margin: 0;
            font-size: 24px;
        }

        .profile {
            display: flex;
            align-items: center;
        }

        .profile img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .profile span {
            font-size: 18px;
        }

        /* Form and buttons styling */
        form {
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            max-width: 600px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 20px;
            text-align: center;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
        }

        label {
            display: block;
            margin: 10px 0;
            font-size: 16px;
        }

        input[type="radio"] {
            margin-right: 10px;
        }

        /* Button styling */
        .button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 20px;
            text-align: center;
        }
        .button1 {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            font-size: 16px;
            border: none;   
            margin-left: 30%;
            border-radius: 5px;
            cursor: pointer;
            justify-content: center;
            align-items: center;
            width: 40%;
            margin-top: 20px;
            text-align: center;
        }

        .button:hover {
            background-color: #0056b3;
        }
         .button1:hover {
            background-color: #0056b3;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            header h1 {
                font-size: 22px;
            }

            .profile span {
                font-size: 16px;
            }

            form {
                padding: 15px;
            }

            .button {
                font-size: 14px;
            }
        }

        @media (max-width: 480px) {
            header h1 {
                font-size: 20px;
            }

            .profile span {
                font-size: 14px;
            }

            form {
                width: 100%;
                padding: 10px;
            }

            .button {
                font-size: 12px;
            }
        }
    </style>
</head>
<body>

<!-- Header Section with Profile -->
<header>
    <h1>Course Registration</h1>
    <div class="profile">
        <!-- Profile Picture and Username -->
        <img src="profile-pic.jpg" alt="Profile Picture">
        <span>Welcome, <%= username %>!</span>
    </div>
</header>

<!-- View Registered Courses Button -->
<form action="ViewRegisteredCoursesServlet" method="get">
    <input type="submit" class="button" value="View My Registered Courses" />
</form>

<!-- Registration Form -->
<h2>You can start the registration process below:</h2>

<!-- Styled Send Email button -->
<button class="button1" onclick="sendEmail('<%= email %>')">Send Email</button>

<form action="CourseRegistrationServlet" method="post">
    <p>Please select one of the options and proceed:</p>

    <!-- Options with radio buttons -->
    <label>
        <input type="radio" name="courseOption" value="DC" required> DC - Discipline Core
    </label><br>
    <label>
        <input type="radio" name="courseOption" value="DE"> DE - Discipline Elective
    </label><br>
    <label>
        <input type="radio" name="courseOption" value="PI"> PI - Projects and Internship
    </label><br>
    <label>
        <input type="radio" name="courseOption" value="OE"> OE - Open Elective
    </label><br>
    <label>
        <input type="radio" name="courseOption" value="SE"> SE - Skill Enhancement
    </label><br><br>

    <!-- Proceed button to submit the selected option -->
    <input type="submit" class="button" value="Proceed"/>
</form>

</body>

<script>
    function sendEmail(email) {
        const emails = email;
        console.log(email);

        fetch('http://localhost:8081/req/mail/verify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: emails })
        })
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error('Error:', error));
    }
</script>
</html>

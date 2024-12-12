<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration Form</title>
    <style>
        form {
            max-width: 500px;
            margin: auto;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }
        button {
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Student Registration Form</h2>
    <form action="RegisterControl" method="POST" enctype="multipart/form-data">
        <!-- Username -->
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <!-- Email -->
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <!-- Password -->
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <!-- Profile Picture -->
        <label for="profilePicture">Profile Picture:</label>
        <input type="file" id="profilePicture" name="profilePicture" accept="image/*">

        <!-- Department -->
        <label for="department">Department:</label>
        <input type="text" id="department" name="department">

        <!-- Year of Study -->
        <label for="year">Year of Study:</label>
        <input type="number" id="year" name="year" min="1" max="4">

        <!-- Class -->
        <label for="class">Class:</label>
        <select id="class" name="class">
            <option value="MCA">MCA</option>
            <option value="BSC">BSC</option>
            <option value="MSC">MSC</option>
            <option value="BCA">BCA</option>
            <a href="StudentRegister.jsp"></a>
            <option value="BE">BE</option>
            <option value="ME">ME</option>
            <option value="BTECH">BTECH</option>
            <option value="Other">Other</option>
        </select>

        <!-- Submit Button -->
        <button type="submit">Submit</button>
    </form>
</body>
</html>

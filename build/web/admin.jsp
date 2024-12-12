<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="POJO.Student" %>
<%@page import="DBConnection.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.StudentDao" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Manage Students</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: #007BFF;
            color: white;
            padding: 15px 20px;
            text-align: center;
        }
        table {
            width: 100%;
            margin: 20px 0;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        .actions {
            text-align: center;
        }
        .actions a {
            margin: 0 10px;
            color: #007BFF;
            text-decoration: none;
            font-weight: bold;
        }
        .actions a:hover {
            text-decoration: underline;
        }
        .message {
            text-align: center;
            margin: 20px;
            color: green;
        }
    </style>
</head>
<body>

<header>
    <h1>Admin Dashboard</h1>
    <p>Manage Students Information</p>
</header>

<div class="message">
    
    <%-- Example message --%>
    <% if (request.getAttribute("message") != null) { %>
        <p><%= request.getAttribute("message") %></p>
    <% } %>
</div>

<!-- Display student records in a table -->
<table>
    <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Department</th>
            <th>Year</th>
            <th>Class</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        
        <% 
             
             StudentDao studentdao = new StudentDao();
             
       
            
            List<Student> studentList = studentdao.getStudents();
            if (studentList != null) {
                for (Student student : studentList) {
        %>
        <tr>
            <td><%= student.getUsername() %></td>
            <td><%= student.getEmail() %></td>
            <td><%= student.getDepartment() %></td>
            <td><%= student.getYear() %></td>
            <td><%= student.getStudentClass() %></td>
            <td class="actions">
                
                <a href="DeleteStudentServlet?id=<%= student.getUsername() %>">Delete</a>
            </td>
        </tr>
        <% 
                }
            }
        %>
    </tbody>
</table>

</body>
</html>

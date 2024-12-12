<%-- 
    Document   : MaxRegistered
    Created on : 10-Nov-2024, 12:54:34 pm
    Author     : SURYA
--%>

<%@ page import="java.util.List" %>
<%@ page import="POJO.Student" %>
<%@ page import="DBConnection.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Subject-wise Student Registration Count</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
        }
        h1 {
            color: #333;
            font-size: 24px;
            margin-top: 20px;
        }
        table {
            width: 70%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }
        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>

    <body>
        <h1>Subject-wise Student Registration Count</h1>
        <% 
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
                con = DBConnection.getConnection();
                String query = "SELECT subjectName, COUNT(*) AS studentCount FROM StudentRegistered GROUP BY subjectName";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
        %>
        
        <table border="1">
            <tr>
                <th>Subject Name</th>
                <th>Student Count</th>
            </tr>
            <% while (rs.next()) { %>
            <tr>
                <td><%= rs.getString("subjectName") %></td>
                <td><%= rs.getInt("studentCount") %></td>
            </tr>
            <% } %>
        </table>
        
        <% 
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            }
        %>
    </body>
</html>
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DBConnection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SURYA
 */
public class EachRegisterCourseServlet extends HttpServlet {

  // Function to insert registration data into StudentRegistered table
private void registerStudentForCourse(String username, String subjectName, String slot, String courseType, HttpServletResponse response, HttpSession session) throws SQLException, IOException {
    // Database connection variables
    Connection conn = null;
    PreparedStatement Countstmt = null;
    PreparedStatement Regstmt = null;
    PreparedStatement updatestmt = null;
    PreparedStatement stmt = null;
    PreparedStatement checkStmt = null;
    int hitcount = 0;
    int seatcount = 0;
    try {
        // Get a connection to the database
        conn = DBConnection.getConnection();

        // SQL query to check if the student is already registered for the course
        String checkQuery = "SELECT * FROM StudentRegistered WHERE username = ? AND subjectName = ? AND slot = ? AND courseType = ?";
        checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        checkStmt.setString(2, subjectName);
        checkStmt.setString(3, slot);
        checkStmt.setString(4, courseType);

        // Execute the check query
        ResultSet resultSet = checkStmt.executeQuery();
        
        // If the student is already registered, redirect to the registration page
        if (resultSet.next()) {
            System.out.print("you have already registered this course " + username + " in this course " + subjectName);
            response.sendRedirect("EachCourseRegister.jsp"); // Redirect to the registration page without adding a new record
        } else {
            // SQL query to insert registration details
            String query = "INSERT INTO StudentRegistered (username, subjectName, slot, courseType, status) VALUES (?, ?, ?, ?, 'registered')";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, subjectName);
            stmt.setString(3, slot);
            stmt.setString(4, courseType);

            // Execute the query to insert the new registration
            stmt.executeUpdate();
            
        String countQuery = "select seatsLeft from courseOffered WHERE subjectName = ?";
        Countstmt = conn.prepareStatement(countQuery);
        Countstmt.setString(1,subjectName);
        ResultSet resultSet1 = Countstmt.executeQuery();
        if (resultSet1.next()) {
            seatcount = resultSet1.getInt("seatsLeft");
        }
        String countReg = "SELECT COUNT(*) FROM StudentRegistered WHERE subjectName = ?";
        Regstmt = conn.prepareStatement(countReg);
        Regstmt.setString(1, subjectName);
        ResultSet resultSet2 = Regstmt.executeQuery();
        
        if (resultSet2.next()) {
            hitcount = resultSet2.getInt(1);
        }
        if(hitcount < seatcount)
        {
            int updatedSeatCount = seatcount - 1;
                String updateCountQuery = "UPDATE courseOffered SET seatsLeft = ? WHERE subjectName = ?";
                updatestmt = conn.prepareStatement(updateCountQuery);
                updatestmt.setInt(1, updatedSeatCount);
                updatestmt.setString(2, subjectName);
                updatestmt.executeUpdate();
        }

            // After successful registration, redirect to the course registration page
           
            session.setAttribute("StudentUsername", username); 
            response.sendRedirect("EachCourseRegister.jsp");  // Redirect after successful registration
        }
    } finally {
        // Close resources
        if (stmt != null) stmt.close();
        if (checkStmt != null) checkStmt.close();
        if (conn != null) conn.close();
    }
}

// Process the request for both GET and POST methods
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        // Retrieve the session and username
         HttpSession session = request.getSession();
        String username = request.getParameter("username");
        if (username == null) {
            out.println("<html><body><h3>Error: Username not found in session. Please try again.</h3></body></html>");
            return;
        }

        // Retrieve other parameters from the request
        String subjectName = request.getParameter("subject");
        String slot = request.getParameter("slot");
        String courseType = request.getParameter("courseType");

        // Try to register the student
        try {
            // Call method to register student and handle redirection
            registerStudentForCourse(username, subjectName, slot, courseType, response,session);
        } catch (SQLException e) {
            // If there's an error during registration, log the error and show an error page
            e.printStackTrace();
            out.println("<html><body><h3>Registration failed due to an internal error. Please try again later.</h3></body></html>");
        }
    }
}

    // Handles the HTTP GET method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handles the HTTP POST method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Optional: returns a short description of the servlet
    @Override
    public String getServletInfo() {
        return "Servlet for registering students for courses";
    }

}
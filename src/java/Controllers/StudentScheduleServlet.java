/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.StudentDao;
import DBConnection.DBConnection;
import POJO.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SURYA
 */
public class StudentScheduleServlet extends HttpServlet {

   private final StudentDao studentDao = new StudentDao(); // Assuming StudentDao is defined

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get username and password from request
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Check if the user exists
            Student student = studentDao.getStudentByUsername(username);
            
            if (student != null) {
                // Compare the provided password with the stored password
                if (student.getPassword().equals(password)) {
                    // Password matches, now check the schedule
                    String course = student.getStudentClass();
                    LocalTime currentTime = LocalTime.now();

                    // Retrieve class timing from the RegistrationClassTimes table
                    if (isTimeMatching(course, currentTime)) {
                        // Set session and forward to the home servlet
                        HttpSession session = request.getSession();
                        session.setAttribute("StudentUsername", student.getUsername());
                        session.setAttribute("email", student.getEmail());
                        // Assuming student has an ID
                         response.sendRedirect("EachCourseRegister.jsp");                          
                    } else {
                        // Time does not match
                        response.sendRedirect("timeMismatch.jsp");
                    }
                } else {
                    // Incorrect password
                    response.sendRedirect("login.jsp?error=Incorrect+password");
                }
            } else {
                // User not found
                response.sendRedirect("login.jsp?error=User+not+found");
            }
        }
    }

    // Method to check if the current time matches the schedule time from RegistrationClassTimes
    private boolean isTimeMatching(String course, LocalTime currentTime) {
        boolean timeMatches = false;
        String query = "SELECT start_time, end_time FROM RegistrationClassTimes WHERE course = ?";
        
        try (Connection conn = DBConnection.getConnection(); // Assuming Database.getConnection() provides a DB connection
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, course);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LocalTime startTime = rs.getTime("start_time").toLocalTime();
                    LocalTime endTime = rs.getTime("end_time").toLocalTime();
                    // Check if the current time is within the start and end time
                    timeMatches = currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return timeMatches;
    }

    // Standard HttpServlet methods calling processRequest

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Student Schedule Servlet";
    }

}

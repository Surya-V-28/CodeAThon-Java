/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.StudentDao;
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

public class CourseRegistrationServlet extends HttpServlet {
    
    private  StudentDao  stuendDao = new StudentDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve the session and the username
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("StudentUsername") : null;
        
        // Get the selected course type from the request
        String courseType = request.getParameter("courseOption");

        if (username == null || courseType == null) {
            // If session or course type is missing, redirect to registration page
            response.sendRedirect("login.jsp?userNotLoggedIn=yes");
            return;
        }

        // Database connection variables
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try (PrintWriter out = response.getWriter()) {
            // Initialize database connection
            conn = DBConnection.getConnection();

            // Get the user's class (example query - adapt as per your schema)
            String userClass = stuendDao.getUserClass(username);

            // SQL query to get the available courses based on user class and course type
            String query = "SELECT subjectName, slot, seatsLeft FROM courseOffered WHERE class = ? AND courseType = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, userClass);
            stmt.setString(2, courseType);
            rs = stmt.executeQuery();

            // Start HTML output
            out.println("<html>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; }");
            out.println("h2 { color: #333; text-align: center; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 12px; text-align: center; }");
            out.println("th { background-color: #f2f2f2; color: #333; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("tr:hover { background-color: #e9e9e9; }");
            out.println("a { color: #0066cc; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("<body>");
              out.println("<button onclick=\"window.location.href='EachCourseRegister.jsp'\" style='padding: 8px 16px; font-size: 16px; margin-bottom: 15px;'>Home</button>");



            out.println("<h2>Available Courses for Registration</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Subject Name</th><th>Slot</th><th>Seats Left</th><th>Register</th></tr>");
            
            // Loop through the result set and display courses
            while (rs.next()) {
                String subjectName = rs.getString("subjectName");
                String slot = rs.getString("slot");
                int seatsLeft = rs.getInt("seatsLeft");

                // Display each course with a register button
                out.println("<tr>");
                out.println("<td>" + subjectName + "</td>");
                out.println("<td>" + slot + "</td>");
                out.println("<td>" + seatsLeft + "</td>");
                PreparedStatement Countstmt = null;
                String countQuery = "select seatsLeft from courseOffered WHERE subjectName = ?";
                Countstmt = conn.prepareStatement(countQuery);
                Countstmt.setString(1,subjectName);
                ResultSet resultSet1 = Countstmt.executeQuery();
                if (resultSet1.next()) {
                    int seatcount = resultSet1.getInt("seatsLeft");
                
                    if(seatcount > 0){
                        out.println("<td><a href='EachRegisterCourseServlet?subject=" + subjectName + "&username=" + username + "&slot=" + slot + "&courseType=" + courseType + "'>Register</a></td>");
                    }
                    else{
                        out.println("<td>Seats Full</td>");
                    }
                }

                out.println("</tr>");
            }

            // Close the table and finish the HTML
            out.println("</table>");
            out.println("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        } finally {
            try {
                // Close resources
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

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
        return "Course Registration Servlet";
    }
}

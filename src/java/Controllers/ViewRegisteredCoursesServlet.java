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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SURYA
 */
public class ViewRegisteredCoursesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("StudentUsername");

        if (username == null) {
            out.println("<html><body><h3>Error: Username not found in session. Please try again.</h3></body></html>");
            return;
        }

        out.println("<html><head>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("h2 { color: #2F4F4F; }");
        out.println("table { width: 80%; margin: auto; border-collapse: collapse; }");
        out.println("th, td { padding: 10px; text-align: center; border: 1px solid #ddd; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("a { color: #1E90FF; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head><body>");

        // Home button
        out.println("<button onclick=\"window.location.href='EachCourseRegister.jsp'\" style='padding: 8px 16px; font-size: 16px; margin-bottom: 15px;'>Home</button>");

        out.println("<h2>Registered Courses</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Subject Name</th><th>Slot</th><th>Course Type</th><th>Delete</th></tr>");

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT subjectName, slot, courseType FROM studentRegistered WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subjectName");
                String slot = rs.getString("slot");
                String courseType = rs.getString("courseType");

                out.println("<tr>");
                out.println("<td>" + subjectName + "</td>");
                out.println("<td>" + slot + "</td>");
                out.println("<td>" + courseType + "</td>");
                out.println("<td><a href='RemoveCourseServlet?subject=" + subjectName + "&username=" + username + "&slot=" + slot + "'>Remove</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error retrieving registered courses.</p>");
        }
    }
}



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

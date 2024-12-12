/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;
import DAO.StudentDao;
import POJO.Student;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author SURYA
 */
@MultipartConfig
public class RegisterControl extends HttpServlet {

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

        // Set request encoding to handle special characters
        request.setCharacterEncoding("UTF-8");
        
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()) {
            
            
        
            // Retrieve form parameters
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String department = request.getParameter("department");
            String yearStr = request.getParameter("year");
            String studentClass = request.getParameter("class");

            // Print parameters to verify values
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Department: " + department);
            System.out.println("Year: " + yearStr);
            System.out.println("Class: " + studentClass);

            // Convert year from String to integer
            int year = parseYear(yearStr);

            // Retrieve profile picture as byte array if provided
            byte[] profilePicture = getProfilePictureBytes(request);

            // Create Student object with retrieved data
            Student student = new Student(username, email, password, profilePicture, department, year, studentClass);

            // Insert student data into the database
            StudentDao studentDao = new StudentDao();
            studentDao.insertStudent(student);
                
            // Display successful registration information
            response.sendRedirect("login.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }

    // Utility method to convert year from String to int
    private int parseYear(String yearStr) {
        int year = 0;
        if (yearStr != null && !yearStr.isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return year;
    }

    // Utility method to retrieve profile picture as byte array
    private byte[] getProfilePictureBytes(HttpServletRequest request) throws IOException, ServletException {
        Part profilePicturePart = request.getPart("profilePicture");
        byte[] profilePicture = null;
        if (profilePicturePart != null) {
            try (InputStream inputStream = profilePicturePart.getInputStream()) {
                profilePicture = new byte[inputStream.available()];
                inputStream.read(profilePicture);
            }
        }
        return profilePicture;
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

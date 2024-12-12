package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminLogin") // URL mapping for the servlet
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String ADMIN_USERNAME = "Team2";
    private static final String ADMIN_PASSWORD = "password"; // Update with a secure password
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Verify credentials
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            response.sendRedirect("AdminHome.jsp"); // Redirect to the dashboard
        } else {
            // Login failed; show error message
            request.setAttribute("message", "Invalid credentials, please try again.");
            request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("AdminLogin.jsp"); // Redirect GET requests to login page
    }

    @Override
    public String getServletInfo() {
        return "AdminController handles admin login and authentication.";
    }
}
package Controllers;

import POJO.Student;
import DAO.StudentDao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.PrintWriter;

@MultipartConfig
public class UserController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set request encoding to handle special characters
        request.setCharacterEncoding("UTF-8");
        
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()) {
             response.sendRedirect("index.html");
        }
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
        return "Student Registration Controller Servlet";
    }
}

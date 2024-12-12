package Controllers;

import DAO.StudentDao;
import POJO.Student;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
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

public class VerifyEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        StudentDao student = new StudentDao();
        Student stud =  student.getStudentByUsername("surya1");
        sendVerificationEmail(stud.getEmail());
    }

    // Call this method to send verification email
   public void sendVerificationEmail(String recipientEmail) {
    String host = "smtp.gmail.com";  // Update with SMTP server address
    String from = "startup.work.231@gmail.com";  // Sender's email
    String password = "your_email_password";  // Sender's email password

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");

    

   
}


    private boolean checkTokenInDatabase(String token) {
        // Logic to check if token exists in the database and is valid
        return true; 
    }

    private void activateUserAccount(String token) {
        // Logic to activate user account
    }
}
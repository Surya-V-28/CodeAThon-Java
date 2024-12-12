package Controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String subjectName = request.getParameter("subject");
        String username = request.getParameter("username");
        String slot = request.getParameter("slot");
        
        if (subjectName == null || username == null || slot == null) {
            response.getWriter().println("<html><body><h3>Error: Missing course details. Please try again.</h3></body></html>");
            return;
        }
        
        response.getWriter().println("<html><body>");
        response.getWriter().println("<a href='GoBackHomeServlet'>  Go back</a>");
        
        Connection conn = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement updateSeatsStmt = null;
        
        try {
            conn = DBConnection.getConnection();

            // Delete the course from studentRegistered table
            String deleteSql = "DELETE FROM studentRegistered WHERE username = ? AND subjectName = ? AND slot = ?";
            deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setString(1, username);
            deleteStmt.setString(2, subjectName);
            deleteStmt.setString(3, slot);
            
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Update seatsLeft in courseOffered table after successful deletion
                String updateSeatsSql = "UPDATE courseOffered SET seatsLeft = seatsLeft + 1 WHERE subjectName = ? AND slot = ?";
                updateSeatsStmt = conn.prepareStatement(updateSeatsSql);
                updateSeatsStmt.setString(1, subjectName);
                updateSeatsStmt.setString(2, slot);
                updateSeatsStmt.executeUpdate();

                response.sendRedirect("ViewRegisteredCoursesServlet");
            } else {
                response.getWriter().println("<html><body><h3>Error: Course not found or could not be removed.</h3></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<html><body><h3>Error removing course.</h3></body></html>");
        } finally {
            try {
                if (deleteStmt != null) deleteStmt.close();
                if (updateSeatsStmt != null) updateSeatsStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.getWriter().println("</body></html>");
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
        return "Servlet to remove a course from registered courses and update seats left";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.StudentDao;


public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDao studentDao = new StudentDao(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("id");  // Get the student username (or id) from the URL

        if (username != null && !username.isEmpty()) {
            studentDao.deleteStudent(username);
            response.sendRedirect("admin.jsp");
            
             
        } else {
            response.sendRedirect("error.jsp");  
        }
    }
}

package DAO;

import java.sql.*;
import POJO.*;
import DBConnection.*;
import java.util.*;

public class StudentDao {
    
    public void insertStudent(Student student) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO Student(username, email, password, profile_picture, department, year, student_class) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            // Set the parameters for the prepared statement
            ps.setString(1, student.getUsername());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword());
            ps.setBytes(4, student.getProfilePicture());
            ps.setString(5, student.getDepartment());
            ps.setInt(6, student.getYear());
            ps.setString(7, student.getStudentClass());
            
            // Execute the insert statement
            ps.executeUpdate();
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
     public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();  // List to hold all students
        Connection con = null;
        
        try {
            con = DBConnection.getConnection();
            String query = "SELECT * FROM STUDENT";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            // Iterate over the ResultSet and create Student objects
           while (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String department = rs.getString("department");
            int year = rs.getInt("year");
            String studentClass = rs.getString("student_class");
            byte[] profilePicture = null;

            // Retrieve profile picture if available
            Blob profilePicBlob = rs.getBlob("profile_picture");
            if (profilePicBlob != null) {
                profilePicture = profilePicBlob.getBytes(1, (int) profilePicBlob.length());
            }

            // Use the constructor with parameters
            Student student = new Student(username, email, password, profilePicture, department, year, studentClass);
            students.add(student);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        if (con != null) {
            con.close();
        }
    }
        return students;
}
      public void deleteStudent(String username) {
        
        String sql = "DELETE FROM student WHERE username = ?";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
      
     
      
      public Student getStudentByUsername(String username) {
        Student student = null;
        String query = "SELECT * FROM student WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the username parameter
            stmt.setString(1, username);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                // If a student is found, create a Student object using the constructor
                if (rs.next()) {
                    String fullname = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String department = rs.getString("department");
                    int year = rs.getInt("year");
                    String studentClass = rs.getString("student_class");
                    byte[] profilePicture = rs.getBytes("profile_picture");  // Retrieve profile picture as byte array

                    // Create a new Student object using the constructor
                    student = new Student(fullname, email, password, profilePicture, department, year, studentClass);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;  // Return the populated student object (or null if no student is found)
    }
      public String getUserClass(String username) throws SQLException {
        // This is the query to fetch the user's class from the database
        String query = "SELECT student_class FROM Student WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("student_class");
                } else {
                    return null;  // Return null if no class found for the user
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


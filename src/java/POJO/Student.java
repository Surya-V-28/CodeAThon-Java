package POJO;

public class Student {
    private String username;
    private String email;
    private String password;
    private byte[] profilePicture; // Store image as byte array
    private String department;
    private int year;
    private String studentClass;
    
    
     public Student(String username, String email, String password, byte[] profilePicture, 
                   String department, int year, String studentClass) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.department = department;
        this.year = year;
        this.studentClass = studentClass;
    }



    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    
    @Override
    public String toString() {
        return "Student{" +
               "username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", profilePicture=" + (profilePicture != null ? profilePicture.length : "null") + " bytes" +
               ", department='" + department + '\'' +
               ", year=" + year +
               ", studentClass='" + studentClass + '\'' +
               '}';
    }
}

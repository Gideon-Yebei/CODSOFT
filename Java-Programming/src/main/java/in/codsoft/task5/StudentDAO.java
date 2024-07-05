package in.codsoft.task5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void createStudentTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    studentID VARCHAR(10) PRIMARY KEY,
                    name VARCHAR(100)
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void createStudentCourseTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS student_courses (
                    studentID VARCHAR(10),
                    courseCode VARCHAR(10),
                    PRIMARY KEY (studentID, courseCode),
                    FOREIGN KEY (studentID) REFERENCES students(studentID),
                    FOREIGN KEY (courseCode) REFERENCES courses(courseCode)
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (studentID, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.executeUpdate();
        }
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(rs.getString("studentID"), rs.getString("name"));
                students.add(student);
            }
        }
        return students;
    }

    public Student findStudentByID(String studentID) throws SQLException {
        String sql = "SELECT * FROM students WHERE studentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getString("studentID"), rs.getString("name"));
                }
            }
        }
        return null;
    }

    public void registerCourse(String studentID, String courseCode) throws SQLException {
        String sql = "INSERT INTO student_courses (studentID, courseCode) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            pstmt.setString(2, courseCode);
            pstmt.executeUpdate();
        }
    }

    public void dropCourse(String studentID, String courseCode) throws SQLException {
        String sql = "DELETE FROM student_courses WHERE studentID = ? AND courseCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            pstmt.setString(2, courseCode);
            pstmt.executeUpdate();
        }
    }
}

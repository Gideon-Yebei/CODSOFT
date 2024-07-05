package in.codsoft.task5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private final Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCourseTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS courses (
                    courseCode VARCHAR(10) PRIMARY KEY,
                    title VARCHAR(100),
                    description VARCHAR(255),
                    capacity INT,
                    schedule VARCHAR(50),
                    enrolledStudents INT
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void addCourse(Course course) throws SQLException {
        String sql = """
                INSERT INTO courses (courseCode, title, description, capacity, schedule, enrolledStudents)
                VALUES (?, ?, ?, ?, ?, ?);
                """;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getTitle());
            pstmt.setString(3, course.getDescription());
            pstmt.setInt(4, course.getCapacity());
            pstmt.setString(5, course.getSchedule());
            pstmt.setInt(6, course.getEnrolledStudents());
            pstmt.executeUpdate();
        }
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getString("courseCode"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("capacity"),
                        rs.getString("schedule")
                );
                course.setEnrolledStudents(rs.getInt("enrolledStudents"));
                courses.add(course);
            }
        }
        return courses;
    }

    public Course findCourseByCode(String courseCode) throws SQLException {
        String sql = "SELECT * FROM courses WHERE courseCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Course course = new Course(
                            rs.getString("courseCode"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("capacity"),
                            rs.getString("schedule")
                    );
                    course.setEnrolledStudents(rs.getInt("enrolledStudents"));
                    return course;
                }
            }
        }
        return null;
    }

    public void updateCourse(Course course) throws SQLException {
        String sql = """
                UPDATE courses
                SET title = ?, description = ?, capacity = ?, schedule = ?, enrolledStudents = ?
                WHERE courseCode = ?;
                """;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getCapacity());
            pstmt.setString(4, course.getSchedule());
            pstmt.setInt(5, course.getEnrolledStudents());
            pstmt.setString(6, course.getCourseCode());
            pstmt.executeUpdate();
        }
    }
}

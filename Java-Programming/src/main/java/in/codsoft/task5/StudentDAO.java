package in.codsoft.task5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        String sql = "INSERT INTO RegisteredCourses (student_id, course_code) " +
                "VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropCourseForStudent(String studentId, String courseCode) {
        String sql = "DELETE FROM RegisteredCourses WHERE student_id = ? AND course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(String studentId, String courseCode) {
        String sql = "INSERT INTO RegisteredCourses (student_id, course_code) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            System.out.println("Student " + studentId + " registered for course " + courseCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(String studentId, String courseCode) {
        String sql = "DELETE FROM RegisteredCourses WHERE student_id = ? AND course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Course " + courseCode + " removed for student " + studentId);
            } else {
                System.out.println("No registration found for student " + studentId + " in course " + courseCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

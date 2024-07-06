package in.codsoft.task5;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.student_id, s.name, c.course_code, c.title, c.description, c.capacity, c.schedule " +
                "FROM Students s " +
                "LEFT JOIN RegisteredCourses r ON s.student_id = r.student_id " +
                "LEFT JOIN Courses c ON r.course_code = c.course_code";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            Map<String, Student> studentMap = new HashMap<>(); // Using map to handle duplicate students due to multiple courses

            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                String courseCode = resultSet.getString("course_code");
                String courseTitle = resultSet.getString("title");
                String courseDescription = resultSet.getString("description");
                int courseCapacity = resultSet.getInt("capacity");
                String courseSchedule = resultSet.getString("schedule");

                if (!studentMap.containsKey(studentId)) {
                    Student student = new Student(studentId, name);
                    studentMap.put(studentId, student);
                    students.add(student);
                }

                if (courseCode != null) {
                    Course course = new Course(courseCode, courseTitle, courseDescription, courseCapacity, courseSchedule);
                    studentMap.get(studentId).getRegisteredCourses().add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        if (isStudentRegisteredForCourse(studentId, courseCode)) {
            System.out.println("Student " + studentId + " is already registered for course " + courseCode);
            return;
        }

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

    private boolean isStudentRegisteredForCourse(String studentId, String courseCode) {
        String sql = "SELECT 1 FROM RegisteredCourses WHERE student_id = ? AND course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void dropCourseForStudent(String studentId, String courseCode) {
        String sql = "DELETE FROM RegisteredCourses WHERE student_id = ? AND course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            System.out.println("Course " + courseCode + " dropped for student " + studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        String sql = "INSERT INTO Students (student_id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.executeUpdate();
            System.out.println("Student " + student.getStudentId() + " added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStudent(String studentId) {
        String sqlDeleteRegisteredCourses = "DELETE FROM RegisteredCourses WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteRegisteredCourses)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlDeleteStudent = "DELETE FROM Students WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteStudent)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
            System.out.println("Student " + studentId + " removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

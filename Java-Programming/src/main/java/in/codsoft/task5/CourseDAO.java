package in.codsoft.task5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private final Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("course_code"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("schedule")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void displayCourses() {
        List<Course> courses = getAllCourses();
        System.out.println("\u001B[32mAvailable Courses:\u001B[0m");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s %-30s %-40s %-10s %-20s%n", "Course Code", "Title", "Description", "Capacity", "Schedule");
        System.out.println("--------------------------------------------------");
        for (Course course : courses) {
            System.out.printf("%-15s %-30s %-40s %-10d %-20s%n",
                    course.getCourseCode(), course.getTitle(), course.getDescription(),
                    course.getCapacity(), course.getSchedule());
        }
        System.out.println("--------------------------------------------------");
    }

    public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (course_code, title, description, capacity, schedule) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getTitle());
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getCapacity());
            statement.setString(5, course.getSchedule());
            statement.executeUpdate();
            System.out.println("\u001B[32mCourse added successfully.\u001B[0m");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(String courseCode) {
        // Remove related registered courses first
        String deleteRegisteredCoursesSql = "DELETE FROM RegisteredCourses WHERE course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteRegisteredCoursesSql)) {
            statement.setString(1, courseCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Now delete the course itself
        String deleteCourseSql = "DELETE FROM Courses WHERE course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteCourseSql)) {
            statement.setString(1, courseCode);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course " + courseCode + " deleted successfully.");
            } else {
                System.out.println("Course " + courseCode + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

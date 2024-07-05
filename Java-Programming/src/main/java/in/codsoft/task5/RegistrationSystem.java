package in.codsoft.task5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class RegistrationSystem {
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;

    public RegistrationSystem(Connection connection) {
        this.courseDAO = new CourseDAO(connection);
        this.studentDAO = new StudentDAO(connection);
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            RegistrationSystem registrationSystem = new RegistrationSystem(connection);

            // Example usage:
            registrationSystem.listAvailableCourses();

            // Uncomment and use as needed:
            // registrationSystem.registerStudent("S001", "C001");
            // registrationSystem.removeCourse("S001", "C001");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAvailableCourses() {
        List<Course> courses = courseDAO.getAllCourses();
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void registerStudent(String studentId, String courseCode) {
        studentDAO.registerStudentForCourse(studentId, courseCode);
        System.out.println("Student " + studentId + " registered for course " + courseCode);
    }

    public void removeCourse(String studentId, String courseCode) {
        studentDAO.dropCourseForStudent(studentId, courseCode);
        System.out.println("Student " + studentId + " dropped course " + courseCode);
    }
}

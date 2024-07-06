package in.codsoft.task5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
            registrationSystem.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\u001B[34mStudent Registration System\u001B[0m");
            System.out.println("1. View Students");
            System.out.println("2. View Available Courses");
            System.out.println("3. Add Course");
            System.out.println("4. Add Student");
            System.out.println("5. Register Student for Course");
            System.out.println("6. Remove Course");
            System.out.println("7. Remove Student");
            System.out.println("8. Drop Course for Student");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStudents();
                    break;
                case 2:
                    viewAvailableCourses();
                    break;
                case 3:
                    addCourse(scanner);
                    break;
                case 4:
                    addStudent(scanner);
                    break;
                case 5:
                    registerStudentForCourse(scanner);
                    break;
                case 6:
                    removeCourse(scanner);
                    break;
                case 7:
                    removeStudent(scanner);
                    break;
                case 8:
                    dropCourseForStudent(scanner);
                    break;
                case 9:
                    System.out.println("\u001B[32mExiting the system. Goodbye!\u001B[0m");
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }

    private void viewStudents() {
        System.out.println("\u001B[32mStudents and their registered courses:\u001B[0m");
        for (Student student : studentDAO.getAllStudents()) {
            System.out.println(student);
        }
    }

    private void viewAvailableCourses() {
        courseDAO.displayCourses();
    }

    private void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course schedule: ");
        String schedule = scanner.nextLine();

        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDAO.addCourse(course);
    }

    private void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentId, name);
        studentDAO.addStudent(student);
    }

    private void registerStudentForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        studentDAO.registerStudentForCourse(studentId, courseCode);
    }

    private void removeCourse(Scanner scanner) {
        System.out.print("Enter course code to remove: ");
        String courseCode = scanner.nextLine();
        courseDAO.removeCourse(courseCode);
    }

    private void removeStudent(Scanner scanner) {
        System.out.print("Enter student ID to remove: ");
        String studentId = scanner.nextLine();
        studentDAO.removeStudent(studentId);
    }

    private void dropCourseForStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();

        studentDAO.dropCourseForStudent(studentId, courseCode);
    }
}

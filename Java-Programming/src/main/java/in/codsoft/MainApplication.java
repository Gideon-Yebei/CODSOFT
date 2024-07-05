package in.codsoft;

import in.codsoft.task5.CourseDAO;
import in.codsoft.task5.StudentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {

    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        /**
         * Task 1
         * Start the Number Game
         * */
        // NumberGame.play();
        /**
         * Task 2
         * Start the Student Grade Calculator
         * */
        // StudentGradeCalculator.calculateGrades();
        /**
         * Task 3
         * atm.start();
         */
        // BankAccount account = new BankAccount(1000.0); // Initial balance
        // ATM atm = new ATM(account);
        // atm.start();
        /**
         * Task 4
         * Start the Quiz Application
         */
        // QuizApplication quizApp = new QuizApplication();
        // quizApp.startQuiz();
        /**
         * Task 5
         *
         */
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CourseDAO courseDAO = new CourseDAO(connection);
            StudentDAO studentDAO = new StudentDAO(connection);

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Welcome to Student Course Registration System");
                System.out.println("1. Display Available Courses");
                System.out.println("2. Register Student for Course");
                System.out.println("3. Remove Student from Course");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        courseDAO.displayCourses();
                        break;
                    case 2:
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter Course Code to Register: ");
                        String courseCode = scanner.nextLine();
                        studentDAO.registerStudent(studentId, courseCode);
                        break;
                    case 3:
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine();
                        System.out.print("Enter Course Code to Remove: ");
                        courseCode = scanner.nextLine();
                        studentDAO.removeCourse(studentId, courseCode);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

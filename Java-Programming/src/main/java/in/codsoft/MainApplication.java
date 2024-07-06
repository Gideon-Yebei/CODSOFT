package in.codsoft;

// import in.codsoft.task1.NumberGame;
// import in.codsoft.task2.StudentGradeCalculator;
//import in.codsoft.task3.ATM;
//import in.codsoft.task3.BankAccount;
//import in.codsoft.task4.QuizApplication;

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
        //StudentGradeCalculator.calculateGrades();
        /**
         * Task 3
         * atm.start();
         */
        // BankAccount account = new BankAccount(1000.0); // Initial balance
        // ATM atm = new ATM(account);
        //atm.start();
        /**
         * Task 4
         * Start the Quiz Application
         */
        // QuizApplication quizApp = new QuizApplication();
        //quizApp.startQuiz();
        /**
         * Task 5
         *
         */
        // try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
        // RegistrationSystem registrationSystem = new RegistrationSystem(connection);
        // registrationSystem.run();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
    }
}

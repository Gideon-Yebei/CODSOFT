package in.codsoft;

import in.codsoft.task4.QuizApplication;

public class MainApplication {

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
        QuizApplication quizApp = new QuizApplication();
        quizApp.startQuiz();
    }
}

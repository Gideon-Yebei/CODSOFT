package in.codsoft.task2;

import in.codsoft.util.ConsoleUtil;

public class StudentGradeCalculator {

    public static void calculateGrades() {
        ConsoleUtil.printColored("Welcome to the Student Grade Calculator!", ConsoleUtil.BLUE);

        ConsoleUtil.printInteractivePrompt("Enter the number of subjects: ");
        int numberOfSubjects;
        try {
            numberOfSubjects = Integer.parseInt(ConsoleUtil.readInput());
        } catch (NumberFormatException e) {
            ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
            return;
        }

        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numberOfSubjects; i++) {
            ConsoleUtil.printInteractivePrompt("Enter marks for subject " + (i + 1) + ": ");
            try {
                marks[i] = Integer.parseInt(ConsoleUtil.readInput());
                if (marks[i] < 0 || marks[i] > 100) {
                    ConsoleUtil.printColored("Marks should be between 0 and 100.", ConsoleUtil.RED);
                    i--; // repeat this iteration
                    continue;
                }
                totalMarks += marks[i];
            } catch (NumberFormatException e) {
                ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
                i--; // repeat this iteration
            }
        }

        double averagePercentage = (double) totalMarks / numberOfSubjects;
        char grade;

        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        ConsoleUtil.printColored("Total Marks: " + totalMarks, ConsoleUtil.GREEN);
        ConsoleUtil.printColored("Average Percentage: " + averagePercentage + "%", ConsoleUtil.GREEN);
        ConsoleUtil.printColored("Grade: " + grade, ConsoleUtil.GREEN);
    }
}

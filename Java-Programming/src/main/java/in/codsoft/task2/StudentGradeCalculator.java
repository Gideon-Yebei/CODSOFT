package in.codsoft.task2;

import in.codsoft.util.ConsoleUtil;

/**
 * StudentGradeCalculator class calculates grades based on entered marks.
 */
public class StudentGradeCalculator {

    /**
     * Calculates grades based on user input for marks.
     */
    public static void calculateGrades() {
        ConsoleUtil.printColored("Welcome to the Student Grade Calculator!", ConsoleUtil.BLUE);

        int numberOfSubjects = getNumberOfSubjects(); // Get number of subjects from user
        if (numberOfSubjects <= 0) {
            ConsoleUtil.printColored("Invalid number of subjects. Please try again.", ConsoleUtil.RED);
            return;
        }

        int[] marks = getMarksForSubjects(numberOfSubjects); // Get marks for each subject
        if (marks == null) {
            ConsoleUtil.printColored("Invalid marks entered. Please try again.", ConsoleUtil.RED);
            return;
        }

        int totalMarks = calculateTotalMarks(marks); // Calculate total marks
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects); // Calculate average percentage
        char grade = calculateGrade(averagePercentage); // Calculate grade

        // Display results to the user
        displayResults(totalMarks, averagePercentage, grade);
    }

    /**
     * Prompts the user for the number of subjects and returns the input.
     *
     * @return Number of subjects entered by the user.
     */
    private static int getNumberOfSubjects() {
        int numberOfSubjects = 0;
        boolean validInput = false;

        do {
            ConsoleUtil.printInteractivePrompt("Enter the number of subjects: ");
            try {
                numberOfSubjects = Integer.parseInt(ConsoleUtil.readInput());
                validInput = numberOfSubjects > 0;
                if (!validInput) {
                    ConsoleUtil.printColored("Number of subjects must be greater than zero.", ConsoleUtil.RED);
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
            }
        } while (!validInput);

        return numberOfSubjects;
    }

    /**
     * Prompts the user for marks for each subject and returns an array of marks.
     *
     * @param numberOfSubjects Number of subjects for which marks are to be entered.
     * @return Array of marks entered by the user.
     */
    private static int[] getMarksForSubjects(int numberOfSubjects) {
        int[] marks = new int[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++) {
            boolean validInput = false;
            do {
                ConsoleUtil.printInteractivePrompt("Enter marks for subject " + (i + 1) + ": ");
                try {
                    marks[i] = Integer.parseInt(ConsoleUtil.readInput());
                    if (marks[i] < 0 || marks[i] > 100) {
                        ConsoleUtil.printColored("Marks should be between 0 and 100.", ConsoleUtil.RED);
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
                }
            } while (!validInput);
        }

        return marks;
    }

    /**
     * Calculates total marks from an array of marks.
     *
     * @param marks Array of marks.
     * @return Total marks calculated from the array.
     */
    private static int calculateTotalMarks(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    /**
     * Calculates average percentage from total marks and number of subjects.
     *
     * @param totalMarks       Total marks obtained.
     * @param numberOfSubjects Number of subjects.
     * @return Average percentage calculated from total marks and number of subjects.
     */
    private static double calculateAveragePercentage(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects;
    }

    /**
     * Calculates grade based on average percentage.
     *
     * @param averagePercentage Average percentage obtained.
     * @return Grade calculated based on average percentage.
     */
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else if (averagePercentage >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }

    /**
     * Displays results including total marks, average percentage, and grade.
     *
     * @param totalMarks        Total marks obtained.
     * @param averagePercentage Average percentage obtained.
     * @param grade             Grade obtained.
     */
    private static void displayResults(int totalMarks, double averagePercentage, char grade) {
        ConsoleUtil.printColored("Total Marks: " + totalMarks, ConsoleUtil.GREEN);
        ConsoleUtil.printColored("Average Percentage: " + String.format("%.2f", averagePercentage) + "%", ConsoleUtil.GREEN);
        ConsoleUtil.printColored("Grade: " + grade, ConsoleUtil.GREEN);
    }
}

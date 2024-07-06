package in.codsoft.task4;

/**
 * Quiz interface defines methods for a quiz application.
 */
public interface Quiz {
    /**
     * Starts the quiz.
     */
    void startQuiz();

    /**
     * Presents the current question.
     */
    void presentQuestion();

    /**
     * Checks the user's answer.
     *
     * @param answer The user's answer (1-4).
     */
    void checkAnswer(int answer);

    /**
     * Displays the quiz results after all questions have been answered.
     */
    void displayResults();
}

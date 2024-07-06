package in.codsoft.task4;

import in.codsoft.util.ConsoleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * QuizApplication class implements a timed quiz application.
 */
public class QuizApplication implements Quiz {
    private final List<QuizQuestion> questions;
    private final Scanner scanner;
    private int score;
    private int currentQuestionIndex;

    /**
     * Constructs a QuizApplication instance.
     */
    public QuizApplication() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
        initializeQuestions();
    }

    /**
     * Initializes the quiz questions.
     */
    private void initializeQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));
        // Add more questions
    }

    @Override
    public void startQuiz() {
        while (currentQuestionIndex < questions.size()) {
            presentQuestion();
            AtomicBoolean answered = new AtomicBoolean(false);
            Thread timerThread = startTimer(answered);
            waitForAnswer(answered);
            timerThread.interrupt();
            if (!answered.get()) {
                System.out.println(ConsoleUtil.colorText("\nTime's up!", ConsoleUtil.RED));
                currentQuestionIndex++;
            }
        }
        displayResults();
    }

    @Override
    public void presentQuestion() {
        QuizQuestion question = questions.get(currentQuestionIndex);
        System.out.println(ConsoleUtil.colorText(question.question(), ConsoleUtil.BLUE));
        Arrays.stream(question.options()).map(s -> ConsoleUtil.colorText(s, ConsoleUtil.YELLOW))
                .forEach(System.out::println);
        System.out.print(ConsoleUtil.colorText("Enter your answer (1-4): ", ConsoleUtil.CYAN));
    }

    private Thread startTimer(AtomicBoolean answered) {
        Thread timerThread = new Thread(() -> {
            try {
                for (int i = 15; i > 0; i--) {
                    if (answered.get()) return;
                    System.out.print(ConsoleUtil.colorText("\rTime left: " + i + " seconds", ConsoleUtil.RED));
                    Thread.sleep(1000);
                }
                if (!answered.get()) {
                    System.out.println(ConsoleUtil.colorText("\nTime's up!", ConsoleUtil.RED));
                    answered.set(true);
                }
            } catch (InterruptedException e) {
                // Timer interrupted, no action needed
            }
        });
        timerThread.start();
        return timerThread;
    }

    private void waitForAnswer(AtomicBoolean answered) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime < 15000) && !answered.get()) {
            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if (answer >= 1 && answer <= 4) {
                    checkAnswer(answer);
                    answered.set(true);
                } else {
                    System.out.println(ConsoleUtil.colorText("Invalid option. Please enter a number between 1 and 4.", ConsoleUtil.RED));
                    scanner.next(); // clear invalid input
                }
            }
        }
    }

    @Override
    public void checkAnswer(int answer) {
        QuizQuestion question = questions.get(currentQuestionIndex);
        if (answer == question.correctAnswer()) {
            score++;
            System.out.println(ConsoleUtil.colorText("Correct!", ConsoleUtil.GREEN));
        } else {
            System.out.println(ConsoleUtil.colorText("Incorrect. The correct answer was " + question.options()[question.correctAnswer() - 1], ConsoleUtil.RED));
        }
        currentQuestionIndex++;
    }

    @Override
    public void displayResults() {
        System.out.println(ConsoleUtil.colorText("\nQuiz Over!", ConsoleUtil.PURPLE));
        System.out.println(ConsoleUtil.colorText("Your final score is: " + score + "/" + questions.size(), ConsoleUtil.GREEN));
        IntStream.range(0, questions.size()).forEach(i -> {
            QuizQuestion question = questions.get(i);
            System.out.println(ConsoleUtil.colorText((i + 1) + ". " + question.question(), ConsoleUtil.BLUE));
            System.out.println(ConsoleUtil.colorText("Correct answer: " + question.options()[question.correctAnswer() - 1], ConsoleUtil.YELLOW));
        });
    }
}

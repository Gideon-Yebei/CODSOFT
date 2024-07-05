package in.codsoft.task1;

import in.codsoft.util.ConsoleUtil;

import java.util.Random;

public class NumberGame {

    private static final int MAX_ATTEMPTS = 10;

    public static void play() {
        Random random = new Random();

        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            ConsoleUtil.printColored("A random number between 1 and 100 has been generated. Try to guess it!", ConsoleUtil.BLUE);

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                ConsoleUtil.printInteractivePrompt("Enter your guess: ");
                int userGuess;
                try {
                    userGuess = Integer.parseInt(ConsoleUtil.readInput());
                } catch (NumberFormatException e) {
                    ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
                    continue;
                }

                attempts++;

                if (userGuess == numberToGuess) {
                    ConsoleUtil.printColored("Congratulations! You guessed the correct number.", ConsoleUtil.GREEN);
                    guessedCorrectly = true;
                    score++;
                } else if (userGuess < numberToGuess) {
                    ConsoleUtil.printColored("Your guess is too low. Try again. Attempts left: " + (MAX_ATTEMPTS - attempts), ConsoleUtil.YELLOW);
                } else {
                    ConsoleUtil.printColored("Your guess is too high. Try again. Attempts left: " + (MAX_ATTEMPTS - attempts), ConsoleUtil.YELLOW);
                }
            }

            if (!guessedCorrectly) {
                ConsoleUtil.printColored("You've run out of attempts. The correct number was " + numberToGuess + ".", ConsoleUtil.RED);
            }

            ConsoleUtil.printColored("Your current score is: " + score, ConsoleUtil.BLUE);
            ConsoleUtil.printInteractivePrompt("Do you want to play again? (yes/no): ");
            String response = ConsoleUtil.readInput().trim().toLowerCase();
            playAgain = response.equals("yes");

            if (playAgain) {
                ConsoleUtil.printColored("Starting a new round...", ConsoleUtil.BLUE);
            }
        }

        ConsoleUtil.printColored("Thank you for playing! Your final score is: " + score, ConsoleUtil.BLUE);
    }
}

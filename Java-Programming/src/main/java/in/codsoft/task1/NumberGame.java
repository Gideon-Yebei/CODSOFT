package in.codsoft.task1;

import in.codsoft.util.ConsoleUtil;

import java.util.Random;

/**
 * NumberGame class implements a simple number guessing game.
 */
public class NumberGame {

    private static final int MAX_ATTEMPTS = 10; // Maximum attempts allowed per game

    /**
     * Starts the number guessing game.
     */
    public static void play() {
        ConsoleUtil.printColored("Welcome to the Number Guessing Game!", ConsoleUtil.BLUE);

        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            ConsoleUtil.printColored("A random number between 1 and 100 has been generated. Try to guess it!", ConsoleUtil.BLUE);

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                int userGuess = getUserGuess(); // Prompt user for guess

                if (userGuess == -1) {
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
            playAgain = askToPlayAgain(); // Ask user if they want to play again
        }

        ConsoleUtil.printColored("Thank you for playing! Your final score is: " + score, ConsoleUtil.BLUE);
    }

    /**
     * Prompts the user for their guess and returns it.
     *
     * @return User's guess or -1 if input is invalid.
     */
    private static int getUserGuess() {
        int userGuess = -1;
        boolean validInput = false;

        do {
            ConsoleUtil.printInteractivePrompt("Enter your guess: ");
            try {
                userGuess = Integer.parseInt(ConsoleUtil.readInput());
                validInput = true;
            } catch (NumberFormatException e) {
                ConsoleUtil.printColored("Invalid input. Please enter a valid number.", ConsoleUtil.RED);
            }
        } while (!validInput);

        return userGuess;
    }

    /**
     * Asks the user if they want to play again and returns their response.
     *
     * @return true if user wants to play again, false otherwise.
     */
    private static boolean askToPlayAgain() {
        boolean playAgain = false;
        boolean validInput = false;

        do {
            ConsoleUtil.printInteractivePrompt("Do you want to play again? (yes/no): ");
            String response = ConsoleUtil.readInput().trim().toLowerCase();

            if (response.equals("yes")) {
                playAgain = true;
                validInput = true;
                ConsoleUtil.printColored("Starting a new round...", ConsoleUtil.BLUE);
            } else if (response.equals("no")) {
                playAgain = false;
                validInput = true;
            } else {
                ConsoleUtil.printColored("Invalid input. Please enter 'yes' or 'no'.", ConsoleUtil.RED);
            }
        } while (!validInput);

        return playAgain;
    }
}

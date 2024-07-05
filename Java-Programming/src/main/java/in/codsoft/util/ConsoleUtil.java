package in.codsoft.util;

import java.util.Scanner;

public class ConsoleUtil {

    // ANSI escape codes for colors
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static String colorText(String text, String color) {
        return color + text + RESET;
    }

    public static void printColored(String message, String color) {
        System.out.println(color + message + RESET);
    }

    public static void printInteractivePrompt(String message) {
        System.out.print(GREEN + message + RESET);
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

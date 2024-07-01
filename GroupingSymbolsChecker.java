import java.util.Scanner;
import java.util.Stack;

public class GroupingSymbolsChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Grouping Symbols Matching Game!");

        // Loop to allow multiple rounds of the game
        while (true) {
            System.out.print("Enter a string with grouping symbols (or 'exit' to quit): ");
            String input = scanner.nextLine();

            // Exit condition
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Check if the input string has correctly paired grouping symbols
            if (isValidGrouping(input)) {
                System.out.println("The string has correctly paired grouping symbols.");
            } else {
                System.out.println("The string has incorrect pairing of grouping symbols.");
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    /**
     * Checks if a string has correctly paired grouping symbols.
     * @param input the input string to check
     * @return true if the grouping symbols are correctly paired, false otherwise
     */
    public static boolean isValidGrouping(String input) {
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            // Push opening symbols onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Check closing symbols against the stack
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If stack is empty, no matching opening symbol
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                // Check for matching pairs
                if ((ch == ')' && last != '(') ||
                    (ch == '}' && last != '{') ||
                    (ch == ']' && last != '[')) {
                    return false;
                }
            }
        }

        // If stack is not empty, there are unmatched opening symbols
        return stack.isEmpty();
    }
}

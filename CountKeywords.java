import java.util.*;
import java.io.*;

public class CountKeywords {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }

        input.close();
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false, and null
        String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package",
                "private", "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void",
                "volatile", "while", "true", "false", "null"};

        Set<String> keywordSet =
                new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        Scanner input = new Scanner(file);
        boolean inBlockComment = false;

        try {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();

                // Skip line comments and block comments
                if (line.startsWith("//")) {
                    continue;
                }

                // Handling block comments
                if (line.startsWith("/*")) {
                    inBlockComment = true;
                }
                if (inBlockComment) {
                    if (line.endsWith("*/")) {
                        inBlockComment = false;
                    }
                    continue;
                }

                // Ignore lines within block comments
                if (inBlockComment) {
                    continue;
                }

                // Split the line into words and check for keywords
                String[] words = line.split("\\s+|\\W+");
                boolean inString = false;

                for (String word : words) {
                    // Skip words inside string literals
                    if (word.contains("\"")) {
                        inString = !inString;
                    }
                    if (!inString && keywordSet.contains(word)) {
                        count++;
                    }
                }
            }
        } finally {
            input.close();
        }

        return count;
    }
}

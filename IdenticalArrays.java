import java.util.Scanner;

public class IdenticalArrays {

    public static boolean equals(int[][] m1, int[][] m2) {
        // Check if the outer array lengths are the same
        if (m1.length != m2.length) {
            return false;
        }
        // Iterate through each row
        for (int i = 0; i < m1.length; i++) {
            // Check if the inner array lengths are the same
            if (m1[i].length != m2[i].length) {
                return false;
            }
            // Iterate through each element in the row
            for (int j = 0; j < m1[i].length; j++) {
                // Compare each corresponding element
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        // If all elements are the same, return true
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Declare two 3x3 arrays
        int[][] list1 = new int[3][3];
        int[][] list2 = new int[3][3];

        // Prompt the user to enter the first 3x3 array
        System.out.println("Enter list1: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list1[i][j] = input.nextInt();
            }
        }

        // Prompt the user to enter the second 3x3 array
        System.out.println("Enter list2: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list2[i][j] = input.nextInt();
            }
        }

        // Check if the arrays are identical and print the result
        if (equals(list1, list2)) {
            System.out.println("The two arrays are identical");
        } else {
            System.out.println("The two arrays are not identical");
        }

        input.close();
    }
}

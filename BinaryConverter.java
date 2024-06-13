// Define the custom exception
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

// Implement the bin2Dec method
public class BinaryConverter {

    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        // Check if the string is a valid binary string
        for (char c : binaryString.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new BinaryFormatException("Invalid binary string: " + binaryString);
            }
        }

        // Convert binary string to decimal
        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                decimalValue += Math.pow(2, binaryString.length() - 1 - i);
            }
        }

        return decimalValue;
    }

    public static void main(String[] args) {
        try {
            System.out.println(bin2Dec("1101"));  // Should print 13
            System.out.println(bin2Dec("100a"));  // Should throw BinaryFormatException
        } catch (BinaryFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}

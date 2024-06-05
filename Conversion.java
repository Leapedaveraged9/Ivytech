public class Conversion {
    
    /** Convert from feet to meters */
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    /** Convert from meters to feet */
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    public static void main(String[] args) {
        // Display tables
        System.out.println("Feet   Meters   |   Meters   Feet");
        System.out.println("----------------|----------------");
        
        for (double feet = 1.0, meters = 20.0; feet <= 10.0; feet++, meters += 5.0) {
            double feetToMeters = footToMeter(feet);
            double metersToFeet = meterToFoot(meters);
            
            System.out.printf("%-7.1f%-9.3f|   %-9.1f%.3f%n", feet, feetToMeters, meters, metersToFeet);
        }
    }
}

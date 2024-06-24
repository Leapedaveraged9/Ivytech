import java.math.BigInteger;
import java.util.Scanner;

class Rational extends Number implements Comparable<Rational> {
    // Data fields for numerator and denominator
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    /** Construct a rational with default properties */
    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    /** Construct a rational with specified numerator and denominator */
    public Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = (denominator.compareTo(BigInteger.ZERO) > 0 ? BigInteger.ONE : new BigInteger("-1")).multiply(numerator).divide(gcd);
        this.denominator = denominator.abs().divide(gcd);
    }

    /** Find GCD of two numbers */
    private static BigInteger gcd(BigInteger n, BigInteger d) {
        return n.gcd(d);
    }

    /** Return numerator */
    public BigInteger getNumerator() {
        return numerator;
    }

    /** Return denominator */
    public BigInteger getDenominator() {
        return denominator;
    }

    /** Add a rational number to this rational */
    public Rational add(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public Rational subtract(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Multiply a rational number by this rational */
    public Rational multiply(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Divide a rational number by this rational */
    public Rational divide(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.getNumerator());
        return new Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator.toString();
        else
            return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object other) {
        if ((this.subtract((Rational)(other))).getNumerator().equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    @Override
    public int intValue() {
        return doubleToBigInteger().intValue();
    }

    @Override
    public float floatValue() {
        return doubleToBigInteger().floatValue();
    }

    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public long longValue() {
        return doubleToBigInteger().longValue();
    }

    private Double doubleToBigInteger() {
        return Double.valueOf(numerator.doubleValue() / denominator.doubleValue());
    }

    @Override
    public int compareTo(Rational o) {
        BigInteger thisVal = this.numerator.multiply(o.getDenominator());
        BigInteger otherVal = o.getNumerator().multiply(this.denominator);
        return thisVal.compareTo(otherVal);
    }
}

public class TestRational {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first rational number (format: numerator/denominator): ");
        String[] firstRational = input.nextLine().split("/");
        BigInteger numerator1 = new BigInteger(firstRational[0].trim());
        BigInteger denominator1 = new BigInteger(firstRational[1].trim());

        System.out.print("Enter the second rational number (format: numerator/denominator): ");
        String[] secondRational = input.nextLine().split("/");
        BigInteger numerator2 = new BigInteger(secondRational[0].trim());
        BigInteger denominator2 = new BigInteger(secondRational[1].trim());

        Rational r1 = new Rational(numerator1, denominator1);
        Rational r2 = new Rational(numerator2, denominator2);

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());

        input.close();
    }
}

public class Loan {
    private double loanAmount;
    private double annualInterestRate;
    private int numberOfYears;

    public Loan(double loanAmount, double annualInterestRate, int numberOfYears) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
    }

    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / 
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
    }

    public double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }

    // Getters and setters for loanAmount, annualInterestRate, and numberOfYears
}

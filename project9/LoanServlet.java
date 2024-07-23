import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Define the servlet and map it to /LoanServlet
@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));

        // Calculate monthly interest rate
        double monthlyInterestRate = annualInterestRate / 1200;

        // Calculate monthly payment
        double monthlyPayment = loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));

        // Calculate total payment
        double totalPayment = monthlyPayment * numberOfYears * 12;

        // Set response content type
        response.setContentType("text/html");

        // Write the response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Loan Amount: " + loanAmount + "</h2>");
        out.println("<h2>Annual Interest Rate: " + annualInterestRate + "</h2>");
        out.println("<h2>Number of Years: " + numberOfYears + "</h2>");
        out.println("<h2>Monthly Payment: " + String.format("%.2f", monthlyPayment) + "</h2>");
        out.println("<h2>Total Payment: " + String.format("%.2f", totalPayment) + "</h2>");
        out.println("</body></html>");
        out.close();
    }
}

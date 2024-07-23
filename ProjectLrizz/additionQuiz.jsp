<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Addition Quiz</title>
</head>
<body>
    <h1>Addition Quiz</h1>
    <form action="additionQuizAnswer.jsp" method="post">
        <%
            Random random = new Random();
            int numQuestions = 10;
            for (int i = 0; i < numQuestions; i++) {
                int num1 = random.nextInt(50) + 1;
                int num2 = random.nextInt(50) + 1;
                out.println(num1 + " + " + num2 + " = <input type='text' name='answer" + i + "' size='5'>");
                out.println("<input type='hidden' name='num1" + i + "' value='" + num1 + "'>");
                out.println("<input type='hidden' name='num2" + i + "' value='" + num2 + "'><br>");
            }
        %>
        <input type="submit" value="Submit">
    </form>
</body>
</html>

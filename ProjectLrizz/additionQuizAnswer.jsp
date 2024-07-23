<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results</title>
</head>
<body>
    <h1>Quiz Results</h1>
    <%
        int numQuestions = 10;
        int correctCount = 0;
        for (int i = 0; i < numQuestions; i++) {
            int num1 = Integer.parseInt(request.getParameter("num1" + i));
            int num2 = Integer.parseInt(request.getParameter("num2" + i));
            int correctAnswer = num1 + num2;
            int userAnswer = Integer.parseInt(request.getParameter("answer" + i));
            if (userAnswer == correctAnswer) {
                correctCount++;
                out.println(num1 + " + " + num2 + " = " + userAnswer + " Correct<br>");
            } else {
                out.println(num1 + " + " + num2 + " = " + userAnswer + " Wrong (Correct answer is " + correctAnswer + ")<br>");
            }
        }
        out.println("The total correct count is " + correctCount);
    %>
</body>
</html>

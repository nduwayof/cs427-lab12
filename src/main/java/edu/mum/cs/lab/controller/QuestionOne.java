package edu.mum.cs.lab.controller;

import edu.mum.cs.lab.domain.Question;
import edu.mum.cs.lab.repository.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(value = "/quiz")
public class QuestionOne extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(QuestionOne.class.getName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int score = 0;
        int question = 0;
        int userInput = 0;
        Question questionObj;
        if (session.isNew()) {
            session.setAttribute(EConstant.QUIZ, new Quiz());
            session.setAttribute(EConstant.SCORE, score);
            session.setAttribute(EConstant.QUESTION_NUMBER, question);
        } else {
            score = (int) session.getAttribute(EConstant.SCORE);
            question = (int) session.getAttribute(EConstant.QUESTION_NUMBER);
        }
        try {
            PrintWriter writer = response.getWriter();
            Quiz quiz = (Quiz) session.getAttribute(EConstant.QUIZ);
            List<Question> questionList = quiz.questionList(session.isNew());
            if(session.isNew()){
                questionObj = quiz.findQuestionByIndex(questionList, question);
            }else{
                question  = question + 1;
                questionObj = quiz.findQuestionByIndex(questionList, question);
                session.setAttribute(EConstant.QUESTION_NUMBER, question);
            }
            if (request.getParameter("answer") != null) {
                userInput = Integer.parseInt(request.getParameter("answer"));
                if (questionObj.getAnswer().getAns() == userInput) {
                    score = score + 1;
                    session.setAttribute(EConstant.SCORE, score);
                }
            }
            response.setContentType("text/html");
            if (question <= 4) {
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                        "    <title>Lab Quiz</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"/lab/resources/css/bootstrap.min.css\">\n" +
                        "    <link rel=\"stylesheet\" href=\"/lab/resources/css/custom.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"container\">");
                writer.println("<div class=\"row\">\n" +
                        "        <div class=\"card border-secondary mb-3\">\n" +
                        "            <div class=\"card-header\">610155 - Fabrice NDUWAYO</div>\n" +
                        "            <div class=\"card-body\">\n" +
                        "                <h4 class=\"card-title\">Lab 11 - Question "+question+"</h4>\n" +
                        "                <p class=\"card-text\">\n" +
                        "                    You current score:" + score + " \n" +
                        "                    <br>\n" +
                        "                    Guess the next number in the sequence " + questionObj.getQ() + "\n" +
                        "                </p>\n" +
                        "                <div>\n" +
                        "                    <form action=\"/lab/quiz\" method=\"get\">\n" +
                        "                        <div class=\"row\">\n" +
                        "                            <div class=\"col\">\n" +
                        "                                <div class=\"form-group\">\n" +
                        "                                    <label for=\"answer\">You answer:</label>\n" +
                        "                                    <input type=\"number\" id=\"answer\" class=\"form-control\" name=\"answer\" />\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "\n" +
                        "                        </div>\n" +
                        "                        <div class=\"row\">\n" +
                        "                            <div class=\"col\">\n" +
                        "                                <button type=\"submit\" class=\"btn btn-primary\">SUBMIT</button>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </form>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>");
                writer.println("</div>\n" +
                        "</body>\n" +
                        "</html>");
            } else {
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                        "    <title>Lab Quiz</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"/lab/resources/css/bootstrap.min.css\">\n" +
                        "    <link rel=\"stylesheet\" href=\"/lab/resources/css/custom.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"container\">");
                writer.println("<div class=\"container\">\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"card border-secondary mb-3\">\n" +
                        "            <div class=\"card-header\">610155 - Fabrice NDUWAYO</div>\n" +
                        "            <div class=\"card-body\">\n" +
                        "                <h4 class=\"card-title\">Lab 11 - Online Quiz</h4>\n" +
                        "                <p class=\"card-text\">\n" +
                        "                    Your current score is " + score + " .\n" +
                        "                    \n" +
                        "                </p> \n" +
                        "                <p> \n" +
                        "                    You have completed the number quiz, with a score of " + score + " out of 5.\n" +
                        "                </p> \n" +
                        "                    <a href=\"/lab/quiz\" class=\"btn btn-primary\">\n" +
                        "                        RESTART THE QUIZ\n" +
                        "                    </a>\n" +
                        "                </p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>");
                writer.println("</div>\n" +
                        "</body>\n" +
                        "</html>");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ">>>>>>>>>>>>>>>>>>> "+ex.getMessage());
            LOGGER.log(Level.INFO, "Checkout the exception in console");

        }

    }
}

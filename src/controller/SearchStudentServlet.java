package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Student;
import service.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search-student")
public class SearchStudentServlet extends HttpServlet {

    private StudentService service =
            new StudentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        List<Student> results =
                new ArrayList<>();

        Student usnStudent =
                service.getByUSN(keyword);

        if(usnStudent != null){
            results.add(usnStudent);
        }

        Student tempStudent =
                service.getByTempRoll(keyword);

        if(tempStudent != null &&
           !results.contains(tempStudent)){
            results.add(tempStudent);
        }

        results.addAll(
                service.searchByName(keyword)
        );

        request.setAttribute(
                "students",
                results
        );

        request.getRequestDispatcher(
                "/jsp/students.jsp"
        ).forward(request,response);
    }
}
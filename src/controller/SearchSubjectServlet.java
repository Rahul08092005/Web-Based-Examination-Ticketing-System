package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import model.Subject;
import service.SubjectService;

import java.io.IOException;
import java.util.List;

@WebServlet("/search-subject")
public class SearchSubjectServlet extends HttpServlet {

    private SubjectService service =
            new SubjectService();

    @Override
    protected void doGet(

            HttpServletRequest request,

            HttpServletResponse response)

            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        // SubjectService.searchSubjects now guards against a null/blank
        // keyword internally (falls back to returning all subjects),
        // so a bare GET to /search-subject no longer throws an NPE.
        List<Subject> subjects =
                service.searchSubjects(keyword);

        request.setAttribute(
                "subjects",
                subjects);

        request.getRequestDispatcher(
                "/jsp/subjects.jsp")
                .forward(request,response);

    }

}

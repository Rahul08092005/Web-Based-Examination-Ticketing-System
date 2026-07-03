package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Subject;
import dao.SubjectDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {

    private SubjectDao dao = new SubjectDao();

    @Override
   protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Subject> subjects =
                dao.getAllSubjects();

        request.setAttribute(
                "subjects",
                subjects
        );

        request.getRequestDispatcher(
                "/jsp/subjects.jsp"
        ).forward(request,response);
    }

}
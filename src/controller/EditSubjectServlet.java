package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import model.Subject;
import service.SubjectService;

import java.io.IOException;

@WebServlet("/edit-subject")
public class EditSubjectServlet extends HttpServlet {

    private SubjectService service =
            new SubjectService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id"));

        String code =
                request.getParameter("code");

        String name =
                request.getParameter("name");

        int semester =
                Integer.parseInt(
                        request.getParameter("semester"));

        String category =
                request.getParameter("category");

        Subject s =
                new Subject(
                        id,
                        code,
                        name,
                        semester,
                        category
                );

        service.updateSubject(s);

        response.sendRedirect(
                request.getContextPath()
                        + "/subjects");
    }

}

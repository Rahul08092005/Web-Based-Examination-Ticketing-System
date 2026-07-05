package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.SubjectService;

import java.io.IOException;

@WebServlet("/add-subject")
public class AddSubjectServlet extends HttpServlet {

    private SubjectService service =
            new SubjectService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String code =
                request.getParameter("code");

        String name =
                request.getParameter("name");

        int semester =
                Integer.parseInt(
                        request.getParameter("semester")
                );

        String category =
                request.getParameter("category");

        service.addSubject(
                code,
                name,
                semester,
                category
        );

        response.sendRedirect(
                request.getContextPath()
                        + "/subjects"
        );

    }

}

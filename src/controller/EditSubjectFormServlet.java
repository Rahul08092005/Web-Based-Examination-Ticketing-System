package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Subject;
import service.SubjectService;

import java.io.IOException;

// Loads one subject by id and forwards to the pre-filled edit form.
// This is the servlet both the "Edit" icon in subjects.jsp and
// select-subject.jsp's "Continue Editing" button should point to.
@WebServlet("/edit-subject-form")
public class EditSubjectFormServlet extends HttpServlet {

    private SubjectService service =
            new SubjectService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id"));

        Subject subject =
                service.getSubjectById(id);

        if (subject == null) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/subjects");
            return;
        }

        request.setAttribute("subject", subject);

        request.getRequestDispatcher(
                "/jsp/edit-subject-form.jsp")
                .forward(request, response);

    }

}

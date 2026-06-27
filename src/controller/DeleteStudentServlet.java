package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.StudentService;

import java.io.IOException;

@WebServlet("/delete-student")
public class DeleteStudentServlet
        extends HttpServlet {

    private StudentService service =
            new StudentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        service.deleteStudent(id);

        response.sendRedirect(
                request.getContextPath()
                        + "/students"
        );
    }
}
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import model.Student;
import service.StudentService;

import java.io.IOException;

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {

    private StudentService service =
            new StudentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        Student student =
                service.getStudentById(id);

        request.setAttribute(
                "student",
                student
        );

        request.getRequestDispatcher(
                "/jsp/edit-student.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        Student student =
                new Student(
                        id,
                        request.getParameter("name"),
                        request.getParameter("branch"),
                        request.getParameter("usn"),
                        null,
                        Integer.parseInt(
                                request.getParameter("semester")
                        ),
                        request.getParameter("department"),
                        false
                );

        service.updateStudent(student);

        response.sendRedirect(
                request.getContextPath()
                        + "/students"
        );
    }
}
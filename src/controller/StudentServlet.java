package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Student;
import service.StudentService;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService =
            new StudentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students =
                studentService.getAllStudents();

        request.setAttribute(
                "students",
                students
        );

        request.getRequestDispatcher(
                "/jsp/students.jsp"
        ).forward(request, response);
    }
}
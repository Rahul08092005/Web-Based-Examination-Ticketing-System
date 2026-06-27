package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import model.Student;
import service.StudentService;

import java.io.IOException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
        
    private StudentService service =
            new StudentService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("name");

        String usn =
                request.getParameter("usn");

        String branch =
                request.getParameter("branch");

        int semester =
                Integer.parseInt(
                        request.getParameter(
                                "semester"));

        String department =
                request.getParameter(
                        "department");

        // Temporary Debugging
        System.out.println("Name = " + name);
        System.out.println("USN = " + usn);
        System.out.println("Branch = " + branch);
        System.out.println("Semester = " + semester);
        System.out.println("Department = " + department);

        Student student =
                new Student(
                        0,
                        name,
                        branch,
                        usn,
                        null,
                        semester,
                        department,
                        false
                );

        service.addStudent(student);

        response.sendRedirect(
                request.getContextPath()
                        + "/students"
        );
    }
    
}
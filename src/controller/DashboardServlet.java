package controller;

import dao.StudentDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        StudentDao studentDao = new StudentDao();

        request.setAttribute(
                "studentCount",
                studentDao.getAllStudents().size()
        );

        request.getRequestDispatcher(
                "/jsp/admin-dashboard.jsp"
        ).forward(request, response);
    }
}
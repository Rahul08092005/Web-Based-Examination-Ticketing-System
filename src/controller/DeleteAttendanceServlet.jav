package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.AttendanceService;

import java.io.IOException;

@WebServlet("/delete-attendance")
public class DeleteAttendanceServlet extends HttpServlet {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        attendanceService.deleteAttendance(id);

        response.sendRedirect(request.getContextPath() + "/attendance");
    }
}

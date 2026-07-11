package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.AttendanceService;

import java.io.IOException;

@WebServlet("/approve-condonation")
public class ApproveCondonationServlet extends HttpServlet {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        attendanceService.approveCondonation(studentId, subjectId, "Approved via web");

        response.sendRedirect(request.getContextPath() + "/attendance");
    }
}

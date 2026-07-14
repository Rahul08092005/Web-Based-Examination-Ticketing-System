package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.AttendanceRecord;
import service.AttendanceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<AttendanceRecord> records =
                attendanceService.getAllAttendanceWithDetails();

        request.setAttribute("records", records);

        request.getRequestDispatcher(
                "/jsp/attendance.jsp"
        ).forward(request, response);
    }
}

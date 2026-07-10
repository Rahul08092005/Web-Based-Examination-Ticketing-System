package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.AttendanceService;

import java.io.IOException;

@WebServlet("/add-attendance")
public class AddAttendanceServlet extends HttpServlet {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String usn = request.getParameter("usn");
        String subjectCode = request.getParameter("subjectCode");
        String category = request.getParameter("category"); // "IPCC" or "OTHER"

        Double theory = null;
        Double lab = null;
        Double overall = null;

        if ("IPCC".equals(category)) {
            theory = parseOrNull(request.getParameter("theory"));
            lab = parseOrNull(request.getParameter("lab"));
        } else {
            overall = parseOrNull(request.getParameter("overall"));
        }

        attendanceService.enterAttendance(usn, subjectCode, theory, lab, overall);

        response.sendRedirect(request.getContextPath() + "/attendance");
    }

    private Double parseOrNull(String val) {
        if (val == null || val.trim().isEmpty()) return null;
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return null;
        }
    }
}

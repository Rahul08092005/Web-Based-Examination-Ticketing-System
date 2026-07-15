package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.AttendanceService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet("/import-attendance")
@MultipartConfig
public class ImportAttendanceServlet extends HttpServlet {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("csvFile");

        String fileName = "attendance_import_" + System.currentTimeMillis() + ".csv";
        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempPath = Path.of(tempDir, fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, tempPath, StandardCopyOption.REPLACE_EXISTING);
        }

        attendanceService.importAttendanceFromCSV(tempPath.toString());

        response.sendRedirect(request.getContextPath() + "/attendance");
    }
}

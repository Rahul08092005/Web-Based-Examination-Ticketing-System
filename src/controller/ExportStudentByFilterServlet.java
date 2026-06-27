package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import service.StudentService;

import java.io.IOException;
import java.io.File;

@WebServlet("/export-students")
public class ExportStudentByFilterServlet
        extends HttpServlet {

    private StudentService service =
            new StudentService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String department =
                request.getParameter("department");

        int semester =
                Integer.parseInt(
                        request.getParameter("semester")
                );

        String exportFolder =
                getServletContext()
                        .getRealPath("/exports");

        File folder =
                new File(exportFolder);

        if(!folder.exists()) {
            folder.mkdirs();
        }

        String filePath =
                exportFolder +
                File.separator +
                department +
                "_SEM_" +
                semester +
                ".csv";

        service.exportStudentsByDeptAndSemester(
                department,
                semester,
                filePath
        );

        response.setContentType(
                "text/plain"
        );

        response.getWriter().println(
                "Export completed:\n\n" +
                filePath
        );
    }
}
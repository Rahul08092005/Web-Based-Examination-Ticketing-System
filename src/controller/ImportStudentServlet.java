package controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import service.StudentService;

import java.io.File;
import java.io.IOException;

@WebServlet("/import-students")
@MultipartConfig
public class ImportStudentServlet extends HttpServlet {

    private StudentService service =
            new StudentService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart =
                request.getPart("csvFile");

        String fileName =
                filePart.getSubmittedFileName();

        String uploadPath =
                getServletContext()
                .getRealPath("/uploads");

        File uploadDir =
                new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fullPath =
                uploadPath +
                File.separator +
                fileName;

        filePart.write(fullPath);

        service.importStudentsFromCSV(fullPath);

        response.sendRedirect(
                request.getContextPath()
                + "/students"
        );
    }
}
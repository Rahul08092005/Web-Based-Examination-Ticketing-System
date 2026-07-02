package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.StudentService;

import java.io.*;

@WebServlet("/export-students")
public class ExportStudentByFilterServlet extends HttpServlet {

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
                        request.getParameter("semester"));

        // Temporary file

        File tempFile =
                File.createTempFile(
                        department + "_SEM_" + semester,
                        ".csv"
                );

        service.exportStudentsByDeptAndSemester(
                department,
                semester,
                tempFile.getAbsolutePath()
        );

        response.setContentType("text/csv");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"" +
                        department +
                        "_SEM_" +
                        semester +
                        ".csv\""
        );

        FileInputStream fis =
                new FileInputStream(tempFile);

        OutputStream os =
                response.getOutputStream();

        byte[] buffer =
                new byte[4096];

        int bytesRead;

        while((bytesRead=fis.read(buffer))!=-1){

            os.write(buffer,0,bytesRead);

        }

        fis.close();

        os.flush();

        tempFile.delete();

    }
}
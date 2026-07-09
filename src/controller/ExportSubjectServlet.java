package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import service.SubjectService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/export-subject")
public class ExportSubjectServlet extends HttpServlet {

    private SubjectService service =
            new SubjectService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String csv = service.exportSubjectsToCSV();

        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"subjects.csv\"");

        PrintWriter out = response.getWriter();
        out.write(csv);
        out.flush();
    }

}

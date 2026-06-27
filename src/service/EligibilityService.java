package service;

import dao.InternalDao;

import java.io.FileOutputStream;
import java.sql.ResultSet;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import dao.AttendanceDao;

public class EligibilityService {

    private InternalDao internalDao = new InternalDao();
    private AttendanceDao attendanceDao = new AttendanceDao();

    // =====================================================
    // EVALUATE ELIGIBILITY FOR ONE STUDENT + SUBJECT
    // =====================================================
    public void evaluateEligibility(int studentId, int subjectId) {

        // ---------------- 1️⃣ CIE CHECK ----------------
        boolean cieEligible = internalDao.isCieEligible(studentId, subjectId);

        // ---------------- 2️⃣ ATTENDANCE CHECK ----------------
        AttendanceDao.AttendanceCondonationStatus att =
                attendanceDao.getCondonationStatus(studentId, subjectId);

        boolean attendanceEligible = false;

        if (att != null) {

            // IPCC attendance logic
            if ("IPCC".equalsIgnoreCase(att.categoryCode())) {

                Double theory = att.theoryPercent();
                Double lab = att.labPercent();

                if (theory != null && lab != null) {
                    attendanceEligible = theory >= 75 && lab >= 75;
                }

            }
            // BSC / PCC / AEC attendance logic
            else {

                Double percent = att.overallPercent();

                if (percent != null) {
                    attendanceEligible = percent >= 75;
                }
            }

            // ❗ Condonation ONLY if attendance failed
            if (!attendanceEligible && att.condonationApproved()) {
                attendanceEligible = true;
            }
        }

        // ---------------- 3️⃣ FINAL DECISION ----------------

        // Case 1: Both failed
        if (!cieEligible && !attendanceEligible) {
            internalDao.updateEligibility(
                studentId,
                subjectId,
                false,
                "CIE + Attendance not satisfied"
            );
            return;
        }

        // Case 2: CIE failed only
        if (!cieEligible) {
            internalDao.updateEligibility(
                studentId,
                subjectId,
                false,
                "CIE criteria not satisfied"
            );
            return;
        }

        // Case 3: Attendance failed only
        if (!attendanceEligible) {
            internalDao.updateEligibility(
                studentId,
                subjectId,
                false,
                "Attendance criteria not satisfied"
            );
            return;
        }

        // Case 4: Both passed
        internalDao.updateEligibility(
            studentId,
            subjectId,
            true,
            "ELIGIBLE"
        );
    }

    // =====================================================
    // BULK COMPUTE ELIGIBILITY (ADMIN OPTION)
    // =====================================================
    public void computeEligibility() {

        for (InternalDao.StudentSubjectPair pair
                : internalDao.getAllStudentSubjectPairs()) {

            evaluateEligibility(pair.studentId(), pair.subjectId());
        }

        System.out.println("✅ Eligibility computed (CIE + Attendance applied correctly)");
    }

    // =====================================================
// EXPORT ELIGIBILITY BY SUBJECT (CSV)
// =====================================================
public void exportEligibilityBySubjectToCSV(String subjectCode, String filePath) {

    try (java.io.FileWriter fw = new java.io.FileWriter(filePath)) {

        java.sql.ResultSet rs =
                internalDao.getEligibilityDataBySubject(subjectCode);

        // CSV header
        fw.write(
            "student_name,usn,subject_code,subject_name," +
            "eligible_for_see,eligibility_reason\n"
        );

        while (rs.next()) {
            fw.write(String.format(
                "%s,%s,%s,%s,%s,%s%n",
                rs.getString("student_name"),
                rs.getString("usn"),
                rs.getString("subject_code"),
                rs.getString("subject_name"),
                rs.getBoolean("eligible_for_see") ? "YES" : "NO",
                rs.getString("eligibility_reason")
            ));
        }

        System.out.println(
            "✅ Eligibility CSV exported successfully for subject: " + subjectCode
        );

    } catch (Exception e) {
        System.out.println(
            "❌ Eligibility CSV export error: " + e.getMessage()
        );
    }
}

// =====================================================
// EXPORT ELIGIBILITY (CSV)
// eligibleOnly = true  -> export only eligible
// notEligibleOnly = true -> export only not eligible
// both false -> export all
// =====================================================
public void exportEligibilityToCSV(String filePath,
                                   boolean eligibleOnly,
                                   boolean notEligibleOnly) {

    try (
        java.io.FileWriter fw = new java.io.FileWriter(filePath);
        java.sql.ResultSet rs =
            internalDao.getEligibilityData(eligibleOnly, notEligibleOnly)
    ) {

        fw.write(
            "student_name,usn,subject_code,subject_name," +
            "eligible_for_see,eligibility_reason\n"
        );

        while (rs.next()) {

            fw.write(String.format(
                "%s,%s,%s,%s,%s,%s%n",
                escapeCSV(rs.getString("student_name")),
                escapeCSV(rs.getString("usn")),
                escapeCSV(rs.getString("subject_code")),
                escapeCSV(rs.getString("subject_name")),
                rs.getBoolean("eligible_for_see") ? "YES" : "NO",
                escapeCSV(rs.getString("eligibility_reason"))
            ));
        }

        System.out.println("✅ Eligibility CSV exported successfully");

    } catch (Exception e) {
        System.out.println("❌ Eligibility CSV export error: " + e.getMessage());
    }
}

private String escapeCSV(String value) {
    if (value == null) return "";
    if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
        value = value.replace("\"", "\"\"");
        return "\"" + value + "\"";
    }
    return value;


}

// =====================================================
// EXPORT INTERNAL MARKS BY SUBJECT (CSV)
// =====================================================
public void exportInternalMarksBySubject(
        String subjectCode, String filePath) {

    try (
        java.io.FileWriter fw = new java.io.FileWriter(filePath);
        java.sql.ResultSet rs =
                internalDao.getInternalMarksBySubject(subjectCode)
    ) {

        fw.write(
            "student_name,usn,subject_code,subject_name," +
            "cie_theory,cie_lab,cie_total\n"
        );

        while (rs.next()) {
            fw.write(String.format(
                "%s,%s,%s,%s,%s,%s,%s%n",
                escapeCSV(rs.getString("student_name")),
                escapeCSV(rs.getString("usn")),
                rs.getString("subject_code"),
                escapeCSV(rs.getString("subject_name")),
                rs.getObject("cie_theory"),
                rs.getObject("cie_lab"),
                rs.getObject("cie_total")
            ));
        }

        System.out.println(
            "✅ Internal marks exported for subject: " + subjectCode
        );

    } catch (Exception e) {
        System.out.println(
            "❌ Internal marks export error: " + e.getMessage()
        );
    }
}

// =====================================================
// EXPORT INELIGIBLE STUDENTS BY SUBJECT (CSV)
// =====================================================
public void exportIneligibleStudentsBySubject(
        String subjectCode, String filePath) {

    try (
        java.io.FileWriter fw = new java.io.FileWriter(filePath);
        java.sql.ResultSet rs =
                internalDao.getIneligibleStudentsBySubject(subjectCode)
    ) {

        fw.write(
            "student_name,usn,subject_code,subject_name,reason\n"
        );

        while (rs.next()) {
            fw.write(String.format(
                "%s,%s,%s,%s,%s%n",
                escapeCSV(rs.getString("student_name")),
                escapeCSV(rs.getString("usn")),
                rs.getString("subject_code"),
                escapeCSV(rs.getString("subject_name")),
                escapeCSV(rs.getString("eligibility_reason"))
            ));
        }

        System.out.println(
            "✅ Ineligible students exported for subject: " + subjectCode
        );

    } catch (Exception e) {
        System.out.println(
            "❌ Ineligible export error: " + e.getMessage()
        );
    }
}
// =====================================================
// EXPORT ATTENDANCE NOT SATISFIED BY SUBJECT (CSV)
// =====================================================
public void exportAttendanceNotSatisfiedBySubject(
        String subjectCode, String filePath) {

    try (
        java.io.FileWriter fw = new java.io.FileWriter(filePath);
        java.sql.ResultSet rs =
                attendanceDao.getAttendanceNotSatisfiedBySubject(subjectCode)
    ) {

        fw.write(
            "student_name,usn,subject_code,subject_name,eligibility_reason\n"
        );

        while (rs.next()) {
            fw.write(String.format(
                "%s,%s,%s,%s,%s%n",
                escapeCSV(rs.getString("student_name")),
                escapeCSV(rs.getString("usn")),
                rs.getString("subject_code"),
                escapeCSV(rs.getString("subject_name")),
                escapeCSV(rs.getString("eligibility_reason"))
            ));
        }

        System.out.println(
            "✅ Attendance-defaulters exported for subject: " + subjectCode
        );

    } catch (Exception e) {
        System.out.println(
            "❌ Attendance export error: " + e.getMessage()
        );
    }
}


// =====================================================
// SUBJECT-WISE ATTENDANCE DEFAULTERS (PDF)
// =====================================================
// =====================================================
// SUBJECT-WISE ATTENDANCE DEFAULTERS (PDF)
// Uses FINAL eligibility result (works for IPCC & PCC)
// =====================================================
public void generateAttendanceDefaultersPDF(
        String subjectCode, String outputFile) {

    try {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        // -------- TITLE --------
        Paragraph title = new Paragraph(
                "Attendance Defaulters Report\nSubject Code: " + subjectCode + "\n\n",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)
        );
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // -------- TABLE --------
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 3, 3, 6});

        addPdfHeader(table, "Student Name");
        addPdfHeader(table, "USN");
        addPdfHeader(table, "Subject");
        addPdfHeader(table, "Reason");

        // ✅ DECLARE rs PROPERLY HERE
        ResultSet rs =
                internalDao.getAttendanceDefaultersBySubject(subjectCode);

        while (rs.next()) {
            table.addCell(rs.getString("student_name"));
            table.addCell(rs.getString("usn"));
            table.addCell(rs.getString("subject_code"));
            table.addCell(rs.getString("eligibility_reason"));
        }

        document.add(table);
        document.close();

        System.out.println("✅ Attendance defaulters PDF generated");

    } catch (Exception e) {
        System.out.println("❌ Attendance defaulters PDF error: " + e.getMessage());
    }
}

// =====================================================
// SUBJECT-WISE INELIGIBLE STUDENTS (PDF)
// =====================================================
public void generateIneligibleStudentsPDF(
        String subjectCode, String outputFile) {

    try {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        Paragraph title = new Paragraph(
                "Ineligible Students Report\nSubject Code: " + subjectCode + "\n\n",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)
        );
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 3, 3, 4, 4});

        addPdfHeader(table, "Student Name");
        addPdfHeader(table, "USN");
        addPdfHeader(table, "Subject");
        addPdfHeader(table, "Status");
        addPdfHeader(table, "Reason");

        ResultSet rs =
                internalDao.getIneligibleStudentsBySubject(subjectCode);

        while (rs.next()) {
            table.addCell(rs.getString("student_name"));
            table.addCell(rs.getString("usn"));
            table.addCell(rs.getString("subject_code"));
            table.addCell("NOT ELIGIBLE");
            table.addCell(rs.getString("eligibility_reason"));
        }

        document.add(table);
        document.close();

        System.out.println("✅ Ineligible students PDF generated");

    } catch (Exception e) {
        System.out.println("❌ Ineligible students PDF error: " + e.getMessage());
    }
}

private void addPdfHeader(PdfPTable table, String text) {
    PdfPCell cell = new PdfPCell(new Phrase(
            text,
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)
    ));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
}

}


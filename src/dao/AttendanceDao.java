package dao;

import java.sql.*;


public class AttendanceDao {

    // =========================
    // RECORD USED BY ELIGIBILITY
    // =========================
    public static record AttendanceCondonationStatus(
        String categoryCode,
        Double theoryPercent,
        Double labPercent,
        Double overallPercent,
        boolean condonationApproved
    ) {}

    // =========================
    // FETCH ATTENDANCE + CATEGORY
    // =========================
    public AttendanceCondonationStatus getCondonationStatus(int studentId, int subjectId) {

        String sql = """
            SELECT
                sub.category_code,
                att.theory_attendance_percent,
                att.lab_attendance_percent,
                att.attendance_percent,
                att.condonation_approved
            FROM attendance att
            JOIN subjects sub ON att.subject_id = sub.id
            WHERE att.student_id = ? AND att.subject_id = ?
        """;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new AttendanceCondonationStatus(
                    rs.getString("category_code"),
                    (Double) rs.getObject("theory_attendance_percent"),
                    (Double) rs.getObject("lab_attendance_percent"),
                    (Double) rs.getObject("attendance_percent"),
                    rs.getBoolean("condonation_approved")
                );
            }

        } catch (Exception e) {
            System.out.println("Condonation fetch error: " + e.getMessage());
        }

        return null;
    }

    public void upsertAttendance(model.Attendance a) {

    String sql = """
        INSERT INTO attendance
        (student_id, subject_id,
         theory_attendance_percent,
         lab_attendance_percent,
         attendance_percent,
         condonation_approved)
        VALUES (?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            theory_attendance_percent = VALUES(theory_attendance_percent),
            lab_attendance_percent = VALUES(lab_attendance_percent),
            attendance_percent = VALUES(attendance_percent),
            condonation_approved = VALUES(condonation_approved)
    """;

    try (Connection conn = DBConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, a.getStudentId());
        ps.setInt(2, a.getSubjectId());
        ps.setObject(3, a.getTheoryAttendance());
        ps.setObject(4, a.getLabAttendance());
        ps.setObject(5, a.getOverallAttendance());
        ps.setBoolean(6, a.isCondonationApproved());

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Attendance upsert error: " + e.getMessage());
    }
}

public ResultSet getAttendanceBySubjectCode(String subjectCode) throws Exception {

    String sql = """
        SELECT
                st.full_name AS student_name,
                st.usn,
                sub.subject_code,
                sub.subject_name,
                sub.category_code,              -- ✅ ADD THIS
                att.theory_attendance_percent,
                att.lab_attendance_percent,
                att.attendance_percent,
                att.condonation_approved

        FROM attendance att
        JOIN students st ON att.student_id = st.id
        JOIN subjects sub ON att.subject_id = sub.id
        WHERE sub.subject_code = ?
    """;

    Connection conn = DBConnectionManager.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, subjectCode);

    return ps.executeQuery();
}

// =====================================================
// EXPORT ATTENDANCE NOT SATISFIED BY SUBJECT CODE
// =====================================================
public ResultSet getAttendanceNotSatisfiedBySubject(String subjectCode)
        throws Exception {

    String sql = """
        SELECT
            st.full_name AS student_name,
            st.usn,
            sub.subject_code,
            sub.subject_name,
            ia.eligibility_reason
        FROM internal_assessments ia
        JOIN students st ON ia.student_id = st.id
        JOIN subjects sub ON ia.subject_id = sub.id
        WHERE sub.subject_code = ?
          AND ia.eligible_for_see = FALSE
          AND ia.eligibility_reason LIKE '%Attendance%'
        ORDER BY st.usn ASC
    """;

    Connection conn = DBConnectionManager.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, subjectCode);

    return ps.executeQuery();
}
public void approveCondonation(
        int studentId,
        int subjectId,
        String reason) {

    String sql = """
        UPDATE attendance
        SET condonation_approved = TRUE
        WHERE student_id = ?
          AND subject_id = ?
    """;

    try (
        Connection conn =
            DBConnectionManager.getConnection();

        PreparedStatement ps =
            conn.prepareStatement(sql)
    ) {

        ps.setInt(1, studentId);
        ps.setInt(2, subjectId);

        ps.executeUpdate();

    } catch (Exception e) {

        System.out.println(
            "Condonation approval error: "
            + e.getMessage()
        );
    }
}


}
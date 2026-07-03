package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Subject;

public class SubjectDao {

    // INSERT SUBJECT
    public void insertSubject(Subject s) {

        String sql = """
            INSERT INTO subjects
            (subject_code, subject_name, semester, category_code)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getCode());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getSemester());
            ps.setString(4, s.getCategoryCode());

            ps.executeUpdate();
            System.out.println("✅ Subject added successfully");

        } catch (Exception e) {
            System.out.println("Insert Subject Error: " + e.getMessage());
        }
    }

    // LIST SUBJECTS
    public List<Subject> getAllSubjects() {

        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects ORDER BY id";

        try (Connection conn = DBConnectionManager.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Subject(
                    rs.getInt("id"),
                    rs.getString("subject_code"),
                    rs.getString("subject_name"),
                    rs.getInt("semester"),
                    rs.getString("category_code")
                ));
            }

        } catch (Exception e) {
            System.out.println("Fetch Subjects Error: " + e.getMessage());
        }

        return list;
    }

    // CHECK SUBJECT EXISTS
    public boolean existsByCode(String code) {

        String sql = "SELECT id FROM subjects WHERE subject_code=?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            return ps.executeQuery().next();

        } catch (Exception e) {
            System.out.println("Exists check error");
        }
        return false;
    }

    

    //NEW Methood 
    public boolean isComponentAllowed(int subjectId, String component) {

    String sql = """
        SELECT cc.has_theory, cc.has_lab
        FROM subjects s
        JOIN course_categories cc
          ON s.category_code = cc.category_code
        WHERE s.id = ?
    """;

    try (Connection conn = DBConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, subjectId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            if (component.equalsIgnoreCase("THEORY"))
                return rs.getBoolean("has_theory");

            if (component.equalsIgnoreCase("LAB"))
                return rs.getBoolean("has_lab");
        }

    } catch (Exception e) {
        System.out.println("Component validation error");
    }

    return false;
}
public int getSubjectIdByCode(String subjectCode) {

    String sql = "SELECT id FROM subjects WHERE subject_code = ?";

    try (Connection conn = DBConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, subjectCode);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

    } catch (Exception e) {
        System.out.println("Subject ID lookup error: " + e.getMessage());
    }

    return -1; // not found
}
public Subject getSubjectById(int id) {

    String sql = "SELECT * FROM subjects WHERE id = ?";

    try (Connection conn = DBConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Subject(
                rs.getInt("id"),
                rs.getString("subject_code"),
                rs.getString("subject_name"),
                rs.getInt("semester"),
                rs.getString("category_code")
            );
        }

    } catch (Exception e) {
        System.out.println("Fetch subject by ID error: " + e.getMessage());
    }

    return null;
}

public Subject getByCode(String subjectCode) {

    Subject subject = null;

    String sql = "SELECT * FROM subjects WHERE subject_code = ?";

    try (Connection con = DBConnectionManager.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, subjectCode);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            subject = new Subject(
                rs.getInt("id"),
                rs.getString("subject_code"),
                rs.getString("subject_name"),
                rs.getInt("semester"),
                rs.getString("category_code")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return subject;
}




public List<Subject> getSubjectsBySemester(int semester) {

    List<Subject> list = new ArrayList<>();

    String sql = "SELECT * FROM subjects WHERE semester = ?";

    try (Connection conn = DBConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, semester);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Subject(
                rs.getInt("id"),
                rs.getString("subject_code"),
                rs.getString("subject_name"),
                rs.getInt("semester"),
                rs.getString("category_code")
            ));
        }

    } catch (Exception e) {
        System.out.println("Fetch subjects by semester error");
    }

    return list;
}
public int getSubjectCount() {

    String sql = "SELECT COUNT(*) FROM subjects";

    try(Connection conn =
            DBConnectionManager.getConnection();
        PreparedStatement ps =
            conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return rs.getInt(1);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return 0;
}
public void updateSubject(Subject s) {

   String sql = """
       UPDATE subjects
       SET subject_code=?,
           subject_name=?,
           semester=?,
           category_code=?
       WHERE id=?
   """;

   try(Connection conn =
           DBConnectionManager.getConnection();
       PreparedStatement ps =
           conn.prepareStatement(sql)){

       ps.setString(1,s.getCode());
       ps.setString(2,s.getName());
       ps.setInt(3,s.getSemester());
       ps.setString(4,s.getCategoryCode());
       ps.setInt(5,s.getId());

       ps.executeUpdate();

   }catch(Exception e){
       System.out.println(e.getMessage());
   }

}

}

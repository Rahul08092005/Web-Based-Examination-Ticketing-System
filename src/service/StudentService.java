package service;

import dao.StudentDao;
import model.Student;
import util.CSVUtils;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private StudentDao dao = new StudentDao();

    public void addStudent(Student s) {
        dao.insertStudent(s);
    }

    public void updateStudent(Student s) {
        dao.updateStudent(s);
    }

    public void listStudents() {
        List<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n=== STUDENT LIST ===");
        for (Student s : students) {
            System.out.println(
                s.getId() + " | " +
                s.getFullName() + " | " +
                s.getBranch() + " | " +        // ✅ branch shown
                (s.getUsn() == null ? "N/A" : s.getUsn()) + " | " +
                (s.getTempRoll() == null ? "N/A" : s.getTempRoll()) + " | " +
                "Sem " + s.getSemester() + " | " +
                s.getDepartment()
            );
        }
        System.out.println("====================");
    }

    public Student getStudentById(int id) {
        return dao.getStudentById(id);
    }

    public Student getByUSN(String usn) {
        return dao.getByUSN(usn);
    }

    public Student getByTempRoll(String roll) {
        return dao.getByTempRoll(roll);
    }

    public List<Student> searchByName(String name) {
        return dao.searchByName(name);
    }

    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    // CSV IMPORT
    public void importStudentsFromCSV(String path) {

    System.out.println("[DEBUG] Importing CSV from: " + path);

    List<String[]> rows = CSVUtils.readCSV(path);

    for (String[] row : rows) {
        try {
            // DEBUG (keep this once to verify)
            // System.out.println(Arrays.toString(row));

            String fullName = row[0].trim();
            String branch = row[1].trim();
            String usn = row[2].trim().isEmpty() ? null : row[2].trim();
            String temp = row[3].trim().isEmpty() ? null : row[3].trim();

            if (row[4].trim().isEmpty()) {
                throw new Exception("Semester is empty");
            }

            int semester = Integer.parseInt(row[4].trim());
            String department = row[5].trim();

            Student s = new Student(
                0,
                fullName,
                branch,
                usn,
                temp,
                semester,
                department,
                false
            );

            dao.insertStudent(s);

        } catch (Exception e) {
            System.out.println("Error importing row: " + e.getMessage());
        }
    }

    System.out.println("CSV Import Completed.");
}


    // CSV EXPORT
    public void exportStudentsToCSV(String path) {
        List<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students to export!");
            return;
        }

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{
            "id", "full_name", "branch", "usn",
            "temp_roll_no", "semester", "department"
        });

        for (Student s : students) {
            rows.add(new String[]{
                String.valueOf(s.getId()),
                s.getFullName(),
                s.getBranch(),
                s.getUsn() == null ? "" : s.getUsn(),
                s.getTempRoll() == null ? "" : s.getTempRoll(),
                String.valueOf(s.getSemester()),
                s.getDepartment()
            });
        }

        CSVWriter.writeCSV(path, rows);
    }

    public void addStudent(String name, String usn, String temp, int sem, String dept) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Student name cannot be empty");
            return;
        }

        if (sem <= 0) {
            System.out.println("❌ Invalid semester");
            return;
        }

        usn = (usn != null && usn.trim().isEmpty()) ? null : usn;
        temp = (temp != null && temp.trim().isEmpty()) ? null : temp;

        Student s = new Student(
            0,
            name.trim(),
            "CSE",       // default branch for manual entry
            usn,
            temp,
            sem,
            dept,
            false
        );

        dao.insertStudent(s);
    }

    // EXPORT students by department & semester
    public void exportStudentsByDeptAndSemester(
            String department, int semester, String filePath) {

        List<Student> students =
            dao.getStudentsByDeptAndSemester(department, semester);

        if (students.isEmpty()) {
            System.out.println("❌ No students found for given criteria");
            return;
        }

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{
            "id", "full_name", "branch", "usn",
            "temp_roll_no", "semester", "department"
        });

        for (Student s : students) {
            rows.add(new String[]{
                String.valueOf(s.getId()),
                s.getFullName(),
                s.getBranch(),
                s.getUsn() == null ? "" : s.getUsn(),
                s.getTempRoll() == null ? "" : s.getTempRoll(),
                String.valueOf(s.getSemester()),
                s.getDepartment()
            });
        }

        CSVWriter.writeCSV(filePath, rows);

        System.out.println(
            "✅ Students exported for " + department +
            " Sem " + semester + " (sorted by USN)"
        );
    }
    public void deleteStudent(int id) {

    dao.deleteStudent(id);

}
}

package model;

public class AttendanceRecord {

    private int id;
    private int studentId;
    private int subjectId;

    private String studentName;
    private String usn;
    private String branch;          // full branch name (students.branch)
    private String department;      // branch code (students.department)
    private int studentSemester;

    private String subjectCode;
    private String subjectName;
    private String categoryCode;

    private Double theoryPercent;
    private Double labPercent;
    private Double overallPercent;

    private boolean condonationApproved;

    public AttendanceRecord(
            int id,
            int studentId,
            int subjectId,
            String studentName,
            String usn,
            String branch,
            String department,
            int studentSemester,
            String subjectCode,
            String subjectName,
            String categoryCode,
            Double theoryPercent,
            Double labPercent,
            Double overallPercent,
            boolean condonationApproved
    ) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.studentName = studentName;
        this.usn = usn;
        this.branch = branch;
        this.department = department;
        this.studentSemester = studentSemester;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.categoryCode = categoryCode;
        this.theoryPercent = theoryPercent;
        this.labPercent = labPercent;
        this.overallPercent = overallPercent;
        this.condonationApproved = condonationApproved;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getSubjectId() { return subjectId; }
    public String getStudentName() { return studentName; }
    public String getUsn() { return usn; }
    public String getBranch() { return branch; }
    public String getDepartment() { return department; }
    public int getStudentSemester() { return studentSemester; }
    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public String getCategoryCode() { return categoryCode; }
    public Double getTheoryPercent() { return theoryPercent; }
    public Double getLabPercent() { return labPercent; }
    public Double getOverallPercent() { return overallPercent; }
    public boolean isCondonationApproved() { return condonationApproved; }
}

public class AcademicPerformance {
    private Student student;
    private int year;
    private String group;
    private String subject;
    private int point;
    private String fioMaster;

   public AcademicPerformance(Student student, int year, String group, String subject, int point, String fioMaster) {
        this.student = student;
        this.year = year;
        this.group = group;
        this.subject = subject;
        this.point = point;
        this.fioMaster = fioMaster;
    }

   public AcademicPerformance(Student student, String subject, int point, String fioMaster) {
        this.student = student;
        this.subject = subject;
        this.point = point;
        this.fioMaster = fioMaster;
    }

    public AcademicPerformance(Student student, int year, String group) {
        this.student = student;
        this.year = year;
        this.group = group;
    }

    public AcademicPerformance(Student student) {
        this.student = student;
    }


    @Override
    public String toString() {
        return this.student.toString();
    }

    public String getStudent() {
        return this.student.toString();
    }

    public int getIdStudent() {
        return this.student.getId();
    }

    public int getPoint() {
        return this.point;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getMaster() {
        return this.fioMaster;
    }

    public String getGroup() {
        return this.group;
    }

    public int getYear() {
        return this.year;
    }
}

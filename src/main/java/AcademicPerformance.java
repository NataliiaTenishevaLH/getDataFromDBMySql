import lombok.Data;

@Data
public class AcademicPerformance {
    private String fioStudent;
    private int year;
    private String group;
    private String subject;
    private int point;
    private String fioMaster;

    AcademicPerformance(String fioStudent, int year, String group, String subject, int point, String fioMaster) {
        this.fioStudent = fioStudent;
        this.year = year;
        this.group = group;
        this.subject = subject;
        this.point = point;
        this.fioMaster = fioMaster;
    }

    AcademicPerformance(String fioStudent, String subject, int point, String fioMaster) {
        this.fioStudent = fioStudent;
        this.subject = subject;
        this.point = point;
        this.fioMaster = fioMaster;
    }



    @Override
    public String toString() {
        return this.fioStudent;
    }

    public String getStudent() {
        return this.fioStudent;
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

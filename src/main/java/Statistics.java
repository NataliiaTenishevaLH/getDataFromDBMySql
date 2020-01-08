import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private List<AcademicPerformance> students;

    public Statistics(List<AcademicPerformance> students) {
        this.students = students;
    }

    public Set<String> getListStudents() {

        Set<String> listStudents = new TreeSet<String>();
        listStudents = this.students.stream().collect(
                Collectors.mapping(
                        p -> p.getStudent().toString(),
                        Collectors.toSet()));
        Set<String> sortedListStudents = new TreeSet<String>(listStudents);

        return (sortedListStudents);
    }

    public Double getAvarage(String nameStudent) {

        List<Integer> newList = students.stream()
                .filter(u -> u.getStudent().equals(nameStudent))
                .collect(
                        Collectors.mapping(
                                p -> p.getPoint(),
                                Collectors.toList()));

        OptionalDouble average = newList
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.getAsDouble();
    }

    public Set<String> getGroupList(String group) {
        Set<String> groupList = students.stream()
                .filter(u -> u.getGroup().equals(group))
                .collect(
                        Collectors.mapping(
                                p -> p.getStudent().toString(),
                                Collectors.toSet()));
        return groupList;
    }

    public Set<String> getListYear(int year) {
        Set<String> listYear = this.students.stream()
                .filter(u -> u.getYear() == year)
                .collect(
                        Collectors.mapping(
                                p -> p.getStudent().toString(),
                                Collectors.toSet()));
        return listYear;
    }

    public List<AcademicPerformance> getAllInfoAboutStudent(String nameStudent) {
        List<AcademicPerformance> studentInfo = new ArrayList<>();
        studentInfo = students.stream()
                .filter(u -> u.getStudent().equals(nameStudent))
                .collect(
                        Collectors.mapping(
                                p -> new AcademicPerformance(new Student(p.getIdStudent(),p.getStudent()), p.getSubject(), p.getPoint(), p.getMaster()),
                                Collectors.toList()));
        return studentInfo;
    }
}

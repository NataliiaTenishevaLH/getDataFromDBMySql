import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
      List<AcademicPerformance> studentList = new ArrayList<>();
      List<AcademicPerformance> students = new ArrayList<>();

       try {
           DataFromDB dataFromDB = new DataFromDB();
           students = dataFromDB.getDataFromDB();
       } catch(Exception e){

       }

       Statistics statistics = new Statistics(students);

        //1. Получить список всех студентов
        Set<String> sortedListStudents = statistics.getListStudents();
        System.out.println("List of students: ");
        Iterator iterator = sortedListStudents.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

        //2. Список студентов обучающихся в одной группе
        Set<String> groupList = statistics.getGroupList("group1");
        System.out.println("List of group group1: ");
        System.out.println(groupList.toString());

        //3. Список студентов одного года поступления
        Set<String> groupYear = statistics.getListYear(2019);
        System.out.println("List of 2019: ");
        System.out.println(groupYear.toString());

        //4. Все предметы студента с оценками и преподавателями
        List<AcademicPerformance> studentAllInfo = new ArrayList<>();
        String nameStudent = "student1";
        studentAllInfo = statistics.getAllInfoAboutStudent(nameStudent);
        System.out.println("Student " + nameStudent + " has:");

        for (AcademicPerformance record : studentAllInfo) {
            StringBuilder allInfo = new StringBuilder();
            allInfo.append(record.getSubject()).append(" mark: ").append(record.getPoint()).append(" master ").append(record.getMaster());
            System.out.println(allInfo.toString());
        }

        //5. Средний бал студента
        System.out.println("average point fot student1: " + statistics.getAvarage("student1").toString());
    }
}

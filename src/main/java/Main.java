import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        List<AcademicPerformance> students = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbUniversity"
                + "?useSSL=false&user=root&password=root&characterEncoding=UTF-8");
        Statement statement = connection.createStatement();

        String queryText = "SELECT st.ID, st.FIO, st.YEAR, gr.NAME, l.NAME, p.POINT, m.FIO  from students st  " +
                "INNER JOIN student_groups gr ON st.ID_GROUP = gr.ID " +
                "LEFT JOIN points p ON st.ID = p.ID_STUDENT " +
                "INNER  JOIN lessons l ON p.ID_LESSON = l.ID " +
                "LEFT JOIN master m ON l.ID_MASTER = m.ID";


        ResultSet resultSet = statement.executeQuery(queryText);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsnumber = rsmd.getColumnCount();


        while (resultSet.next()) {
            students.add(new AcademicPerformance(
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }

        Statistics statistics = new Statistics(students);

        //1. Получить список всех студентов
        Set<String> sortedListStudents = statistics.getListStudents();
        System.out.println("List of students: ");
        System.out.println(sortedListStudents.toString());

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

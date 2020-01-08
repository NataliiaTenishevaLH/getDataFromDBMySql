import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class DataFromDB {

        public List<AcademicPerformance> getDataFromDB() throws  ClassNotFoundException, SQLException, Exception{

        List<AcademicPerformance> students = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbUniversity"
                + "?useSSL=false&user=root&password=root&characterEncoding=UTF-8");
        Statement statement = connection.createStatement();

        String queryText = "SELECT st.ID, st.FIO, st.YEAR, gr.NAME, l.NAME, p.POINT, m.FIO  from students st  " +
                "INNER JOIN student_groups gr ON st.ID_GROUP = gr.ID " +
                "LEFT JOIN points p ON st.ID = p.ID_STUDENT " +
                "LEFT  JOIN lessons l ON p.ID_LESSON = l.ID " +
                "LEFT JOIN master m ON l.ID_MASTER = m.ID WHERE st.DELETED = false";

        ResultSet resultSet = statement.executeQuery(queryText);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsnumber = rsmd.getColumnCount();

        Map<Integer,Student> studentList = new TreeMap<Integer, Student>();
        studentList = getStudentList();

        while (resultSet.next()) {
            students.add(new AcademicPerformance(
                    studentList.get(resultSet.getInt(1)),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }

        return students;
    };

    public Map<Integer,Student>  getStudentList() throws  ClassNotFoundException, SQLException, Exception{

        Map<Integer,Student> studentList = new TreeMap<Integer, Student>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbUniversity"
                + "?useSSL=false&user=root&password=root&characterEncoding=UTF-8");

        Statement statement = connection.createStatement();
        String queryText = "SELECT st.ID, st.FIO from students st WHERE st.DELETED = false";
        ResultSet resultSet = statement.executeQuery(queryText);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsnumber = rsmd.getColumnCount();

        while (resultSet.next()) {
            studentList.put(resultSet.getInt(1), new Student(resultSet.getInt(1),resultSet.getString(2)));
        }

        return studentList;
    };
   }


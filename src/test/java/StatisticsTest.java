import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class StatisticsTest {

    @Test
    public void testStatisticMethod() {

        Statistics mock = mock(Statistics.class);
        List<AcademicPerformance> students = new ArrayList<>();
        students.add(new AcademicPerformance("student1", 2019,"group1", "maths", 10, "FIO master maths"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "physics", 10, "FIO master physics"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student2", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student3", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student5", 2018,"group3", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student6", 2019,"group4", "computer science", 10, "FIO computer science"));

        Statistics statistics = new Statistics(students);
        //1 Проверяем, что метод getListStudents вернет всех студентов
        Set<String> listStudents = new TreeSet<String>();
        listStudents.add("student1");
        listStudents.add("student2");
        listStudents.add("student3");
        listStudents.add("student4");
        listStudents.add("student5");
        listStudents.add("student6");
        Set<String> sortedListStudents = new TreeSet<String>(listStudents);

        when(mock.getListStudents()).thenReturn(statistics.getListStudents());
        assertThat(mock.getListStudents(), is(listStudents));

        //2. Проверям что мето getGroupList вернет список студентов обучающихся в одной группе
        when(mock.getGroupList("group1")).thenReturn(statistics.getGroupList("group1"));
        assertThat(mock.getGroupList("group1"), containsInAnyOrder("student1", "student4"));

        //3. Список студентов одного года поступления
        when(mock.getListYear(2019)).thenReturn(statistics.getListYear(2019));
        assertThat(mock.getListYear(2019), containsInAnyOrder("student1", "student4", "student6"));

        //4. Все предметы студента с оценками и преподавателями
         List<AcademicPerformance> listStudentInfo = new ArrayList<>();
        listStudentInfo.add(new AcademicPerformance("student1","maths", 10, "FIO master math"));
        listStudentInfo.add(new AcademicPerformance("student1", "physics", 10, "FIO master physics"));
        listStudentInfo.add(new AcademicPerformance("student1", "computer science", 10, "FIO master computer science"));
        when(mock.getAllInfoAboutStudent("student1")).thenReturn(statistics.getAllInfoAboutStudent("student1"));

        //5. Средний бал студента
        when(mock.getAvarage("student1")).thenReturn(statistics.getAvarage("student1"));
        Double avarage = 10.0;
        Assertions.assertEquals(mock.getAvarage("student1"),avarage);
     }

    @RepeatedTest(3)
    void repeatedTest(TestInfo testInfo) {
        Statistics mock = mock(Statistics.class);
        List<AcademicPerformance> students = new ArrayList<>();
        students.add(new AcademicPerformance("student1", 2019,"group1", "maths", 10, "FIO master maths"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "physics", 10, "FIO master physics"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student2", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student3", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student5", 2018,"group3", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student6", 2019,"group4", "computer science", 10, "FIO computer science"));

        Statistics statistics = new Statistics(students);

        List<AcademicPerformance> listStudentInfo = new ArrayList<>();
        listStudentInfo.add(new AcademicPerformance("student1","maths", 10, "FIO master math"));
        listStudentInfo.add(new AcademicPerformance("student1", "physics", 10, "FIO master physics"));
        listStudentInfo.add(new AcademicPerformance("student1", "computer science", 10, "FIO master computer science"));
        when(mock.getAllInfoAboutStudent("student1")).thenReturn(statistics.getAllInfoAboutStudent("student1"));
    }

    @Test
    void testExpectedException() {
        Statistics mock = mock(Statistics.class);
        List<AcademicPerformance> students = new ArrayList<>();
        students.add(new AcademicPerformance("student1", 2019,"group1", "maths", 10, "FIO master maths"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "physics", 10, "FIO master physics"));
        students.add(new AcademicPerformance("student1", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student2", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student3", 2018,"group2", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student4", 2019,"group1", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student5", 2018,"group3", "computer science", 10, "FIO computer science"));
        students.add(new AcademicPerformance("student6", 2019,"group4", "computer science", 10, "FIO computer science"));

        Statistics statistics = new Statistics(students);
        //1 Проверяем, что метод getListStudents вернет всех студентов
        when(mock.getListStudents()).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
          mock.getListStudents();
        });

        //2. Проверям что мето getGroupList вернет список студентов обучающихся в одной группе
        when(mock.getGroupList("group1")).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            mock.getGroupList("group1");
        });

        //3. Список студентов одного года поступления
        when(mock.getListYear(2019)).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            mock.getListYear(2019);
        });

         //4. Все предметы студента с оценками и преподавателями
        when(mock.getAllInfoAboutStudent("student1")).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            mock.getAllInfoAboutStudent("student1");
        });

        //5. Средний бал студента
        when(mock.getAvarage("student1")).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            mock.getAvarage("student1");
        });
    }
}

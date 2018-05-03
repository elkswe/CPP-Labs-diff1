package University.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class University {
    private static final byte RANDOM_RANGE_FROM = 1;
    private static final byte RANDOM_RANGE_TO = 3;

    private ObservableList<SomeStudent> studentsList = FXCollections.observableArrayList();
    private ObservableList<SomeClass> classesList = FXCollections.observableArrayList();
    private ObservableList<Teacher> teachersList = FXCollections.observableArrayList();

    public University() {

    }

    public void addStudent(SomeStudent someStudent) {
        studentsList.add(someStudent);
    }

    public void generateSchedule() {
        int count = RANDOM_RANGE_FROM + (int) (Math.random() * RANDOM_RANGE_TO);

        if (teachersList.size() != 0) {
            classesList.clear();
            for (int i = 0; i < count; i++) {
                classesList.add(new Lecture(teachersList.get((int) (Math.random() * teachersList.size())), "SomeTheme"));
            }
        }
    }

    public void addTeacher(Teacher teacher) {
        teachersList.add(teacher);
    }

    public ObservableList<SomeStudent> getStudentsList() {
        return studentsList;
    }

    public ObservableList<SomeClass> getClassesList() {
        return classesList;
    }

    public ObservableList<Teacher> getTeachersList() {
        return teachersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(studentsList, that.studentsList) &&
                Objects.equals(classesList, that.classesList) &&
                Objects.equals(teachersList, that.teachersList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentsList, classesList, teachersList);
    }
}

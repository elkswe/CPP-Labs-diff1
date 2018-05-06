package university.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;

public class Lecture extends SomeClass {
    public static final int LECTURE_IS_GIVE = 1;
    public  static final int LECTURE_IS_REPEATED = 2;

    private String theme;
    private int isGive = 0;

    private ObservableList<SomeStudent> missingStudentsList = FXCollections.observableArrayList();

    public Lecture(String theme) {
        super();
        this.theme = theme;
    }

    public Lecture(Teacher teacher, String theme) {
        super(teacher);
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public int getIsGive() {
        return isGive;
    }

    public boolean isGive() {
        return isGive == LECTURE_IS_GIVE;
    }

    public void give() {
        if (isGive != LECTURE_IS_REPEATED) ++isGive;
    }

    /**
     * @param studentsList
     */
    public void fillMissingStudents(final ObservableList<SomeStudent> studentsList) {
        missingStudentsList.clear();
        for (SomeStudent someStudent
                : studentsList) {
            Student student = (Student) someStudent;
            if (!student.isAttendClass()) {
                missingStudentsList.add(student);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecture lecture = (Lecture) o;
        return isGive == lecture.isGive &&
                Objects.equals(theme, lecture.theme);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), theme, isGive);
    }

    @Override
    public String toString() {
        return teacher.toString() + ' ' + theme;
    }

    public void giveKnowledge(List<SomeStudent> students) {
        for (SomeStudent someStudent :
                students) {
            Student student = (Student) someStudent;
            if (student.isAttendClass()) {
                student.getKnowledge(this.subject);
            }
        }
    }

    public ObservableList<SomeStudent> getMissingStudentsList() {
        return missingStudentsList;
    }
}

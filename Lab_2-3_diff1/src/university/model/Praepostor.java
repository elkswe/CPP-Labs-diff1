package university.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Praepostor extends Student {
    private static final double CHANCE_TO_MARK = 0.25;

    private short karma;

    private ObservableList<SomeStudent> missingStudentsList = FXCollections.observableArrayList();

    public Praepostor() {
        super();
        karma = 0;
    }

    public Praepostor(int group, String secondName, String firstName, String middleName) {
        super(group, secondName, firstName, middleName);
        karma = 0;
    }

    public void markMissingStudents(ObservableList<SomeStudent> studentsList) {
        missingStudentsList.clear();
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            if (this.group == student.group && !student.isGiveClass()) {
                if (Math.random() > CHANCE_TO_MARK) {
                    missingStudentsList.add(student);
                } else {
                    ++karma;
                }
            }
        }
    }

    public boolean isMissing(SomeStudent someStudent) {
        return missingStudentsList.contains(someStudent);
    }

    public void showMissingStudents() {
        System.out.println(this.group);
        for (SomeStudent someStudent :
                missingStudentsList) {
            System.out.println(someStudent.toString());
        }
        System.out.println("\n===========================================\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Praepostor that = (Praepostor) o;
        return karma == that.karma &&
                Objects.equals(missingStudentsList, that.missingStudentsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), karma, missingStudentsList);
    }

    @Override
    public String toString() {
        return super.toString() + " - Староста";
    }

    public short getKarma() {
        return karma;
    }

    public ObservableList<SomeStudent> getMissingStudentsList() {
        return missingStudentsList;
    }
}

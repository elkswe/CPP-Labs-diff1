package university.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Praepostor extends Student {
    private static final double CHANCE_TO_MARK = 0.25;

    private short karma;

    private Journal journal;

    private ObservableList<SomeStudent> missingStudentsList
            = FXCollections.observableArrayList();

    private ObservableList<Boolean> markedMissingStudentsList
            = FXCollections.observableArrayList();

    public Praepostor() {
        super();
        karma = 0;
    }

    public Praepostor(int group,
                      String secondName,
                      String firstName,
                      String middleName) {
        super(group, secondName, firstName, middleName);
        karma = 0;
    }

    public Praepostor(int group,
                      String secondName,
                      String firstName,
                      String middleName,
                      Journal journal) {
        this(group, secondName, firstName, middleName);
        this.journal = journal;
    }

    public void markMissingStudents(ObservableList<SomeStudent> studentsList) {
        missingStudentsList.clear();
        markedMissingStudentsList.clear();
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            if (this.group == student.group) {
                boolean key = true;
                if (!student.isAttendClass()) {
                    missingStudentsList.add(student);
                    if (Math.random() < CHANCE_TO_MARK) {
                        ++karma;
                        key = false;
                    }
                    markedMissingStudentsList.add(key);
                }
                journal.markStudent(student, key);
            }
        }
    }

    public void finishMarking(Student student) {
        journal.remarkStudent(student);
        --karma;
        markedMissingStudentsList.set(
                missingStudentsList.indexOf(student),
                true);
    }

    public boolean isMissing(SomeStudent someStudent) {
        return missingStudentsList.contains(someStudent);
    }

    //For debug
    public void showMissingStudents() {
        System.out.println(this.group + " Отсутствующие:");
        for (SomeStudent someStudent :
                missingStudentsList) {
            System.out.println(someStudent.toString());
        }
        System.out.println("\n===========================================\n");
    }

    @Override
    public void attendOrNot() {
        this.attendClass = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Praepostor that = (Praepostor) o;
        return karma == that.karma &&
                Objects.equals(journal, that.journal) &&
                Objects.equals(missingStudentsList, that.missingStudentsList) &&
                Objects.equals(markedMissingStudentsList, that.markedMissingStudentsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), karma, journal, missingStudentsList, markedMissingStudentsList);
    }

    @Override
    public String toString() {
        return super.toString() + " - Староста";
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public short getKarma() {
        return karma;
    }

    public ObservableList<SomeStudent> getMissingStudentsList() {
        return missingStudentsList;
    }
}

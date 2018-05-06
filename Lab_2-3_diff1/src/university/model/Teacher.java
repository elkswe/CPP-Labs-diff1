package university.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Teacher {
    private String subject;
    private String secondName;
    private String firstName;
    private String middleName;

    private Journal journal;

    private ObservableList<SomeStudent> missingStudentsList
            = FXCollections.observableArrayList();

    public Teacher() {
        this("КПП",
                "Искра",
                "Наталья",
                "Александровна");
    }

    public Teacher(String subject,
                   String secondName,
                   String firstName,
                   String middleName) {
        this.subject = subject;
        this.secondName = secondName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Teacher(String subject,
                   String secondName,
                   String firstName,
                   String middleName,
                   Journal journal) {
        this(subject, secondName, firstName, middleName);
        this.journal = journal;
    }

    public void markMissingStudents(ObservableList<SomeStudent> studentsList) {
        ObservableList<Praepostor> praepostors
                = FXCollections.observableArrayList();
        missingStudentsList.clear();
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            if (!student.isAttendClass()) {
                missingStudentsList.add(student);
            }
            if (someStudent instanceof Praepostor) {
                praepostors.add((Praepostor) someStudent);
            }
        }
        for (Praepostor praepostor
                : praepostors) {
            for (SomeStudent someStudent
                    : missingStudentsList) {
                Student student = (Student) someStudent;
                if (student.getGroup() == praepostor.getGroup()) {
                    praepostor.finishMarking(student);
                }
            }
        }
    }

    //For debug
    public void showMissingStudents() {
        System.out.println(this.toString());
        for (SomeStudent someStudent :
                missingStudentsList) {
            System.out.println(someStudent.toString());
        }
        System.out.println("\n===========================================\n");
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getSubject() {
        return subject;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public ObservableList<SomeStudent> getMissingStudentsList() {
        return missingStudentsList;
    }

    public void attendLecture(Lecture lecture) {
        lecture.give();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(subject, teacher.subject) &&
                Objects.equals(secondName, teacher.secondName) &&
                Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(middleName, teacher.middleName) &&
                Objects.equals(journal, teacher.journal) &&
                Objects.equals(missingStudentsList, teacher.missingStudentsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, secondName, firstName, middleName, journal, missingStudentsList);
    }

    @Override
    public String toString() {
        return subject +
                ' ' +
                secondName +
                ' ' +
                firstName +
                ' ' +
                middleName;
    }
}

package university.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import university.model.*;
import university.view.AddStudentView;
import university.view.AddTeacherView;
import university.view.JournalView;

import java.util.Comparator;
import java.util.List;

import static university.model.Lecture.LECTURE_IS_REPEATED;

/** Create main view for application. */
public class UniversityViewController {
    /** Constant part of Button "Прочитать лекцию". */
    private static final String DEFAULT_GIVE_LECTURE_BTN_TEXT = "Прочитать лекцию по ";

    /** ListView for showing list of students. */
    private ListView<SomeStudent> studentsListView;
    /** Button showing dialog window for adding student. */
    private Button addStudentBtn;
    /** ListView for showing list of classes. */
    private ListView<SomeClass> classesListView;
    /** Button creating schedule. */
    private Button makeScheduleBtn;
    /** ListView for showing list of teachers. */
    private ListView<Teacher> teachersListView;
    /** Button showing dialog window for adding teacher. */
    private Button hireTeacherBtn;
    /** Perform all actions for the lecture. */
    private Button giveLectureBtn;
    /** Says the praepostors to note their students. */
    private Button praepostorMarkStudents;
    /** . */
    private Button teacherMarkStudentsBtn;
    /** . */
    private TextArea studentDataView;
    /** . */
    private Button journalBtn;

    /** . */
    private ObservableList<SomeStudent> studentsList;
    /** . */
    private ObservableList<SomeClass> classesList;
    /** . */
    private ObservableList<Teacher> teachersList;

    /** . */
    private University university;

    /** Link to main Journal. */
    private Journal journal;

    /** . */
    private Stage primaryStage;

    /** . */
    private SomeStudent tempStudent;
    /** . */
    private Teacher tempTeacher;

    /** . */
    private int currentLectureNumber = 0;
    /** . */
    private int lastCurrentLectureNumber = 0;

    /** . */
    public UniversityViewController() {
        tempStudent = null;
        tempTeacher = null;
    }

    /** . */
    public void initialize() {
        studentsListView.setItems(studentsList);
        classesListView.setItems(classesList);
        teachersListView.setItems(teachersList);

        studentsList.addAll(
                new Student(650503, "Ананько", "Егор", "Эдуардович"),
                new Student(650503, "Резниченко", "Светлана", "Андреевна"),
                new Praepostor(650503, "Кушнеренко", "Роман", "Александрович", journal),
                new Student(650504, "Буслов", "Алексей", "Валерьевич"),
                new Praepostor(650504, "Климова", "Дана", "Вячеславовна", journal),
                new Student(650501, "Ананькоо", "Егорр", "Эдуардовичч"),
                new Student(650501, "Резниченкоо", "Светлана", "Андреевна"),
                new Praepostor(650501, "Кушнеренкоо", "Роман", "Александрович", journal),
                new Student(650502, "Целогузз", "Дмитрий", "Сергеевич"),
                new Student(650502, "Бусловв", "Алексей", "Валерьевич"),
                new Praepostor(650502, "Климоваа", "Дана", "Вячеславовна", journal),
                new Student(650505, "Ананькооо", "Егор", "Эдуардович"),
                new Student(650505, "Резниченкооо", "Светлана", "Андреевна"),
                new Praepostor(650505, "Кушнеренкооо", "Роман", "Александрович", journal),
                new Student(650506, "Целогуззз", "Дмитрий", "Сергеевич"),
                new Student(650506, "Бусловвв", "Алексей", "Валерьевич"),
                new Praepostor(650506, "Климовааа", "Дана", "Вячеславовна", journal));
        studentsList.sort(Comparator.comparing(SomeStudent::toString));

        teachersList.addAll(
                new Teacher("КПП", "Искра", "Наталья", "Александровна", journal),
                new Teacher("АПК", "Прытков", "Валерий", "Александрович", journal),
                new Teacher("СхемТ", "Байрак", "Сергей", "Анатольевич", journal),
                new Teacher("СПОВМ", "Фролов", "Игорь", "Иванович", journal));
        teachersList.sort(Comparator.comparing(Teacher::toString));

        setMyCellFactory(studentsListView);
        setMyCellFactory(classesListView);
        setMyCellFactory(teachersListView);

        addStudentBtn.setOnAction(event -> addStudentWindow());
        hireTeacherBtn.setOnAction(event -> hireTeacherWindow());
        makeScheduleBtn.setOnAction(event -> makeScheduleHandler());
        giveLectureBtn.setOnAction(event -> giveLecture());
        praepostorMarkStudents.setOnAction(event -> praepostorMarkStudentsBtnHandler());
        teacherMarkStudentsBtn.setOnAction(event -> teacherMarkStudentsBtnHandler());
        Event.fireEvent(makeScheduleBtn, new ActionEvent());
        giveLectureBtn.setText(DEFAULT_GIVE_LECTURE_BTN_TEXT + classesList.get(0).getSubject());
        studentsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (studentsListView.getSelectionModel().getSelectedItem() != null) showStudentData(newValue);
            else studentDataView.clear();
        });
        teachersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (teachersListView.getSelectionModel().getSelectedItem() != null) showTeacherData(newValue);
            else studentDataView.clear();
        });
        classesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (classesListView.getSelectionModel().getSelectedItem() != null) showClassesData(newValue);
            else studentDataView.clear();
        });
        journalBtn.setOnAction(event -> showJournal());
    }

    /** . */
    private void showJournal() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Журнал");
        dialogStage.setScene(new Scene(JournalView.getView()));
        dialogStage.setWidth(1000);
        dialogStage.setHeight(700);

        JournalViewController controller = new JournalViewController();
        JournalView.initController(controller);
        controller.setJournal(journal);
        controller.initialize();

        dialogStage.show();
    }

    /**
     * @param student
     */
    private void showStudentData(final SomeStudent student) {
        StringBuilder dataSB = new StringBuilder("Статус: ");
        Student tempStudent = (Student) student;
        if (tempStudent instanceof Praepostor) {
            dataSB.append("Староста")
                    .append("\nКарма: ").append(((Praepostor) tempStudent).getKarma());
        } else {
            dataSB.append("Студент");
        }
        dataSB.append("\nГруппа: ").append(tempStudent.getGroup())
                .append("\nФамилия: ").append(tempStudent.getFirstName())
                .append("\nИмя: ").append(tempStudent.getSecondName())
                .append("\nОтчество: ").append(tempStudent.getMiddleName())
                .append("\nПосещение последней лекции: ").append(tempStudent.isAttendClass() ? "Да" : "Нет")
                .append("\nОценки:");
        List<Student.Mark> marks = tempStudent.getMarks();
        if (marks.size() != 0) {
            for (Student.Mark mark
                    : marks) {
                dataSB.append("\n\t").append(mark.toString());
            }
        } else {
            dataSB.append(" Нет");
        }
        if (tempStudent instanceof Praepostor) {
            Praepostor praepostor = (Praepostor) tempStudent;
            dataSB.append("\n\nСписок отсутствующих: ");
            List<SomeStudent> missingList = praepostor.getMissingStudentsList();
            if (missingList.size() != 0) {
                for (SomeStudent someStudent
                        : missingList) {
                    dataSB.append("\n\t").append(someStudent.toString());
                }
            } else {
                dataSB.append("Нет");
            }
        }
        studentDataView.setText(dataSB.toString());
    }

    /**
     * @param teacher
     */
    private void showTeacherData(final Teacher teacher) {
        StringBuilder dataSB = new StringBuilder("Предмет: ");
        dataSB.append(teacher.getSubject())
                .append("\nФамилия: ").append(teacher.getFirstName())
                .append("\nИмя: ").append(teacher.getSecondName())
                .append("\nОтчество: ").append(teacher.getMiddleName())
                .append("\n\nСписок отсутствующих: ");
        List<SomeStudent> missingList = teacher.getMissingStudentsList();
        if (missingList.size() != 0) {
            for (SomeStudent someStudent
                    : missingList) {
                dataSB.append("\n\t").append(someStudent.toString());
            }
        } else {
            dataSB.append("Нет");
        }
        studentDataView.setText(dataSB.toString());
    }

    /**
     * @param someClass
     */
    private void showClassesData(final SomeClass someClass) {
        Lecture lecture = (Lecture) someClass;
        StringBuilder dataSB = new StringBuilder("Предмет: ");
        dataSB.append(lecture.getSubject())
                .append("\nТема: ").append(lecture.getTheme())
                .append("\nПара проведена: ").append(lecture.isGive() ? "Да" : "Нет");
        Teacher teacher = lecture.getTeacher();
        dataSB.append("\nФамилия: ").append(teacher.getFirstName())
                .append("\nИмя: ").append(teacher.getSecondName())
                .append("\nОтчество: ").append(teacher.getMiddleName())
                .append("\n\nСписок отсутствующих: ");
        List<SomeStudent> missingList = teacher.getMissingStudentsList();
        if (missingList.size() != 0) {
            for (SomeStudent someStudent
                    : missingList) {
                dataSB.append("\n\t").append(someStudent.toString());
            }
        } else {
            dataSB.append("Нет");
        }
        studentDataView.setText(dataSB.toString());
    }

    /**
     * @param listView
     * @param <T>
     */
    private <T> void setMyCellFactory(final ListView<T> listView) {
        listView.setCellFactory(param -> new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }

    /** . */
    private void addStudentWindow() {
        boolean okClicked = showStudentAddDialog();
        if (okClicked) {
            if (tempStudent instanceof Praepostor) {
                ((Praepostor) tempStudent).setJournal(journal);
            }
            university.addStudent(tempStudent);
            university.getStudentsList()
                    .sort(Comparator.comparing(SomeStudent::toString));
        }
    }

    /** . */
    private void hireTeacherWindow() {
        boolean okClicked = showTeacherHireDialog();
        if (okClicked) {
            tempTeacher.setJournal(journal);
            university.addTeacher(tempTeacher);
            university.getTeachersList()
                    .sort(Comparator.comparing(Teacher::toString));
        }
    }

    /** . */
    private void makeScheduleHandler() {
        university.generateSchedule();
        giveLectureBtn.setText(DEFAULT_GIVE_LECTURE_BTN_TEXT
                + classesList.get(0).getSubject());
        giveLectureBtn.setDisable(false);
    }

    /** . */
    private void giveLecture() {
        fillTheAudience();
        Lecture lecture = (Lecture) classesList.get(currentLectureNumber);
        lecture.fillMissingStudents(studentsList);
        lecture.getTeacher().attendLecture(lecture);
        if (lecture.isGive()) {
            lecture.giveKnowledge(studentsList);
        }
        //debug
        for (SomeStudent someStudent
                : studentsList) {
            Student student = (Student) someStudent;
            System.out.println(student.isAttendClass());
            student.showMarks();

        }
        System.out.println("\n===========================================\n");

        for (SomeStudent someStudent
                : lecture.getMissingStudentsList()) {
            Student student = (Student) someStudent;
            System.out.println(student.isAttendClass());
            System.out.println(student.toString());
        }
        System.out.println("\n===========================================\n");
        //debug
        lastCurrentLectureNumber = currentLectureNumber;
        if (++currentLectureNumber < classesList.size()) {
            makeScheduleBtn.setDisable(true);
        } else {
            currentLectureNumber = 0;
            makeScheduleBtn.setDisable(false);
            giveLectureBtn.setDisable(true);
        }
        giveLectureBtn.setText(DEFAULT_GIVE_LECTURE_BTN_TEXT
                + lecture.getSubject());
    }

    /** . */
    private void praepostorMarkStudentsBtnHandler() {
        Lecture lecture = (Lecture) classesList.get(lastCurrentLectureNumber);
        if (lecture.isGive()) {
            for (SomeStudent someStudent
                    : studentsList) {
                if (someStudent instanceof Praepostor) {
                    Praepostor praepostor = (Praepostor) someStudent;
                    praepostor.markMissingStudents(studentsList);
                    //debug
                    praepostor.showMissingStudents();
                    //debug
                }
            }
            journal.showJournal();
            System.out.println();
        }
    }

    /** . */
    private void teacherMarkStudentsBtnHandler() {
        if (((Lecture) classesList.get(lastCurrentLectureNumber)).isGive()) {
            classesList.get(lastCurrentLectureNumber)
                    .getTeacher().markMissingStudents(studentsList);
            //debug
            System.out.println();
            classesList.get(lastCurrentLectureNumber)
                    .getTeacher().showMissingStudents();
            System.out.println();
            //debug
            journal.showJournal();
            System.out.println();
        }
    }

    /** . */
    private void fillTheAudience() {
        for (SomeStudent someStudent
                : studentsList) {
            Student student = (Student) someStudent;
            student.attendOrNot();
        }
    }

    /** . */
    private boolean showStudentAddDialog() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Зачислить студента");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(new Scene(AddStudentView.getView()));
        dialogStage.sizeToScene();
        dialogStage.setResizable(false);

        AddStudentViewController controller = new AddStudentViewController();
        AddStudentView.initController(controller);
        controller.setDialogStage(dialogStage);
        controller.initialize();

        dialogStage.showAndWait();
        tempStudent = controller.getSomeStudent();

        return controller.isOkClicked();
    }

    /** . */
    private boolean showTeacherHireDialog() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Нанять преподавателя");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(new Scene(AddTeacherView.getView()));
        dialogStage.sizeToScene();
        dialogStage.setResizable(false);

        AddTeacherViewController controller = new AddTeacherViewController();
        AddTeacherView.initController(controller);
        controller.setDialogStage(dialogStage);
        controller.initialize();

        dialogStage.showAndWait();
        tempTeacher = controller.getTeacher();

        return controller.isOkClicked();
    }

    /**
     * @param university
     */
    public void setUniversity(final University university) {
        this.university = university;
        this.journal = university.getJournal();
        initLists();
    }

    /** . */
    private void initLists() {
        studentsList = university.getStudentsList();
        classesList = university.getClassesList();
        teachersList = university.getTeachersList();
    }

    /**
     * @param studentsListView
     */
    public void setStudentsListView(final ListView<SomeStudent> studentsListView) {
        this.studentsListView = studentsListView;
    }

    /**
     * @param addStudentBtn
     */
    public void setAddStudentBtn(final Button addStudentBtn) {
        this.addStudentBtn = addStudentBtn;
    }

    /**
     * @param classesListView
     */
    public void setClassesListView(final ListView<SomeClass> classesListView) {
        this.classesListView = classesListView;
    }

    /**
     * @param makeScheduleBtn
     */
    public void setMakeScheduleBtn(final Button makeScheduleBtn) {
        this.makeScheduleBtn = makeScheduleBtn;
    }

    /**
     * @param teachersListView
     */
    public void setTeachersListView(final ListView<Teacher> teachersListView) {
        this.teachersListView = teachersListView;
    }

    /**
     * @param hireTeacherBtn
     */
    public void setHireTeacherBtn(final Button hireTeacherBtn) {
        this.hireTeacherBtn = hireTeacherBtn;
    }

    /**
     * @param primaryStage
     */
    public void setPrimaryStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * @param giveLectureBtn
     */
    public void setGiveLectureBtn(final Button giveLectureBtn) {
        this.giveLectureBtn = giveLectureBtn;
    }

    /**
     * @param praepostorMarkStudents
     */
    public void setPraepostorMarkStudents(final Button praepostorMarkStudents) {
        this.praepostorMarkStudents = praepostorMarkStudents;
    }

    /**
     * @param teacherMarkStudentsBtn
     */
    public void setTeacherMarkStudentsBtn(final Button teacherMarkStudentsBtn) {
        this.teacherMarkStudentsBtn = teacherMarkStudentsBtn;
    }

    /**
     * @param studentDataView
     */
    public void setStudentDataView(final TextArea studentDataView) {
        this.studentDataView = studentDataView;
    }

    /**
     * @param journalBtn
     */
    public void setJournalBtn(final Button journalBtn) {
        this.journalBtn = journalBtn;
    }
}

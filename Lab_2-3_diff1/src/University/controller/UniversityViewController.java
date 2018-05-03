package University.controller;

import University.model.*;
import University.view.AddStudentView;
import University.view.AddTeacherView;
import javafx.collections.FXCollections;
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

import java.util.Comparator;
import java.util.List;

public class UniversityViewController {
    private static final String DEFAULT_GIVE_LECTURE_BTN_TEXT = "Прочитать лекцию по ";

    private ListView<SomeStudent> studentsListView;
    private Button addStudentBtn;
    private ListView<SomeClass> classesListView;
    private Button makeScheduleBtn;
    private ListView<Teacher> teachersListView;
    private Button hireTeacherBtn;
    private Button giveLectureBtn;
    private Button praepostorMarkStudents;
    private Button teacherMarkStudentsBtn;
    private TextArea studentDataView;

    private ObservableList<SomeStudent> studentsList;
    private ObservableList<SomeClass> classesList;
    private ObservableList<Teacher> teachersList;

    private ObservableList<SomeStudent> missingStudentsList = FXCollections.observableArrayList();

    private University university;

    private Stage primaryStage;

    private SomeStudent tempStudent;
    private Teacher tempTeacher;

    private int currentLectureNumber = 0;
    private int lastCurrentLectureNumber = 0;

    public UniversityViewController() {
        tempStudent = null;
        tempTeacher = null;
    }

    public void initialize() {
        studentsListView.setItems(studentsList);
        classesListView.setItems(classesList);
        teachersListView.setItems(teachersList);

        studentsList.addAll(
                new Student(650503, "Ананько", "Егор", "Эдуардович"),
                new Student(650503, "Резниченко", "Светлана", "Андреевна"),
                new Praepostor(650503, "Кушнеренко", "Роман", "Александрович"),
                new Student(650504, "Буслов", "Алексей", "Валерьевич"));
        studentsList.sort(Comparator.comparing(SomeStudent::toString));

        teachersList.addAll(
                new Teacher("КПП", "Искра", "Наталья", "Александровна"),
                new Teacher("АПК", "Прытков", "Валерий", "Александрович"),
                new Teacher("СхемТ", "Байрак", "Сергей", "Анатольевич"),
                new Teacher("СПОВМ", "Фролов", "Игорь", "Иванович"));
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
    }

    private void showStudentData(SomeStudent student) {
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
            for (Student.Mark mark :
                    marks) {
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
                for (SomeStudent someStudent :
                        missingList) {
                    dataSB.append("\n\t").append(someStudent.toString());
                }
            } else {
                dataSB.append("Нет");
            }
        }
        studentDataView.setText(dataSB.toString());
    }

    private void showTeacherData(Teacher teacher) {
        StringBuilder dataSB = new StringBuilder("Предмет: ");
        dataSB.append(teacher.getSubject())
                .append("\nФамилия: ").append(teacher.getFirstName())
                .append("\nИмя: ").append(teacher.getSecondName())
                .append("\nОтчество: ").append(teacher.getMiddleName())
                .append("\n\nСписок отсутствующих: ");
        List<SomeStudent> missingList = teacher.getMissingStudentsList();
        if (missingList.size() != 0) {
            for (SomeStudent someStudent :
                    missingList) {
                dataSB.append("\n\t").append(someStudent.toString());
            }
        } else {
            dataSB.append("Нет");
        }
        studentDataView.setText(dataSB.toString());
    }

    private void showClassesData(SomeClass someClass) {
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
            for (SomeStudent someStudent :
                    missingList) {
                dataSB.append("\n\t").append(someStudent.toString());
            }
        } else {
            dataSB.append("Нет");
        }
        studentDataView.setText(dataSB.toString());
    }

    private <T> void setMyCellFactory(ListView<T> listView) {
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

    private void addStudentWindow() {
        boolean okClicked = showStudentAddDialog();
        if (okClicked) {
            university.addStudent(tempStudent);
            university.getStudentsList().sort(Comparator.comparing(SomeStudent::toString));
        }
    }

    private void hireTeacherWindow() {
        boolean okClicked = showTeacherHireDialog();
        if (okClicked) {
            university.addTeacher(tempTeacher);
            university.getTeachersList().sort(Comparator.comparing(Teacher::toString));
        }
    }

    private void makeScheduleHandler() {
        university.generateSchedule();
        giveLectureBtn.setText(DEFAULT_GIVE_LECTURE_BTN_TEXT + classesList.get(0).getSubject());
        giveLectureBtn.setDisable(false);
    }

    private void giveLecture() {
        fillTheAudience();
        fillMissingStudents();
        classesList.get(currentLectureNumber).getTeacher().attendLecture((Lecture) classesList.get(currentLectureNumber));
        if (((Lecture) classesList.get(currentLectureNumber)).isGive()) {
            ((Lecture) classesList.get(currentLectureNumber)).giveKnowledge(studentsList);
        }
        //debug
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            System.out.println(student.isGiveClass());
            student.showMarks();

        }
        System.out.println("\n===========================================\n");

        for (SomeStudent someStudent :
                missingStudentsList) {
            Student student = (Student) someStudent;
            System.out.println(student.isGiveClass());
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
        giveLectureBtn.setText(DEFAULT_GIVE_LECTURE_BTN_TEXT + classesList.get(currentLectureNumber).getSubject());
    }

    private void praepostorMarkStudentsBtnHandler() {
        if (((Lecture) classesList.get(lastCurrentLectureNumber)).isGive()) {
            for (SomeStudent someStudent :
                    studentsList) {
                if (someStudent instanceof Praepostor && ((Praepostor) someStudent).isGiveClass()) {
                    Praepostor praepostor = (Praepostor) someStudent;
                    praepostor.markMissingStudents(studentsList);
                    //debug
                    System.out.println();
                    praepostor.showMissingStudents();
                    System.out.println();
                    //debug
                }
            }
        }
    }

    private void teacherMarkStudentsBtnHandler() {
        if (((Lecture) classesList.get(lastCurrentLectureNumber)).isGive()) {
            classesList.get(lastCurrentLectureNumber).getTeacher().markMissingStudents(studentsList);
            //debug
            System.out.println();
            classesList.get(lastCurrentLectureNumber).getTeacher().showMissingStudents();
            System.out.println();
            //debug
        }
    }

    private void fillTheAudience() {
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            student.attendOrNot();
        }
    }

    private void fillMissingStudents() {
        missingStudentsList.clear();
        for (SomeStudent someStudent :
                studentsList) {
            Student student = (Student) someStudent;
            if (!student.isGiveClass()) {
                missingStudentsList.add(student);
            }
        }
    }

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

    public void setUniversity(University university) {
        this.university = university;
        initLists();
    }

    private void initLists() {
        studentsList = university.getStudentsList();
        classesList = university.getClassesList();
        teachersList = university.getTeachersList();
    }

    public void setStudentsListView(ListView<SomeStudent> studentsListView) {
        this.studentsListView = studentsListView;
    }

    public void setAddStudentBtn(Button addStudentBtn) {
        this.addStudentBtn = addStudentBtn;
    }

    public void setClassesListView(ListView<SomeClass> classesListView) {
        this.classesListView = classesListView;
    }

    public void setMakeScheduleBtn(Button makeScheduleBtn) {
        this.makeScheduleBtn = makeScheduleBtn;
    }

    public void setTeachersListView(ListView<Teacher> teachersListView) {
        this.teachersListView = teachersListView;
    }

    public void setHireTeacherBtn(Button hireTeacherBtn) {
        this.hireTeacherBtn = hireTeacherBtn;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setGiveLectureBtn(Button giveLectureBtn) {
        this.giveLectureBtn = giveLectureBtn;
    }

    public void setPraepostorMarkStudents(Button praepostorMarkStudents) {
        this.praepostorMarkStudents = praepostorMarkStudents;
    }

    public void setTeacherMarkStudentsBtn(Button teacherMarkStudentsBtn) {
        this.teacherMarkStudentsBtn = teacherMarkStudentsBtn;
    }

    public void setStudentDataView(TextArea studentDataView) {
        this.studentDataView = studentDataView;
    }
}

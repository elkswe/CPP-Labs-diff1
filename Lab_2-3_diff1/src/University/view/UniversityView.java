package University.view;

import University.controller.UniversityViewController;
import University.model.SomeClass;
import University.model.SomeStudent;
import University.model.Teacher;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public final class UniversityView {
    public static final double WINDOW_MIN_HEIGHT = 600;
    public static final double WINDOW_MIN_WIDTH = 1250;
    private static final byte DEFAULT_SPACING = 15;
    private static final double MIN_VIEWBOX_HEIGHT = 700;
    private static final int VIEWBOX_LABEL_INDEX = 0;
    private static final int VIEWBOX_BUTTON_INDEX = 2;
    private static final int BUTTON_MIN_HEIGHT = 35;
    private static final double FOR_DEFAULT = 0;

    private static final ListView<SomeStudent> studentsListView;
    private static final Button addStudentBtn;
    private static final ListView<SomeClass> classesListView;
    private static final Button makeScheduleBtn;
    private static final ListView<Teacher> teachersListView;
    private static final Button hireTeacherBtn;
    private static final Button giveLectureBtn;
    private static final Button praepostorMarkStudentsBtn;
    private static final Button teacherMarkStudentsBtn;
    private static final TextArea studentDataView;

    static {
        studentsListView = new ListView<>();
        addStudentBtn = new Button("Зачислить студента");
        classesListView = new ListView<>();
        makeScheduleBtn = new Button("Составить план занятий");
        teachersListView = new ListView<>();
        hireTeacherBtn = new Button("Нанять преподавателя");
        giveLectureBtn = new Button();
        praepostorMarkStudentsBtn = new Button("Старосты -> Отметить");
        teacherMarkStudentsBtn = new Button("Преподаватель -> Отметить");
        studentDataView = new TextArea();
    }

    private UniversityView() {

    }

    public static VBox getView() {
        praepostorMarkStudentsBtn.setFont(new Font(14));
        praepostorMarkStudentsBtn.setMinHeight(BUTTON_MIN_HEIGHT);
        VBox studentsViewBox = new VBox(
                new Label("Студенты"),
                studentsListView,
                addStudentBtn,
                new Separator(Orientation.HORIZONTAL),
                praepostorMarkStudentsBtn
        );
        configureViewBox(studentsViewBox);

        studentDataView.setFont(new Font(14));
        studentDataView.setPrefSize(FOR_DEFAULT, FOR_DEFAULT);
        VBox.setVgrow(studentDataView, Priority.ALWAYS);
        VBox classesViewBox = new VBox(
                new Label("Занятия"),
                classesListView,
                makeScheduleBtn,
                new Separator(Orientation.HORIZONTAL),
                studentDataView
        );
        configureViewBox(classesViewBox);

        giveLectureBtn.setFont(new Font(14));
        giveLectureBtn.setMinHeight(BUTTON_MIN_HEIGHT);
        teacherMarkStudentsBtn.setFont(new Font(14));
        teacherMarkStudentsBtn.setMinHeight(BUTTON_MIN_HEIGHT);
        VBox teachersViewBox = new VBox(
                new Label("Преподаватели"),
                teachersListView,
                hireTeacherBtn,
                new Separator(Orientation.HORIZONTAL),
                giveLectureBtn,
                teacherMarkStudentsBtn
        );
        configureViewBox(teachersViewBox);

        HBox viewBoxes = new HBox(
                DEFAULT_SPACING,
                studentsViewBox,
                classesViewBox,
                teachersViewBox
        );
        viewBoxes.setMinHeight(MIN_VIEWBOX_HEIGHT);
        viewBoxes.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(viewBoxes, Priority.ALWAYS);

        VBox mainBox = new VBox(viewBoxes);
        mainBox.setPadding(new Insets(DEFAULT_SPACING));
        mainBox.setSpacing(DEFAULT_SPACING);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMinSize(
                WINDOW_MIN_WIDTH,
                WINDOW_MIN_HEIGHT
        );
        mainBox.setPrefSize(
                Region.USE_COMPUTED_SIZE,
                Region.USE_COMPUTED_SIZE
        );

        return mainBox;
    }

    private static void configureViewBox(VBox viewBox) {
        Label label = (Label) viewBox.getChildren().get(VIEWBOX_LABEL_INDEX);
        label.setFont(new Font(14));

        Button button = (Button) viewBox.getChildren().get(VIEWBOX_BUTTON_INDEX);
        button.setFont(new Font(14));
        button.setMinHeight(BUTTON_MIN_HEIGHT);

        HBox.setHgrow(viewBox, Priority.ALWAYS);
        viewBox.setSpacing(DEFAULT_SPACING);
        viewBox.setPrefSize(
                Region.USE_COMPUTED_SIZE,
                Region.USE_COMPUTED_SIZE
        );
        viewBox.setAlignment(Pos.TOP_CENTER);
    }

    public static void initController(UniversityViewController controller) {
        controller.setStudentsListView(studentsListView);
        controller.setAddStudentBtn(addStudentBtn);
        controller.setClassesListView(classesListView);
        controller.setMakeScheduleBtn(makeScheduleBtn);
        controller.setTeachersListView(teachersListView);
        controller.setHireTeacherBtn(hireTeacherBtn);
        controller.setGiveLectureBtn(giveLectureBtn);
        controller.setPraepostorMarkStudents(praepostorMarkStudentsBtn);
        controller.setTeacherMarkStudentsBtn(teacherMarkStudentsBtn);
        controller.setStudentDataView(studentDataView);
    }
}

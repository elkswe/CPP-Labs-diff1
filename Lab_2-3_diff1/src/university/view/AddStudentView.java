package university.view;

import university.controller.AddStudentViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public final class AddStudentView {
    private static final double DEFAULT_SPACING = 15;
    private static final double DEFAULT_PADDING = 15;
    private static final int BUTTON_MIN_HEIGHT = 35;
    private static final int BUTTON_MIN_WIDTH = 100;
    private static final int TEXTFIELD_MIN_HEIGHT = 35;

    private static final TextField secondNameTextField;
    private static final TextField firstNameTextField;
    private static final TextField middleNameTextField;
    private static final ComboBox<Integer> groupComboBox;
    private static final CheckBox praepostorCheckBox;
    private static final Button okBtn;
    private static final Button cancelBtn;

    static {
        secondNameTextField = new TextField();
        firstNameTextField = new TextField();
        middleNameTextField = new TextField();
        ObservableList<Integer> groupList = FXCollections.observableArrayList();
        groupList.addAll(650501, 650502, 650503, 650504, 650505);
        groupComboBox = new ComboBox<>(groupList);
        groupComboBox.getSelectionModel().selectFirst();
        praepostorCheckBox = new CheckBox("Староста");
        okBtn = new Button("OK");
        cancelBtn = new Button("Отмена");
    }

    private AddStudentView() {

    }

    public static VBox getView() {
        Font font = new Font(14);
        secondNameTextField.setPromptText("Фамилия");
        secondNameTextField.setFont(font);
        secondNameTextField.setMinHeight(TEXTFIELD_MIN_HEIGHT);

        firstNameTextField.setPromptText("Имя");
        firstNameTextField.setFont(font);
        firstNameTextField.setMinHeight(TEXTFIELD_MIN_HEIGHT);

        middleNameTextField.setPromptText("Отчество");
        middleNameTextField.setFont(font);
        middleNameTextField.setMinHeight(TEXTFIELD_MIN_HEIGHT);

        Label comboBoxLabel = new Label("Группа");
        comboBoxLabel.setFont(font);

        HBox comboBoxHBox = new HBox(DEFAULT_SPACING, comboBoxLabel, groupComboBox);
        comboBoxHBox.setAlignment(Pos.CENTER_LEFT);

        okBtn.setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        cancelBtn.setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        HBox btnsHBox = new HBox(DEFAULT_SPACING, okBtn, cancelBtn);
        btnsHBox.setAlignment(Pos.CENTER_LEFT);

        VBox mainVBox = new VBox(
                DEFAULT_SPACING,
                secondNameTextField,
                firstNameTextField,
                middleNameTextField,
                comboBoxHBox,
                praepostorCheckBox,
                btnsHBox
        );

        mainVBox.setPadding(new Insets(DEFAULT_PADDING));
        mainVBox.setAlignment(Pos.CENTER_LEFT);

        return mainVBox;
    }

    public static void initController(AddStudentViewController controller) {
        controller.setSecondNameTextField(secondNameTextField);
        controller.setFirstNameTextField(firstNameTextField);
        controller.setMiddleNameTextField(middleNameTextField);
        controller.setGroupComboBox(groupComboBox);
        controller.setPraepostorCheckBox(praepostorCheckBox);
        controller.setOkBtn(okBtn);
        controller.setCancelBtn(cancelBtn);
    }
}

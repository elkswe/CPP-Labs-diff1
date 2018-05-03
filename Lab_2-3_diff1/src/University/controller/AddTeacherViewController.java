package University.controller;

import University.model.Teacher;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static University.utils.verificationOfInitials.verify;

public class AddTeacherViewController {
    private TextField secondNameTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private ComboBox<String> groupComboBox;
    private Button okBtn;
    private Button cancelBtn;

    private Teacher teacher;

    private boolean isOkClicked;

    private Stage dialogStage;

    public AddTeacherViewController() {
        teacher = null;
        isOkClicked = false;
    }

    public void initialize() {
        okBtn.setOnAction(event -> handleOk());
        cancelBtn.setOnAction(event -> handleCancel());
    }

    private void handleOk() {
        if (isInputValid()) {
            teacher = new Teacher(
                    groupComboBox.getSelectionModel().getSelectedItem(),
                    secondNameTextField.getText(),
                    firstNameTextField.getText(),
                    middleNameTextField.getText());

            isOkClicked = true;
            dialogStage.close();
        }
    }

    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        return verify(
                secondNameTextField,
                firstNameTextField,
                middleNameTextField,
                dialogStage);
    }

    public void setSecondNameTextField(TextField secondNameTextField) {
        this.secondNameTextField = secondNameTextField;
    }

    public void setFirstNameTextField(TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public void setMiddleNameTextField(TextField middleNameTextField) {
        this.middleNameTextField = middleNameTextField;
    }

    public void setGroupComboBox(ComboBox<String> groupComboBox) {
        this.groupComboBox = groupComboBox;
    }

    public void setOkBtn(Button okBtn) {
        this.okBtn = okBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }
}

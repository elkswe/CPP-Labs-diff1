package University.controller;

import University.model.Praepostor;
import University.model.SomeStudent;
import University.model.Student;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static University.utils.verificationOfInitials.verify;

public class AddStudentViewController {
    private TextField secondNameTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private ComboBox<Integer> groupComboBox;
    private CheckBox praepostorCheckBox;
    private Button okBtn;
    private Button cancelBtn;

    private SomeStudent someStudent;

    private boolean isOkClicked;

    private Stage dialogStage;

    public AddStudentViewController() {
        someStudent = null;
        isOkClicked = false;
    }

    public void initialize() {
        okBtn.setOnAction(event -> handleOk());
        cancelBtn.setOnAction(event -> handleCancel());
    }

    private void handleOk() {
        if (isInputValid()) {
            if (praepostorCheckBox.isSelected()) {
                someStudent = new Praepostor(
                        groupComboBox.getSelectionModel().getSelectedItem(),
                        secondNameTextField.getText(),
                        firstNameTextField.getText(),
                        middleNameTextField.getText());
            } else {
                someStudent = new Student(
                        groupComboBox.getSelectionModel().getSelectedItem(),
                        secondNameTextField.getText(),
                        firstNameTextField.getText(),
                        middleNameTextField.getText());
            }

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

    public void setGroupComboBox(ComboBox<Integer> groupComboBox) {
        this.groupComboBox = groupComboBox;
    }

    public void setPraepostorCheckBox(CheckBox praepostorCheckBox) {
        this.praepostorCheckBox = praepostorCheckBox;
    }

    public SomeStudent getSomeStudent() {
        return someStudent;
    }

    public void setOkBtn(Button okBtn) {
        this.okBtn = okBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}

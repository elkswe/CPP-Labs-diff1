package university.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import university.model.Teacher;

import static university.utils.verificationOfInitials.verify;

/**
 * Create view for adding student.
 */
public class AddTeacherViewController {
    /**
     * TextField for entering second name.
     */
    private TextField secondNameTextField;
    /**
     * TextField for entering first name.
     */
    private TextField firstNameTextField;
    /**
     * TextField for entering middle name.
     */
    private TextField middleNameTextField;
    /**
     * ComboBox for choosing subject.
     */
    private ComboBox<String> groupComboBox;
    /**
     * Button for confirm creating.
     */
    private Button okBtn;
    /**
     * Button for close dialog window.
     */
    private Button cancelBtn;
    /**
     * Created teacher.
     */
    private Teacher teacher;

    /**
     * True, if OK button is clicked.
     */
    private boolean isOkClicked;

    /**
     * Stage for showing dialog window.
     */
    private Stage dialogStage;

    /**
     * Default constructor.
     */
    public AddTeacherViewController() {
        teacher = null;
        isOkClicked = false;
    }

    /**
     * Initialize handlers.
     */
    public void initialize() {
        okBtn.setOnAction(event -> handleOk());
        cancelBtn.setOnAction(event -> handleCancel());
    }

    /**
     * Handler for OK button.
     * Checking input and create new student
     * if success.
     */
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

    /**
     * Handler for Cancel button.
     * Close]ing dialog window.
     */
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Checks entered data in fields.
     *
     * @return true, if entered data is correct.
     */
    private boolean isInputValid() {
        return verify(
                secondNameTextField,
                firstNameTextField,
                middleNameTextField,
                dialogStage);
    }

    /**
     * Set new link to TextField for second name.
     *
     * @param secondNameTextField new TextField for entering second name.
     */
    public void setSecondNameTextField(final TextField secondNameTextField) {
        this.secondNameTextField = secondNameTextField;
    }

    /**
     * Set new link to TextField for first name.
     *
     * @param firstNameTextField new TextField for entering first name.
     */
    public void setFirstNameTextField(final TextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    /**
     * Set new link to TextField for middle name.
     *
     * @param middleNameTextField new TextField for entering middle name.
     */
    public void setMiddleNameTextField(final TextField middleNameTextField) {
        this.middleNameTextField = middleNameTextField;
    }

    /**
     * Set new link to ComboBox for subject selector.
     *
     * @param groupComboBox new ComboBox for choosing subject.
     */
    public void setGroupComboBox(final ComboBox<String> groupComboBox) {
        this.groupComboBox = groupComboBox;
    }

    /**
     * Set new link to Button for confirming creating.
     *
     * @param okBtn new confirm button.
     */
    public void setOkBtn(final Button okBtn) {
        this.okBtn = okBtn;
    }

    /**
     * Set new link to Button for canceling creating.
     *
     * @param cancelBtn new cancel button.
     */
    public void setCancelBtn(final Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    /**
     * Set new link to dialog Stage.
     *
     * @param dialogStage dialog stage.
     */
    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return Teacher - created teacher.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @return true - if Ok button is clicked.
     */
    public boolean isOkClicked() {
        return isOkClicked;
    }
}

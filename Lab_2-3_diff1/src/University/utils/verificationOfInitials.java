package University.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class verificationOfInitials {
    private verificationOfInitials() {

    }

    public static boolean verify(TextField secondNameTextField, TextField firstNameTextField, TextField middleNameTextField, Stage owner) {
        String errorMessage = "";
        String patern = "[А-Я|Ё][а-я|ё]*";

        if (secondNameTextField.getText() == null || secondNameTextField.getText().length() == 0 || !secondNameTextField.getText().matches(patern)) {
            errorMessage += "Некорректная фамилия!\n";
        }
        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0 || !firstNameTextField.getText().matches(patern)) {
            errorMessage += "Некорректное имя!\n";
        }
        if (middleNameTextField.getText() == null || middleNameTextField.getText().length() == 0 || !middleNameTextField.getText().matches(patern)) {
            errorMessage += "Некорректное отчество!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(owner);
            alert.setTitle("Неверные поля");
            alert.setHeaderText("Пожалуйста исправьте неправильно введённые поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

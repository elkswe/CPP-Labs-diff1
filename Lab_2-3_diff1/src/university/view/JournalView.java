package university.view;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import university.controller.JournalViewController;
import university.model.Journal.JournalLine;

import java.util.List;

public class JournalView {
    private final static TableView<JournalLine> table;
    private final static TableColumn<JournalLine, String> studentCol;
    private final static TableColumn<JournalLine, String> presenceCol;

    static {
        table = new TableView<>();
        studentCol = new TableColumn<>("Student");
        presenceCol = new TableColumn<>("Presence");
        table.getColumns().addAll(studentCol, presenceCol);
    }

    private JournalView() {

    }

    public static StackPane getView() {

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);
        return root;
    }

    public static void initController(JournalViewController controller) {
        controller.setPresenceCol(presenceCol);
        controller.setStudentCol(studentCol);
        controller.setTable(table);
    }
}

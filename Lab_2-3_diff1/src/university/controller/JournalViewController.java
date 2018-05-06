package university.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import university.model.Journal;
import university.model.Journal.JournalLine;

public class JournalViewController {
    private TableView<JournalLine> table;
    private TableColumn<JournalLine, String> studentCol;
    private TableColumn<JournalLine, String> presenceCol;

    private Journal journal;

    public void initialize() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        studentCol.setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().student));
        presenceCol.setCellValueFactory(param ->
            new SimpleStringProperty(param.getValue().toString()));

        ObservableList<JournalLine> list
                = FXCollections.observableArrayList(journal.getStudentsPresence());
        table.setItems(list);
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void setTable(TableView<JournalLine> table) {
        this.table = table;
    }

    public void setStudentCol(TableColumn<JournalLine, String> studentCol) {
        this.studentCol = studentCol;
    }

    public void setPresenceCol(TableColumn<JournalLine, String> presenceCol) {
        this.presenceCol = presenceCol;
    }
}

package university;

import university.controller.UniversityViewController;
import university.model.University;
import university.view.UniversityView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Университет");
        this.primaryStage.setScene(new Scene(UniversityView.getView()));
        this.primaryStage.sizeToScene();
        University university = new University();

        UniversityViewController controller = new UniversityViewController();
        UniversityView.initController(controller);
        controller.setUniversity(university);
        controller.setPrimaryStage(primaryStage);
        controller.initialize();

        this.primaryStage.sizeToScene();
        this.primaryStage.setMinWidth(UniversityView.WINDOW_MIN_WIDTH);
        this.primaryStage.setMinHeight(UniversityView.WINDOW_MIN_HEIGHT);
        this.primaryStage.show();
    }
}

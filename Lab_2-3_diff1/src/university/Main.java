package university;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import university.controller.UniversityViewController;
import university.model.University;
import university.view.UniversityView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Университет");
        primaryStage.setScene(new Scene(UniversityView.getView()));
        primaryStage.sizeToScene();
        University university = new University();

        UniversityViewController controller = new UniversityViewController();
        UniversityView.initController(controller);
        controller.setUniversity(university);
        controller.setPrimaryStage(primaryStage);
        controller.initialize();

        primaryStage.sizeToScene();
        primaryStage.setMinWidth(UniversityView.WINDOW_MIN_WIDTH);
        primaryStage.setMinHeight(UniversityView.WINDOW_MIN_HEIGHT);
        primaryStage.show();
    }
}

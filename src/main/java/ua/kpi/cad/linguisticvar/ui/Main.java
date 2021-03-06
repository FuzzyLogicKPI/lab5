package ua.kpi.cad.linguisticvar.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String ROOT_FXML_FILE = "/templates/main-scene.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getClass().getResourceAsStream(ROOT_FXML_FILE));

        primaryStage.setTitle("Linguistic variable assignment.");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/chart.css");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

package com.jhallat.automated.testing.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AutomatedTestingUI extends Application {

    public static void main(String...args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Scene scene = createScene();
        stage.setTitle("Automated Web Testing");
        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene() {

        BorderPane mainForm = new BorderPane();

        return new Scene(mainForm, 1200, 800);
    }
}

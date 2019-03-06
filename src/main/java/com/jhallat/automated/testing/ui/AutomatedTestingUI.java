package com.jhallat.automated.testing.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutomatedTestingUI extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(AutomatedTestingUI.class);
    }

    @Override
    public void stop() {
        springContext.stop();
    }

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
        MenuBar menuBar = createMenuBar();

        mainForm.setTop(menuBar);

        return new Scene(mainForm, 1200, 800);
    }

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem newProjectMenuItem = new MenuItem("New Test Project");
        fileMenu.getItems().addAll(newProjectMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }
}

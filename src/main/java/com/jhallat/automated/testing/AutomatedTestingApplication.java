package com.jhallat.automated.testing;

import com.jhallat.automated.testing.command.NewTestProjectCommand;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutomatedTestingApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Autowired
    private NewTestProjectCommand newTestProjectCommand;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(AutomatedTestingApplication.class);
        newTestProjectCommand = springContext.getBean(NewTestProjectCommand.class);
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
        MenuItem newTestProjectMenuItem = new MenuItem("New Test Project");
        //newTestProjectMenuItem.setOnAction(newTestProjectCommand);
        newTestProjectMenuItem.setOnAction(action -> {
            if (newTestProjectCommand == null) {
                System.out.println("command is null");
            } else {
                newTestProjectCommand.handle(action);
            }
        });
        fileMenu.getItems().addAll(newTestProjectMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }
}

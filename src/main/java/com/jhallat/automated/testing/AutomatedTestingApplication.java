package com.jhallat.automated.testing;

import com.jhallat.automated.testing.command.NewTestProjectCommand;
import com.jhallat.automated.testing.controller.ProjectExplorerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class AutomatedTestingApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Autowired
    private NewTestProjectCommand newTestProjectCommand;

    @Autowired
    private ProjectExplorerController projectExplorerController;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(AutomatedTestingApplication.class);
        newTestProjectCommand = springContext.getBean(NewTestProjectCommand.class);
        projectExplorerController = springContext.getBean(ProjectExplorerController.class);
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

        SplitPane projectPane = new SplitPane(createProjectExplorerPane(), new BorderPane());
        mainForm.setCenter(projectPane);

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

    private Pane createProjectExplorerPane() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui-components/project-explorer.fxml"));
        loader.setController(projectExplorerController);
        try {
            Pane formPane = loader.load();
            return formPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HBox();
    }
}

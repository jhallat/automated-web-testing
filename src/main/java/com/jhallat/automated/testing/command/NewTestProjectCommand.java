package com.jhallat.automated.testing.command;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class NewTestProjectCommand implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {

        Stage stage = new Stage();
        BorderPane formPane = new BorderPane();

        Label projectFolderLabel = new Label("Project Folder");
        TextField projectFolderTextField = new TextField();
        GridPane inputFrame = new GridPane();
        inputFrame.add(projectFolderLabel, 0, 0);
        inputFrame.add(projectFolderTextField, 1, 0);
        formPane.setCenter(inputFrame);

        stage.setScene(new Scene(formPane, 300, 100));
        stage.show();
    }
}

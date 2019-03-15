package com.jhallat.automated.testing.command;

import com.jhallat.automated.testing.controller.NewTestProjectDialogController;
import com.jhallat.automated.testing.domain.TestProject;
import com.jhallat.automated.testing.listener.NewTestProjectListener;
import com.jhallat.automated.testing.service.TestProjectService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewTestProjectCommand implements EventHandler<ActionEvent>, NewTestProjectListener {

    @Autowired
    private TestProjectService testProjectService;

    private Stage stage;

    @Override
    public void handle(ActionEvent actionEvent) {

        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui-components/new-test-project-dialog.fxml"));
            Pane formPane = loader.load();
            NewTestProjectDialogController controller = loader.getController();
            controller.addListener(this);
            controller.setStage(stage);

            stage.setTitle("Add New Test Project");
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(formPane, 350, 200));
            stage.show();
        } catch (IOException exception) {
            //TODO Replace with logger
            exception.printStackTrace();
        }

    }

    @Override
    public void onCancel() {
       if (stage != null) {
           stage.close();
       }
    }

    @Override
    public void onCreate(TestProject testProject) {
        testProjectService.addTestProject(testProject);
        if (stage != null) {
            stage.close();
        }
    }
}

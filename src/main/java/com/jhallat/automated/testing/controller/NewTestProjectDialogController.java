package com.jhallat.automated.testing.controller;

import com.jhallat.automated.testing.domain.TestProject;
import com.jhallat.automated.testing.listener.NewTestProjectListener;
import com.jhallat.automated.testing.service.TestProjectService;
import com.jhallat.automated.testing.validation.FXValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewTestProjectDialogController implements Initializable {

    public TextField txtProjectName;
    public TextField txtProjectFolder;
    public TextField txtHomeURL;
    public Label lblMessage;
    public Button btnCreate;

    private Stage parent;
    private TestProjectService testProjectService;

    private List<NewTestProjectListener> listeners = new ArrayList<>();
    private FXValidator validator = new FXValidator();

    public void addListener(NewTestProjectListener listener) {
        listeners.add(listener);
    }
    public void setStage(Stage parent) {
        this.parent = parent;
    }

    public void setTestProjectService(TestProjectService testProjectService) {
        this.testProjectService = testProjectService;
    }

    @FXML
    public void cancel() {
        listeners.forEach(NewTestProjectListener::onCancel);
    }

    @FXML
    public void create() {
        TestProject testProject = new TestProject(txtProjectName.getText(),
                new File(txtProjectFolder.getText()),
                txtHomeURL.getText());
        listeners.forEach(item -> item.onCreate(testProject));
    }

    @FXML
    private void validate() {
        btnCreate.setDisable(!validator.validate());
    }

    @FXML
    private void selectFolder() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(parent);
        if (directory != null) {
            txtProjectFolder.setText(directory.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (txtProjectName == null) {
            System.out.println("text field is null");
        }
        validator.required(txtProjectName);
        validator.required(txtProjectFolder);
        validator.required(txtHomeURL);
    }
}

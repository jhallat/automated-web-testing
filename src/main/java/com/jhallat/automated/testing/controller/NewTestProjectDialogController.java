package com.jhallat.automated.testing.controller;

import com.jhallat.automated.testing.listener.NewTestProjectListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewTestProjectDialogController {

    public TextField txtProjectName;
    public TextField txtProjectFolder;
    public TextField txtHomeURL;
    public Label lblMessage;

    private List<NewTestProjectListener> listeners = new ArrayList<>();

    public void addListener(NewTestProjectListener listener) {
        listeners.add(listener);
    }

    @FXML
    public void cancel() {
        listeners.forEach(NewTestProjectListener::onCancel);
    }

    @FXML
    public void create() {
        validateFields();
        System.out.println("Created " + txtProjectName.getText() );
    }

    private boolean validateFields() {
        //TODO Display multiple validation messages and highlight invalid fields.
        //TODO Create a validation framework???
        if (txtProjectName.getText() == null || txtProjectName.getText().isEmpty()) {
            lblMessage.setText("Project name is required");
            return false;
        }
        if (txtProjectName.getText() == null || txtProjectName.getText().isEmpty()) {
            lblMessage.setText("Project name is required");
            return false;
        }

        return true;
    }
}

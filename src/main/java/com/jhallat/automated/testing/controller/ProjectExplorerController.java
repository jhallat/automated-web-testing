package com.jhallat.automated.testing.controller;

import com.jhallat.automated.testing.domain.TestProject;
import com.jhallat.automated.testing.service.TestProjectService;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProjectExplorerController implements Initializable {

    private final class TestProjectItem {

    }

    @Autowired
    private TestProjectService testProjectService;

    public TreeItem rootItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testProjectService.subscribe(this::addNewTestProject);
    }

    private void addNewTestProject(TestProject testProject) {
        TreeItem projectRoot = new TreeItem(testProject);
        projectRoot.getChildren().addAll( new TreeItem("Data Sheets"),
                new TreeItem("Web Elements"),
                new TreeItem("Scripts"));
        rootItem.getChildren().add(projectRoot);
        if (!rootItem.isExpanded()) {
            rootItem.setExpanded(true);
        }
        projectRoot.setExpanded(true);
    }
}

package com.jhallat.automated.testing.domain;

import java.io.File;

public class TestProject {

    private String description;
    private File folderLocation;
    private String homeURL;

    public TestProject(String description, File folderLocation, String homeURL) {
        this.description = description;
        this.folderLocation = folderLocation;
        this.homeURL = homeURL;
    }

    public String getDescription() {
        return description;
    }

    public File getFolderLocation() {
        return folderLocation;
    }

    public String getHomeURL() {
        return homeURL;
    }

}

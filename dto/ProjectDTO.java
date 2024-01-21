package com.example.testcase.dto;

public class ProjectDTO {
    private String projectName;
    private String description;


    public ProjectDTO() {
    }


    public ProjectDTO(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

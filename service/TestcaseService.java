package com.example.testcase.service;

import com.example.testcase.entity.LoginEntity;
import com.example.testcase.entity.ProjectEntity;
import com.example.testcase.entity.TestcaseEntity;
import com.example.testcase.enums.TestCaseStatus;
import com.example.testcase.repository.LoginRepository;
import com.example.testcase.repository.ProjectRepository;
import com.example.testcase.repository.TestcaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@Service
public class TestcaseService {
    @Autowired
    private TestcaseRepository testcaseRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<TestcaseEntity> getAllTestcases() {
        return testcaseRepository.findAll();
    }
    public List<LoginEntity> getAllLogins() {
        return loginRepository.findAll();
    }

    public LoginEntity createLogin(LoginEntity loginEntity) {

        return loginRepository.save(loginEntity);
    }



    public ProjectEntity createProject(ProjectEntity projectEntity) {

        return projectRepository.save(projectEntity);
    }


@Transactional
public String performAddition(Long projectId, TestcaseEntity testcaseEntity) {
    ProjectEntity projectEntity = projectRepository.findById(projectId)
            .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
    testcaseEntity.setProjectEntity(projectEntity);
    testcaseRepository.save(testcaseEntity);

    return "Data saved successfully";
}

    @Transactional
    public String performSubtraction(Long projectId, TestcaseEntity testcaseEntity) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        testcaseEntity.setProjectEntity(projectEntity);
        testcaseRepository.save(testcaseEntity);
        return "Data saved successfully";
    }

    @Transactional
    public String performMultiplication(Long projectId, TestcaseEntity testcaseEntity) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        testcaseEntity.setProjectEntity(projectEntity);
        testcaseRepository.save(testcaseEntity);
        return "Data saved successfully";
    }
    public List<ProjectEntity> getProjectById(Long id) {

        return projectRepository.findById(id)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    public void deleteById(Long id) {
         testcaseRepository.deleteById(id);
    }
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

}



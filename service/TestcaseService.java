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

import java.util.Collections;
import java.util.List;

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
        int result = performOperation(testcaseEntity.getInput1(), testcaseEntity.getInput2(), Operation.ADDITION);


        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        testcaseEntity.setProjectEntity(projectEntity);


        updateTestcaseResult(testcaseEntity, result);
        testcaseRepository.save(testcaseEntity);

        return String.valueOf(result);
    }


    @Transactional
    public String performSubtraction(Long projectId, TestcaseEntity testcaseEntity) {
        int result = performOperation(testcaseEntity.getInput1(), testcaseEntity.getInput2(), Operation.SUBTRACTION);
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        testcaseEntity.setProjectEntity(projectEntity);
        updateTestcaseResult(testcaseEntity, result);
        testcaseRepository.save(testcaseEntity);
        return String.valueOf(result);
    }

    @Transactional
    public String performMultiplication(Long projectId, TestcaseEntity testcaseEntity) {
        int result = performOperation(testcaseEntity.getInput1(), testcaseEntity.getInput2(), Operation.MULTIPLICATION);
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        testcaseEntity.setProjectEntity(projectEntity);
        updateTestcaseResult(testcaseEntity, result);
        testcaseRepository.save(testcaseEntity);
        return String.valueOf(result);
    }

    private int performOperation(String input1, String input2, Operation operation) {
        int operand1 = Integer.parseInt(input1);
        int operand2 = Integer.parseInt(input2);

        switch (operation) {
            case ADDITION:
                return operand1 + operand2;
            case SUBTRACTION:
                return operand1 - operand2;
            case MULTIPLICATION:
                return operand1 * operand2;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

    private void updateTestcaseResult(TestcaseEntity testcaseEntity, int result) {
        testcaseRepository.save(testcaseEntity);
        testcaseEntity.setResult(String.valueOf(result));
        testcaseEntity.setStatus(TestCaseStatus.SUCCESS);
    }
    private enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION
    }
    public List<ProjectEntity> getProjectById(Long id) {

        return projectRepository.findById(id)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    public void deleteById(Long id) {
         testcaseRepository.deleteById(id);
    }
}



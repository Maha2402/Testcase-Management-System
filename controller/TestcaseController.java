package com.example.testcase.controller;

import com.example.testcase.dto.ProjectDTO;

import com.example.testcase.entity.LoginEntity;
import com.example.testcase.entity.ProjectEntity;
import com.example.testcase.entity.TestcaseEntity;
import com.example.testcase.repository.ProjectRepository;
import com.example.testcase.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestcaseController {
    @Autowired
    private TestcaseService consolidatedService;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/fetch-testcases")
    public List<TestcaseEntity> getAllTestcases() {
        return consolidatedService.getAllTestcases();
    }

    @GetMapping("/fetch-logins")
    public List<LoginEntity> getAllLogins() {
        return consolidatedService.getAllLogins();
    }

    @PostMapping("/post-logins")
    public LoginEntity createLogin(@RequestBody LoginEntity loginEntity) {
        return consolidatedService.createLogin(loginEntity);
    }
    @PostMapping("/post-projects")
    public ProjectEntity createProject(@RequestBody ProjectEntity projectEntity) {
        return consolidatedService.createProject(projectEntity);
    }

    @PostMapping("/post-addition-testcases")
    public String performAddition(@RequestParam Long projectId, @RequestBody TestcaseEntity testcaseEntity) {
        return consolidatedService.performAddition(projectId, testcaseEntity);
    }

    @PostMapping("/post-subtraction-testcases")
    public String performSubtraction(@RequestParam Long projectId, @RequestBody TestcaseEntity testcaseEntity) {
        return consolidatedService.performSubtraction(projectId, testcaseEntity);
    }

    @PostMapping("/post-multiplication-testcases")
    public String performMultiplication(@RequestParam Long projectId, @RequestBody TestcaseEntity testcaseEntity) {
        return consolidatedService.performMultiplication(projectId, testcaseEntity);
    }



    @GetMapping("/fetch-project-by-id/{id}")
    public List<ProjectDTO> getProjectById(@PathVariable Long id) {
        return consolidatedService.getProjectById(id)
                .stream()
                .map(project -> new ProjectDTO(project.getProjectTitle(), project.getDescription()))
                .collect(Collectors.toList());
    }
    @DeleteMapping("delete-testcase/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
         consolidatedService.deleteById(id);
         return ResponseEntity.ok("deleted");
    }





}


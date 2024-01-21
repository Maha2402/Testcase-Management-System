package com.example.testcase.repository;

import com.example.testcase.entity.TestcaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestcaseRepository extends JpaRepository<TestcaseEntity,Long> {



}

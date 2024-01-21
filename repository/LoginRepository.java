package com.example.testcase.repository;

import com.example.testcase.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
}

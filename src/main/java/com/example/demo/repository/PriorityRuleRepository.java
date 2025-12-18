package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PriorityRule;

public interface PriorityRuleRepository extends JpaRepository<PriorityRule, Long> {
    PriorityRule findByCategory(String category);
}

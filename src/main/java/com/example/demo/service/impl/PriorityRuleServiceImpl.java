package com.example.demo.service.impl;

import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    public int calculatePriority(String category) {
        var rule = repo.findByCategory(category);
        return rule != null ? rule.getBaseScore() : 0;
    }

    public Object getAllRules() {
        return repo.findAll();
    }
}

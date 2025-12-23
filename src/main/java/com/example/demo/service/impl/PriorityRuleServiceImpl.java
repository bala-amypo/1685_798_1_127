package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import java.util.List;

public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public int computePriorityScore(Complaint c) {
        int score = 0;

        if (c.getSeverity() != null)
            score += c.getSeverity().ordinal() * 10;

        if (c.getUrgency() != null)
            score += c.getUrgency().ordinal() * 5;

        for (PriorityRule rule : repo.findByActiveTrue()) {
            score += rule.getWeight();
        }
        return Math.max(score, 0);
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}

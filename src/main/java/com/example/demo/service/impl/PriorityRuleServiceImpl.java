package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository priorityRuleRepository;

    public PriorityRuleServiceImpl(PriorityRuleRepository priorityRuleRepository) {
        this.priorityRuleRepository = priorityRuleRepository;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {

        int score = 0;

        // Severity contribution
        if (complaint.getSeverity() != null) {
            switch (complaint.getSeverity()) {
                case LOW -> score += 1;
                case MEDIUM -> score += 3;
                case HIGH -> score += 5;
                case CRITICAL -> score += 7;
            }
        }

        // Urgency contribution
        if (complaint.getUrgency() != null) {
            switch (complaint.getUrgency()) {
                case LOW -> score += 1;
                case MEDIUM -> score += 3;
                case HIGH -> score += 5;
                case IMMEDIATE -> score += 7;
            }
        }

        // Priority rules contribution
        List<PriorityRule> rules = priorityRuleRepository.findByActiveTrue();
        if (rules != null) {
            for (PriorityRule rule : rules) {
                score += rule.getWeight();
            }
        }

        return Math.max(score, 0);
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}

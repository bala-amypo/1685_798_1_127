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
        List<PriorityRule> activeRules = getActiveRules();
        int baseScore = 0;
        
        // Base score from severity
        if (complaint.getSeverity() != null) {
            switch (complaint.getSeverity()) {
                case LOW -> baseScore += 1;
                case MEDIUM -> baseScore += 3;
                case HIGH -> baseScore += 5;
                case CRITICAL -> baseScore += 8;
            }
        }
        
        // Base score from urgency
        if (complaint.getUrgency() != null) {
            switch (complaint.getUrgency()) {
                case LOW -> baseScore += 1;
                case MEDIUM -> baseScore += 2;
                case HIGH -> baseScore += 4;
                case IMMEDIATE -> baseScore += 6;
            }
        }
        
        // Apply rule weights
        int ruleScore = activeRules.stream()
                .mapToInt(PriorityRule::getWeight)
                .sum();
        
        return Math.max(0, baseScore + ruleScore);
    }
    
    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}
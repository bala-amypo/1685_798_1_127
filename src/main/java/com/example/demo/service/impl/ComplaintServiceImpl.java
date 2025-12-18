package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService priorityRuleService;
    private final UserService userService;

    public ComplaintServiceImpl(
            ComplaintRepository repo,
            PriorityRuleService priorityRuleService,
            UserService userService) {
        this.repo = repo;
        this.priorityRuleService = priorityRuleService;
        this.userService = userService;
    }

    public Complaint submitComplaint(ComplaintRequest req) {
        User user = userService.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Complaint c = new Complaint();
        c.setTitle(req.getTitle());
        c.setDescription(req.getDescription());
        c.setCategory(req.getCategory());
        c.setPriorityScore(priorityRuleService.calculatePriority(req.getCategory()));
        c.setUser(user);

        return repo.save(c);
    }

    public List<Complaint> getUserComplaints(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }

    public Complaint updateComplaintStatus(Long id, String status) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }
}

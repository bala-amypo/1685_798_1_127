package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ REQUIRED
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService priorityRuleService;

    public ComplaintServiceImpl(ComplaintRepository repo,
                                PriorityRuleService priorityRuleService) {
        this.repo = repo;
        this.priorityRuleService = priorityRuleService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest r, User user) {
        Complaint c = new Complaint();
        c.setCustomer(user);
        c.setTitle(r.title);
        c.setDescription(r.description);
        c.setCategory(r.category);
        c.setChannel(r.channel);
        c.setSeverity(r.severity);
        c.setUrgency(r.urgency);

        c.setPriorityScore(priorityRuleService.computePriorityScore(c));
        return repo.save(c);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User user) {
        return repo.findByCustomer(user);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}

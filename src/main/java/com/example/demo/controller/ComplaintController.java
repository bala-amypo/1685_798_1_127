package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public Complaint submit(@RequestBody ComplaintRequest req) {
        return service.submitComplaint(req);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> user(@PathVariable Long userId) {
        return service.getUserComplaints(userId);
    }

    @GetMapping("/prioritized")
    public List<Complaint> prioritized() {
        return service.getPrioritizedComplaints();
    }
}

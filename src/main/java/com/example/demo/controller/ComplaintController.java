package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    public ComplaintController(ComplaintService complaintService,
                               UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Complaint> submitComplaint(
            @RequestBody ComplaintRequest request,
            @RequestParam String email) {

        User customer = userService.findByEmail(email);
        Complaint complaint = complaintService.submitComplaint(request, customer);
        return ResponseEntity.ok(complaint);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Complaint>> getUserComplaints(
            @PathVariable Long userId) {

        User user = new User();
        user.setId(userId);
        List<Complaint> complaints = complaintService.getComplaintsForUser(user);
        return ResponseEntity.ok(complaints);
    }

    @GetMapping("/prioritized")
    public ResponseEntity<List<Complaint>> getPrioritizedComplaints() {
        return ResponseEntity.ok(
                complaintService.getPrioritizedComplaints()
        );
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status) {

        complaintService.updateStatus(id, status);
        return ResponseEntity.ok("Status updated");
    }
}
